package model;

import java.util.Objects;

public abstract class ImmigrationRecord extends EmploymentRecord {
	// Fields
	private String immigrationStatus;

	// Potential Immigration Statuses


	public static final String[] STATUSES = { 

			"Total", "Born in Canada", "Total Landed immigrants", "Very recent immigrants; 5 years or less",
			"Recent immigrants 5+ years", "Recent immigrants; 5+ to 10 years", "Established immigrants; 10+ years",
			"Non-landed immigrants"
	};

	// Constructors
	public ImmigrationRecord() {
		super();
	}

	public ImmigrationRecord(int year, String location, String ageGroup, int bothGenders, int males, int females,
			String immigrationStatus) {
		super(year, location, ageGroup, bothGenders, males, females);
		this.immigrationStatus = immigrationStatus;
	}

	// Getters and Setters
	public String getImmigrationStatus() {
		return immigrationStatus;
	}

	public void setImmigrationStatus(String immigrationStatus) {
		this.immigrationStatus = immigrationStatus;
	}

	// Hashcode and equals method to check for equality
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(immigrationStatus);
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
		ImmigrationRecord other = (ImmigrationRecord) obj;
		return Objects.equals(immigrationStatus, other.immigrationStatus);
	}

}
