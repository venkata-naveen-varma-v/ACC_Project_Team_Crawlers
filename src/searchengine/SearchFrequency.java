package searchengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchFrequency {
	String filePath = "src/resources/searchHistory/searchHistory.txt";

	public void updateSearchHistory(String word) {

		List<String> list = new ArrayList<>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
			String line = reader.readLine();

			while (line != null) {
				String trimmedLine = line.trim();
				list.add(trimmedLine);
				// read next line
				line = reader.readLine();
			}
			reader.close();
			System.out.println(list);

			if (list.contains(word.toLowerCase())) {
				clearHistory();
				list.forEach(item -> {
					try {
						if(!item.equals(word.toLowerCase())) {
						writer.write(item + "\n");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				writer.write(word.toLowerCase()+"\n");
			} else {
				writer.write(word.toLowerCase() + "\n");
			}

			writer.close();
			System.out.println("Successfully added " + word + " to the search history");

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void display() {
		List<String> list = new ArrayList<>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(filePath));

			String line = reader.readLine();
			while (line != null) {
				String trimmedLine = line.trim();
				list.add(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
			Collections.reverse(list);
			System.out.println(list);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void clearHistory() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write("");
			writer.flush();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws java.lang.Exception {
		SearchFrequency s = new SearchFrequency();
		s.updateSearchHistory("hello2");
//		s.clearHistory();
//		s.display();
	}
}