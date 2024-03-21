package de.bwv_ac.view.bot_2.PPEvent;

import de.bwv_ac.data.PPerEvents;
import de.bwv_ac.data.Wishes;
import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PPEvent extends JPanel implements Observer {
    private JPanel contentPane;
    private JTable table1;
    private final DefaultTableModel tableModel;

    public PPEvent(){
        table1.setDefaultEditor(Object.class, null); // Disable editing the table
        this.setLayout(new BorderLayout());
        add(contentPane);
        tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
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

    @Override
    public void update(Object obj) {
        if(obj == null)
            return;
        if(obj instanceof PPerEvents){
            PPerEvents pPerEvents = (PPerEvents) obj;
            String[] cols = pPerEvents.getColumns();

            setTableData(cols, pPerEvents.getArrays());
        }
    }
}
