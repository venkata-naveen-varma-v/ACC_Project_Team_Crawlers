package searchengine;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class PageSorting {

	
public static List<Entry<String, Integer>> SortMap(Map<String, Integer> webpages){
		
		
		List<Entry<String, Integer>> list = null ;
		
		 if(!webpages.isEmpty())
		 {
	       
	     System.out.println("\nPages after sorting based on no. of occurences : ");
	       
	     Stream<Map.Entry<String,Integer> > sorted= webpages.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
	       
//		 sorted.forEach(System.out::println);
		 
		 System.out.println("Top 5 pages with the best results are : -");
		 
	     list = sorted.toList();
	
		 
		 for(int k=0;k<5;k++)
		 {
			 System.out.println("Page Name : "+list.get(k).getKey()+"\nNo. of Occurences : "+list.get(k).getValue());
//			 Top5.put(list.get(k).getKey(), list.get(k).getValue());
			 
		 }
		 
		 
	       
		 }
		
		
		return list;
	}
}
