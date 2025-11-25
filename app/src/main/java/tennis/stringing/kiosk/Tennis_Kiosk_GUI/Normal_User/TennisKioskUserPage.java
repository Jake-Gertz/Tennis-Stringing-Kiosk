package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.DataBase.DataBaseManager;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.KioskLandingPage;

import java.awt.*;

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
    
    private Font welcomeFont = new Font("SansSerif", Font.BOLD, 36); 
    private Font subTitleFont = new Font("SansSerif", Font.ITALIC, 20);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color secondaryColor = new Color(108, 117, 125);
    private Color logOutColor = new Color(220, 53, 69);

    public TennisKioskUserPage(TennisPlayer tennisPlayer, TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;
        this.thisPlayer = tennisPlayer;
        this.userName = tennisPlayer.getPlayerName();

        DataBaseManager dbManager = new DataBaseManager();
        dbManager.storeKiosk(thisKiosk);

        this.setTitle("Home Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(new GridBagLayout()); 

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(800, 650)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20); 
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!");
        welcomeLabel.setFont(welcomeFont);
        welcomeLabel.setForeground(primaryColor);
        centerPanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        
        JLabel selectLabel = new JLabel("Select an option below to continue.");
        selectLabel.setFont(subTitleFont);
        selectLabel.setForeground(new Color(80, 80, 80));
        centerPanel.add(selectLabel, gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 30, 0);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        centerPanel.add(separator, gbc);
        
        gbc.gridwidth = 2; 
        gbc.gridheight = 1;
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        
        Dimension buttonSize = new Dimension(300, 60);

        gbc.gridx = 0;
        gbc.gridy = 3;
        seeMyRackets = new JButton("See My Rackets Status");
        seeMyRackets.setPreferredSize(buttonSize);
        seeMyRackets.setFont(buttonFont);
        seeMyRackets.setBackground(secondaryColor);
        seeMyRackets.setForeground(Color.WHITE);
        seeMyRackets.setFocusPainted(false);
        seeMyRackets.addActionListener(this);
        centerPanel.add(seeMyRackets, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        dropOffRacket = new JButton("Drop Off Racket To String");
        dropOffRacket.setPreferredSize(buttonSize);
        dropOffRacket.setFont(buttonFont);
        dropOffRacket.setBackground(primaryColor);
        dropOffRacket.setForeground(Color.WHITE);
        dropOffRacket.setFocusPainted(false);
        dropOffRacket.addActionListener(this);
        centerPanel.add(dropOffRacket, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pickUpRacket = new JButton("Pick Up Strung Racket");
        pickUpRacket.setPreferredSize(buttonSize);
        pickUpRacket.setFont(buttonFont);
        pickUpRacket.setBackground(primaryColor);
        pickUpRacket.setForeground(Color.WHITE);
        pickUpRacket.setFocusPainted(false);
        pickUpRacket.addActionListener(this);
        centerPanel.add(pickUpRacket, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        seeStringList = new JButton("See Available String Options");
        seeStringList.setPreferredSize(buttonSize);
        seeStringList.setFont(buttonFont);
        seeStringList.setBackground(secondaryColor);
        seeStringList.setForeground(Color.WHITE);
        seeStringList.setFocusPainted(false);
        seeStringList.addActionListener(this);
        centerPanel.add(seeStringList, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(40, 30, 10, 30);
        
        logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize(buttonSize);
        logOutButton.setFont(buttonFont);
        logOutButton.setBackground(logOutColor);
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