package de.bwv_ac.classes;

import de.bwv_ac.data.*;
import de.bwv_ac.view.bot_2.PPEvent.PPEvent;
import de.bwv_ac.view.bot_2.WishList.WishListDialog;
import de.bwv_ac.view.bot_2.WishList.WishList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Bot2Controller {

    private static final String delimiter = ";"; // TODO: get from other location

    private final Wishes wishes;

    /**
     * GUI Object
     */
    private final WishList wishList;

    /**
     * Data class
     */
    private final PPerEvents pPerEvents;

    /**
     * Data class
     */
    private final PPEvent ppEvent;
    /**
     * GUI object
     */
    private final Companies companies;
    private final WishListDialog wishListAddDialog;
    private final WishListDialog wishListChangeDialog;

    public Bot2Controller(Wishes wishes, PPerEvents pPerEvents, Companies companies){

        // Init data model
        wishList = new WishList();
        this.pPerEvents = pPerEvents;
        this.companies = companies;

        // Init add dialog panel
        wishListAddDialog = new WishListDialog();
        wishListAddDialog.setTitle("Wünsche hinzufügen");
        wishListAddDialog.setOkButtonText("Hinzufügen");
        wishListAddDialog.setActionListener(WishListDialog.Buttons.CANCEL_BUTTON, onCancelAddDialog);

        // Init change dialog panel
        wishListChangeDialog = new WishListDialog();
        wishListChangeDialog.setTitle("Wünsche bearbeiten");
        wishListChangeDialog.setOkButtonText("Ändern");
        wishListChangeDialog.setActionListener(WishListDialog.Buttons.CANCEL_BUTTON, onCancelChangeDialog);


        // Fille comboboxes of dialogs initial
        String[] data = new String[companies.size()];
        for (int i = 0; i < companies.size()-1;i++){
            Company c = companies.get(i);
            String line = c.getID() + " " + c.getName();
            data[i] = line;
        }
        wishListAddDialog.fillComboBoxes(data);
        wishListChangeDialog.fillComboBoxes(data);

        // Init Second main panel
        ppEvent = new PPEvent();
        pPerEvents.addObserver(ppEvent);

        // Init the main JPanel for this controller
        this.wishes = wishes;
        wishes.addObserver(wishList);

        wishList.setActionListener(WishList.Buttons.IMPORT, onImportAction);
        wishList.setActionListener(WishList.Buttons.ON_ADD_OPEN, onOpenAddDialog);
        wishList.setActionListener(WishList.Buttons.ON_EDIT_OPEN, onOpenChangeDialog);
        wishListChangeDialog.setActionListener(WishListDialog.Buttons.OK_BUTTON, onChangeAction);
        wishListAddDialog.setActionListener(WishListDialog.Buttons.OK_BUTTON, onAddAction);
        wishList.setActionListener(WishList.Buttons.DELETE, onRemoveAction);
        //wishList.setActionListener(WishList.Buttons.EXPORT, );


        wishes.notifyObservers();
    }

    /**
     * Get the panel for bot 2
     * @return a JPanel
     */
    public Component getPanel() {
        return wishList;
    }

    /**
     * Get the participants per event panel
     * @return
     */
    public Component getPanel2() {
        return ppEvent;
    }

    /**
     * Handle User Action<br>
     * Configure and show a JFileChooser.<br>
     * After selecting a file to import,<br>
     * read the first line and the body separately<br>
     * and import them to the wishes data model
     */
    private final ActionListener onImportAction = new ActionListener() {
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

            int rval = chooser.showOpenDialog(wishList);

            if(rval != JFileChooser.APPROVE_OPTION){
                return;
            }

            File f = chooser.getSelectedFile();

            try {
                ArrayList<Wish> wish = CSVReader.read(f.getAbsolutePath(),true, Wish.class);
                String[] columns = CSVReader.getFirstLine(f.getAbsolutePath());
                wishes.add(wish, columns);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(wishList, "Datei nicht gefunden!", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(wishList, "Fehlerhaftes dateiformat!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    };

    private final ActionListener onExportListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    /**
     * Handle User Action<br>
     * By closing the window they ask for conformation.<br>
     */
    private final ActionListener onCancelAddDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rval = JOptionPane.showConfirmDialog(wishListAddDialog, "Vorgang Abbrechen. Sind Sie sicher?");
            if(rval == JOptionPane.OK_OPTION){
                wishListAddDialog.dispose();
            }
        }
    };


    private final ActionListener onOpenAddDialog =  new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Fille comboboxes of dialogs initial
            int s = companies.size();
            String[] data = new String[s+1];
            data[0] = "";
            for (int i = 1; i < s+1;i++){
                Company c = companies.get(i-1);
                String line = c.getID() + " " + c.getName();
                data[i] = line;
            }
            wishListAddDialog.fillComboBoxes(data);

            wishListAddDialog.pack();
            wishListAddDialog.setVisible(true);
        }
    };
    private final ActionListener onCancelChangeDialog = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rval = JOptionPane.showConfirmDialog(wishListChangeDialog, "Vorgang Abbrechen. Sind Sie sicher?");
            if(rval == JOptionPane.OK_OPTION){
                wishListChangeDialog.dispose();
            }
        }
    };

    private final ActionListener onOpenChangeDialog =  new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = wishList.getSelectedItem();
            if(row == -1){
                JOptionPane.showMessageDialog(wishList, "Bitte wähle eine Zeile zum bearbeiten aus");
                return;
            }
            // Get wish from data model and set the selected row to the dialog
            Wish w = wishes.get(row);

            // create getter and fill coboboxes with eventlist

            // Fill comboboxes of the dialog with current data from the data class
            String[] data = new String[companies.size()];
            for (int i = 0; i < companies.size()-1;i++){
                Company c = companies.get(i);
                String line = c.getID() + " " + c.getName();
                data[i] = line;
            }
            wishListChangeDialog.fillComboBoxes(data);

            // change the selection from the comboboxes of the dialog with the data of the chosen row
            for(int i = 0; i< 6; i++){
                // -1 Because they start with 1 and this with 0
                int wishI = w.getSelection(i)-1;
                wishListChangeDialog.setComboBoxWish(wishI, i);
            }

            // Show the dialog
            wishListChangeDialog.pack();
            wishListChangeDialog.setVisible(true);
        }
    };

    ActionListener onChangeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] choosenWishes = new Object[6][];
            choosenWishes[0] = wishListChangeDialog.getComboBoxWish1();
            choosenWishes[1] = wishListChangeDialog.getComboBoxWish2();
            choosenWishes[2] = wishListChangeDialog.getComboBoxWish3();
            choosenWishes[3] = wishListChangeDialog.getComboBoxWish4();
            choosenWishes[4] = wishListChangeDialog.getComboBoxWish5();
            choosenWishes[5] = wishListChangeDialog.getComboBoxWish6();

            int row = wishList.getSelectedItem();
            if(row == -1){
                JOptionPane.showMessageDialog(wishList, "Bitte wähle eine Zeile zum bearbeiten aus");
                return;
            }

            // Get wish from data model and set the selected row to the dialog
            Wish chosenWishO = wishes.get(row);

            for(int i = 0; i< choosenWishes.length; i++){
                int index = (int) choosenWishes[i][0]+1;
                chosenWishO.setSelection(i,index);

            }

            wishes.change(row, chosenWishO);

            JOptionPane.showMessageDialog(wishListChangeDialog, "Erfolgreich gespeichert");
            wishListChangeDialog.dispose();

            wishList.setSelectedItem(row);
        }
    };

    ActionListener onAddAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] choosenWishes = new Object[6][];
            choosenWishes[0] = wishListAddDialog.getComboBoxWish1();
            choosenWishes[1] = wishListAddDialog.getComboBoxWish2();
            choosenWishes[2] = wishListAddDialog.getComboBoxWish3();
            choosenWishes[3] = wishListAddDialog.getComboBoxWish4();
            choosenWishes[4] = wishListAddDialog.getComboBoxWish5();
            choosenWishes[5] = wishListAddDialog.getComboBoxWish6();
            for (Object[] wish : choosenWishes){
                if(((int) wish[0] ) == -1 ){
                    wish[0] = 0;
                }
            }

            String klass = wishListAddDialog.getTextFieldClass();
            String surname = wishListAddDialog.getTextFieldSurname();
            String name = wishListAddDialog.getTextFieldName();

            StringBuilder csv = new StringBuilder();
            csv.append(klass+";").append(surname+";").append(name+";")
                    .append(choosenWishes[0][0] + ";")
                    .append(choosenWishes[1][0] + ";")
                    .append(choosenWishes[2][0] + ";")
                    .append(choosenWishes[3][0] + ";")
                    .append(choosenWishes[4][0] + ";")
                    .append(choosenWishes[5][0] + ";");


            Wish wish = new Wish();
            System.out.println(csv.toString());
            try{
                wish.FromCSVStringToObject(csv.toString(), delimiter);

            }catch (Exception ex){
                //TODO handling
                ex.printStackTrace();
            }
            wishes.add(wish);
            JOptionPane.showMessageDialog(wishListAddDialog, "Erfolgreich gespeichert");
            wishListAddDialog.dispose();
        }
    };

    /**
     * Handle User Action<br>
     * Get the selected row index and search for it in the companies model.<br>
     * Show a confirmation message and delete if they choose OK.<br>
     * Show a result message.
     */
    private ActionListener onRemoveAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = wishList.getSelectedItem();
            if(row == -1){
                JOptionPane.showMessageDialog(wishList, "Bitte wähle eine Zeile zum entfernen aus");
                return;
            }

            Wish wish = wishes.get(row);

            int retVal = JOptionPane.showConfirmDialog(wishList, "Sind Sie sich sicher, die Wünsche des Schülers " + wish.getSurname() +", " + wish.getName() + " zu entfernen?");

            String message;
            if(retVal == JOptionPane.OK_OPTION){
                wishes.remove(wish);
                message = "Erfoglreich entfernt";
            }else
                message = "Vorgang Abbgebrochen";


            JOptionPane.showMessageDialog(wishList, message);

        }
    };



}
