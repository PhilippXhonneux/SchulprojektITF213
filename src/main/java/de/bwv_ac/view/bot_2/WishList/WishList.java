package de.bwv_ac.view.bot_2.WishList;

import de.bwv_ac.data.Companies;
import de.bwv_ac.data.Wishes;
import de.bwv_ac.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class WishList extends JPanel implements Observer {
    private JPanel contentPanel;
    private JTable wishTable;
    private JButton exportButton;
    private JButton importButton;
    private JButton bearbeitenButton;
    private JButton hinzufuegenButton;
    private JButton entfernenButton;

    private final DefaultTableModel tableModel;

    public WishList() {

        wishTable.setDefaultEditor(Object.class, null); // Disable editing the table
        hinzufuegenButton.setFocusable(false);
        this.setLayout(new BorderLayout());
        this.add(contentPanel);

        tableModel = new DefaultTableModel();
        wishTable.setModel(tableModel);
    }

    @Override
    public void update(Object obj) {
        if(obj == null)
            return;
        if(obj instanceof Wishes){
            Wishes wishes = (Wishes) obj;
            String[] cols = wishes.getColumns();

            setTableData(cols, wishes.getArrays());
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

    public int getSelectedItem() {
        return  wishTable.getSelectedRow();
    }

    public enum Buttons {
        IMPORT, EXPORT, ADD, EDIT, DELETE
    }
    public void setActionListener(Buttons btn, ActionListener l){
        switch (btn){
            case IMPORT:
                importButton.addActionListener(l);
                break;
            case EXPORT:
                exportButton.addActionListener(l);
                break;
            case ADD:
                hinzufuegenButton.addActionListener(l);
                break;
            case EDIT:
                bearbeitenButton.addActionListener(l);
                break;
            case DELETE:
                entfernenButton.addActionListener(l);
                break;
        }
    }


}
