package de.bwv_ac.classes;

import de.bwv_ac.data.*;
import de.bwv_ac.view.bot_3.rooms.RoomCapacity;
import de.bwv_ac.view.bot_3.rooms.RoomCapacityListDialog;
import de.bwv_ac.view.bot_3.rooms.TimeTableList.TimeTableList;

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
    private final TimeTableList timeTableList;
    private final Timetables timetables;
    private final PPerEvents pPerEvents;
    private final Companies companies;

    public Bot3Controller(Rooms rooms, Timetables timetables, PPerEvents pPerEvents, Companies companies){
        this.rooms = rooms;
        this.roomList = new RoomCapacity();
        rooms.addObserver(roomList);

        this.timetables = timetables;
        this.timeTableList = new TimeTableList();
        timetables.addObserver(timeTableList);

        this.pPerEvents = pPerEvents;
        this.companies = companies;

        roomList.setAction(RoomCapacity.Buttons.ON_IMPORT_OPEN, onImportOpen);

        timeTableList.setAction(TimeTableList.Buttons.ON_GENERATE, onGenerateAction);

        rooms.notifyObservers();
        timetables.notifyObservers();
        timetables.notifyObservers();
    }

    private ActionListener onImportOpen = new ActionListener() {
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



    private ActionListener onExportOpen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    public Component getPanelRooms() {
        return roomList;
    }

    public Component getPanelTimeTables() {
        return timeTableList;
    }

    private ActionListener onGenerateAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Call generate:");
            try {
                //timetables.generate(pPerEvents, rooms);
                timetables.gen(pPerEvents, rooms, companies);
            }catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(timeTableList, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
}
