package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.DataBase.DataBaseManager;
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

    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 18);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color dangerColor = new Color(220, 53, 69);
    private Color secondaryColor = new Color(108, 117, 125);

    public UpdateAdminInfo(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Admin Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);
        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(650, 700)); 
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
        
        JLabel titleLabel = new JLabel("Admin Settings");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        centerPanel.add(titleLabel, gbc);
        
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 30, 20, 30);
        
        JPanel idUpdatePanel = new JPanel(new GridBagLayout());
        idUpdatePanel.setBackground(panelColor);
        idUpdatePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Update Admin ID", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));

        GridBagConstraints idGBC = new GridBagConstraints();
        idGBC.insets = new Insets(10, 15, 10, 15);
        idGBC.anchor = GridBagConstraints.WEST;
        idGBC.fill = GridBagConstraints.HORIZONTAL;
        idGBC.weightx = 0;
        Dimension fieldDim = new Dimension(200, 35);
        
        idGBC.gridx = 0;
        idGBC.gridy = 0;
        idGBC.anchor = GridBagConstraints.EAST;
        JLabel currentLabel = new JLabel("Current Admin ID:");
        currentLabel.setFont(labelFont);
        idUpdatePanel.add(currentLabel, idGBC);

        idGBC.gridx = 1;
        idGBC.anchor = GridBagConstraints.WEST;
        idGBC.weightx = 1.0;
        currentIdField = new JPasswordField(15);
        currentIdField.setPreferredSize(fieldDim);
        currentIdField.setFont(labelFont);
        idUpdatePanel.add(currentIdField, idGBC);

        idGBC.gridx = 0;
        idGBC.gridy = 1;
        idGBC.anchor = GridBagConstraints.EAST;
        JLabel newLabel = new JLabel("New Admin ID:");
        newLabel.setFont(labelFont);
        idUpdatePanel.add(newLabel, idGBC);

        idGBC.gridx = 1;
        idGBC.anchor = GridBagConstraints.WEST;
        newIdField = new JPasswordField(15);
        newIdField.setPreferredSize(fieldDim);
        newIdField.setFont(labelFont);
        idUpdatePanel.add(newIdField, idGBC);

        idGBC.gridx = 0;
        idGBC.gridy = 2;
        idGBC.anchor = GridBagConstraints.EAST;
        JLabel confirmLabel = new JLabel("Confirm New ID:");
        confirmLabel.setFont(labelFont);
        idUpdatePanel.add(confirmLabel, idGBC);

        idGBC.gridx = 1;
        idGBC.anchor = GridBagConstraints.WEST;
        confirmIdField = new JPasswordField(15);
        confirmIdField.setPreferredSize(fieldDim);
        confirmIdField.setFont(labelFont);
        idUpdatePanel.add(confirmIdField, idGBC);
        
        idGBC.gridx = 0;
        idGBC.gridy = 3;
        idGBC.gridwidth = 2;
        idGBC.anchor = GridBagConstraints.CENTER;
        idGBC.fill = GridBagConstraints.NONE;
        idGBC.insets = new Insets(20, 15, 10, 15);
        updateIdButton = new JButton("Update Admin ID");
        updateIdButton.setFont(buttonFont);
        updateIdButton.setBackground(primaryColor);
        updateIdButton.setForeground(Color.WHITE);
        updateIdButton.setPreferredSize(new Dimension(220, 45));
        updateIdButton.setFocusPainted(false);
        updateIdButton.addActionListener(this);
        idUpdatePanel.add(updateIdButton, idGBC);
        
        centerPanel.add(idUpdatePanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 10, 20, 10);
        centerPanel.add(new JSeparator(), gbc);

        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(20, 10, 20, 10);
        
        resetKioskButton = new JButton("Reset Kiosk to Factory Defaults");
        resetKioskButton.setFont(buttonFont);
        resetKioskButton.setPreferredSize(new Dimension(300, 45));
        resetKioskButton.setBackground(dangerColor);
        resetKioskButton.setForeground(Color.WHITE);
        resetKioskButton.setFocusPainted(false);
        resetKioskButton.addActionListener(this);
        centerPanel.add(resetKioskButton, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 30, 30, 10);
        
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(150, 45));
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        centerPanel.add(backButton, gbc);
        
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
                    thisKiosk.setAdminID(9999);
                    thisKiosk.updatePickUpAndStrungCount();

                    DataBaseManager dbManager = new DataBaseManager();
                    dbManager.resetKiosk();

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