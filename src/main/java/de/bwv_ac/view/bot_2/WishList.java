package de.bwv_ac.view.bot_2;

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

    public void setActionListener(String btn, ActionListener l){
        switch (btn){
            case "import":
                importButton.addActionListener(l);
                break;
            case "export":
                exportButton.addActionListener(l);
                break;
            case "add":
                hinzufuegenButton.addActionListener(l);
                break;
            case "edit":
                bearbeitenButton.addActionListener(l);
                break;
            case "delete":
                entfernenButton.addActionListener(l);
                break;
        }
    }
}
