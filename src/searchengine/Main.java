package searchengine;

import searchengine.Crawler;

import java.util.Scanner;

import searchengine.Convert_to_text;

public class Main {

	public static void main(String args[]) throws java.lang.Exception {
		SearchFrequency searchFrequency = new SearchFrequency();
		BestDeal b =new BestDeal();
		
		String url = "https://www.royallepagebinder.com/residential-properties/";

		System.out.println("Fectching all the Html Pages from " + url);

		Crawler.crawl(url, 0);

//		for(String link: Crawler.uniqueLinks) {
//			System.out.println(link);
//		}

		System.out.println("\nProcess completed for fetching Html pages!!");

		Convert_to_text.convert_html_to_text();

		Wordranks wc = new Wordranks();
		b.addToPriorityQueue();

		// System.in is a standard input stream
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a city name: ");
		String city = "";
		city = sc.nextLine();
		searchFrequency.updateSearchHistory(city);

		wc.getPageRank(city);

		System.out.print("\n*******************************\n\n");
		System.out.print("Enter number of bedrooms: ");
		String bedroomNo = sc.nextLine();
		System.out.println(b.bestDeal(Integer.valueOf(bedroomNo)));

		System.out.println("Do you want to search again ? Press Y or N");
		String input = sc.nextLine();

		if (input.equalsIgnoreCase("y")) {
			while (input.equalsIgnoreCase("y")) {
				System.out.print("Enter a city name: ");
				city = sc.nextLine();
				searchFrequency.updateSearchHistory(city);
				wc.getPageRank(city);
				System.out.print("\n*******************************\n\n");
				System.out.print("Enter number of bedrooms: ");
				bedroomNo = sc.nextLine();
				System.out.println(b.bestDeal(Integer.valueOf(bedroomNo)));
				System.out.println("Do you want to search again ? Press Y or N");
				input = sc.nextLine();
			}
		}
		searchFrequency.display();
		
		System.out.println("\nThanks. The Demo ends here. Have a good day.");

	}

}
