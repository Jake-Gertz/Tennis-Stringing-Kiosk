package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;

import java.awt.*;

public class UpdateAdminInfo extends JFrame implements ActionListener {

    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JPasswordField currentIdField;
    private JPasswordField newIdField;
    private JPasswordField confirmIdField;
    private JButton updateIdButton;
    private JButton backButton;
    private JButton resetKioskButton;

    public UpdateAdminInfo(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Admin Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Update Admin ID"), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Current Admin ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        currentIdField = new JPasswordField(15);
        this.add(currentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("New Admin ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        newIdField = new JPasswordField(15);
        this.add(newIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Confirm New ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        confirmIdField = new JPasswordField(15);
        this.add(confirmIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateIdButton = new JButton("Update Admin ID");
        updateIdButton.addActionListener(this);
        this.add(updateIdButton, gbc);

        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JSeparator(), gbc);

        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        resetKioskButton = new JButton("Reset Kiosk to Factory Defaults");
        resetKioskButton.setBackground(Color.RED);
        resetKioskButton.setForeground(Color.WHITE);
        resetKioskButton.addActionListener(this);
        this.add(resetKioskButton, gbc);

        gbc.gridy = 7;
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        this.add(backButton, gbc);
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();

        } else if (e.getSource() == updateIdButton) {
            String currentIdText = new String(currentIdField.getPassword());
            String newIdText = new String(newIdField.getPassword());
            String confirmIdText = new String(confirmIdField.getPassword());

            if (currentIdText.trim().isEmpty() || newIdText.trim().isEmpty() || confirmIdText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int currentId, newId, confirmId;
            try {
                currentId = Integer.parseInt(currentIdText);
                newId = Integer.parseInt(newIdText);
                confirmId = Integer.parseInt(confirmIdText);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Error: IDs must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (currentId != thisKiosk.getAdminID()) {
                JOptionPane.showMessageDialog(this, "Error: Current Admin ID is incorrect.", "Auth Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newId != confirmId) {
                JOptionPane.showMessageDialog(this, "Error: New IDs do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newId == currentId) {
                JOptionPane.showMessageDialog(this, "Error: New ID must be different from the current ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            thisKiosk.setAdminID(newId);
            JOptionPane.showMessageDialog(this, "Success: Admin ID updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();
            
        } else if (e.getSource() == resetKioskButton) {

            int choice = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to reset this kiosk?\n" + 
                "ALL stringers, strings, and player data will be permanently deleted.\n" +
                "The Admin ID will be reset to its default.", 
                "Confirm Kiosk Reset", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                String confirmText = JOptionPane.showInputDialog(
                    this, 
                    "This is your final confirmation.\n" +
                    "This action cannot be undone.\n" +
                    "Please type 'RESET' to proceed.", 
                    "Final Confirmation",
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmText != null && confirmText.equals("RESET")) {

                    thisKiosk.updateStringerList(new LinkedList<TennisStringer>());
                    
                    thisKiosk.updateTennisStringList(new LinkedList<TennisString>());
                    
                    thisKiosk.setAdminID(5555);
                    
                    thisKiosk.updatePickUpAndStrungCount();

                    JOptionPane.showMessageDialog(this, "Kiosk has been reset to factory defaults.", "Reset Complete", JOptionPane.INFORMATION_MESSAGE);
                    
                    new TennisKioskAdminPage(thisKiosk);
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Reset canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
