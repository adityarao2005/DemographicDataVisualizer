package model;

import java.util.Objects;

public class PartTimeEmploymentRecord extends ImmigrationRecord {
	// Fields
	private String reason;

	// Potential Reasons
	public static final String[] REASONS = { "Part-time employment; all reasons", "Own illness", "Caring for children",
			"Other personal or family responsibilities", "Going to school", "Personal preference", "Other voluntary",
			"Business conditions; did not look for full-time work in last month",
			"Could not find full-time work; did not look for full-time work in last month",
			"Business conditions; looked for full-time work in last month",
			"Could not find full-time work; looked for full-time work in last month" };

	public static final String[] LOCATIONS = { "Canada", "Ontario" };

	// Potential Age Groups
	public static final String[] AGE_GROUPS = { "15 years and over", "15-24 years", "25-54 years",
			"55 years and over" };

	// Constructors
	public PartTimeEmploymentRecord() {
		super();
	}

	public PartTimeEmploymentRecord(int year, String location, String ageGroup, int bothGenders, int males, int females,
			String immigrationStatus, String reason) {
		super(year, location, ageGroup, bothGenders, males, females, immigrationStatus);
		this.reason = reason;
	}

	// Getters and Setters
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	// Hashcode and equals method to check for equality
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(reason);
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
		PartTimeEmploymentRecord other = (PartTimeEmploymentRecord) obj;
		return Objects.equals(reason, other.reason);
	}

}
