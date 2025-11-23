package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.KioskLandingPage;

public class TennisStringerPage extends JFrame implements ActionListener {
    private String userName;

    private TennisStringer thisStringer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton stringRackets;
    private JButton addPlayer;
    private JButton removePlayer;
    private JButton logOutButton;

    private Font welcomeFont = new Font("SansSerif", Font.BOLD, 36);
    private Font instructionFont = new Font("SansSerif", Font.PLAIN, 20);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 20);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color secondaryColor = new Color(40, 167, 69);
    private Color dangerColor = new Color(220, 53, 69);

    public TennisStringerPage(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;
        this.userName = thisStringer.getStringerName();

        this.setTitle("Stringer Home Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);
        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(800, 600)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!");
        welcomeLabel.setFont(welcomeFont);
        welcomeLabel.setForeground(primaryColor);
        centerPanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        JLabel instructionLabel = new JLabel("Please select an action from the menu below.");
        instructionLabel.setFont(instructionFont);
        centerPanel.add(instructionLabel, gbc);
        
        Dimension buttonSize = new Dimension(350, 80);
        int gridGap = 40; 
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(gridGap, 20, 10, 20);
        stringRackets = new JButton("Select Rackets to String");
        stringRackets.setPreferredSize(buttonSize);
        stringRackets.setFont(buttonFont);
        stringRackets.setBackground(secondaryColor);
        stringRackets.setForeground(Color.WHITE);
        stringRackets.setFocusPainted(false);
        stringRackets.addActionListener(this);
        centerPanel.add(stringRackets, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        addPlayer = new JButton("Add a Player to Your Roster");
        addPlayer.setPreferredSize(buttonSize);
        addPlayer.setFont(buttonFont);
        addPlayer.setBackground(primaryColor);
        addPlayer.setForeground(Color.WHITE);
        addPlayer.setFocusPainted(false);
        addPlayer.addActionListener(this);
        centerPanel.add(addPlayer, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, gridGap, 20);
        removePlayer = new JButton("Remove Player from Roster");
        removePlayer.setPreferredSize(buttonSize);
        removePlayer.setFont(buttonFont);
        removePlayer.setBackground(dangerColor);
        removePlayer.setForeground(Color.WHITE);
        removePlayer.setFocusPainted(false);
        removePlayer.addActionListener(this);
        centerPanel.add(removePlayer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize(buttonSize);
        logOutButton.setFont(buttonFont);
        logOutButton.setBackground(new Color(150, 150, 150));
        logOutButton.setForeground(Color.WHITE);
        logOutButton.setFocusPainted(false);
        logOutButton.addActionListener(this);
        centerPanel.add(logOutButton, gbc);

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
        if(e.getSource() == stringRackets) {
            int racketsToString = 0;

            for(TennisPlayer tp: thisStringer.getPlayers()){
                racketsToString += tp.getNumberOfRacketsToString();
            }

            if (racketsToString == 0) {
                JOptionPane.showMessageDialog(this, "There are no rackets that need stringing!");
            } else {
                new StringRackets(thisStringer, thisKiosk);
                this.dispose();
            }
        }

        if(e.getSource() == addPlayer) {
            new AddPlayerToStringer(thisStringer, thisKiosk);
            this.dispose();
        }

        if(e.getSource() == removePlayer) {
            new RemovePlayer(thisStringer, thisKiosk);
            this.dispose();
        } 

        if(e.getSource() == logOutButton) {
            new KioskLandingPage(thisKiosk);
            this.dispose();
        }
    }
}