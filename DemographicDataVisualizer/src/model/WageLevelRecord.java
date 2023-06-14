package model;

import java.util.Objects;

public class WageLevelRecord extends EmploymentRecord {
	// Fields
	private String levelOfEducation;
	private String wage;
	private String typeOfWork;

	// Potential Types of Work
	public static final String[] TYPES_OF_WORK = { "Both full- and part-time", "Full-time", "Part-time" };
	// Potential Wages
	public static final String[] WAGES = { "Total employees", "Average hourly wage rate", "Average weekly wage rate",
			"Median hourly wage rate", "Median weekly wage rate" };
	// Potential Levels of Education
	public static final String[] LEVELS_OF_EDUCATION = { "Total; all education levels", "0 - 8  years",
			"Some high school", "High school graduate", "Some post-secondary", "Post-secondary certificate or diploma",
			"Trade certificate or diploma", "Community college; CEGEP", "University certificate below bachelors degree",
			"University degree", "Bachelor's degree", "Above bachelor's degree", "PSE  (5;6;7;8;9))",
			"No PSE  (0;1;2;3;4)" };

	public static final String[] LOCATIONS = { "Canada", "Newfoundland and Labrador", "Prince Edward Island",
			"Nova Scotia", "New Brunswick", "Quebec", "Ontario", "Manitoba", "Saskatchewan", "Alberta",
			"British Columbia" };

	// Potential Age Groups
	public static final String[] AGE_GROUPS = { "15 years and over", "15-24 years", "20-34 years", "25 years and over",
			"25-34 years", "25-54 years", "25-64 years", "55 years and over", "65 years and over" };

	// Constructors
	public WageLevelRecord() {
		super();
	}

	public WageLevelRecord(int year, String location, String ageGroup, int bothGenders, int males, int females,
			String levelOfEducation, String wage, String typeOfWork) {
		super(year, location, ageGroup, bothGenders, males, females);
		this.levelOfEducation = levelOfEducation;
		this.wage = wage;
		this.typeOfWork = typeOfWork;
	}

	// Getters and Setters
	public String getLevelOfEducation() {
		return levelOfEducation;
	}

	public void setLevelOfEducation(String levelOfEducation) {
		this.levelOfEducation = levelOfEducation;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

	public String getTypeOfWork() {
		return typeOfWork;
	}

	public void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	// Hashcode and equals for comparing equality
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(levelOfEducation, typeOfWork, wage);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WageLevelRecord other = (WageLevelRecord) obj;
		return Objects.equals(levelOfEducation, other.levelOfEducation) && Objects.equals(typeOfWork, other.typeOfWork)
				&& Objects.equals(wage, other.wage);
	}

}
