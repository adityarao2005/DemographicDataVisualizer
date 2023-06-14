package model;

import java.util.Objects;

public abstract class EmploymentRecord {
	// Fields
	private int year;
	private String location;
	private String ageGroup;
	private int bothGenders;
	private int males;
	private int females;

	// Constructors
	public EmploymentRecord() {
		super();
	}

	public EmploymentRecord(int year, String location, String ageGroup, int bothGenders, int males, int females) {
		super();
		this.year = year;
		this.location = location;
		this.ageGroup = ageGroup;
		this.bothGenders = bothGenders;
		this.males = males;
		this.females = females;
	}

	// Getters and Setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public int getBothGenders() {
		return bothGenders;
	}

	public void setBothGenders(int bothGenders) {
		this.bothGenders = bothGenders;
	}

	public int getMales() {
		return males;
	}

	public void setMales(int males) {
		this.males = males;
	}

	public int getFemales() {
		return females;
	}

	public void setFemales(int females) {
		this.females = females;
	}

	// Utility methods
	// Hashcode and Equals method to check for equality
	@Override
	public int hashCode() {
		return Objects.hash(ageGroup, bothGenders, females, location, males, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmploymentRecord other = (EmploymentRecord) obj;
		return Objects.equals(ageGroup, other.ageGroup) && bothGenders == other.bothGenders && females == other.females
				&& Objects.equals(location, other.location) && males == other.males && year == other.year;
	}

	@Override
	public String toString() {
		return "EmploymentRecord [year=" + year + ", location=" + location + ", ageGroup=" + ageGroup + ", bothGenders="
				+ bothGenders + ", males=" + males + ", females=" + females + "]";
	}

}
