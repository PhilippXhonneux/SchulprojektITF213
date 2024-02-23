package de.bwv_ac.data;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Datastructur for Stundenplan BOT3
 *
 * @author Philipp Goebel, Philipp Xhonneux
 * @version 1.1.0
 */

//TODO Klasse überarbeiten da Ergebnis generiert wird und somit nicht teil der Datastructure ist

public class Timetable extends Datastructure {
    private Integer ID;
    private String company;
    private String room;
    private LocalTime[] time;


    public static final LocalTime[] TIME_A = {LocalTime.of(8, 45), LocalTime.of(9, 30)};
    public static final LocalTime[] TIME_B = {LocalTime.of(9, 50), LocalTime.of(10, 30)};
    public static final LocalTime[] TIME_C = {LocalTime.of(10, 35), LocalTime.of(11, 20)};
    public static final LocalTime[] TIME_D = {LocalTime.of(11, 40), LocalTime.of(12, 25)};
    public static final LocalTime[] TIME_E = {LocalTime.of(12, 25), LocalTime.of(13, 10)};

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalTime[] getTime() {
        return time;
    }

    /**
     * Set the time base on the given timeslot in the string.
     * Leagel Arguments are "A","B","C","D" or "E"
     *
     * @author Philipp Xhonneux
     *
     * @param timeSlot
     * @throws IllegalArgumentException
     */
    public void setTime(String timeSlot) throws IllegalArgumentException
    {
        timeSlot = timeSlot.toUpperCase();

        switch (timeSlot) {
            case "A":
                this.time = TIME_A;
                break;
            case "B":
                this.time = TIME_B;
                break;
            case "C":
                this.time = TIME_C;
                break;
            case "D":
                this.time = TIME_D;
                break;
            case "E":
                this.time = TIME_E;
                break;
            default:
                throw new IllegalArgumentException("String must contain one of the timeslots A,B,C,D or E.");
        }
    }
}
