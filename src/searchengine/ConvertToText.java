package searchengine;

import java.io.*;


import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class ConvertToText {
	
	// Fetch html page files and scrap the data from it in required format and store it in a text file.
	public static int convert_html_to_text()
	{
		System.out.println("\nFetch and convert all .html files to .txt files");
		try {
			String source_path = "src/resources/htmlFiles/";
			String dest_path = "src/resources/textFiles/";
			File dir = new File(source_path);
			
			String files_list[] = dir.list();
			String address, price, type;
			
			for(String filename: files_list) {
				System.out.println("\nIn page:" + filename + "\n");
				File currentFile = new File(source_path + filename);
				Document doc = Jsoup.parse(currentFile, "UTF-8");
				
				Elements property_listings = doc.getElementsByClass("listing_quick_info");
				
				
				for(Element property: property_listings) {
					// Fetch data
					address = property.getElementsByClass("address").text();
					price = property.getElementsByClass("price").text();
					type = property.getElementsByClass("type").text();
					
					// Format data
					address = address.replace(",", "");
					price = price.replace("$", "");
					price = price.replace(",", "");
					type = type.replace("|", "");
					
					String print_value = address+","+price+","+type;
					
//					System.out.println(print_value);
					
					String fileName = filename.replace(".html", ".txt");
					BufferedWriter out = new BufferedWriter(new FileWriter(dest_path + fileName, true));
			        out.write(print_value + "\n");
			        out.close();
				}
				System.out.println("page:" + filename + " data extracted and stored in .txt!\n");
			}
			
			System.out.println("\nAll files converted to .txt!!\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String args[]) {
		convert_html_to_text();
	}

}
