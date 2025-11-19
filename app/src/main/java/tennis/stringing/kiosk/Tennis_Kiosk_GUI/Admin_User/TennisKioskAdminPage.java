package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User;

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

    public TennisKioskAdminPage (TennisKiosk thisKiosk) {
        this.thisKiosk = thisKiosk;

        this.setTitle("Admin Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 7;
        this.add(new JLabel("Welcome: Admin"), gbc);

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
        addStringer = new JButton("Add Stringer");
        addStringer.addActionListener(this);
        this.add(addStringer, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        addString = new JButton("Add String");
        addString.addActionListener(this);
        this.add(addString, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        updateStringerInfo = new JButton("Update/Delete Stringer Info");
        updateStringerInfo.addActionListener(this);
        this.add(updateStringerInfo, gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        updateAdminInfo = new JButton("Update Admin Info");
        updateAdminInfo.addActionListener(this);
        this.add(updateAdminInfo, gbc);

        gbc.gridx = 8;
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

