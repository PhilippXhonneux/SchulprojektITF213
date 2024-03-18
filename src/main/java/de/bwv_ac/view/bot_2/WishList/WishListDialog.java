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
    private JComboBox comboBoxWahl1;
    private JComboBox comboBoxWahl2;
    private JComboBox comboBoxWahl3;
    private JComboBox comboBoxWahl4;
    private JComboBox comboBoxWahl5;
    private JComboBox comboBoxWahl6;

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
        for (String line : data){
            comboBoxWahl1.getModel().setSelectedItem(line);
            comboBoxWahl2.getModel().setSelectedItem(line);
            comboBoxWahl3.getModel().setSelectedItem(line);
            comboBoxWahl4.getModel().setSelectedItem(line);
            comboBoxWahl5.getModel().setSelectedItem(line);
            comboBoxWahl6.getModel().setSelectedItem(line);
        }
    }

    public Object[] getComboBoxWahl1() {
        return new Object[]{comboBoxWahl1.getSelectedIndex(), comboBoxWahl1.getSelectedItem()};
    }

    public void setComboBoxWahl1(int index) {
        this.comboBoxWahl1.setSelectedIndex(index);
    }

    public Object[] getComboBoxWahl2() {
        return new Object[]{comboBoxWahl2.getSelectedIndex(), comboBoxWahl2.getSelectedItem()};
    }

    public void setComboBoxWahl2(int index) {
        this.comboBoxWahl2.setSelectedIndex(index);
    }

    public Object[] getComboBoxWahl3() {
        return new Object[]{comboBoxWahl3.getSelectedIndex(), comboBoxWahl3.getSelectedItem()};
    }

    public void setComboBoxWahl3(int index) {
        this.comboBoxWahl3.setSelectedIndex(index);
    }

    public Object[] getComboBoxWahl4() {
        return new Object[]{comboBoxWahl4.getSelectedIndex(), comboBoxWahl4.getSelectedItem()};
    }

    public void setComboBoxWahl4(int index) {
        this.comboBoxWahl4.setSelectedIndex(index);
    }

    public Object[] getComboBoxWahl5() {
        return new Object[]{comboBoxWahl5.getSelectedIndex(), comboBoxWahl5.getSelectedItem()};
    }

    public void setComboBoxWahl5(int index) {
        this.comboBoxWahl5.setSelectedIndex(index);
    }

    public Object[] getComboBoxWahl6() {
        return new Object[]{comboBoxWahl6.getSelectedIndex(), comboBoxWahl6.getSelectedItem()};
    }

    public void setComboBoxWahl6(int index) {
        this.comboBoxWahl6.setSelectedIndex(index);
    }
}
