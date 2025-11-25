package tennis.stringing.kiosk.Tennis_Kiosk_GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.Admin_User.TennisKioskAdminPage;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User.TennisKioskUserPage;
import tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User.TennisStringerPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The KioskLandingPage class is the initial screen for the
 * Tennis Kiosk application. It provides the input field for a user to enter their ID
 * to be routed to the appropriate Stringer, Player, or Admin page.
 * 
 * @author Jake Gertz
 * @date 11/14/2025
 * @version 1.0 
 */
public class KioskLandingPage extends JFrame implements ActionListener{
    
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton submissionButton;
    private JTextField enterUserID;

    private TennisKiosk tennisKiosk;

    private final int USER_ID_LENGTH = 11;

    private Font titleFont = new Font("SansSerif", Font.BOLD, 30); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 18); 
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18); 
    
    private Color backgroundColor = new Color(240, 248, 255); 
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(0, 123, 255);

    public KioskLandingPage(TennisKiosk thisKiosk) {
        this.tennisKiosk = thisKiosk;
        
        initializeGUI();
    }

    public KioskLandingPage() {
        this(new TennisKiosk());
    }
    
    /**
     * Initializes and styles the graphical user interface.
     */
    private void initializeGUI() {
        this.setTitle("Kiosk Landing Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor);
        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(750, 350)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 30, 10); 
        
        JLabel titleLabel = new JLabel("Welcome to the Tennis Stringing Kiosk");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        centerPanel.add(titleLabel, gbc);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        inputPanel.setBackground(panelColor);
        
        JLabel idLabel = new JLabel("Enter User ID:"); 
        idLabel.setFont(labelFont);
        inputPanel.add(idLabel);

        enterUserID = new JTextField(USER_ID_LENGTH);
        enterUserID.setFont(labelFont);
        enterUserID.setPreferredSize(new Dimension(280, 35)); 
        inputPanel.add(enterUserID);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 15, 15, 15);
        centerPanel.add(inputPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40, 10, 10, 10);
        
        submissionButton = new JButton("Submit");
        submissionButton.addActionListener(this);
        submissionButton.setFont(buttonFont);
        submissionButton.setPreferredSize(new Dimension(150, 45));
        submissionButton.setBackground(primaryColor);
        submissionButton.setForeground(Color.WHITE);
        submissionButton.setFocusPainted(false);
        centerPanel.add(submissionButton, gbc);

        GridBagConstraints wrapperGBC = new GridBagConstraints();
        wrapperGBC.weightx = 1.0; 
        wrapperGBC.weighty = 1.0; 
        wrapperGBC.anchor = GridBagConstraints.CENTER;
        this.add(centerPanel, wrapperGBC);
        
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
            String idText = enterUserID.getText().trim();

            try {
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this,"Please enter a User ID.");
                    return;
                }
                userID = Integer.parseInt(idText);
            } catch (NumberFormatException nfe) {
                userID = 0;
            }
            
            if (userID == 0) {
                enterUserID.setText("");
                JOptionPane.showMessageDialog(this,"Invalid user ID. Please try again.");
                return;
            } 
            
            if (userID == tennisKiosk.getAdminID()) {
                new TennisKioskAdminPage(tennisKiosk);
                this.dispose();
                return;
            } 
            
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

            JOptionPane.showMessageDialog(this, "Could not find user with ID: " + userID + "\n Please enter a valid ID and try again");
            enterUserID.setText("");
        }
    }
}