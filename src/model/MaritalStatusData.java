package model;

public class MaritalStatusData {

	// fields
	private int year;
	private String status1;
	private String status2;
	private int numOfMarriages;

	// constructor
	public MaritalStatusData(int year, String status1, String status2, int numOfMarriages) {
		super();
		this.year = year;
		this.status1 = status1;
		this.status2 = status2;
		this.numOfMarriages = numOfMarriages;
	}

	// getters and setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public int getNumOfMarriages() {
		return numOfMarriages;
	}

	public void setNumOfMarriages(int numOfMarriages) {
		this.numOfMarriages = numOfMarriages;
	}

}