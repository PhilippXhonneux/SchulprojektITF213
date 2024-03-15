package de.bwv_ac.classes;

import de.bwv_ac.data.*;
import de.bwv_ac.view.bot_2.PPEvent.PPEvent;
import de.bwv_ac.view.bot_2.WishList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Bot2Controller {

    private static final String delimiter = ";"; // TODO: get from other location

    private final Wishes wishes;

    /**
     * GUI Object
     */
    private final WishList wishList;

    /**
     * Data class
     */
    private final PPerEvents pPerEvents;

    /**
     * GUI object
     */
    private final PPEvent ppEvent;

    public Bot2Controller(Wishes wishes, PPerEvents pPerEvents){
        this.wishes = wishes;
        this.pPerEvents = pPerEvents;
        ppEvent = new PPEvent();
        wishList = new WishList();
        wishes.addObserver(wishList);
        pPerEvents.addObserver(ppEvent);
        wishList.setActionListener("import", onImportAction);

        wishes.notifyObservers();
    }

    /**
     * Get the panel for bot 2
     * @return a JPanel
     */
    public Component getPanel() {
        return wishList;
    }

    /**
     * Get the participants per event panel
     * @return
     */
    public Component getPanel2() {
        return ppEvent;
    }

    /**
     * Handle User Action<br>
     * Configure and show a JFileChooser.<br>
     * After selecting a file to import,<br>
     * read the first line and the body separately<br>
     * and import them to the wishes data model
     */
    ActionListener onImportAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Wähle eine Kommaseparierte Liste im .CSV format aus");
            chooser.setApproveButtonText("Auswählen");
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String name = f.getName();

                    if(f.isDirectory())
                        return true;

                    return name.endsWith(".csv");
                }

                @Override
                public String getDescription() {
                    return "CSV Datei / *.csv";
                }
            };
            chooser.setFileFilter(filter);

            int rval = chooser.showOpenDialog(wishList);

            if(rval != JFileChooser.APPROVE_OPTION){
                return;
            }

            File f = chooser.getSelectedFile();

            try {
                ArrayList<Wish> wish = CSVReader.read(f.getAbsolutePath(),true, Wish.class);
                String[] columns = CSVReader.getFirstLine(f.getAbsolutePath());
                wishes.add(wish, columns);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
                // TODO: Error handling
            }
        }
    };

    ActionListener onExportListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
}
