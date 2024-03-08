package de.bwv_ac;

import de.bwv_ac.data.Company;
import de.bwv_ac.data.Room;
import de.bwv_ac.data.Timetable;
import de.bwv_ac.data.Timetables;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataObjectTests
{
    @Test
    public void CompanyTests()
    {
        Company c = new Company();
        c.setID(1);
        c.setName("test GmbH");
        c.setMaxEvents(2);
        c.setTime(Timetable.TimeSlot.A.toString());
        c.setSpecialty("specialty");
        c.setMaxParticipants(25);

        Assert.assertEquals(1, c.getID());
        Assert.assertEquals("test GmbH", c.getName());
        Assert.assertEquals(2, c.getMaxEvents());
        Assert.assertEquals("A", c.getTime());
        Assert.assertEquals("specialty", c.getSpecialty());
        Assert.assertEquals(25, c.getMaxParticipants());

        Assert.assertThrows(IllegalArgumentException.class, () -> c.setID(-2));
        Assert.assertThrows(IllegalArgumentException.class, () -> c.setName(""));
        Assert.assertThrows(IllegalArgumentException.class, () -> c.setTime("Hallo"));
        Assert.assertThrows(IllegalArgumentException.class, () -> c.setMaxEvents(-5));
        Assert.assertThrows(IllegalArgumentException.class, () -> c.setMaxParticipants(-5));
    }
    @Test
    public void RoomTests()
    {
        Room r = new Room();
        r.setRoomname("Mibba");
        r.setCapacity(50);
        Assert.assertEquals("Mibba", r.getRoomname());
        Assert.assertEquals(50, (int) r.getCapacity());

        Assert.assertThrows(IllegalArgumentException.class, () -> r.setCapacity(-5));
        Assert.assertThrows(IllegalArgumentException.class, () -> r.setRoomname(""));
        Assert.assertThrows(IllegalArgumentException.class, () -> r.setRoomname(null));

    }
}
