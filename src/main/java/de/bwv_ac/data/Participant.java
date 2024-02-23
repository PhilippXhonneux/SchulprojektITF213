package de.bwv_ac.data;

/**
 * Datastructur for Teilnehmer je Veranstaltung BOT2
 *
 * @author Philipp Goebel
 * @version 1.0.0
 */
public class Participant extends Datastructure {
    private Integer ID;
    private String organizer;
    private String participant;

    public Integer getID() {
        return ID;
    }
    public void setID(int ID) throws IllegalArgumentException {
        if(ID <= 0)
            throw new IllegalArgumentException("Identification number must be greater than 0.");
        this.ID = ID;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}
