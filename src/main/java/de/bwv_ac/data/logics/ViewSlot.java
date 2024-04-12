package de.bwv_ac.data.logics;

import de.bwv_ac.data.Datastructure;

public class ViewSlot extends Datastructure {
    private int id;
    private String companyName;
    private String eventName;
    private String slotARoom;
    private String slotBRoom;
    private String slotCRoom;
    private String slotDRoom;
    private String slotERoom;

   /* public ViewSlot(Company company, String slotARoom, String slotBRoom, String slotCRoom, String slotDRoom, String slotERoom) {
        this.company = company;
        this.slotARoom = slotARoom;
        this.slotBRoom = slotBRoom;
        this.slotCRoom = slotCRoom;
        this.slotDRoom = slotDRoom;
        this.slotERoom = slotERoom;
    }*/

    public ViewSlot(int id, String conpanyName, String eventName, String sa, String sb, String sc, String sd, String se) {
        this.id = id;
        this.companyName = conpanyName;
        this.eventName = eventName;
        this.slotARoom = slotARoom;
        this.slotBRoom = slotBRoom;
        this.slotCRoom = slotCRoom;
        this.slotDRoom = slotDRoom;
        this.slotERoom = slotERoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSlotARoom() {
        return slotARoom;
    }

    public void setSlotARoom(String slotARoom) {
        this.slotARoom = slotARoom;
    }

    public String getSlotBRoom() {
        return slotBRoom;
    }

    public void setSlotBRoom(String slotBRoom) {
        this.slotBRoom = slotBRoom;
    }

    public String getSlotCRoom() {
        return slotCRoom;
    }

    public void setSlotCRoom(String slotCRoom) {
        this.slotCRoom = slotCRoom;
    }

    public String getSlotDRoom() {
        return slotDRoom;
    }

    public void setSlotDRoom(String slotDRoom) {
        this.slotDRoom = slotDRoom;
    }

    public String getSlotERoom() {
        return slotERoom;
    }

    public void setSlotERoom(String slotERoom) {
        this.slotERoom = slotERoom;
    }
}
