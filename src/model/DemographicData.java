// Name: Jack & Ismit

package model;

public class DemographicData {

	// fields
	private int year;
	private String month;
	private int total;
	private String city;

	// constructor
	public DemographicData(int year, String month, int total, String city) {
		super();
		this.year = year;
		this.month = month;
		this.total = total;
		this.city = city;
	}

	// getters and setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// in the controller, TimeSeriesDataItem only can contain month in int. this
	// methods is for changing the String in file into int value
	public int getMonthInNumber() {

		switch (month.toUpperCase()) {
		case "JAN":
			return 1;
		case "FEB":
			return 2;
		case "MAR":
			return 3;
		case "APR":
			return 4;
		case "MAY":
			return 5;
		case "JUN":
			return 6;
		case "JUL":
			return 7;
		case "AUG":
			return 8;
		case "SEP":
			return 9;
		case "OCT":
			return 10;
		case "NOV":
			return 11;
		case "DEC":
			return 12;
		default:
			return 0;
		}
	}

	@Override
	public String toString() {
		return "DemographicData [year=" + year + ", month=" + month + ", total=" + total + ", city=" + city + "]";
	}

}