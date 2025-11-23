package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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

    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 18);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color dangerColor = new Color(220, 53, 69);
    private Color secondaryColor = new Color(108, 117, 125);

    public UpdateStringerInfo(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;
        this.allStringers = thisKiosk.getStringers();

        initializeGUI();
        populateFields(); // Initial population
    }

    private void initializeGUI() {
        this.setTitle("Update Stringer Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);
        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(650, 750)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 15, 25, 15);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        // --- Title ---
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        
        JLabel titleLabel = new JLabel("Update Stringer Information");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        centerPanel.add(titleLabel, gbc);

        // --- Stringer Selector Panel ---
        gbc.gridy = 1;
        gbc.weighty = 0; 
        gbc.insets = new Insets(60, 15, 40, 15); 
        
        JLabel selectLabel = new JLabel("Select Stringer:");
        selectLabel.setFont(labelFont);
        
        // JComboBox Initialization
        DefaultComboBoxModel<TennisStringer> model = new DefaultComboBoxModel<>();
        for (TennisStringer s : allStringers) {
            model.addElement(s);
        }
        stringerSelector = new JComboBox<>(model);
        stringerSelector.setFont(labelFont);
        stringerSelector.setPreferredSize(new Dimension(300, 35));
        stringerSelector.addActionListener(this);
        
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setPreferredSize(new Dimension(300, 1));
        
        JPanel selectorPanel = new JPanel();
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setBackground(panelColor);

        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stringerSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        selectorPanel.add(selectLabel);
        selectorPanel.add(Box.createVerticalStrut(5));
        selectorPanel.add(stringerSelector);
        selectorPanel.add(Box.createVerticalStrut(15));
        selectorPanel.add(separator);
        
        centerPanel.add(selectorPanel, gbc);

        // --- Input Fields Panel ---
        gbc.gridy = 2;
        gbc.weighty = 1.0; 
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 15, 20, 15); 
        
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(panelColor);
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Details", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));
        
        GridBagConstraints fieldGBC = new GridBagConstraints();
        fieldGBC.insets = new Insets(15, 20, 15, 20);
        fieldGBC.anchor = GridBagConstraints.WEST;
        fieldGBC.fill = GridBagConstraints.HORIZONTAL; 

        // Name Field
        fieldGBC.gridx = 0;
        fieldGBC.gridy = 0;
        fieldGBC.weightx = 0;
        fieldGBC.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("Stringer Name:");
        nameLabel.setFont(labelFont);
        inputPanel.add(nameLabel, fieldGBC);
        
        fieldGBC.gridx = 1;
        fieldGBC.weightx = 1.0;
        fieldGBC.anchor = GridBagConstraints.WEST;
        stringerNameField = new JTextField(20);
        stringerNameField.setFont(labelFont);
        inputPanel.add(stringerNameField, fieldGBC);

        // ID Field
        fieldGBC.gridx = 0;
        fieldGBC.gridy = 1;
        fieldGBC.weightx = 0;
        fieldGBC.anchor = GridBagConstraints.EAST;
        JLabel idLabel = new JLabel("Stringer User ID:");
        idLabel.setFont(labelFont);
        inputPanel.add(idLabel, fieldGBC);
        
        fieldGBC.gridx = 1;
        fieldGBC.weightx = 1.0;
        fieldGBC.anchor = GridBagConstraints.WEST;
        stringerIdField = new JTextField(20);
        stringerIdField.setFont(labelFont);
        inputPanel.add(stringerIdField, fieldGBC);
        
        centerPanel.add(inputPanel, gbc);

        // --- Button Panel (FIXED LAYOUT) ---
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(40, 10, 40, 10); 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        
        // Changed from FlowLayout to GridBagLayout for forced horizontal alignment
        JPanel buttonPanel = new JPanel(new GridBagLayout()); 
        buttonPanel.setBackground(panelColor);
        
        Dimension buttonSize = new Dimension(180, 45);
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.insets = new Insets(0, 15, 0, 15); // Added generous spacing between buttons

        // 1. Back Button
        buttonGBC.gridx = 0;
        backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton, buttonGBC);
        
        // 2. Update Button
        buttonGBC.gridx = 1;
        updateButton = new JButton("Update Info");
        updateButton.setPreferredSize(buttonSize);
        updateButton.setFont(buttonFont);
        updateButton.setBackground(primaryColor);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton, buttonGBC);

        // 3. Delete Button (Now guaranteed to be visible)
        buttonGBC.gridx = 2;
        deleteButton = new JButton("Remove Stringer");
        deleteButton.setPreferredSize(buttonSize);
        deleteButton.setFont(buttonFont);
        deleteButton.setBackground(dangerColor);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton, buttonGBC);

        centerPanel.add(buttonPanel, gbc);
        
        // --- Final placement of center panel on frame ---
        GridBagConstraints wrapperGBC = new GridBagConstraints();
        wrapperGBC.weightx = 1.0; 
        wrapperGBC.weighty = 1.0; 
        wrapperGBC.anchor = GridBagConstraints.CENTER;
        this.add(centerPanel, wrapperGBC);
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    // --- Utility method to refresh the combo box ---
    private void refreshStringerSelector() {
        DefaultComboBoxModel<TennisStringer> model = (DefaultComboBoxModel<TennisStringer>) stringerSelector.getModel();
        TennisStringer selected = (TennisStringer) stringerSelector.getSelectedItem();
        
        model.removeAllElements();
        this.allStringers = thisKiosk.getStringers();
        TennisStringer newSelection = null;
        for (TennisStringer s : allStringers) {
            model.addElement(s);
            if (selected != null && s.equals(selected)) {
                newSelection = s;
            }
        }
        
        if (newSelection != null) {
            stringerSelector.setSelectedItem(newSelection);
        } else if (model.getSize() > 0) {
            stringerSelector.setSelectedIndex(0);
        } else {
            populateFields();
        }
    }

    private void populateFields() {
        TennisStringer selected = (TennisStringer) stringerSelector.getSelectedItem();
        if (selected != null) {
            stringerNameField.setText(selected.getStringerName());
            stringerIdField.setText(String.valueOf(selected.getUserID()));
            stringerNameField.setEnabled(true);
            stringerIdField.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } else {
            stringerNameField.setText("N/A");
            stringerIdField.setText("N/A");
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
            
            if (selectedStringer == null) {
                 JOptionPane.showMessageDialog(this, "Error: No stringer selected.", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }
            
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
            
            // Check for duplicate ID among *other* stringers
            for (TennisStringer s : allStringers) {
                if (s.getUserID() == newUserID && s != selectedStringer) {
                    JOptionPane.showMessageDialog(this, "Error: User ID " + newUserID + " is already taken by " + s.getStringerName() + ".", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Perform Update
            selectedStringer.setStringerName(newName);
            selectedStringer.setUserID(newUserID);
            
            refreshStringerSelector(); 
            populateFields();
            
            JOptionPane.showMessageDialog(this, "Success: Stringer info updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == deleteButton) {
            TennisStringer selectedStringer = (TennisStringer) stringerSelector.getSelectedItem();
            
            if (selectedStringer == null) {
                 JOptionPane.showMessageDialog(this, "Error: No stringer selected to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }

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
                
                refreshStringerSelector(); 
                populateFields(); 
                
                JOptionPane.showMessageDialog(this, "Stringer removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}