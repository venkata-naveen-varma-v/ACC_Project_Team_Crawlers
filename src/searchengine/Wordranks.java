package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;
import searchengine.PageSorting;
import searchengine.TopListing;



public class Wordranks {
	Map<String, Integer> ranks= new HashMap<String, Integer>();
	public Map<String,Integer> getPageRank(String searchKey) {
		searchKey=searchKey.toLowerCase();
		File directory=new File("src/resources/textFiles");
		File filesList[] = directory.listFiles();
		for(File file : filesList) {
			String line = "";
			int c=1;
			FileReader fr = null;
			try {
				fr = new FileReader(file.getPath());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}  
			
			Scanner scanFile=new Scanner(fr);
			
			while(scanFile.hasNext()) {	
				line=line+" "+ scanFile.nextLine();
			}
			line=line.toLowerCase();
			line=line.trim();
			int startIndex=0;
			
			for(int index=0;index<=line.length();index=index+startIndex+searchKey.length()) {
				BoyerMoore bm= new BoyerMoore(searchKey);
				startIndex=bm.search(line.substring(index));
			
				if(startIndex+index<line.length())
				{
					ranks.put(file.getName(), c);
					c= ranks.get(file.getName())+1;
				}
			}
			scanFile.close();
		}
		
		
		for (String name: ranks.keySet()) {
		    String key = name.toString();
		    String value = ranks.get(name).toString();	
		    
		}
		
//		 System.out.println("\n Values before sorting : ");
//	     
//		 display(ranks);
		 
		 
		List<Entry<String, Integer>> listTop5=null;
	
		if(ranks.size()!=0)
		{
		listTop5=PageSorting.SortMap(ranks);
		Map<String, Integer> Top5= new HashMap<String, Integer>();
		
		
		for(int g=0;g<5;g++)
		 {
		
			 Top5.put(listTop5.get(g).getKey(), listTop5.get(g).getValue());
			 
		 }

		
		TopListing.Listing(Top5,searchKey);
		
//		 Map<String, Integer> Top5= new HashMap<String, Integer>();
		}
		 
		else if(ranks.size()==0) {
			System.out.println("Result Not found");;
		}
		return ranks;
	}
		 
	  public static void display(Map<String, Integer> hashmap) {
	       for( Map.Entry entry : hashmap.entrySet() ) {
	           System.out.println( "Key : " + entry.getKey()+ " \t  value :  " + entry.getValue() );
	       }
	   }
	  
	  
	
	
//	public static void main(String args[]) throws java.lang.Exception
//	{
//		Wordranks wc=new Wordranks();
//		
//		//System.in is a standard input stream  
//		Scanner sc= new Scanner(System.in); 
//		System.out.print("Enter a city name: ");  
//		String city= sc.nextLine();     
//		
//		wc.getPageRank(city);
//	}

}