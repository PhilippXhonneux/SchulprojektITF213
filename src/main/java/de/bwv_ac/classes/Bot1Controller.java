package de.bwv_ac.classes;

import de.bwv_ac.data.Companies;
import de.bwv_ac.data.Company;
import de.bwv_ac.view.bot_1.AddDialog;
import de.bwv_ac.view.bot_1.Events;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Handle bot 1 views and notify the observers about changes
 *
 * @author Robin Goerissen
 * @version 1.0.0
 */
@SuppressWarnings("ALL")
public class Bot1Controller {

    private static final String delimiter = ";"; // TODO: get from other location

    /**
     * Observable companies data model
     */
    private Companies companies;

    /**
     * A JDialog view to add data
     */
    private AddDialog addDialog;

    /**
     * A JDialog view to change data
     */
    private AddDialog changeDialog;

    /**
     * The main JPanel for this controller
     */
    private Events eventPanel;

    /**
     * Create a Bot1Controller object
     * @param companies Data Model
     */
    public Bot1Controller(Companies companies){

        this.companies = companies;
        // Init add dialog panel
        addDialog = new AddDialog();
        addDialog.setTitle("Veranstaltung hinzufügen");
        addDialog.setOkButtonText("Hinzufügen");
        addDialog.setCancelAction(onCancelActionAddDialog);
        addDialog.setOKAction(onAddAction);

        // Init change dialog
        changeDialog = new AddDialog(); // Use a changed add dialog for it
        changeDialog.setTitle("Veranstaltung bearbeiten");
        changeDialog.setOkButtonText("Ändern");
        changeDialog.setCancelAction(onCancelActionChangeDialog);
        changeDialog.setOKAction(onChangeAction);

        // Init the main JPanel for this controller
        eventPanel = new Events();
        companies.addObserver(eventPanel); // register the panel to the observable data model
        eventPanel.setOpenAddDialogAction(onOpenAddDialog);
        eventPanel.setOpenChangeDialogAction(onOpenChangeDialog);
        eventPanel.setOpenImportAction(onOpenImport);
        eventPanel.setRemoveAction(onRemove);
        eventPanel.setOnExportAction(onOpenExport);

    }

