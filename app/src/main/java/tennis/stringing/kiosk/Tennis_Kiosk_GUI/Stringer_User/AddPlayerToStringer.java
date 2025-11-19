package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

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

    public AddPlayerToStringer(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;

        this.setTitle("Create New Player and Add to Roster");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Create New Player"), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("First Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        firstNameField = new JTextField(15);
        this.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Last Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        lastNameField = new JTextField(15);
        this.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("User ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        userIdField = new JTextField(15);
        this.add(userIdField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(buttonPanel, gbc);
        
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
