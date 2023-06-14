package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.BirthData;
import model.DeathData;
import model.DemographicData;
import model.DurationOfUnemploymentRecord;
import model.MaritalAgeData;
import model.MaritalStatusData;
import model.PartTimeEmploymentRecord;
import model.WageLevelRecord;

// Imports the files
// and returns them in the forms of lists of the data structures
// Uses lazy loading
public class FileImportController {

	// Fields
	// Aditya's Data
	private static List<WageLevelRecord> totalWageRecords;
	private static List<DurationOfUnemploymentRecord> totalDurationRecords;
	private static List<PartTimeEmploymentRecord> totalPartTimeRecords;

	// Rhett's Data
	private static ArrayList<MaritalAgeData> totalMaritalAgeData = null;
	private static ArrayList<MaritalStatusData> totalMaritalStatusData = null;

	// Jack's Data
	private static List<DemographicData> totalBirthRecords;
	// Ismit's Data
	private static List<DemographicData> totalDeathRecords;

	// Demographic Data Cities
	private static String[] cities;

	// Aditya's File Read
	// Reads the wages by education level records
	public static List<WageLevelRecord> readWageRecords() {
		// Lazy load
		if (totalWageRecords == null) {
			// Create the list
			totalWageRecords = new ArrayList<>();

			// Create the scanner and try to read the file
			try {
				Scanner fileReader = new Scanner(new File("files/wages_by_education_level.csv")).useDelimiter(",|\r\n");

				// Skip the first line
				fileReader.nextLine();

				// Until the file is not empty
				while (fileReader.hasNext()) {

					// Get the data
					int year = fileReader.nextInt();
					String location = fileReader.next().trim();
					String typeOfWork = fileReader.next().trim();
					String wages = fileReader.next().trim();
					String education = fileReader.next().trim();
					String ageGroup = fileReader.next().trim();
					int bothGenders = (int) fileReader.nextDouble();
					int males = (int) fileReader.nextDouble();
					int females = (int) fileReader.nextDouble();

					// Add the record to the list
					totalWageRecords.add(new WageLevelRecord(year, location, ageGroup, bothGenders, males, females,
							education, wages, typeOfWork));
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return totalWageRecords;
	}

	// Read the duration of unemployment records
	public static List<DurationOfUnemploymentRecord> readDurationRecords() {
		// Lazy load
		if (totalDurationRecords == null) {
			// Create the list
			totalDurationRecords = new ArrayList<>();

			// Create the scanner and try to read the file
			try {
				Scanner fileReader = new Scanner(new File("files/duration_of_unemployment.csv")).useDelimiter(",|\r\n");

				// Skip the first line
				fileReader.nextLine();

				// Until the file is not empty
				while (fileReader.hasNext()) {

					// Get the data
					int year = fileReader.nextInt();
					String location = fileReader.next().trim();
					String immigrationStatus = fileReader.next().trim();
					String duration = fileReader.next().trim();
					String ageGroup = fileReader.next().trim();
					int bothGenders = (int) fileReader.nextDouble();
					int males = (int) fileReader.nextDouble();
					int females = (int) fileReader.nextDouble();

					// Add the record to the list
					totalDurationRecords.add(new DurationOfUnemploymentRecord(year, location, ageGroup, bothGenders,
							males, females, immigrationStatus, duration));
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return totalDurationRecords;
	}

	// Reads the part time records
	public static List<PartTimeEmploymentRecord> readPartTimeRecords() {
		// Lazy load
		if (totalPartTimeRecords == null) {
			// Create the list
			totalPartTimeRecords = new ArrayList<>();

			// Create the scanner and try to read the file
			try {
				Scanner fileReader = new Scanner(new File("files/part_time_work_by_status.csv")).useDelimiter(",|\r\n");

				// Skip the first line
				fileReader.nextLine();

				// Until the file is not empty
				while (fileReader.hasNext()) {

					// Get the data
					int year = fileReader.nextInt();
					String location = fileReader.next().trim();
					String immigrationStatus = fileReader.next().trim();
					String reason = fileReader.next().trim();
					String ageGroup = fileReader.next().trim();
					int bothGenders = (int) fileReader.nextDouble();
					int males = (int) fileReader.nextDouble();
					int females = (int) fileReader.nextDouble();

					// Add the record to the list
					totalPartTimeRecords.add(new PartTimeEmploymentRecord(year, location, ageGroup, bothGenders, males,
							females, immigrationStatus, reason));
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return totalPartTimeRecords;
	}

	// Rhett's file read
	// reads data from the files and adds it to list
	public static ArrayList<MaritalAgeData> readMaritalAgeData() {

		// checks if the list has already been created
		if (totalMaritalAgeData == null) {

			// creates the arraylist
			totalMaritalAgeData = new ArrayList<MaritalAgeData>();

			// use try and catch to import data
			try {

				// create the scanner to import the data
				Scanner fileInput = new Scanner(new File("files/marriageAges.csv"));
				fileInput.useDelimiter(",|\r\n");

				fileInput.nextLine();

				// check the file until there is no more data
				while (fileInput.hasNext()) {

					// assign data to variables to be used to initialize the object

					int year = fileInput.nextInt();
					String age1 = fileInput.next();
					String age2 = fileInput.next();
					int numOfMarriages = fileInput.nextInt();

					// add the object to the list
					totalMaritalAgeData.add(new MaritalAgeData(year, age1, age2, numOfMarriages));

				}

				// if the file could not be found
			} catch (FileNotFoundException e) {

				System.out.println("File not found");

			}
		}

		return totalMaritalAgeData;

	}

	// reads data from the files and adds it to list
	public static ArrayList<MaritalStatusData> readMaritalStatusData() {

		// checks if the list has already been created
		if (totalMaritalStatusData == null) {

			// creates the arraylist
			totalMaritalStatusData = new ArrayList<MaritalStatusData>();

			// use try and catch to import data
			try {

				// create the scanner to import the data
				Scanner fileInput = new Scanner(new File("files/marriageStatus.csv"));
				fileInput.useDelimiter(",|\r\n");

				fileInput.nextLine();

				// check the file until there is no more data
				while (fileInput.hasNext()) {

					// assign data to variables to be used to initialize the object
					int year = fileInput.nextInt();
					String status1 = fileInput.next();
					String status2 = fileInput.next();
					int numOfMarriages = fileInput.nextInt();

					// add the object to the list
					totalMaritalStatusData.add(new MaritalStatusData(year, status1, status2, numOfMarriages));

				}

				// if the file could not be found
			} catch (FileNotFoundException e) {

				System.out.println("File not found");

			}
		}

		return totalMaritalStatusData;

	}

	// Jack & Ismits file read
	// Loading methods
	public static List<DemographicData> readDemographicRecords(boolean birth) {

		// Lazy load
		List<DemographicData> list = (birth ? totalBirthRecords : totalDeathRecords);

		if (list == null) {
			// Create the list
			list = new ArrayList<>();

			// Create the scanner and try to read the file
			try {
				Scanner fileReader = new Scanner(new File(birth ? "files/BirthData.csv" : "files/DeathData.csv"))
						.useDelimiter(",|\r\n");

				// Skip the first line
				fileReader.nextLine();

				// Until the file is not empty
				while (fileReader.hasNext()) {

					// Get the data
					DemographicData data = null;

					int year = fileReader.nextInt();
					String month = fileReader.next().trim();
					String location = fileReader.next().trim();
					int total = fileReader.nextInt();

					// Store it in birth data or death data
					if (birth)
						data = new BirthData(year, month, total, location);
					else
						data = new DeathData(year, month, total, location);

					// Add the record to the list
					list.add(data);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// Set the value if cached
			if (birth)
				totalBirthRecords = list;
			else
				totalDeathRecords = list;
		}

		return list;
	}

	// Cities reader
	public static String[] getCities() {
		if (cities == null) {

			List<String> cityList = new ArrayList<>();

			// Create the scanner and try to read the file
			try {
				Scanner fileReader = new Scanner(new File("files/cities.txt"));

				// Skip the first line
				fileReader.nextLine();

				while (fileReader.hasNextLine()) {
					cityList.add(fileReader.nextLine());
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Collections.sort(cityList);

			cities = cityList.toArray(String[]::new);

		}

		return cities;
	}
}
