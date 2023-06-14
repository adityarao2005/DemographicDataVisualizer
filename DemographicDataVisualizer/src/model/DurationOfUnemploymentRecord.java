package model;

import java.util.Objects;

public class DurationOfUnemploymentRecord extends ImmigrationRecord {
	// Fields
	private String duration;

	// Potential durations fo unemployment
	public static final String[] DURATIONS = { "Total unemployed", "1 - 4 weeks", "5 - 13 weeks", "14 - 25 weeks",
			"26 weeks", "27 weeks or more", "27 - 51 weeks", "52 weeks", "53 weeks or more", "Duration unknown",
			"Average weeks unemployed (no top-code)", "Average weeks unemployed (top-code = 99 weeks)" };

	public static final String[] LOCATIONS = { "Canada", "Ontario" };

	// Potential Age Groups
	public static final String[] AGE_GROUPS = { "15 years and over", "15-24 years", "15-29 years", "25 years and over",
			"25-44 years", "25-54 years", "25-64 years", "45 years and over", "55 years and over", "55-64 years",
			"65 years and over" };

	// Constructors
	public DurationOfUnemploymentRecord() {
		super();
	}

	public DurationOfUnemploymentRecord(int year, String location, String ageGroup, int bothGenders, int males,
			int females, String immigrationStatus, String duration) {
		super(year, location, ageGroup, bothGenders, males, females, immigrationStatus);
		this.duration = duration;
	}

	// Getters and Setters
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	// Hashcode and equals methods to check for equality
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(duration);
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
		DurationOfUnemploymentRecord other = (DurationOfUnemploymentRecord) obj;
		return Objects.equals(duration, other.duration);
	}

}
