package de.bwv_ac.classes;

import de.bwv_ac.data.Companies;
import de.bwv_ac.data.Company;
import de.bwv_ac.util.Subject;
import de.bwv_ac.view.bot_1.AddDialog;
import de.bwv_ac.view.bot_1.Events;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Handle bot 1 views and notify the observers about changes
 *
 * @author Robin Goerissen
 * @version 1.0.0
 */
public class Bot1Controller {

    private static String delimiter = ";";

    private Companies companies;
    private AddDialog addDialog;

    private AddDialog changeDialog;

    private Events eventPanel;

    public Bot1Controller(Companies companies){

        this.companies = companies;


        addDialog = new AddDialog();
        addDialog.setTitle("Veranstaltung hinzufügen");
        addDialog.setOkButtonText("Hinzugügen");
        addDialog.setCancelAction(onCancelActionAddDialog);
        addDialog.setOKAction(onAddAction);

        changeDialog = new AddDialog();
        changeDialog.setTitle("Veranstaltung bearbeiten");
        changeDialog.setOkButtonText("Ändern");
        changeDialog.setCancelAction(onCancelActionCahngeDialog);
        changeDialog.setOKAction(onChangeAction);

        eventPanel = new Events();
        companies.addObserver(eventPanel);
        eventPanel.setOpenAddDialogAction(onOpenAddDialog);
        eventPanel.setOpenChangeDialogAction(onOpenChangeDialog);
        eventPanel.setOpenImportAction(onOpenImport);

        //String[] cols = new String[]{"ID", "Unternehmen", "Veranstaltung", "Max. Teilnehmer", "Max. Veranstaltung", "Fühster Zeitpunkt", "Teilnehmer", "Veranstaltungen",};
        String[] cols = companies.getColumns();
        String[][] rows = companies.getCompaniesArray();
        //eventPanel.setTableData(cols, rows);

    }

    private ActionListener onCancelActionAddDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //addDialog.setVisible(false);
            int rval = JOptionPane.showConfirmDialog(addDialog, "Vorgang Abbrechen. Sind Sie sicher?");
            if(rval == JOptionPane.OK_OPTION){
                addDialog.dispose();
            }
            //System.out.println("dispose");
        }
    };
    private ActionListener onCancelActionCahngeDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rval = JOptionPane.showConfirmDialog(changeDialog, "Vorgang Abbrechen. Sind Sie sicher?");
            if(rval == JOptionPane.OK_OPTION){
                changeDialog.dispose();
            }
        }
    };

    private ActionListener onAddAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = addDialog.getID(); // TODO Proof
            String companyName = addDialog.getCompanyName();
            String eventName = addDialog.getEventName();
            int maxParticipants = addDialog.getMaxParticipants();
            int maxEvents = addDialog.getMaxEvent();
            String startAt = addDialog.gerStartTime();

            String csv = id + delimiter + companyName + delimiter + eventName + delimiter;
            csv += maxParticipants + delimiter + maxEvents + delimiter + startAt;

            Company c = new Company();
            c.FromCSVStringToObject(csv, delimiter);
            companies.addCompany(c);
            JOptionPane.showMessageDialog(addDialog, "Erfolgreich gespeichert");
            addDialog.dispose();
        }
    };

    private ActionListener onChangeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    private ActionListener onOpenAddDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            addDialog.pack();
            addDialog.setVisible(true);
        }
    };

    private ActionListener onOpenChangeDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int col = eventPanel.getSelectedItem();
            if(col == -1){
                JOptionPane.showMessageDialog(eventPanel, "Bitte wähle eine Zeile zum bearbeiten aus");
                return;
            }
            // TODO Get Data from Companies data model and load the company dataset in the change dialog
            Vector row = eventPanel.getSelectedItemRow();

            changeDialog.setId(Integer.parseInt((String) row.get(0)));
            changeDialog.setCompanyName(String.valueOf(row.get(1)));
            changeDialog.setEventName(String.valueOf(row.get(2)));
            changeDialog.setMaxParticipant(Integer.parseInt((String) row.get(3)));
            changeDialog.setMaxEvents(Integer.parseInt((String) row.get(4)));
            changeDialog.setStartAt(String.valueOf(row.get(5)));
            changeDialog.pack();
            changeDialog.setVisible(true);
        }
    };

    private ActionListener onOpenImport = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Wähle eine Kommaseparierte Liste im .CSV format aus");
            chooser.setApproveButtonText("Auswählen");
            chooser.setMultiSelectionEnabled(false);
            javax.swing.filechooser.FileFilter filter = new FileFilter() {
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

            int rval = chooser.showOpenDialog(eventPanel);

            if(rval != JFileChooser.APPROVE_OPTION){
                return;
            }

            File f = chooser.getSelectedFile();

            try {
                //ArrayList<Company> companiesA = BotFiles.CSVReader(f.getAbsolutePath(), true, Company.class);
                //String[] columns = BotFiles.getFirstLine(f.getAbsolutePath(), ";"); // TODO: Get Delimiter, get columns from Companies class
                ArrayList<Company> companiesA = CSVReader.read(f.getAbsolutePath(),true, Company.class);
                String[] columns = CSVReader.getFirstLine(f.getAbsolutePath());
                companies.addCompanies(companiesA, columns);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
                // TODO: Error handling
            }

            System.out.println(f.getAbsolutePath());

        }
    };

    public Component getPanel() {
        return eventPanel;
    }
}
