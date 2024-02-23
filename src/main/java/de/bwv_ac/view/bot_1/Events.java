package de.bwv_ac.view.bot_1;

import de.bwv_ac.data.Companies;
import de.bwv_ac.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Panel for the companies and event list
 *
 * @author Robin Goerissen
 * @version 1.0.0
 */
public class Events extends JPanel implements Observer {
    private JPanel contentPane;
    private JButton csvExportButton;
    private JButton csvImportButton;
    private JPanel controlPanel; // Panel for the buttons
    private JTable eventTable;
    private JButton changeButton;
    private JButton addButton;

    private DefaultTableModel tableModel;

    public Events(){
        eventTable.setDefaultEditor(Object.class, null); // Disable editing the table
        addButton.setFocusable(false);
        this.setLayout(new BorderLayout());
        this.add(contentPane);
        tableModel = new DefaultTableModel();
        eventTable.setModel(tableModel);
    }

    /**
     * Fill the table with given data
     *
     * @param columns header data
     * @param rows    body data
     */
    public void setTableData(Object[] columns, Object[][] rows) {
        this.tableModel.setDataVector(rows, columns);
        //resizeColumnWidth(companiesTable);
        // Setze die MultiLineTableCellRenderer als Standard-Renderer für alle Spalten
        for (int i = 0; i < this.tableModel.getColumnCount(); i++) {
            //eventTable.getColumnModel().getColumn(i).setCellRenderer(new MultiLineTableCellRenderer());
        }
    }

    @Override
    public void update(Object obj) {
        if(obj == null)
            return;
        Companies companies = (Companies) obj;
        //String[] cols = new String[]{"ID", "Unternehmen", "Veranstaltung", "Max. Teilnehmer", "Max. Veranstaltung", "Fühster Zeitpunkt", "Teilnehmer", "Veranstaltungen",};
        String[] cols = companies.getColumns();

        setTableData(cols, companies.getCompaniesArray());

    }

    public void setOpenAddDialogAction(ActionListener l){
        addButton.addActionListener(l);
    }

    public void setOpenChangeDialogAction(ActionListener l){
        changeButton.addActionListener(l);
    }

    public int getSelectedItem() {
        return eventTable.getSelectedColumn();
    }

    public Vector getSelectedItemRow(){
        Vector<Vector> rows = tableModel.getDataVector();
        return rows.get(eventTable.getSelectedRow());
    }

    public void setOpenImportAction(ActionListener l){
        csvImportButton.addActionListener(l);
    }
}
