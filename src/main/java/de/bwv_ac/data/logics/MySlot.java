package de.bwv_ac.data.logics;

import de.bwv_ac.data.Company;
import de.bwv_ac.data.Room;

public class MySlot {

    private Room room;

    private String timeSlot;

    private Company event;

    public MySlot(String timeSlot, Room room){
        this.timeSlot = timeSlot;
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Company getEvent() {
        return event;
    }

    public void setEvent(Company event) {
        this.event = event;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if(event != null){
            out.append(event.getID()).append(" ").append(event.getName())
                    .append(" ");
        }
        out.append(timeSlot).append(" ").append(room.getRoomname());
        return out.toString();
    }

    public boolean isAvailable() {
        return event == null;
    }

}
