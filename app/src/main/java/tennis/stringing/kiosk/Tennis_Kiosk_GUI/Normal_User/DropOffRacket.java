package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisRacketBrand;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisStringBrand;

public class DropOffRacket extends JFrame implements ActionListener {
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private final int NUMBER_OF_SUPORTED_BRANDS = 9;
    private LinkedList<TennisString> availableString;

    private JButton submitButton;
    private JButton exitButton;
    private JButton selectedRacketButton;
    private JButton selectedMainStringButton;
    private JButton selectedCrossStringButton;
    private JButton[] jButtons;
    private JButton[] mainStringOptions;
    private JButton[] crossStringOptions;

    private JTextField mainTension;
    private JTextField crossTension;
    private JTextField enterRacketName;

    private TennisRacketBrand racketBrand;
    private TennisString mainTennisString;
    private TennisString crossTennisString;

    private final int STRING_LAYOUT_COLS = 3;
    private final int MAIN_STRING_START_Y = 6;
    private final int CROSS_STRING_START_Y = 10;


    public DropOffRacket(TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;
        this.thisPlayer = thisPlayer;

        jButtons = new JButton[NUMBER_OF_SUPORTED_BRANDS];
        availableString = thisKiosk.getString();

        this.setTitle("Racket Drop Off");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 8;
        this.add(new JLabel("Enter the brand of racket you're dropping off"), gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton babolotButton = new JButton("BABOLOT");
        babolotButton.addActionListener(this);
        babolotButton.setOpaque(true);
        jButtons[0] = (babolotButton);
        this.add(babolotButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton wilsonButton = new JButton("WILSON");
        wilsonButton.addActionListener(this);
        wilsonButton.setOpaque(true); 
        jButtons[1] = (wilsonButton);
        this.add(wilsonButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton headButton = new JButton("HEAD");
        headButton.addActionListener(this);
        headButton.setOpaque(true); 
        jButtons[2] = (headButton);
        this.add(headButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton yonexButton = new JButton("YONEX");
        yonexButton.addActionListener(this);
        yonexButton.setOpaque(true); 
        jButtons[3] = (yonexButton);
        this.add(yonexButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton tecnifibreButton = new JButton("TECNIFIBRE");
        tecnifibreButton.addActionListener(this);
        tecnifibreButton.setOpaque(true); 
        jButtons[4] = (tecnifibreButton);
        this.add(tecnifibreButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton princeButton = new JButton("PRINCE");
        princeButton.addActionListener(this);
        princeButton.setOpaque(true); 
        jButtons[5] = (princeButton);
        this.add(princeButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton dunlopButton = new JButton("DUNLOP");
        dunlopButton.addActionListener(this);
        dunlopButton.setOpaque(true); 
        jButtons[6] = (dunlopButton);
        this.add(dunlopButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton solincoButton = new JButton("SOLINCO");
        solincoButton.addActionListener(this);
        solincoButton.setOpaque(true); 
        jButtons[7] = (solincoButton);
        this.add(solincoButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton otherButton = new JButton("OTHER");
        otherButton.addActionListener(this);
        otherButton.setOpaque(true); 
        jButtons[8] = (otherButton);
        this.add(otherButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 8;
        this.add(new JLabel("Enter the Racket Model Name:"), gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        enterRacketName = new JTextField();
        enterRacketName.setColumns(20); 
        this.add(enterRacketName, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 8;
        this.add(new JLabel("Pick what string you would like for the mains"), gbc);
        gbc.gridwidth = 1;

        mainStringOptions = new JButton[availableString.size() + 1];
        int stringsInList = 0;

        for(TennisString ts: availableString) {
            int stringButtonX = stringsInList % STRING_LAYOUT_COLS;
            int stringButtonY = MAIN_STRING_START_Y + (stringsInList / STRING_LAYOUT_COLS);

            gbc.gridx = stringButtonX;
            gbc.gridy = stringButtonY;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton newButton = new JButton(ts.toString());
            newButton.addActionListener(this);
            newButton.setOpaque(true);
            mainStringOptions[stringsInList] = (newButton);
            this.add(newButton, gbc);
            stringsInList++;
        }
        
        int stringButtonX = stringsInList % STRING_LAYOUT_COLS;
        int stringButtonY = MAIN_STRING_START_Y + (stringsInList / STRING_LAYOUT_COLS);
        gbc.gridx = stringButtonX;
        gbc.gridy = stringButtonY;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton byoButton = new JButton("Bring Your Own String");
        byoButton.addActionListener(this);
        byoButton.setOpaque(true);
        mainStringOptions[stringsInList] = byoButton;
        this.add(byoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 8;
        this.add(new JLabel("Pick what string you would like for the crosses"), gbc);
        gbc.gridwidth = 1;

        crossStringOptions = new JButton[availableString.size() + 1];
        stringsInList = 0;

        for(TennisString ts: availableString) {
            stringButtonX = stringsInList % STRING_LAYOUT_COLS;
            stringButtonY = CROSS_STRING_START_Y + (stringsInList / STRING_LAYOUT_COLS); 

            gbc.gridx = stringButtonX;
            gbc.gridy = stringButtonY;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton newButton = new JButton(ts.toString());
            newButton.addActionListener(this);
            newButton.setOpaque(true);
            crossStringOptions[stringsInList] = (newButton);
            this.add(newButton, gbc);
            stringsInList++;
        }

        stringButtonX = stringsInList % STRING_LAYOUT_COLS;
        stringButtonY = CROSS_STRING_START_Y + (stringsInList / STRING_LAYOUT_COLS);
        gbc.gridx = stringButtonX;
        gbc.gridy = stringButtonY;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton byoCrossButton = new JButton("Bring Your Own String");
        byoCrossButton.addActionListener(this);
        byoCrossButton.setOpaque(true);
        crossStringOptions[stringsInList] = byoCrossButton;
        this.add(byoCrossButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 8;
        this.add(new JLabel("Enter your rackets main and cross tension"), gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Main Tension: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.CENTER;
        mainTension = new JTextField();
        mainTension.setColumns(5);
        this.add(mainTension, gbc);

        gbc.gridx = 2;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Cross Tension: "), gbc);

        gbc.gridx = 3;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.CENTER;
        crossTension = new JTextField();
        crossTension.setColumns(5);
        this.add(crossTension, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);
        submitButton.setOpaque(true);
        this.add(submitButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 14;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        exitButton.setOpaque(true);
        this.add(exitButton, gbc);

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
            if (racketName == null || racketName.trim().isEmpty()) {
                racketName = "DEFAULT";
            }
            
            int mainStringTension = 0;
            int crossStringTension = 0;

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
        } else {

            for (int i = 0; i < NUMBER_OF_SUPORTED_BRANDS; i++) {
                jButtons[i].setBackground(Color.WHITE);
            } 
            for (int i = 0; i < mainStringOptions.length; i++) {
                mainStringOptions[i].setBackground(Color.WHITE);
            } 
            for (int i = 0; i < crossStringOptions.length; i++) {
                crossStringOptions[i].setBackground(Color.WHITE);
            }
            
            for (int i = 0; i < NUMBER_OF_SUPORTED_BRANDS; i++) {
                if(e.getSource() == jButtons[i]) {
                    selectedRacketButton = jButtons[i];
                    try {

                        switch (i) {
                            case 0: racketBrand = TennisRacketBrand.BABOLAT; break;
                            case 1: racketBrand = TennisRacketBrand.WILSON; break;
                            case 2: racketBrand = TennisRacketBrand.HEAD; break;
                            case 3: racketBrand = TennisRacketBrand.YONEX; break;
                            case 4: racketBrand = TennisRacketBrand.TECNIFIBRE; break;
                            case 5: racketBrand = TennisRacketBrand.PRINCE; break;
                            case 6: racketBrand = TennisRacketBrand.DUNLOP; break;
                            case 7: racketBrand = TennisRacketBrand.SOLINCO; break;
                            case 8: racketBrand = TennisRacketBrand.DEFAULT; break; // Assumes "OTHER" button maps to "DEFAULT" enum
                        }
                    } catch (Exception ex) {
                        System.err.println("Error matching racket brand: " + ex.getMessage());
                    }
                }
            } 
            
            for (int i = 0; i < mainStringOptions.length; i++) {
                if(e.getSource() == mainStringOptions[i]) {
                    selectedMainStringButton = mainStringOptions[i];
                    
                    if (i == mainStringOptions.length - 1) {
                        mainTennisString = new TennisString(TennisStringBrand.DEFAULT, "USER PROVIDED");
                    } else {
                        mainTennisString = availableString.get(i);
                    }
                }
            } 

            for (int i = 0; i < crossStringOptions.length; i++) {
                if(e.getSource() == crossStringOptions[i]) {
                    selectedCrossStringButton = crossStringOptions[i];

                    if (i == crossStringOptions.length - 1) {
                        crossTennisString = new TennisString(TennisStringBrand.DEFAULT, "USER PROVIDED");
                    } else {
                        crossTennisString = availableString.get(i);
                    }
                }
            }
            
            if (selectedRacketButton != null) {
                selectedRacketButton.setBackground(Color.RED);
            }

            if (selectedMainStringButton != null) {
                selectedMainStringButton.setBackground(Color.RED);
            }

            if (selectedCrossStringButton != null) {
                selectedCrossStringButton.setBackground(Color.RED);
            }
        }
    }
}
