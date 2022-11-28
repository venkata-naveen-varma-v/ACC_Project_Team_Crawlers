package searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BestDeal {
	
	static PriorityQueue<Buildings> buildingPriorityQueue = new PriorityQueue<Buildings>();

	public static void addToPriorityQueue() {
		String Text_file_path = "src/resources/textFiles/";

		File dir = new File(Text_file_path);

		String Text_files_list[] = dir.list();

		List<String> buildingDetails = new ArrayList<>();
		
		
		
		// read text files
		for (String filename : Text_files_list) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(Text_file_path + "/" + filename));

				String line = reader.readLine();

				//reading each line and adding to priority queue
				while (line != null) {
					String trimmedLine = line.trim();
					if (trimmedLine != null) {
						if (trimmedLine.split(",")[2].split("  ").length > 2) {
							buildingPriorityQueue.offer(
									new Buildings(trimmedLine.split(",")[0], Float.parseFloat(trimmedLine.split(",")[1]),
											Integer.valueOf(trimmedLine.split(",")[2].split("  ")[0]),
											Integer.valueOf(trimmedLine.split(",")[2].split("  ")[1]),
											trimmedLine.split(",")[2].split("  ")[2]));
						} else if (trimmedLine.split(",")[2].split("  ").length > 1) {
							buildingPriorityQueue.offer(
									new Buildings(trimmedLine.split(",")[0], Float.valueOf(trimmedLine.split(",")[1]),
											Integer.valueOf(trimmedLine.split(",")[2].split("  ")[0]), 0,
											trimmedLine.split(",")[2].split("  ")[1]));
						} else {
							buildingPriorityQueue.offer(new Buildings(trimmedLine.trim().split(",")[0],
									Float.valueOf(trimmedLine.trim().split(",")[1]), 0, 0,
									trimmedLine.trim().split(",")[2].split("  ")[0]));
						}
					}
					// read next line
					line = reader.readLine();
				}
				reader.close();

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param bedroomNo {number of bedrooms}
	 * @return 
	 * @return String {details of best deal}
	 */
	public static String bestDeal(int bedroomNo) {
		while (!buildingPriorityQueue.isEmpty()) {
			Buildings building = buildingPriorityQueue.poll();
			
			if(bedroomNo==building.bedrooms) {
				return "Address: "+building.address + ",\nPrice: $" + building.price + ",\nNumber of bedrooms: " + building.bedrooms + ",\nNumber of washrooms: " + building.washrooms;
			}
			
		}
		return "Not able to find a residence with given number of bedrooms";
	}

//	public static void main(String args[]) throws java.lang.Exception {
//		addToPriorityQueue();
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter number of bedrooms: ");
//		String bedroomNo = sc.nextLine();
//		System.out.println(bestDeal(Integer.valueOf(bedroomNo)));
//
//	}

}