package searchengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class crawler {
	static HashSet<String> uniqueLinks = new HashSet<String>(); 
	
	private static String pattern = ".*www.royallepagebinder.com/residential-properties/.*";
	
//	Get links from the url
	public static void crawl(String url, int page_count)
	{
		System.out.println("Fetching all the URL's from website: "+url);
		try {
			String currentUrl;
			for(int i=0;i<page_count;i++) {
				currentUrl = url + "page-" + 24*i;
				uniqueLinks.add(currentUrl);
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
//		System.out.println("\n URL search Pattern: "+ pattern);
		crawl(url, 40);
		download_html();
	}

}
