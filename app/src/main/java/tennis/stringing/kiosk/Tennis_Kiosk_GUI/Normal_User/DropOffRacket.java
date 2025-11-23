package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisRacketBrand;

public class DropOffRacket extends JFrame implements ActionListener {
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private TennisString[] availableStringArray;

    private JButton submitButton;
    private JButton exitButton;

    private JComboBox<TennisRacketBrand> brandComboBox;
    private JComboBox<TennisString> mainStringComboBox;
    private JComboBox<TennisString> crossStringComboBox;

    private JTextField mainTension;
    private JTextField crossTension;
    private JTextField enterRacketName;

    private TennisRacketBrand racketBrand;
    private TennisString mainTennisString;
    private TennisString crossTennisString;

    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 16);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color secondaryColor = new Color(220, 53, 69);

    public DropOffRacket(TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;
        this.thisPlayer = thisPlayer;

        this.availableStringArray = thisKiosk.getString();

        this.setTitle("Racket Drop Off");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(800, 750)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel titleLabel = new JLabel("Racket Drop Off");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(40, 40, 40)); 
        centerPanel.add(titleLabel, gbc);
        
        JPanel racketInfoPanel = new JPanel(new GridBagLayout());
        racketInfoPanel.setBackground(panelColor);
        racketInfoPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "1. Racket Details", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));
        GridBagConstraints infoGBC = new GridBagConstraints();
        infoGBC.insets = new Insets(10, 10, 10, 10);
        infoGBC.fill = GridBagConstraints.HORIZONTAL;

        infoGBC.gridx = 0;
        infoGBC.gridy = 0;
        infoGBC.anchor = GridBagConstraints.EAST;
        
        JLabel brandLabel = new JLabel("Racket Brand:");
        brandLabel.setFont(labelFont);
        racketInfoPanel.add(brandLabel, infoGBC);
        
        infoGBC.gridx = 1;
        infoGBC.anchor = GridBagConstraints.WEST;
        brandComboBox = new JComboBox<TennisRacketBrand>(TennisRacketBrand.values());
        brandComboBox.setFont(labelFont);
        brandComboBox.setPreferredSize(new Dimension(200, 30));
        racketInfoPanel.add(brandComboBox, infoGBC);

        infoGBC.gridx = 0;
        infoGBC.gridy = 1;
        infoGBC.anchor = GridBagConstraints.EAST;
        
        JLabel modelLabel = new JLabel("Racket Model Name:");
        modelLabel.setFont(labelFont);
        racketInfoPanel.add(modelLabel, infoGBC);
        
        infoGBC.gridx = 1;
        infoGBC.anchor = GridBagConstraints.WEST;
        enterRacketName = new JTextField();
        enterRacketName.setColumns(20);
        enterRacketName.setFont(labelFont);
        racketInfoPanel.add(enterRacketName, infoGBC);
        
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(racketInfoPanel, gbc);
        
        JPanel stringPanel = new JPanel(new GridBagLayout());
        stringPanel.setBackground(panelColor);
        stringPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "2. String Selection", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));
        GridBagConstraints stringGBC = new GridBagConstraints();
        stringGBC.insets = new Insets(10, 10, 10, 10);
        stringGBC.fill = GridBagConstraints.HORIZONTAL;

        stringGBC.gridx = 0;
        stringGBC.gridy = 0;
        stringGBC.anchor = GridBagConstraints.EAST;
        
        JLabel mainStringLabel = new JLabel("Pick Main String:");
        mainStringLabel.setFont(labelFont);
        stringPanel.add(mainStringLabel, stringGBC);

        stringGBC.gridx = 1;
        stringGBC.anchor = GridBagConstraints.WEST;
        mainStringComboBox = new JComboBox<TennisString>(availableStringArray);
        mainStringComboBox.setFont(labelFont);
        mainStringComboBox.setPreferredSize(new Dimension(300, 30));
        stringPanel.add(mainStringComboBox, stringGBC);

        stringGBC.gridx = 0;
        stringGBC.gridy = 1;
        stringGBC.anchor = GridBagConstraints.EAST;
        
        JLabel crossStringLabel = new JLabel("Pick Cross String:");
        crossStringLabel.setFont(labelFont);
        stringPanel.add(crossStringLabel, stringGBC);

        stringGBC.gridx = 1;
        stringGBC.anchor = GridBagConstraints.WEST;
        crossStringComboBox = new JComboBox<TennisString>(availableStringArray);
        crossStringComboBox.setFont(labelFont);
        crossStringComboBox.setPreferredSize(new Dimension(300, 30));
        stringPanel.add(crossStringComboBox, stringGBC);

        gbc.gridy = 2;
        centerPanel.add(stringPanel, gbc);
        
        JPanel tensionPanel = new JPanel(new GridBagLayout());
        tensionPanel.setBackground(panelColor);
        tensionPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "3. Tension Settings", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));
        GridBagConstraints tensionGBC = new GridBagConstraints();
        tensionGBC.insets = new Insets(10, 10, 10, 10);
        
        tensionGBC.gridx = 0;
        tensionGBC.gridy = 0;
        tensionGBC.gridwidth = 4;
        tensionGBC.anchor = GridBagConstraints.CENTER;
        JLabel tensionInstruction = new JLabel("Enter your rackets main and cross tension (Or 0 for default, 1 for high altitude, 2 for low altitude)");
        tensionInstruction.setFont(labelFont);
        tensionPanel.add(tensionInstruction, tensionGBC);
        tensionGBC.gridwidth = 1;

        tensionGBC.gridx = 0;
        tensionGBC.gridy = 1;
        tensionGBC.anchor = GridBagConstraints.EAST;
        
        JLabel mainTensionLabel = new JLabel("Main Tension: ");
        mainTensionLabel.setFont(labelFont);
        tensionPanel.add(mainTensionLabel, tensionGBC);

        tensionGBC.gridx = 1;
        tensionGBC.anchor = GridBagConstraints.WEST;
        mainTension = new JTextField();
        mainTension.setColumns(5);
        mainTension.setFont(labelFont);
        tensionPanel.add(mainTension, tensionGBC);

        tensionGBC.gridx = 2;
        tensionGBC.anchor = GridBagConstraints.EAST;
        
        JLabel crossTensionLabel = new JLabel("Cross Tension: ");
        crossTensionLabel.setFont(labelFont);
        tensionPanel.add(crossTensionLabel, tensionGBC);

        tensionGBC.gridx = 3;
        tensionGBC.anchor = GridBagConstraints.WEST;
        crossTension = new JTextField();
        crossTension.setColumns(5);
        crossTension.setFont(labelFont);
        tensionPanel.add(crossTension, tensionGBC);
        
        gbc.gridy = 3;
        centerPanel.add(tensionPanel, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(panelColor);
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.insets = new Insets(20, 30, 20, 30);
        
        Dimension buttonSize = new Dimension(220, 50);

        submitButton = new JButton("SUBMIT");
        submitButton.setPreferredSize(buttonSize);
        submitButton.setFont(buttonFont);
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);
        buttonGBC.gridx = 0;
        buttonPanel.add(submitButton, buttonGBC);

        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(buttonSize);
        exitButton.setFont(buttonFont);
        exitButton.setBackground(secondaryColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(this);
        buttonGBC.gridx = 1;
        buttonPanel.add(exitButton, buttonGBC);

        gbc.gridy = 4;
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
        if (e.getSource() == exitButton) {
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();
            
        } else if(e.getSource() == submitButton) {
            String racketName = enterRacketName.getText();
            int mainStringTension = 0;
            int crossStringTension = 0;
            racketBrand = (TennisRacketBrand) brandComboBox.getSelectedItem();
            mainTennisString = (TennisString) mainStringComboBox.getSelectedItem();
            crossTennisString = (TennisString) crossStringComboBox.getSelectedItem();

            if (racketName == null || racketName.trim().isEmpty()) {
                racketName = "DEFAULT";
            }

            try {
                mainStringTension = Integer.parseInt(mainTension.getText());
                crossStringTension = Integer.parseInt(crossTension.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Invalid Input for Main Tension or Cross Tension!\n Please enter a valid number and try again");
                return;
            }

            if(racketBrand == null || mainTennisString == null || crossTennisString == null) {
                JOptionPane.showMessageDialog(this, "No racket brand or strings were selected!\n Please select a racket brand and strings for mains and crosses");
            } else {
                
                TennisRacket newRacket = new TennisRacket(racketBrand, mainTennisString, crossTennisString, mainStringTension, crossStringTension);
                
                newRacket.setRacketModelName(racketName); 
                
                thisPlayer.addRacketToString(newRacket);
                new TennisKioskUserPage(thisPlayer, thisKiosk);
                this.dispose();
            }
        }
    }
}