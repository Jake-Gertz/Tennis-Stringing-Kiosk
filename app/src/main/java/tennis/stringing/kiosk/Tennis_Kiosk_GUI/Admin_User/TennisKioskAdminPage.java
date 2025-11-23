package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.KioskLandingPage;

public class TennisKioskAdminPage extends JFrame implements ActionListener {
    
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton addStringer;
    private JButton addString;
    private JButton updateStringerInfo;
    private JButton updateAdminInfo;
    private JButton logOutButton;

    private Font titleFont = new Font("SansSerif", Font.BOLD, 36); 
    private Font subtitleFont = new Font("SansSerif", Font.ITALIC, 20);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);
    private Color logoutColor = new Color(220, 53, 69);

    public TennisKioskAdminPage (TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Admin Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(800, 600)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(40, 40, 40, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel titleLabel = new JLabel("Welcome: Admin", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        centerPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 15, 40, 15);
        JLabel subtitleLabel = new JLabel("Select an option", SwingConstants.CENTER);
        subtitleLabel.setFont(subtitleFont);
        subtitleLabel.setForeground(Color.DARK_GRAY);
        centerPanel.add(subtitleLabel, gbc);

        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.gridwidth = 1;
        gbc.weightx = 1.0; 
        gbc.weighty = 0.0;
        Dimension buttonSize = new Dimension(280, 60);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        addStringer = new JButton("Add Stringer");
        addStringer.addActionListener(this);
        addStringer.setFont(buttonFont);
        addStringer.setPreferredSize(buttonSize);
        addStringer.setBackground(new Color(20, 200, 20));
        addStringer.setForeground(Color.WHITE);
        centerPanel.add(addStringer, gbc);

        gbc.gridx = 1;
        addString = new JButton("Add String to Inventory");
        addString.addActionListener(this);
        addString.setFont(buttonFont);
        addString.setPreferredSize(buttonSize);
        addString.setBackground(new Color(34, 139, 34));
        addString.setForeground(Color.WHITE);
        centerPanel.add(addString, gbc);
                
        gbc.gridx = 0;
        gbc.gridy = 3;
        updateStringerInfo = new JButton("Update/Delete Stringer Info");
        updateStringerInfo.addActionListener(this);
        updateStringerInfo.setFont(buttonFont);
        updateStringerInfo.setPreferredSize(buttonSize);
        updateStringerInfo.setBackground(new Color(255, 165, 0));
        updateStringerInfo.setForeground(Color.BLACK);
        centerPanel.add(updateStringerInfo, gbc);

        gbc.gridx = 1;
        updateAdminInfo = new JButton("Update Admin Info/Reset Kiosk");
        updateAdminInfo.addActionListener(this);
        updateAdminInfo.setFont(buttonFont);
        updateAdminInfo.setPreferredSize(buttonSize);
        updateAdminInfo.setBackground(new Color(255, 140, 0));
        updateAdminInfo.setForeground(Color.BLACK);
        centerPanel.add(updateAdminInfo, gbc);

        gbc.gridy = 4;
        gbc.weighty = 1.0;
        centerPanel.add(Box.createVerticalStrut(0), gbc); 

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(30, 20, 5, 20);

        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(this);
        logOutButton.setFont(buttonFont);
        logOutButton.setPreferredSize(new Dimension(250, 50));
        logOutButton.setBackground(logoutColor);
        logOutButton.setForeground(Color.WHITE);
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
        if (e.getSource() == addStringer) {
            new AddStringer(thisKiosk);
            this.dispose();
            return;
        }

        if(e.getSource() == addString) {
            new AddStringToKiosk(thisKiosk);
            this.dispose();
            return;
        }

        if(e.getSource() == updateStringerInfo) {
            new UpdateStringerInfo(thisKiosk);
            this.dispose();
            return;
        }

        if(e.getSource() == updateAdminInfo) {
            new UpdateAdminInfo(thisKiosk);
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