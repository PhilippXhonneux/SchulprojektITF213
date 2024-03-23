package de.bwv_ac.view.bot_3.rooms;

import de.bwv_ac.data.Rooms;
import de.bwv_ac.data.Wishes;
import de.bwv_ac.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class RoomCapacity extends JPanel implements Observer {
    private JPanel contentPane;
    private JTable table1;
    private DefaultTableModel tableModel;
    private JButton CSVImportButton;
    private JButton CSVExportButton;

    public RoomCapacity(){

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
        if(obj instanceof Rooms){
            Rooms rooms = (Rooms) obj;
            String[] cols = rooms.getColumns();

            setTableData(cols, rooms.getArrays());
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
        ON_IMPORT_OPEN, ON_EXPORT_OPEN
    }
    public void setAction(Buttons button, ActionListener l) {

        switch (button){
            case ON_IMPORT_OPEN -> CSVImportButton.addActionListener(l);
            case ON_EXPORT_OPEN -> CSVExportButton.addActionListener(l);
        }
    }
}
