package de.bwv_ac.classes;

import de.bwv_ac.data.Room;
import de.bwv_ac.data.Rooms;
import de.bwv_ac.data.Wish;
import de.bwv_ac.view.bot_3.rooms.RoomCapacity;
import de.bwv_ac.view.bot_3.rooms.RoomCapacityListDialog;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Bot3Controller {

    private final Rooms rooms;
    private final RoomCapacity roomList;

    public Bot3Controller(Rooms rooms){ //TODO romlist einbinden und dialog einbinden
        this.rooms = rooms;
        this.roomList = new RoomCapacity();
        rooms.addObserver(roomList);

        roomList.setAction(RoomCapacity.Buttons.ON_IMPORT_OPEN, onImportOpen);

        rooms.notifyObservers();
    }

    ActionListener onImportOpen = new ActionListener() {
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

            int rval = chooser.showOpenDialog(roomList);

            if(rval != JFileChooser.APPROVE_OPTION){
                return;
            }

            File f = chooser.getSelectedFile();

            try {
                ArrayList<Room> room = CSVReader.read(f.getAbsolutePath(),true, Room.class);
                String[] columns = CSVReader.getFirstLine(f.getAbsolutePath());
                rooms.add(room, columns);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(roomList, "Datei nicht gefunden!", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(roomList, "Fehlerhaftes dateiformat!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    };



    ActionListener onExportOpen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    public Component getPanelRooms() {
        return roomList;
    }
}
