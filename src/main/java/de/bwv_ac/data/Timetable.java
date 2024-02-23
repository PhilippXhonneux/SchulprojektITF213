package de.bwv_ac.data;

import java.lang.annotation.ElementType;
import java.util.ArrayList;

/**
 * Datastructur for Stundenplan BOT3
 *
 * @author Philipp Goebel, Philipp Xhonneux
 * @version 3.0.0
 */

public class Timetable {
    /**
     * Id of the {@link Company}
     */
    private Integer ID;
    /**
     * Name of the {@link Company}
     */
    private String company;
    /**
     * {@link ArrayList} of {@link Event}'s the {@link Company} is holding
     */
    private ArrayList<Event> events = new ArrayList<>();

    /**
     * Subclass for representing Events
     *
     * @author Philipp Xhonneux
     * @version 1.0.0
     */
    @SuppressWarnings("InnerClassMayBeStatic")
    private class Event{
        /**
         * Room the {@link Event} is hold in.
         */
        public String room;
        /**
         * Timeslot the {@link Event} is in.
         * May only be the {@link String}'s "A","B",..., "E"
         */
        public String timeSlot;

        /**
         * Builds a {@link Event}
         * @param room the {@link Event} is hold in.
         * @param timeSlot the {@link Event} is in.
         */
        public Event(String room, String timeSlot)
        {
            this.room = room;
            this.timeSlot = timeSlot;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass() != this.getClass())
                return false;

            Event event = (Event) obj;

			return event.room.equals(this.room) && event.timeSlot.equals(this.timeSlot);
		}
    }


    /**
     * Gets the ID of the {@link Company}.
     * @return ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Sets the ID of the {@link Company}.
     * @param ID of the {@link Company}.
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the {@link Company}
     * @return Name of the {@link  Company}
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the name of the {@link Company}
     * @param company  the name of the {@link Company}
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Adds an Event for the {@link Company}
     * @param room of the {@link Event}
     * @param timeSlot of the {@link Event}, will be set in uppercase
     * @throws IllegalArgumentException if the timeslot is already used for the {@link Company}
     */
    public void addEvent(String room, String timeSlot) throws IllegalStateException
    {

        for (Event event : events)
        {
            if(event.timeSlot.equals(timeSlot.toUpperCase()))
                throw new IllegalStateException("Timeslot " + timeSlot.toUpperCase() + "is already in use.");
        }

        events.add(new Event(room, timeSlot.toUpperCase()));
    }

    /**
     * Removes the {@link Event}
     * @param room the {@link Event} is hold in.
     * @param timeSlot the {@link Event} is hold in.
     */
    public void removeEvent(String room, String timeSlot)
    {
        Event event = new Event(room, timeSlot);
        events.remove(event);
    }

    /**
     * Gets a {@link String}[] containing the room and timeslot of the {@link Event}
     * @param index of the {@link Event}
     * @return {@link String}[] containing the room and timeslot of the {@link Event}
     */
    public String[] getEvent(int index)
    {
        String[] event = new String[2];
        event[0] = events.get(index).room;
        event[1] = events.get(index).timeSlot;

        return event;
    }
}
