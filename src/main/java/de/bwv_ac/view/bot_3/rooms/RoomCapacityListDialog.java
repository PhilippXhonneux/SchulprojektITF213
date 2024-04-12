package de.bwv_ac.view.bot_3.rooms;

import javax.swing.*;
import java.awt.event.*;

public class RoomCapacityListDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldRoom;
    private JSpinner spinnerCapacity;

    public RoomCapacityListDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

    }


    public static void main(String[] args) {
        RoomCapacityListDialog dialog = new RoomCapacityListDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


    public void setButtonOK(ActionListener l){
        buttonOK.addActionListener(l);
    }

    /**
     * Add a cancel function by clicking cancel, press ESC or by closing the dialog
     * @param l ActionListener Interface
     */
    public void setButtonCancel(ActionListener l){
        buttonCancel.addActionListener(l);
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


}
