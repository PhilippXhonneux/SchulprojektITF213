package de.bwv_ac;

import com.formdev.flatlaf.FlatLightLaf;
import de.bwv_ac.classes.Bot1Controller;
import de.bwv_ac.classes.Bot2Controller;
import de.bwv_ac.classes.Bot3Controller;
import de.bwv_ac.data.*;
import de.bwv_ac.view.bot_3.rooms.RoomCapacity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args )
    {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() ); // Light mode
            //UIManager.setLookAndFeel( new FlatDarkLaf() ); // Dark mode
            //UIManager.setLookAndFeel( new FlatDarculaLaf() ); // Dracula mode
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        Companies companies = new Companies(new ArrayList<>());
        Bot1Controller b1c = new Bot1Controller(companies);

        PPerEvents pPerEvents = new PPerEvents(new ArrayList<>());
        Wishes wishes = new Wishes(new ArrayList<>(), pPerEvents, companies);
        Bot2Controller b2c = new Bot2Controller(wishes, pPerEvents, companies);

        Rooms rooms = new Rooms(new ArrayList<>());
        Timetables timetables = new Timetables(new ArrayList<>(), rooms, wishes);
        Bot3Controller b3c = new Bot3Controller(rooms, timetables, pPerEvents, companies);



        JFrame window = new JFrame("SchoolBot - o - Mat");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("1. Unternehmen und Veranstaltungen", null, b1c.getPanel(), "");

        //JSplitPane spPane = new JSplitPane();

        //spPane.setRightComponent(b1c.getPanel());

        //window.add(spPane);

        tabbedPane.addTab("2. Schülerwünsche", null, b2c.getPanel(), "");
        window.add(tabbedPane);

        tabbedPane.addTab("3. Teilnehmer pro Veranstaltung", null, b2c.getPanel2(), "");
        window.add(tabbedPane);

        tabbedPane.addTab("4. Räume & Kapazitäten", null, b3c.getPanelRooms(), ":D");
        window.add(tabbedPane);

        tabbedPane.addTab("5. Zeitslots", null, b3c.getPanelTimeTables(), ":D");
        window.add(tabbedPane);

        tabbedPane.addTab("6. Zuordnung", null, new JPanel(), ":D");
        window.add(tabbedPane);

        tabbedPane.addTab("7. Anwesenheitsliste Drucken", null, new JPanel(), ":D");
        window.add(tabbedPane);


        //window.add(b1c.getPanel(), BorderLayout.EAST);
        window.pack();
        window.setMinimumSize(new Dimension(500, 300));
        window.setSize(new Dimension(1200, 480));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
