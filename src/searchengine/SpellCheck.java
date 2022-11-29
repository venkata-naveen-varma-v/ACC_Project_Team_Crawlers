package searchengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Stream;

public class SpellCheck {

	/**
	 * Read the words from a text file and create a List
	 * @return words {list of words for comparison}
	 */
	private static ArrayList<String> createDict() {
		ArrayList<String> words = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("src/resources/dict/EnglishWords.txt"));
			String line = br.readLine();
			while (line != null) {
				words.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Error while accessing Dictionary " + e);
		}
		return words;
	}

	
	/**
	 * Use EditDistance algorithm to find alternate words to the given word
	 * @param word {search word}
	 * @return word correction list
	 */
	public static List<Entry<String, Integer>> correction(String word) {
		ArrayList<String> wordCorrection = new ArrayList<String>();
		ArrayList<String> wordCorrection1 = new ArrayList<String>();
		

		List<Entry<String, Integer>> list = null;
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		
		try {
			ArrayList<String> ar = new ArrayList<String>();
			int i = 0;
			int d = 0;
			ar = createDict();
		
			if (!ar.contains(word)) {

				for (i = 0; i < ar.size(); i++) {
					//Find the difference in distance between the words
					d = Sequences.editDistance(word, ar.get(i)); 
				
					//Add as alternate word if the distance is 1
					if (d == 1) {
						wordCorrection.add(ar.get(i));
					}else if(d == 2){
						wordCorrection1.add(ar.get(i));
					}
					distance.put(ar.get(i),d);

					Stream<Map.Entry<String, Integer>> sorted = distance.entrySet().stream()
							.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
					
					list = sorted.toList();

				}
				wordCorrection.addAll(wordCorrection1);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	/**
	 * Check if a word exists in the dictionary
	 * @param letter
	 * @return
	 */
	public static boolean check(String letter) {
		ArrayList<String> ar = new ArrayList<String>();
		ar = createDict();
		if (ar.contains(letter)) {
			return true;
		} else {
			return false;
		}

	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter word");

	    String word = scanner.nextLine();

		ArrayList<String> wordCorrection = new ArrayList<String>();
		wordCorrection=correction(word);
		System.out.println(wordCorrection);
		
	}
}
