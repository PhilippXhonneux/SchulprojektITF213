package de.bwv_ac.view.bot_1;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class AddDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JSpinner idSpinner;
    private JTextField companyNameTextField;
    private JTextArea eventNameTextField;
    private JSpinner maxEventsSpinner;
    private JSlider maxParticipantSlider;
    private JComboBox startAtComboBox;

    public AddDialog() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        maxEventsSpinner.setValue(5);

        //setContentPane(contentPane);
        JScrollPane sc = new JScrollPane(contentPane);
        setContentPane(sc);
        setModal(true);

        buttonCancel.setText("Abbrechen");
    }


    public void setOKAction(ActionListener l){
        buttonOK.addActionListener(l);
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
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                l.actionPerformed(new ActionEvent(buttonCancel, 99, buttonCancel.getActionCommand()));
            }
        });
        contentPane.registerKeyboardAction(l, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void setId(int id){
        idSpinner.setValue(id);
    }

    public void setCompanyName(String name){
        companyNameTextField.setText(name);
    }

    public void setEventName(String name){
        eventNameTextField.setText(name);
    }

    public void setMaxParticipant(int i){
        maxParticipantSlider.setValue(i);
    }

    public void setMaxEvents(int i){
        maxEventsSpinner.setValue(i);
    }

    public void setStartAt(String timeslot){
        startAtComboBox.getModel().setSelectedItem(timeslot);
    }

    public void setOkButtonText(String text){
        buttonOK.setText(text);
    }
}
