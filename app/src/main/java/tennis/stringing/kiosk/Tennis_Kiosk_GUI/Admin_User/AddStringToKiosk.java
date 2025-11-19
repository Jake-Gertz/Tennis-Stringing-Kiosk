package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisStringBrand;

import java.awt.*;

public class AddStringToKiosk extends JFrame implements ActionListener {

    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JComboBox<TennisStringBrand> brandComboBox;
    private JTextField stringNameField;
    private JTextField quantityField;
    private JButton submitButton;
    private JButton backButton;

    public AddStringToKiosk(TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Add New String to Kiosk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Add New String to Inventory"), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("String Brand:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        brandComboBox = new JComboBox<>(TennisStringBrand.values());
        this.add(brandComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("String Model Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        stringNameField = new JTextField(15);
        this.add(stringNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(new JLabel("Initial Quantity (ft):"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        quantityField = new JTextField(15);
        this.add(quantityField, gbc);

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
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();

        } else if (e.getSource() == submitButton) {
            TennisStringBrand brand = (TennisStringBrand) brandComboBox.getSelectedItem();
            String name = stringNameField.getText();
            String quantityText = quantityField.getText();

            if (name.trim().isEmpty() || quantityText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (brand == TennisStringBrand.DEFAULT) {
                JOptionPane.showMessageDialog(this, "Error: Please select a valid brand.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int initialQuantity;
            try {
                initialQuantity = Integer.parseInt(quantityText);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Error: Quantity must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (initialQuantity <= 0) {
                 JOptionPane.showMessageDialog(this, "Error: Quantity must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TennisString newString = new TennisString(brand, name);
            
            newString.addLengthInStock(initialQuantity);
            
            thisKiosk.addTennisString(newString);
            
            JOptionPane.showMessageDialog(this, "Success: " + newString.toString() + " (" + initialQuantity + " ft) added.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            new TennisKioskAdminPage(thisKiosk);
            this.dispose();
        }
    }
}