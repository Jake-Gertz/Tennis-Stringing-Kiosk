package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;

import java.awt.*;
import javax.swing.border.TitledBorder;

public class PickUpRacket extends JFrame implements ActionListener {

    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton backButton;
    private JButton submitButton;
    private JCheckBox[] checkBoxes;
    private LinkedList<TennisRacket> racketsToPickUp;
    
    private Font displayFont = new Font("Consolas", Font.PLAIN, 16);
    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color secondaryColor = new Color(108, 117, 125);

    public PickUpRacket(TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisPlayer = thisPlayer;
        this.thisKiosk = thisKiosk;

        this.racketsToPickUp = thisPlayer.getRacketsToPickUp();
        this.checkBoxes = new JCheckBox[racketsToPickUp.size()];

        this.setTitle("Pick Up Rackets");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        
        this.getContentPane().setBackground(backgroundColor);
        
        this.setLayout(new GridBagLayout()); 

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(850, 750)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        
        JLabel titleLabel = new JLabel("Select Rackets to Pick Up");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(40, 40, 40)); 
        centerPanel.add(titleLabel, gbc);

        JPanel mainListPanel = new JPanel(new GridLayout(0, 1, 15, 15)); 
        mainListPanel.setBackground(panelColor);

        if (racketsToPickUp.isEmpty()) {
            JLabel emptyLabel = new JLabel("You have no rackets to pick up.");
            emptyLabel.setFont(displayFont);
            mainListPanel.add(emptyLabel);
        } else {
            for (int i = 0; i < racketsToPickUp.size(); i++) {
                TennisRacket racket = racketsToPickUp.get(i);

                JPanel racketPanel = new JPanel(new BorderLayout(20, 20)); 
                racketPanel.setBackground(panelColor);
                racketPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1), 
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));

                JCheckBox checkBox = new JCheckBox();
                checkBoxes[i] = checkBox;
                checkBox.setBackground(panelColor);
                racketPanel.add(checkBox, BorderLayout.WEST);

                JTextArea racketInfo = new JTextArea(racket.toString());
                racketInfo.setEditable(false);
                racketInfo.setFont(displayFont);
                racketInfo.setBackground(panelColor);
                racketInfo.setWrapStyleWord(true);
                racketInfo.setLineWrap(true);
                racketPanel.add(racketInfo, BorderLayout.CENTER);

                mainListPanel.add(racketPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainListPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Rackets Ready for Collection", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.PLAIN, 14), new Color(80, 80, 80)
        ));

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(scrollPane, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout()); 
        buttonPanel.setBackground(panelColor);
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.insets = new Insets(20, 25, 20, 25);
        
        Dimension buttonSize = new Dimension(260, 50);

        backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonGBC.gridx = 0;
        buttonPanel.add(backButton, buttonGBC);
        
        submitButton = new JButton("Pick Up Selected Rackets");
        submitButton.setPreferredSize(buttonSize);
        submitButton.setFont(buttonFont);
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);
        buttonGBC.gridx = 1;
        buttonPanel.add(submitButton, buttonGBC);


        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
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
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();

        } else if (e.getSource() == submitButton) {
            int pickedUpCount = 0;
            
            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    TennisRacket racketToPickUp = this.racketsToPickUp.get(i);
                    
                    thisPlayer.pickUpRacket(racketToPickUp);
                    pickedUpCount++;
                }
            }

            if (pickedUpCount > 0) {
                JOptionPane.showMessageDialog(this, pickedUpCount + " racket(s) picked up successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No rackets were selected.");
            }
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();
        }
    }
}