package searchengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class crawler {
	static HashSet<String> uniqueLinks = new HashSet<String>(); 
	
	private static String pattern = ".*www.royallepagebinder.com/residential-properties/.*";
	private static int max_pages = 40;
//	Get links from the url
	public static void crawl(String url, int page_count)
	{
		try {
			if(!uniqueLinks.contains(url)) {
				if(page_count>0) {
					uniqueLinks.add(url);
				}
				page_count++;
				if(page_count <= max_pages) {
					Document document= Jsoup.connect(url).get();
					Elements linkpage= document.select("a[href^=\"https://www.royallepagebinder.com/residential-properties/page\"]");
					for(Element page: linkpage)
					{
						crawl(page.attr("abs:href"), page_count);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void download_html()
	{
		System.out.println("Downloading all the links as .html from the list_of_links");
		try {
			String html, currentURL;
			String filePath = "src/resources/htmlFiles/";
			Iterator<String> itr = uniqueLinks.iterator();
			while(itr.hasNext())
			{
				currentURL = itr.next();
				Document document = Jsoup.connect(currentURL).get();
				html = document.html();
				
				String fileName = currentURL.replaceAll("[^a-zA-Z0-9_-]", "") + ".html";
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath + fileName, true));
		        out.write(currentURL + " " + html);
		        out.close();
			}
			System.out.println("All files downloaded as .html !!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[])
	{
		String url = "https://www.royallepagebinder.com/residential-properties/";
		crawl(url, 0);
		for(String link: uniqueLinks) {
			System.out.println(link);
		}
		download_html();
	}

}
