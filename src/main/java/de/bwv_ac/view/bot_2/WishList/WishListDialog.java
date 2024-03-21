package de.bwv_ac.view.bot_2.WishList;

import javax.swing.*;
import java.awt.event.*;

public class WishListDialog extends JDialog {

    public static void main(String[] args) {
        WishListDialog dialog = new WishListDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldClass;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JComboBox comboBoxWish1;
    private JComboBox comboBoxWish2;
    private JComboBox comboBoxWish3;
    private JComboBox comboBoxWish4;
    private JComboBox comboBoxWish5;
    private JComboBox comboBoxWish6;

    /**
     * Create an object of PPEventDialog
     */
    public WishListDialog() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JScrollPane sc = new JScrollPane(contentPane);
        setContentPane(sc);
        setModal(true);

        getRootPane().setDefaultButton(buttonOK);
        buttonCancel.setText("Abbrechen");
    }

    /**
     * Change the text of the OK button
     * @param text text to change
     */
    public void setOkButtonText(String text) {
        buttonOK.setText(text);
    }

    public void setComboBoxWish(int indexOfWish, int combobox) {
        switch (combobox) {
            case 0 -> this.comboBoxWish1.setSelectedIndex(indexOfWish);
            case 1 -> this.comboBoxWish2.setSelectedIndex(indexOfWish);
            case 2 -> this.comboBoxWish3.setSelectedIndex(indexOfWish);
            case 3 -> this.comboBoxWish4.setSelectedIndex(indexOfWish);
            case 4 -> this.comboBoxWish5.setSelectedIndex(indexOfWish);
            case 5 -> this.comboBoxWish6.setSelectedIndex(indexOfWish);
            default -> throw new IllegalArgumentException("The index given must be between 0 and 5.");
        };
    }

    /**
     * Available buttons in this gui
     */
    public enum Buttons{
        OK_BUTTON, CANCEL_BUTTON
    }
    /**
     * Set the Listener to the Button
     * @param btn the enum of the button PPEventDialog.Buttons.OK_BUTTON
     * @param l the action
     */
    public void setActionListener(Buttons btn, ActionListener l){
        switch (btn){
            case OK_BUTTON:
                buttonOK.addActionListener(l);
                break;
            case CANCEL_BUTTON:
                buttonCancel.addActionListener(l);
                addWindowListener(new WindowAdapter() {
                    /**
                     * Invoked when a window is in the process of being closed.
                     * The close operation can be overridden at this point.
                     *
                     * @param e {@link WindowEvent}
                     */
                    @Override
                    public void windowClosing(WindowEvent e) {
                        //super.windowClosing(e);
                        l.actionPerformed(new ActionEvent(buttonCancel, 99, buttonCancel.getActionCommand()));
                    }
                });
                contentPane.registerKeyboardAction(l, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
                break;
        }
    }

    /**
     * Add a cancel function by clicking cancel, press ESC or by closing the dialog
     * @param l ActionListener Interface
     */
    public void setCancelAction(ActionListener l){
        buttonCancel.addActionListener(l);
        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e {@link WindowEvent}
             */
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                l.actionPerformed(new ActionEvent(buttonCancel, 99, buttonCancel.getActionCommand()));
            }
        });
        contentPane.registerKeyboardAction(l, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public String getTextFieldClass() {
        return textFieldClass.getText();
    }

    public void setTextFieldClass(String text) {
        this.textFieldClass.setText(text);
    }

    public JTextField getTextFieldName() {
        return textFieldName;
    }

    public void setTextFieldName(String text) {
        this.textFieldName.setText(text);
    }

    public String getTextFieldSurname() {
        return textFieldSurname.getText();
    }

    public void setTextFieldSurname(String text) {
        this.textFieldSurname.setText(text);
    }

    /**
     * Fill all combo boxes
     * @param data data to fill
     */
    public void fillComboBoxes(String[] data){
        DefaultComboBoxModel<String> hehe = ((DefaultComboBoxModel<String>) comboBoxWish1.getModel());
        ((DefaultComboBoxModel<String>) comboBoxWish1.getModel()).removeAllElements();
        ((DefaultComboBoxModel<String>) comboBoxWish2.getModel()).removeAllElements();
        ((DefaultComboBoxModel<String>) comboBoxWish3.getModel()).removeAllElements();
        ((DefaultComboBoxModel<String>) comboBoxWish4.getModel()).removeAllElements();
        ((DefaultComboBoxModel<String>) comboBoxWish5.getModel()).removeAllElements();
        ((DefaultComboBoxModel<String>) comboBoxWish6.getModel()).removeAllElements();
        for (String line : data){
            ((DefaultComboBoxModel<String>) comboBoxWish1.getModel()).addElement(line);
            ((DefaultComboBoxModel<String>) comboBoxWish2.getModel()).addElement(line);
            ((DefaultComboBoxModel<String>) comboBoxWish3.getModel()).addElement(line);
            ((DefaultComboBoxModel<String>) comboBoxWish4.getModel()).addElement(line);
            ((DefaultComboBoxModel<String>) comboBoxWish5.getModel()).addElement(line);
            ((DefaultComboBoxModel<String>) comboBoxWish6.getModel()).addElement(line);
            //comboBoxWahl1.getModel().setSelectedItem(line);
            /*comboBoxWahl2.getModel().setSelectedItem(line);
            comboBoxWahl3.getModel().setSelectedItem(line);
            comboBoxWahl4.getModel().setSelectedItem(line);
            comboBoxWahl5.getModel().setSelectedItem(line);
            comboBoxWahl6.getModel().setSelectedItem(line);*/
        }
    }

    /**
     * Get the index and the String of the 1st wish
     * @return [index:int, wish:String]
     */
    public Object[] getComboBoxWish1() {
        return new Object[]{comboBoxWish1.getSelectedIndex(), comboBoxWish1.getSelectedItem()};
    }

    public void setComboBoxWish1(int index) {
        this.comboBoxWish1.setSelectedIndex(index);
    }

    public Object[] getComboBoxWish2() {
        return new Object[]{comboBoxWish2.getSelectedIndex(), comboBoxWish2.getSelectedItem()};
    }

    public void setComboBoxWish2(int index) {
        this.comboBoxWish2.setSelectedIndex(index);
    }

    public Object[] getComboBoxWish3() {
        return new Object[]{comboBoxWish3.getSelectedIndex(), comboBoxWish3.getSelectedItem()};
    }

    public void setComboBoxWish3(int index) {
        this.comboBoxWish3.setSelectedIndex(index);
    }

    public Object[] getComboBoxWish4() {
        return new Object[]{comboBoxWish4.getSelectedIndex(), comboBoxWish4.getSelectedItem()};
    }

    public void setComboBoxWish4(int index) {
        this.comboBoxWish4.setSelectedIndex(index);
    }

    public Object[] getComboBoxWish5() {
        return new Object[]{comboBoxWish5.getSelectedIndex(), comboBoxWish5.getSelectedItem()};
    }

    public void setComboBoxWish5(int index) {
        this.comboBoxWish5.setSelectedIndex(index);
    }

    public Object[] getComboBoxWish6() {
        return new Object[]{comboBoxWish6.getSelectedIndex(), comboBoxWish6.getSelectedItem()};
    }

    public void setComboBoxWish6(int index) {
        this.comboBoxWish6.setSelectedIndex(index);
    }
}