    /**
     * Handle User Action<br>
     * By closing the window they ask for conformation.<br>
     */
    private ActionListener onCancelActionAddDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rval = JOptionPane.showConfirmDialog(addDialog, "Vorgang Abbrechen. Sind Sie sicher?");
            if(rval == JOptionPane.OK_OPTION){
                addDialog.dispose();
            }
        }
    };

    /**
     * Handle User Action<br>
     * Open the change dialog
     */
    private ActionListener onCancelActionChangeDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rval = JOptionPane.showConfirmDialog(changeDialog, "Vorgang Abbrechen. Sind Sie sicher?");
            if(rval == JOptionPane.OK_OPTION){
                changeDialog.dispose();
            }
        }
    };

    /**
     * Handle User Action<br>
     * Get data from the add dialog<br>
     * and add them to the companies data model.<br>
     * show a message and dispose the add dialog if the action was successful.
     */
    private ActionListener onAddAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = addDialog.getID(); // TODO Proof
            String companyName = addDialog.getCompanyName();
            String eventName = addDialog.getEventName();
            int maxParticipants = addDialog.getMaxParticipants();
            int maxEvents = addDialog.getMaxEvent();
            String startAt = addDialog.getStartTime();

            String csv = id + delimiter + companyName + delimiter + eventName + delimiter;
            csv += maxParticipants + delimiter + maxEvents + delimiter + startAt;

            Company c = new Company();
            c.FromCSVStringToObject(csv, delimiter);
            companies.add(c);
            JOptionPane.showMessageDialog(addDialog, "Erfolgreich gespeichert");
            addDialog.dispose();
        }
    };

    /**
     * Handle User Action<br>
     * Get data from the change dialog,<br>
     * search the company in data model and change them.<br>
     * show a message if no row is selected and return<br>
     * show a message and dispose the change dialog if the action was successful.
     */
    private ActionListener onChangeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = eventPanel.getSelectedItem();
            if(row == -1){
                JOptionPane.showMessageDialog(eventPanel, "Bitte wähle eine Zeile zum bearbeiten aus");
                return;
            }

            int id = changeDialog.getID(); // TODO Proof
            String companyName = changeDialog.getCompanyName();
            String eventName = changeDialog.getEventName();
            int maxParticipants = changeDialog.getMaxParticipants();
            int maxEvents = changeDialog.getMaxEvent();
            String startAt = changeDialog.getStartTime();

            String csv = id + delimiter + companyName + delimiter + eventName + delimiter;
            csv += maxParticipants + delimiter + maxEvents + delimiter + startAt;

            Company c = new Company();
            c.FromCSVStringToObject(csv, delimiter);

            companies.change(changeDialog.getIndex(), c);

            JOptionPane.showMessageDialog(changeDialog, "Erfolgreich gespeichert");
            changeDialog.dispose();

            eventPanel.setSelectedItem(row);
        }
    };

    /**
     * Handle User Action<br>
     * Show the add dialog
     */
    private ActionListener onOpenAddDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            addDialog.pack();
            addDialog.setVisible(true);
        }
    };

    /**
     * Handle User Action<br>
     * Get selected row index and search for it in the companies data model.<br>
     * Add the company data to the change dialog and show the dialog<br>
     * Show a message and return if they found no selected row
     */
    private ActionListener onOpenChangeDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = eventPanel.getSelectedItem();
            if(row == -1){
                JOptionPane.showMessageDialog(eventPanel, "Bitte wähle eine Zeile zum bearbeiten aus");
                return;
            }
            // Get company from data model and set the selected row to the dialog
            Company c = companies.get(row);
            changeDialog.setIndex(row);

            changeDialog.setCompany(c);

            changeDialog.pack();
            changeDialog.setVisible(true);

        }
    };

    /**
     * Handle User Action<br>
     * Configure and show a JFileChooser.<br>
     * After selecting a file to import,<br>
     * read the first line and the body separately<br>
     * and import them to the companies data model
     */
    private ActionListener onOpenImport = new ActionListener() {
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

            int rval = chooser.showOpenDialog(eventPanel);

            if(rval != JFileChooser.APPROVE_OPTION){
                return;
            }

            File f = chooser.getSelectedFile();

            try {
                ArrayList<Company> companiesA = CSVReader.read(f.getAbsolutePath(),true, Company.class);
                String[] columns = CSVReader.getFirstLine(f.getAbsolutePath());
                companies.add(companiesA, columns);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
                // TODO: Error handling
            }

        }
    };

    /**
     * Handle User Action<br>
     * Configure and show a JFileChooser.<br>
     * After selecting a file to import,<br>
     */
    private ActionListener onOpenExport = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Wähle einen Pfad oder eine Datei im .CSV format aus zum Exportieren aus.");
            chooser.setApproveButtonText("Auswählen");
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
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

            int rval = chooser.showOpenDialog(eventPanel);

            if(rval != JFileChooser.APPROVE_OPTION){
                return;
            }

            File f = chooser.getSelectedFile();

            File exportFile = null;

            if(f.isDirectory()){
                String name = null;
                while(name == null){
                    name = JOptionPane.showInputDialog(eventPanel, "Bitte gebe einen für die Datei an. (Ohne Endung)", "Name der Datei zum exportieren.",  JOptionPane.PLAIN_MESSAGE);
                }
                try {
                    f.mkdirs(); // If the path does not exist, it will be created
                }catch (SecurityException exception){
                    JOptionPane.showMessageDialog(eventPanel, "Es gab einen Fehler mit der Berechtigung für den Pfad!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }

            }else{
                exportFile = f;
            }


            if(exportFile.exists()){
                int retVal = JOptionPane.showConfirmDialog(eventPanel, "Wollen Sie die Datei wirklich überschreiben?","Datei Existiert bereits", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                if(retVal == JOptionPane.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(eventPanel, "Vorgang wurde abgebrochen.", "Abgebrochen", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Nr.;Unternehmen;Fachrichtung;Max. Teilnehmer;Max. Veranstaltungen;Fruehester Zeitpunkt\n");
            for (Company company : companies){
                sb.append(company.ToCSVString(delimiter)).append("\n");
            }
            String csvOut = sb.toString();


            try(FileWriter writer = new FileWriter(exportFile)){
                writer.write(csvOut);
                writer.flush();
                JOptionPane.showMessageDialog(eventPanel, "Erfolgreich exportiert:\n" + f.getAbsolutePath(), "Erfolgreich exportiert", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(eventPanel, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    };

    /**
     * Handle User Action<br>
     * Get the selected row index and search for it in the companies model.<br>
     * Show a confirmation message and delete if they choose OK.<br>
     * Show a result message.
     */
    private ActionListener onRemove = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = eventPanel.getSelectedItem();
            if(row == -1){
                JOptionPane.showMessageDialog(eventPanel, "Bitte wähle eine Zeile zum entfernen aus");
                return;
            }

            Company c = companies.get(row);

            int retVal = JOptionPane.showConfirmDialog(eventPanel, "Sind Sie sich sicher das Event mit der ID: " + c.getID() + " zu entfernen?");

            String message;
            if(retVal == JOptionPane.OK_OPTION){
                companies.remove(c);
                message = "Erfoglreich entfernt";
            }else
                message = "Vorgang Abbgebrochen";


            JOptionPane.showMessageDialog(eventPanel, message);

        }
    };

    /**
     * Get the panel for bot 1
     * @return a JPanel
     */
    public Component getPanel() {
        return eventPanel;
    }
}
