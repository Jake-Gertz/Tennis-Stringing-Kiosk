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
    private Font displayFont = new Font("Monospaced", Font.PLAIN, 12);

    public SeeRackets (TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisPlayer = thisPlayer;
        this.thisKiosk = thisKiosk;

        this.setTitle("Your Rackets");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Rackets to pick up"), gbc);

        JPanel pickUpPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        LinkedList<TennisRacket> racketsToPickUp = thisPlayer.getRacketsToPickUp();

        if (racketsToPickUp.isEmpty()) {
            pickUpPanel.add(new JLabel("No rackets ready for pick up."));
        } else {
            for (TennisRacket racket : racketsToPickUp) {
                JTextArea racketInfo = new JTextArea(racket.toString());
                racketInfo.setEditable(false);
                racketInfo.setFont(displayFont);
                racketInfo.setWrapStyleWord(true);
                racketInfo.setLineWrap(true);
                racketInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                pickUpPanel.add(racketInfo);
            }
        }
        
        JScrollPane pickUpScrollPane = new JScrollPane(pickUpPanel);
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(pickUpScrollPane, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Rackets awaiting stringing"), gbc);

        JPanel stringingPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        LinkedList<TennisRacket> racketsToString = thisPlayer.getRacketsToString();

        if (racketsToString.isEmpty()) {
            stringingPanel.add(new JLabel("No rackets awaiting stringing."));
        } else {
            for (TennisRacket racket : racketsToString) {
                JTextArea racketInfo = new JTextArea(racket.toString());
                racketInfo.setEditable(false);
                racketInfo.setFont(displayFont);
                racketInfo.setWrapStyleWord(true);
                racketInfo.setLineWrap(true);
                racketInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                stringingPanel.add(racketInfo);
            }
        }

        JScrollPane stringingScrollPane = new JScrollPane(stringingPanel);
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(stringingScrollPane, gbc);
        
        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        this.add(backButton, gbc);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();
        }
    }
}
