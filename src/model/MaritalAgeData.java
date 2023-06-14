package model;

public class MaritalAgeData {

	// fields
	private int year;
	private String age1;
	private String age2;
	private int numOfMarriages;

	// constructor
	public MaritalAgeData(int year, String age1, String age2, int numOfMarriages) {
		super();
		this.year = year;
		this.age1 = age1;
		this.age2 = age2;
		this.numOfMarriages = numOfMarriages;
	}

	// getters and setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAge1() {
		return age1;
	}

	public void setAge1(String age1) {
		this.age1 = age1;
	}

	public String getAge2() {
		return age2;
	}

	public void setAge2(String age2) {
		this.age2 = age2;
	}

	public int getNumOfMarriages() {
		return numOfMarriages;
	}

	public void setNumOfMarriages(int numOfMarriages) {
		this.numOfMarriages = numOfMarriages;
	}

	@Override
	public String toString() {
		return "MaritalAgeData [year=" + year + ", age1=" + age1 + ", age2=" + age2 + ", numOfMarriages="
				+ numOfMarriages + "]";
	}

}