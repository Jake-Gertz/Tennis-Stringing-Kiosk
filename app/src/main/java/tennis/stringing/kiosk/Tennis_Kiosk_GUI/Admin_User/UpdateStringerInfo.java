package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;

import java.awt.*;

public class UpdateStringerInfo extends JFrame implements ActionListener {

    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JComboBox<TennisStringer> stringerSelector;
    private JTextField stringerNameField;
    private JTextField stringerIdField;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    
    private LinkedList<TennisStringer> allStringers;

    public UpdateStringerInfo(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;
        this.allStringers = thisKiosk.getStringers();

        this.setTitle("Update Stringer Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Select Stringer to Update"), gbc);

        gbc.gridy = 1;
        DefaultComboBoxModel<TennisStringer> model = new DefaultComboBoxModel<>();
        for (TennisStringer s : allStringers) {
            model.addElement(s);
        }
        stringerSelector = new JComboBox<>(model);
        stringerSelector.addActionListener(this);
        this.add(stringerSelector, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Stringer Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        stringerNameField = new JTextField(15);
        this.add(stringerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Stringer User ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        stringerIdField = new JTextField(15);
        this.add(stringerIdField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        updateButton = new JButton("Update Info");
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);

        deleteButton = new JButton("Remove Stringer");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(buttonPanel, gbc);
        
        populateFields();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void populateFields() {
        TennisStringer selected = (TennisStringer) stringerSelector.getSelectedItem();
        if (selected != null) {
            stringerNameField.setText(selected.getStringerName());
            stringerIdField.setText(String.valueOf(selected.getUserID()));
        } else {
            stringerNameField.setText("");
            stringerIdField.setText("");
            stringerNameField.setEnabled(false);
            stringerIdField.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();

        } else if (e.getSource() == stringerSelector) {
            populateFields();

        } else if (e.getSource() == updateButton) {
            TennisStringer selectedStringer = (TennisStringer) stringerSelector.getSelectedItem();
            
            String newName = stringerNameField.getText();
            String newIdText = stringerIdField.getText();

            if (newName.trim().isEmpty() || newIdText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newUserID;
            try {
                newUserID = Integer.parseInt(newIdText);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Error: User ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            for (TennisStringer s : allStringers) {
                if (s.getUserID() == newUserID && s != selectedStringer) {
                    JOptionPane.showMessageDialog(this, "Error: User ID " + newUserID + " is already taken by " + s.getStringerName() + ".", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            selectedStringer.setStringerName(newName);
            selectedStringer.setUserID(newUserID);
            JOptionPane.showMessageDialog(this, "Success: Stringer info updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();

        } else if (e.getSource() == deleteButton) {
            TennisStringer selectedStringer = (TennisStringer) stringerSelector.getSelectedItem();
            
            int racketsToString = selectedStringer.getRacketsToString();
            if (racketsToString > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Error: Cannot remove " + selectedStringer.getStringerName() + ".\n" +
                    "They still have " + racketsToString + " racket(s) to string.", 
                    "Deletion Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int choice = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to remove " + selectedStringer.getStringerName() + "?\n" +
                "This stringer has no pending rackets.", 
                "Confirm Removal", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                thisKiosk.removeStringer(selectedStringer);
                JOptionPane.showMessageDialog(this, "Stringer removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new TennisKioskAdminPage(thisKiosk);
                this.dispose();
            }
        }
    }
}
