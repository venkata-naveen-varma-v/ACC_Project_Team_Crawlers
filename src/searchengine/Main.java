package searchengine;

import searchengine.Crawler;

import java.util.ArrayList;
import java.util.Scanner;

import searchengine.Convert_to_text;

public class Main {

	public static void main(String args[]) throws java.lang.Exception {
		SearchFrequency searchFrequency = new SearchFrequency();
		BestDeal b = new BestDeal();
		SpellCheck spellCheck = new SpellCheck();
		Wordranks wc = new Wordranks();

		ArrayList<String> wordCorrection = new ArrayList<String>();

		String url = "https://www.royallepagebinder.com/residential-properties/";

		System.out.println("Fetching all the Html Pages from " + url);
		Crawler.crawl(url, 0);

//		for(String link: Crawler.uniqueLinks) {
//			System.out.println(link);
//		}

		System.out.println("\nProcess completed for fetching Html pages!!");
		Convert_to_text.convert_html_to_text();

		
		b.addToPriorityQueue();

//		System.out.println("Search History\n");
//		searchFrequency.display();
//		System.out.println("\n");
//
//		System.out.print("Enter a city name: ");
//		String city = "";
//		city = sc.nextLine();
//
//		wordCorrection = spellCheck.correction(city);
//
//		while(wordCorrection.size() > 0) {
//			System.out.println("Are you sure the spelling is correct. Please select a word from the given list");
//			System.out.println(wordCorrection);
//			
//			System.out.print("Enter a city name: ");
//		    city = "";
//			city = sc.nextLine();
//
//			wordCorrection = spellCheck.correction(city);
//		}
//
//		searchFrequency.updateSearchHistory(city);
//		wc.getPageRank(city);
//
//		System.out.print("\n*******************************\n\n");
//		System.out.print("Enter number of bedrooms: ");
//		String bedroomNo = sc.nextLine();
//		System.out.println(b.bestDeal(Integer.valueOf(bedroomNo)));
//
//		System.out.println("Do you want to search again ? Press Y or N");
//		String input = sc.nextLine();
		
		Scanner sc = new Scanner(System.in);
		
		String input = "y";
		String city;
		String bedroomNo;

//		if (input.equalsIgnoreCase("y")) {
		while (input.equalsIgnoreCase("y")) {
			System.out.print("Enter a city name: ");
			city = sc.nextLine();

			wordCorrection = spellCheck.correction(city);

			while (wordCorrection.size() > 0) {
				System.out.println("Are you sure the spelling is correct. Please select a word from the given list");
				System.out.println(wordCorrection);

				System.out.print("Enter a city name: ");
				city = "";
				city = sc.nextLine();

				wordCorrection = spellCheck.correction(city);
			}


			searchFrequency.updateSearchHistory(city);
			wc.getPageRank(city);
			System.out.print("\n*******************************\n\n");
			System.out.print("Enter number of bedrooms: ");
			bedroomNo = sc.nextLine();
			System.out.println(b.bestDeal(Integer.valueOf(bedroomNo)));
			System.out.println("Do you want to search again ? Press Y or N");
			input = sc.nextLine();
		}
//		}

		System.out.println("\nThanks. The Demo ends here. Have a good day.");

	}

}
