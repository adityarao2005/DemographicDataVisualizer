package model;

import controller.FileImportController;

// Name: Jack
public class BirthData extends DemographicData {

	public BirthData(int year, String month, int total, String city) {
		super(year, month, total, city);
	}

	
	public static final String[] LOCATIONS = FileImportController.getCities();
}