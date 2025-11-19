package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

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

    public AddStringer(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Add New Stringer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Add New Stringer to Kiosk"), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Stringer Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        stringerNameField = new JTextField(15);
        this.add(stringerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
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
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
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
