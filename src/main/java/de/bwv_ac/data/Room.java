package de.bwv_ac.data;

/**
 * The Room.
 *
 * @author Philipp Xhonneux
 * @version 1.0.0
 */
public class Room extends Datastructure{

	private String Roomname;
	private  Integer capacity;

	/**
	 * Gets roomname.
	 *
	 * @return the roomname
	 */
	public String getRoomname() {
		return Roomname;
	}

	/**
	 * Sets roomname.
	 *
	 * @param roomname the roomname
	 */
	public void setRoomname(String roomname) {
		Roomname = roomname;
	}

	/**
	 * Gets capacity.
	 *
	 * @return the capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * Sets capacity.
	 *
	 * @param capacity the capacity
	 */
	public void setCapacity(Integer capacity) {
		if(capacity < 0)
			throw new IllegalArgumentException("Capacity must be at least 0.");
		this.capacity = capacity;
	}
}
