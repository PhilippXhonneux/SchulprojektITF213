package de.bwv_ac.view.bot_3.rooms.TimeTableList;

import de.bwv_ac.data.Rooms;
import de.bwv_ac.data.Timetable;
import de.bwv_ac.data.Timetables;
import de.bwv_ac.data.logics.MySlot;
import de.bwv_ac.data.logics.ViewSlot;
import de.bwv_ac.util.Observer;
import de.bwv_ac.view.bot_3.rooms.RoomCapacity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Time;

public class TimeTableList extends JPanel implements Observer {
    private JPanel contentPane;
    private JTable table1;

    private DefaultTableModel tableModel;
    private JButton generateButton;
    private JButton CSVExportButton;

    public TimeTableList(){
        table1.setDefaultEditor(Object.class, null); // Disable editing the table
        //hinzufuegenButton.setFocusable(false);

        this.setLayout(new BorderLayout());
        this.add(contentPane);

        this.tableModel = new DefaultTableModel();
        this.table1.setModel(tableModel);
    }

    /**
     * @param obj
     */
    @Override
    public void update(Object obj) {
        if(obj == null)
            return;
        if(obj instanceof Timetables){
            Timetables timetables = (Timetables) obj;
            String[] cols = timetables.getColumns();

            Object[][] rows = new Object[timetables.size()][];
            // Quick refactor
            for(int i = 0; i< rows.length; i++) {

                Object[] row = new Object[8];
                row[0] = timetables.get(i).getEvent().getID();
                row[1] = timetables.get(i).getEvent().getName();
                row[2] = timetables.get(i).getEvent().getSpecialty();
                String slot = timetables.get(i).getTimeSlot();

                int index = -1;
                switch (slot) {
                    case "A":
                        index = 3;
                        break;
                    case "B":
                        index = 4;
                        break;
                    case "C":
                        index = 5;
                        break;
                    case "D":
                        index = 6;
                        break;
                    case "E":
                        index = 7;
                        break;
                    default:
                        System.out.println("Ungültiger Slot-Wert: " + slot);
                        break;
                        // TODO: Show the error to the user and return
                }

                if (index != -1) {

                    for (int y = 3; y < row.length; y++) {
                        if (y == index) {
                            row[y] = timetables.get(y).getRoom().getRoomname();
                        } else {
                            row[y] = "-";
                        }
                    }
                }

                rows[i] = row;
            }

            //setTableData(cols, timetables.getArrays());
            setTableData(cols, rows);
        }
    }

    /**
     * Fill the table with given data
     *
     * @param columns header data
     * @param rows    body data
     */
    public void setTableData(Object[] columns, Object[][] rows) {
        this.tableModel.setDataVector(rows, columns);
    }

    public enum Buttons{
        ON_GENERATE, ON_EXPORT_OPEN
    }
    public void setAction(TimeTableList.Buttons button, ActionListener l) {

        switch (button){
            case ON_GENERATE -> generateButton.addActionListener(l);
            case ON_EXPORT_OPEN -> CSVExportButton.addActionListener(l);
        }
    }
}
