package de.bwv_ac.data;

/**
 * Datastructur for companies
 *
 * @author Philipp Xhonneux
 * @version 1.0.0
 */
public class Company extends Datastructure {

	/**
	 * Identification number of the company
	 */
	private int ID;
	/**
	 *  Formal name of the company
	 */
	private String name;
	/**
	 *  Specialty of the company
	 */
	private String specialty;
	/**
	 * Max number of students that can visit one event
	 */
	private int maxParticipants;
	/**
	 * Max number of events that can handle from the company.
	 */
	private int maxEvents;

	/**
	 * The earliest time for an event
	 */
	private String time;

	/**
	 * Gets the Identification number of the company.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets id.
	 *
	 * @param ID the id
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setID(int ID) throws IllegalArgumentException {
		if(ID <= 0)
			throw new IllegalArgumentException("Identification number must be greater than 0.");
		this.ID = ID;
	}

	/**
	 * Gets the formal name of the company.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets specialty.
	 *
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * Sets specialty.
	 *
	 * @param specialty the specialty
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * Gets the max number of students that can visit one event.
	 *
	 * @return the max
	 */
	public int getMaxParticipants() {
		return maxParticipants;
	}

	/**
	 * Sets the max number of students that can visit one event.
	 *
	 * @param maxParticipants the max
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setMaxParticipants(int maxParticipants) throws IllegalArgumentException {
		if(maxParticipants < 1)
			throw new IllegalArgumentException("The max number of studens must be atleast 1.");

		this.maxParticipants = maxParticipants;
	}

	/**
	 * Gets the min number of max event that can handle from the company.
	 *
	 * @return the max
	 */
	public int getMaxEvents() {
		return maxEvents;
	}

	/**
	 * Sets the max number of events that can handle from the company.
	 *
	 * @param maxEvents the max
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setMaxEvents(int maxEvents) throws IllegalArgumentException{
		if(maxEvents < 0 )
			throw new IllegalArgumentException("The min number of students can't be negative.");
		this.maxEvents = maxEvents;
	}

	/**
	 * Gets the earliest time for an event.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the earliest time for an event.
	 *
	 * @param time the time
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
