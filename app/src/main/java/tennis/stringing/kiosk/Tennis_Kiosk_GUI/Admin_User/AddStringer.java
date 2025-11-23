package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;

import java.awt.*;

public class AddStringer extends JFrame implements ActionListener {

    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JTextField stringerNameField;
    private JTextField stringerIdField;
    private JButton submitButton;
    private JButton backButton;

    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 18);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color secondaryColor = new Color(108, 117, 125);

    public AddStringer(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Add New Stringer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);
        this.setLayout(new GridBagLayout()); 

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(650, 450)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(10, 10, 40, 10);
        
        JLabel titleLabel = new JLabel("Add New Stringer to Kiosk");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        centerPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 30, 10, 30); 

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(panelColor);
        
        GridBagConstraints fieldGBC = new GridBagConstraints();
        fieldGBC.insets = new Insets(15, 15, 15, 15);
        fieldGBC.anchor = GridBagConstraints.WEST;
        fieldGBC.fill = GridBagConstraints.HORIZONTAL;
        Dimension fieldDim = new Dimension(220, 35);
        
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
        stringerNameField = new JTextField(15);
        stringerNameField.setFont(labelFont);
        stringerNameField.setPreferredSize(fieldDim);
        inputPanel.add(stringerNameField, fieldGBC);

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
        stringerIdField = new JTextField(15);
        stringerIdField.setFont(labelFont);
        stringerIdField.setPreferredSize(fieldDim);
        inputPanel.add(stringerIdField, fieldGBC);
        
        centerPanel.add(inputPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 30, 10); 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setBackground(panelColor);

        Dimension buttonSize = new Dimension(160, 45);
        
        backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(buttonSize);
        submitButton.setFont(buttonFont);
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        centerPanel.add(buttonPanel, gbc);
        
        gbc.gridy = 3;
        gbc.weighty = 0.5;
        centerPanel.add(Box.createVerticalStrut(0), gbc);
        
        GridBagConstraints wrapperGBC = new GridBagConstraints();
        wrapperGBC.weightx = 1.0; 
        wrapperGBC.weighty = 1.0; 
        wrapperGBC.anchor = GridBagConstraints.CENTER;
        this.add(centerPanel, wrapperGBC);
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();

        } else if (e.getSource() == submitButton) {
            String name = stringerNameField.getText();
            String idText = stringerIdField.getText();

            if (name.trim().isEmpty() || idText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newUserID;
            try {
                newUserID = Integer.parseInt(idText);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Error: User ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LinkedList<TennisStringer> allStringers = thisKiosk.getStringers();
            for (TennisStringer s : allStringers) {
                if (s.getUserID() == newUserID) {
                    JOptionPane.showMessageDialog(this, "Error: User ID " + newUserID + " is already taken.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            TennisStringer newStringer = new TennisStringer(0, name);
            newStringer.setUserID(newUserID);
            thisKiosk.addStringer(newStringer);
            
            JOptionPane.showMessageDialog(this, "Success: " + name + " has been added to the kiosk.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();
        }
    }
}