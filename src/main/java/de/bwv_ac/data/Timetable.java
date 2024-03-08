package de.bwv_ac.data;

import java.util.ArrayList;

/**
 * Datastructures for Stundenplan BOT3
 *
 * @author Philipp Goebel, Philipp Xhonneux
 * @version 3.1.0
 */

@SuppressWarnings({"ALL", "JavadocDeclaration"})
public class Timetable extends Datastructure {
    /**
     * Identification number of the {@link Company}
     */
    private Integer ID;
    /**
     * Name of the {@link Company}
     */
    private String company;
    /**
     * {@link ArrayList} of {@link Event}'s the {@link Company} is holding
     */
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Event> events = new ArrayList<>();

    /**
     * Subclass for representing Events
     *
     * @author Philipp Xhonneux
     * @version 1.1.0
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
            this.timeSlot = timeSlot.toUpperCase();
        }

        /**
         * Builds a {@link Event}
         * @param room the {@link Event} is hold in.
         * @param timeSlot the {@link Event} is in.
         */
        public Event(String room, TimeSlot timeSlot)
        {
            this.room = room;
            this.timeSlot = timeSlot.label;
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
     * Enum containing all TimeSlots
     * @author Philipp Xhonneux
     * @version 1.0.0
     */
    public enum TimeSlot{

        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E");
        private final String label;
        @SuppressWarnings("UnnecessaryModifier")
        private TimeSlot(String label){this.label = label;}
    }


    /**
     * Gets the {@link Company#getID()}.
     * @return {@link Company#getID()}
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Sets the {@link Company#getID()}.
     * @param ID {@link Company#getID()}.
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Gets the {@link Company#getName()}
     * @return {@link  Company#getName()}
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the {@link  Company#getName()}
     * @param company  {@link  Company#getName()}
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Adds an Event for the {@link Company}
     * @param room {@link Event#room}
     * @param timeSlot {@link Event#timeSlot}, will be set in uppercase
     * @throws IllegalArgumentException if the timeslot is already used for the {@link Company}
     */
    public void addEvent(String room, String timeSlot) throws IllegalStateException {
        for (Event event : events) {
            if (event.timeSlot.equals(timeSlot.toUpperCase()))
                throw new IllegalStateException("Timeslot " + timeSlot.toUpperCase() + "is already in use.");
        }
     events.add(new Event(room, timeSlot.toUpperCase()));
    }

    /**
     * Adds an Event for the {@link Company}
     * @param room {@link Event#room}
     * @param timeSlot {@link Event#timeSlot}, will be set in uppercase
     * @throws IllegalArgumentException if the timeslot is already used for the {@link Company}
     */
    public void addEvent(String room, TimeSlot timeSlot) throws IllegalStateException
    {
        addEvent(room, timeSlot.label);
    }

    /**
     * Removes the {@link Event}
     * @param room {@link Event#room}
     * @param timeSlot {@link Event#timeSlot}
     */
    public void removeEvent(String room, String timeSlot)
    {
        Event event = new Event(room, timeSlot);
        events.remove(event);
    }

    /**
     * Removes the {@link Event}
     * @param room {@link Event#room}
     * @param timeSlot {@link Event#timeSlot}
     */
    public void removeEvent(String room, TimeSlot timeSlot)
    {
        removeEvent(room, timeSlot.label);
    }

    /**
     * Gets a {@link String}[] containing the {@link Event#room} and {@link Event#timeSlot} of the {@link Event}
     * @param index of the {@link Event}
     * @return {@link String}[] containing the {@link Event#room} and {@link Event#timeSlot} of the {@link Event}
     * @throws IllegalArgumentException if index is out of bound
     */
    public String[] getEvent(int index) throws IllegalArgumentException
    {
        if(index >= events.size())
            throw new IllegalArgumentException("Index is not in bound.");

        String[] event = new String[2];
        event[0] = events.get(index).room;
        event[1] = events.get(index).timeSlot;

        return event;
    }

    @Override
    public void FromCSVStringToObject(String csvString, String delimeter) {
        String[] values = csvString.split(delimeter);

        if(values.length < 2)
            throw new IllegalArgumentException("The CSV-String is in the wrong format.");


        this.ID = Integer.parseInt(values[0]);
        this.company = values[1];

        try {
            this.addEvent(values[2], TimeSlot.A.label);
            this.addEvent(values[3], TimeSlot.B.label);
            this.addEvent(values[4], TimeSlot.C.label);
            this.addEvent(values[5], TimeSlot.D.label);
            this.addEvent(values[6], TimeSlot.E.label);
        }catch (Exception ignored){}
    }

    @Override
    public String ToCSVString(String delimeter){
        String re = getID().toString() + delimeter + getCompany() + delimeter;
        String[] events = {"","","","",""};

        for(Event event : this.events)
        {
            switch (event.timeSlot)
            {
                case "A":
                    events[0] = event.room;
                    break;
                case "B":
                    events[1] = event.room;
                    break;
                case "C":
                    events[2] = event.room;
                    break;
                case "D":
                    events[3] = event.room;
                    break;
                case "E":
                    events[4] = event.room;
                    break;
            }
        }

        for (String roomname : events){
            re += roomname + delimeter;
        }

        return re;
    }
}
