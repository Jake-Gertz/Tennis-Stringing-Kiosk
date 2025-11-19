package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;

import java.awt.*;

public class StringRackets extends JFrame implements ActionListener {

    private TennisStringer thisStringer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton backButton;
    private JButton submitButton;
    private JCheckBox[] checkBoxes;
    
    private LinkedList<TennisRacket> allRacketsToString; 
    private LinkedList<TennisPlayer> racketOwners;

    private Font displayFont = new Font("Monospaced", Font.PLAIN, 12);

    public StringRackets(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;

        this.allRacketsToString = new LinkedList<>();
        this.racketOwners = new LinkedList<>();

        LinkedList<TennisPlayer> assignedPlayers = thisStringer.getPlayers();
        
        for (TennisPlayer player : assignedPlayers) {
            for (TennisRacket racket : player.getRacketsToString()) {
                allRacketsToString.add(racket);
                racketOwners.add(player);
            }
        }
        
        this.checkBoxes = new JCheckBox[allRacketsToString.size()];

        this.setTitle("String Rackets");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        this.add(new JLabel("Select Rackets You Have Strung"), gbc);

        JPanel mainListPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        if (allRacketsToString.isEmpty()) {
            mainListPanel.add(new JLabel("You have no rackets assigned to string."));
        } else {
            for (int i = 0; i < allRacketsToString.size(); i++) {
                TennisRacket racket = allRacketsToString.get(i);
                TennisPlayer owner = racketOwners.get(i);

                JPanel racketPanel = new JPanel(new BorderLayout(10, 10));
                racketPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                JCheckBox checkBox = new JCheckBox();
                checkBoxes[i] = checkBox;
                racketPanel.add(checkBox, BorderLayout.WEST);

                String racketInfoText = "Owner: " + owner.getPlayerName() + "\n" +
                                        "Racket: " + racket.getRacketModelName() + "\n" +
                                        "----------------\n" +
                                        racket.stringsToString();

                JTextArea racketInfo = new JTextArea(racketInfoText);
                racketInfo.setEditable(false);
                racketInfo.setFont(displayFont);
                racketPanel.add(racketInfo, BorderLayout.CENTER);

                mainListPanel.add(racketPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainListPanel);
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        submitButton = new JButton("Mark Selected as Strung");
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(buttonPanel, gbc);

        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisStringerPage(thisStringer, thisKiosk);
            this.dispose();

        } else if (e.getSource() == submitButton) {
            int strungCount = 0;
            
            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    TennisRacket racket = this.allRacketsToString.get(i);
                    TennisPlayer owner = this.racketOwners.get(i);
                    
                    racket.setLastStrung(); 

                    owner.strungRacket(racket); 
                    
                    strungCount++;
                }
            }

            if (strungCount > 0) {
                JOptionPane.showMessageDialog(this, strungCount + " racket(s) marked as strung!");
            } else {
                JOptionPane.showMessageDialog(this, "No rackets were selected.");
            }
            new TennisStringerPage(thisStringer, thisKiosk);
            this.dispose();
        }
    }
}
