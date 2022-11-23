package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		    System.out.println(key + " " + value);
		}
		if(ranks.size()==0) {
			System.out.println("no value");;
		}
		return ranks;
	}
	
	
	public static void main(String args[])
	{
		Wordranks wc=new Wordranks();
		wc.getPageRank("TORONTO");
	}

}
