package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

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
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.KioskLandingPage;

public class TennisKioskUserPage extends JFrame implements ActionListener {
    private String userName;

    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton seeMyRackets;
    private JButton dropOffRacket;
    private JButton pickUpRacket;
    private JButton seeStringList;
    private JButton logOutButton;

    public TennisKioskUserPage(TennisPlayer tennisPlayer, TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;
        this.thisPlayer = tennisPlayer;
        this.userName = tennisPlayer.getPlayerName();

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
        seeMyRackets = new JButton("See my strung and unstrung rackets");
        seeMyRackets.addActionListener(this);
        this.add(seeMyRackets, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        dropOffRacket = new JButton("Drop Off Racket To String");
        dropOffRacket.addActionListener(this);
        this.add(dropOffRacket, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        pickUpRacket = new JButton("Pick Up Strung Racket");
        pickUpRacket.addActionListener(this);
        this.add(pickUpRacket, gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2; 
        seeStringList = new JButton("See Available String Options");
        seeStringList.addActionListener(this);
        this.add(seeStringList, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
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
        if (e.getSource() == seeMyRackets) {
            if (thisPlayer.getNumberOfRacketsToString() == 0 && thisPlayer.getNumberOfRacketsToPickUp() == 0) {
                JOptionPane.showMessageDialog(this, "You have no rackets to pick up or that still need to be strung!");
            } else {
                new SeeRackets(thisPlayer, thisKiosk);
                this.dispose();
                return;
            }
        }

        if(e.getSource() == dropOffRacket) {
            new DropOffRacket(thisPlayer, thisKiosk);
            this.dispose();
            return;
        }

        if(e.getSource() == pickUpRacket) {
            if(thisPlayer.getNumberOfRacketsToPickUp() == 0) {
                JOptionPane.showMessageDialog(this, "You have no rackets to pick up!");
            } else {
                new PickUpRacket(thisPlayer, thisKiosk);
                this.dispose();
                return;
            }
        }

        if(e.getSource() == seeStringList) {
            new SeeStringList(thisPlayer, thisKiosk);
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

