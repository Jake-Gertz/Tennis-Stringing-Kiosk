package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;

import java.awt.*;

public class AddPlayerToStringer extends JFrame implements ActionListener {

    private TennisStringer thisStringer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField userIdField;
    private JButton submitButton;
    private JButton backButton;
    
    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 18);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color secondaryColor = new Color(108, 117, 125);

    public AddPlayerToStringer(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;

        this.setTitle("Create New Player and Add to Roster");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor); 
        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(600, 450)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel titleLabel = new JLabel("Create New Player Account");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(40, 40, 40)); 
        centerPanel.add(titleLabel, gbc);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(panelColor);
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Player Details", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));
        
        GridBagConstraints inputGBC = new GridBagConstraints();
        inputGBC.insets = new Insets(10, 15, 10, 15);
        inputGBC.fill = GridBagConstraints.HORIZONTAL;

        inputGBC.gridx = 0;
        inputGBC.gridy = 0;
        inputGBC.anchor = GridBagConstraints.EAST;
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(labelFont);
        inputPanel.add(firstNameLabel, inputGBC);
        
        inputGBC.gridx = 1;
        inputGBC.anchor = GridBagConstraints.WEST;
        firstNameField = new JTextField(15);
        firstNameField.setFont(labelFont);
        inputPanel.add(firstNameField, inputGBC);

        inputGBC.gridx = 0;
        inputGBC.gridy = 1;
        inputGBC.anchor = GridBagConstraints.EAST;
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(labelFont);
        inputPanel.add(lastNameLabel, inputGBC);
        
        inputGBC.gridx = 1;
        inputGBC.anchor = GridBagConstraints.WEST;
        lastNameField = new JTextField(15);
        lastNameField.setFont(labelFont);
        inputPanel.add(lastNameField, inputGBC);

        inputGBC.gridx = 0;
        inputGBC.gridy = 2;
        inputGBC.anchor = GridBagConstraints.EAST;
        JLabel userIdLabel = new JLabel("User ID (Numeric):");
        userIdLabel.setFont(labelFont);
        inputPanel.add(userIdLabel, inputGBC);
        
        inputGBC.gridx = 1;
        inputGBC.anchor = GridBagConstraints.WEST;
        userIdField = new JTextField(15);
        userIdField.setFont(labelFont);
        inputPanel.add(userIdField, inputGBC);
        
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        centerPanel.add(inputPanel, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0)); // Increased horizontal gap
        buttonPanel.setBackground(panelColor);
        
        Dimension buttonSize = new Dimension(150, 45);

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);
        centerPanel.add(buttonPanel, gbc);
        
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
            new TennisStringerPage(thisStringer, thisKiosk); 
            this.dispose();

        } else if (e.getSource() == submitButton) {
            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            String idText = userIdField.getText();

            if (fName.trim().isEmpty() || lName.trim().isEmpty() || idText.trim().isEmpty()) {
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
                for (TennisPlayer p : s.getPlayers()) {
                    if (p.getUserID() == newUserID) {
                        JOptionPane.showMessageDialog(this, "Error: User ID " + newUserID + " is already taken.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            TennisPlayer newPlayer = new TennisPlayer(fName, lName, newUserID);
            
            thisStringer.addPlayer(newPlayer);
            
            JOptionPane.showMessageDialog(this, "Success: " + newPlayer.getPlayerName() + " has been created and added to your roster.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            new TennisStringerPage(thisStringer, thisKiosk);
            this.dispose();
        }
    }
}