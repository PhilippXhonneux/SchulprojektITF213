package de.bwv_ac.data;

/**
 * Datastructure for companies
 *
 * @author Philipp Xhonneux
 * @version 1.1.1
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
	 * The earliest timeslot for an event.
	 * Timeslot is represented with a {@link String}.
	 * {@link String}'s may be "A","B", ... ,"E"
	 */
	private String time;

	/**
	 * Gets the {@link Company#ID}.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets {@link Company#ID}.
	 *
	 * @param ID the Identification number
	 * @throws IllegalArgumentException if ID is less or equal to 0.
	 */
	public void setID(int ID) throws IllegalArgumentException {
		if(ID <= 0)
			throw new IllegalArgumentException("Identification number must be greater than 0.");
		this.ID = ID;
	}

	/**
	 * Gets the {@link Company#name}.
	 *
	 * @return {@link Company#name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets {@link Company#name}.
	 *
	 * @param name {@link Company#name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets {@link Company#specialty}
	 *
	 * @return {@link Company#specialty}
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * Sets {@link Company#specialty}.
	 *
	 * @param specialty {@link Company#specialty}
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * Gets {@link Company#maxParticipants}
	 *
	 * @return {@link Company#specialty}
	 */
	public int getMaxParticipants() {
		return maxParticipants;
	}

	/**
	 * Sets {@link Company#maxParticipants}
	 *
	 * @param maxParticipants {@link Company#maxParticipants}
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setMaxParticipants(int maxParticipants) throws IllegalArgumentException {
		if(maxParticipants < 1)
			throw new IllegalArgumentException("The max number of studens must be atleast 1.");

		this.maxParticipants = maxParticipants;
	}

	/**
	 * Gets {@link Company#maxEvents}
	 *
	 * @return {@link Company#maxEvents}
	 */
	public int getMaxEvents() {
		return maxEvents;
	}

	/**
	 * Sets {@link Company#maxEvents}
	 *
	 * @param maxEvents {@link Company#maxEvents}
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setMaxEvents(int maxEvents) throws IllegalArgumentException{
		if(maxEvents < 0 )
			throw new IllegalArgumentException("The min number of events can't be negative.");
		this.maxEvents = maxEvents;
	}

	/**
	 * Gets {@link Company#time}
	 *
	 * @return {@link Company#time}
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets {@link Company#time}
	 *
	 * @param time {@link Company#time}
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
