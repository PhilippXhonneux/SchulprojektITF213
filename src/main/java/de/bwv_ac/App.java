package de.bwv_ac;

import com.formdev.flatlaf.FlatLightLaf;
import de.bwv_ac.classes.Bot1Controller;
import de.bwv_ac.classes.Bot2Controller;
import de.bwv_ac.data.Companies;
import de.bwv_ac.data.Wishes;

import javax.swing.*;
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

        Wishes wishes = new Wishes(new ArrayList<>());
        Bot2Controller b2c = new Bot2Controller(wishes);


        JFrame window = new JFrame("SchoolBot - o - Mat");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("1. Unternehmen und Veranstaltungen", null, b1c.getPanel(), "");

        //JSplitPane spPane = new JSplitPane();

        //spPane.setRightComponent(b1c.getPanel());

        //window.add(spPane);

        tabbedPane.addTab("2. Schülerwünsche", null, b2c.getPanel(), ":F");
        window.add(tabbedPane);

        tabbedPane.addTab("3. Teilnehmer pro Veranstaltung", null, new JPanel(), ":D");
        window.add(tabbedPane);

        tabbedPane.addTab("4. Zeitslots", null, new JPanel(), ":D");
        window.add(tabbedPane);

        tabbedPane.addTab("5. Zuordnung", null, new JPanel(), ":D");
        window.add(tabbedPane);

        tabbedPane.addTab("6. Anwesenheitsliste Drucken", null, new JPanel(), ":D");
        window.add(tabbedPane);


        //window.add(b1c.getPanel(), BorderLayout.EAST);
        window.pack();
        window.setVisible(true);
    }
}
