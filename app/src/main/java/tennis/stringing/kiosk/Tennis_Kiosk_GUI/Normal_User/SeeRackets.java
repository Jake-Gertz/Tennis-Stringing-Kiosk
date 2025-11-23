package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeeRackets extends JFrame implements ActionListener {
    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton backButton;
    
    private Font displayFont = new Font("Consolas", Font.PLAIN, 16); 
    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font sectionTitleFont = new Font("SansSerif", Font.BOLD, 22);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color secondaryColor = new Color(108, 117, 125);
    private Color accentColor = new Color(0, 150, 136);

    public SeeRackets (TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisPlayer = thisPlayer;
        this.thisKiosk = thisKiosk;

        this.setTitle("Your Rackets");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        
        this.getContentPane().setBackground(backgroundColor);
        
        this.setLayout(new GridBagLayout()); 

        JPanel centerPanel = new JPanel(new GridBagLayout()); 
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(1000, 900)); 
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
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel mainTitleLabel = new JLabel("Your Racket Status");
        mainTitleLabel.setFont(titleFont);
        mainTitleLabel.setForeground(new Color(40, 40, 40)); 
        centerPanel.add(mainTitleLabel, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 25, 0);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(accentColor);
        centerPanel.add(separator, gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel pickUpTitle = new JLabel("Rackets Ready for Pick Up:");
        pickUpTitle.setFont(sectionTitleFont);
        pickUpTitle.setForeground(accentColor);
        centerPanel.add(pickUpTitle, gbc);

        JPanel pickUpPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        pickUpPanel.setBackground(panelColor);
        
        LinkedList<TennisRacket> racketsToPickUp = thisPlayer.getRacketsToPickUp();

        if (racketsToPickUp.isEmpty()) {
            JLabel emptyPickUpLabel = new JLabel("No rackets ready for pick up.");
            emptyPickUpLabel.setFont(displayFont);
            pickUpPanel.add(emptyPickUpLabel);
        } else {
            for (TennisRacket racket : racketsToPickUp) {
                pickUpPanel.add(createRacketInfoArea(racket));
            }
        }
        
        JScrollPane pickUpScrollPane = new JScrollPane(pickUpPanel);
        pickUpScrollPane.setBorder(BorderFactory.createTitledBorder("Rackets Ready"));
        
        gbc.gridy = 3;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(pickUpScrollPane, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.insets = new Insets(30, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel stringingTitle = new JLabel("Rackets Awaiting Stringing:");
        stringingTitle.setFont(sectionTitleFont);
        stringingTitle.setForeground(accentColor);
        centerPanel.add(stringingTitle, gbc);

        JPanel stringingPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        stringingPanel.setBackground(panelColor);
        
        LinkedList<TennisRacket> racketsToString = thisPlayer.getRacketsToString();

        if (racketsToString.isEmpty()) {
            JLabel emptyStringingLabel = new JLabel("No rackets awaiting stringing.");
            emptyStringingLabel.setFont(displayFont);
            stringingPanel.add(emptyStringingLabel);
        } else {
            for (TennisRacket racket : racketsToString) {
                stringingPanel.add(createRacketInfoArea(racket));
            }
        }

        JScrollPane stringingScrollPane = new JScrollPane(stringingPanel);
        stringingScrollPane.setBorder(BorderFactory.createTitledBorder("Rackets in Queue"));
        
        gbc.gridy = 5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(stringingScrollPane, gbc);
        
        gbc.gridy = 6;
        gbc.weighty = 0;
        gbc.insets = new Insets(30, 15, 10, 15);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        
        Dimension buttonSize = new Dimension(220, 50);
        
        backButton = new JButton("Back to Main Menu");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
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
    
   
    private JTextArea createRacketInfoArea(TennisRacket racket) {
        JTextArea racketInfo = new JTextArea(racket.toString());
        racketInfo.setEditable(false);
        racketInfo.setFont(displayFont);
        racketInfo.setBackground(new Color(245, 245, 245));
        racketInfo.setWrapStyleWord(true);
        racketInfo.setLineWrap(true);
        racketInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return racketInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();
        }
    }
}