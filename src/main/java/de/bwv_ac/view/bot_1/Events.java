package de.bwv_ac.view.bot_1;

import de.bwv_ac.data.Companies;
import de.bwv_ac.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel for the companies and event list.<br>
 * This class listen for changes at the companies data model
 *
 * @author Robin Goerissen
 * @version 1.0.0
 */
@SuppressWarnings({"FieldMayBeFinal"})
public class Events extends JPanel implements Observer {
    private JPanel contentPane;
    private JButton csvExportButton;
    private JButton csvImportButton;
    private JPanel controlPanel; // Panel for the buttons
    private JTable eventTable;
    private JButton changeButton;
    private JButton addButton;
    private JButton removeButton;

    private DefaultTableModel tableModel;

    /**
     * Get Event JPanel object.
     */
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
    }

    /**
     * update the table in the view with the new data model
     * @param obj Companies data model
     */
    @Override
    public void update(Object obj) {
        if(obj == null)
            return;
        Companies companies = (Companies) obj;
        //String[] cols = new String[]{"ID", "Unternehmen", "Veranstaltung", "Max. Teilnehmer", "Max. Veranstaltung", "Frühster Zeitpunkt", "Teilnehmer", "Veranstaltungen",};
        String[] cols = companies.getColumns();

        setTableData(cols, companies.getArrays());

    }

    /**
     * Add an Action to click on the button addButton.
     * @param l with the action that execute on click.
     */
    public void setOpenAddDialogAction(ActionListener l){
        addButton.addActionListener(l);
    }

    /**
     * Add an Action to click on the button changeButton.
     * @param l with the action that execute on click.
     */
    public void setOpenChangeDialogAction(ActionListener l){
        changeButton.addActionListener(l);
    }

    /**
     * Add an Action to click on the button removeButton.
     * @param l with the action that execute on click.
     */
    public void setRemoveAction(ActionListener l){
        removeButton.addActionListener(l);
    }

    public int getSelectedItem() {
        return eventTable.getSelectedRow();
    }

    /**
     * Add an Action to click on the button importButton.
     * @param l with the action that execute on click.
     */
    public void setOpenImportAction(ActionListener l){
        csvImportButton.addActionListener(l);
    }

    /**
     * Set the selection to the given index.
     * @param row to select.
     */
    public void setSelectedItem(int row) {
        eventTable.changeSelection(row, 0, false, false);
    }
}
