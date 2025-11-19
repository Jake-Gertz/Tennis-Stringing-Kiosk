package tennis.stringing.kiosk.Tennis_Kiosk_GUI;

import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User.TennisKioskAdminPage;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User.TennisKioskUserPage;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User.TennisStringerPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO update java doc here
/**
 * The TennisKiosk.Tennis_Kiosk_GUI.TennisKioskGUI class is the driver class of the
 * TennisKiosk.TennisKiosk package. This GUI creates the interactable
 * layer of the tennis kiosk, manages when to add different 
 * objects to the TennisKiosk.TennisKiosk, tracks the current state of the
 * tennis kiosk, and reports useful information to either a 
 * normal user, admin user, or stringer!
 * 
 * @author Jake Gertz
 * @date 11/14/2025
 * @version 1.0
 */
public class KioskLandingPage extends JFrame  implements ActionListener{
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton submissionButton;
    private JTextField enterUserID;

    private TennisKiosk tennisKiosk;

    private final int USER_ID_LENGTH = 4;

    public KioskLandingPage() {
        //TODO update to read from file (will require updating the TennisKiosk.TennisKiosk class)
        tennisKiosk = new TennisKiosk();
        TennisStringer stringer = new TennisStringer();
        TennisPlayer tennisPlayer = new TennisPlayer("Jake", "Gertz");
        stringer.addPlayer(tennisPlayer);
        tennisKiosk.addStringer(stringer);

        this.setTitle("Kiosk Landing Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("User ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        enterUserID = new JTextField(USER_ID_LENGTH);
        this.add(enterUserID, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        submissionButton = new JButton("Submit");
        submissionButton.addActionListener(this);
        this.add(submissionButton, gbc);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

     public KioskLandingPage(TennisKiosk thisKiosk) {
        this.tennisKiosk = thisKiosk;

        this.setTitle("Kiosk Landing Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("User ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        enterUserID = new JTextField(USER_ID_LENGTH);
        this.add(enterUserID, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        submissionButton = new JButton("Submit");
        submissionButton.addActionListener(this);
        this.add(submissionButton, gbc);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public TennisKiosk getKiosk() {
        return tennisKiosk;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submissionButton) {
            int userID = 0;

            try {
                userID = Integer.parseInt(enterUserID.getText());
            } catch (NumberFormatException nfe) {
                userID = 0;
            }
            if (userID == 0) {
                enterUserID.setText("");
                JOptionPane.showMessageDialog(this,"Invalid user name or user ID please try again");
                return;
            } else if (userID == tennisKiosk.getAdminID()) {
                new TennisKioskAdminPage(tennisKiosk);
                this.dispose();
                return;
            } else {
                for (TennisStringer ts: tennisKiosk.getStringers()) {
                    for (TennisPlayer tp: ts.getPlayers()) {
                        if(tp.getUserID() == userID) {
                            new TennisKioskUserPage(tp, tennisKiosk);
                            this.dispose();
                            return;
                        }
                    }
                }
                for(TennisStringer ts: tennisKiosk.getStringers()) {
                    if (ts.getUserID() == userID) {
                        new TennisStringerPage(ts, tennisKiosk);
                        this.dispose();
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Could not find user with ID: " + userID + "\n Please enter a valid ID and try again");
        }
    }
}
