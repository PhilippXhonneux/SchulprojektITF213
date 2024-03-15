package de.bwv_ac.data;

public class PPerEvent extends Datastructure{

    /**
     * ID of the event
     */
    private int ID;

    /**
     * Name of the even
     */
    private String event;

    /**
     * Count of all participans of this event
     */
    private int count;

    /**
     * Get the id of the event
     * @return The id
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the id of the event
     * @param ID The id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Get the name of the event
     * @return The name
     */
    public String getEvent() {
        return event;
    }

    /**
     * Set the name of the event
     * @param event The name
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Get the count of patricians in this event
     * @return The count
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the count of participants in the event
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
