package de.bwv_ac;

import com.formdev.flatlaf.FlatDarculaLaf;
import de.bwv_ac.classes.Bot1Controller;
import de.bwv_ac.data.Companies;

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
            //UIManager.setLookAndFeel( new FlatLightLaf() ); // Light mode
            //UIManager.setLookAndFeel( new FlatDarkLaf() ); // Dark mode
            UIManager.setLookAndFeel( new FlatDarculaLaf() ); // Dracula mode
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        Companies companies = new Companies(new ArrayList<>());
        Bot1Controller b1c = new Bot1Controller(companies);


        JFrame window = new JFrame("SchoolBot - o - Mat");
        window.setDefaultCloseOperation(3);

        window.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Unternehmen und Veranstaltungen", null, b1c.getPanel(), "");

        //JSplitPane spPane = new JSplitPane();

        //spPane.setRightComponent(b1c.getPanel());

        //window.add(spPane);

        tabbedPane.addTab("Test", null, new JPanel(), ":D");
        window.add(tabbedPane);


        //window.add(b1c.getPanel(), BorderLayout.EAST);
        window.pack();
        window.setVisible(true);
    }
}
