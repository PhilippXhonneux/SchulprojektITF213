package de.bwv_ac.view.bot_3.rooms.TimeTableList;

import de.bwv_ac.data.Rooms;
import de.bwv_ac.data.Timetable;
import de.bwv_ac.data.Timetables;
import de.bwv_ac.data.logics.ViewSlot;
import de.bwv_ac.util.Observer;
import de.bwv_ac.view.bot_3.rooms.RoomCapacity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

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
        if(obj instanceof ViewSlot){
            Timetables timetables = (Timetables) obj;
            String[] cols = timetables.getColumns();

            setTableData(cols, timetables.getArrays());
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
