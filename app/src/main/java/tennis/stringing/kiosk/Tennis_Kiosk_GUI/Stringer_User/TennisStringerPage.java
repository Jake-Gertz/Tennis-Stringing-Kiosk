package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

    public TennisStringerPage(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;
        this.userName = thisStringer.getStringerName();

        this.setTitle("Home Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 7;
        this.add(new JLabel("Welcome: " + userName), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 7;
        this.add(new JLabel("Select an option"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        stringRackets = new JButton("Select Rackets to String");
        stringRackets.addActionListener(this);
        this.add(stringRackets, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        addPlayer = new JButton("Add a player to your list");
        addPlayer.addActionListener(this);
        this.add(addPlayer, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        removePlayer = new JButton("Remove player from your list");
        removePlayer.addActionListener(this);
        this.add(removePlayer, gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2; 
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(this);
        this.add(logOutButton, gbc);

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

            if  (racketsToString == 0) {
                JOptionPane.showMessageDialog(this, "There are no rackets that need stringing!");
            } else {
                new StringRackets(thisStringer, thisKiosk);
                this.dispose();
                return;
            }
        }

        if(e.getSource() == addPlayer) {
            new AddPlayerToStringer(thisStringer, thisKiosk);
            return;
        }

        if(e.getSource() == removePlayer) {
            new RemovePlayer(thisStringer, thisKiosk);
            this.dispose();
            return;
        } 

        if(e.getSource() == logOutButton) {
            new KioskLandingPage(thisKiosk);
            this.dispose();
            return;
        }
    }
}

