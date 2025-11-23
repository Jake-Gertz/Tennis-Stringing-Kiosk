package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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

    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.BOLD, 14);
    private Font listFont = new Font("Monospaced", Font.PLAIN, 12);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color primaryColor = new Color(40, 167, 69);
    private Color secondaryColor = new Color(108, 117, 125);

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
        this.getContentPane().setBackground(backgroundColor);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("String Queue: Mark Completed Rackets");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(40, 40, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, gbc);

        JPanel mainListContainer = new JPanel(new GridBagLayout());
        mainListContainer.setBackground(panelColor);
        mainListContainer.setBorder(new EmptyBorder(10, 10, 10, 10)); 

        GridBagConstraints listGBC = new GridBagConstraints();
        listGBC.gridx = 0;
        listGBC.weightx = 1.0;
        listGBC.fill = GridBagConstraints.HORIZONTAL;
        listGBC.insets = new Insets(8, 0, 8, 0);

        if (allRacketsToString.isEmpty()) {
            JLabel emptyLabel = new JLabel("You have no rackets currently assigned to string.");
            emptyLabel.setFont(labelFont);
            listGBC.gridy = 0;
            listGBC.anchor = GridBagConstraints.CENTER;
            listGBC.insets = new Insets(50, 0, 50, 0);
            mainListContainer.add(emptyLabel, listGBC);
        } else {
            for (int i = 0; i < allRacketsToString.size(); i++) {
                TennisRacket racket = allRacketsToString.get(i);
                TennisPlayer owner = racketOwners.get(i);

                JPanel racketPanel = new JPanel(new GridBagLayout());
                racketPanel.setBackground(new Color(248, 248, 248));
                racketPanel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200), 1),
                    new EmptyBorder(10, 15, 10, 15)
                ));

                GridBagConstraints innerGBC = new GridBagConstraints();
                innerGBC.insets = new Insets(2, 5, 2, 5);
                innerGBC.fill = GridBagConstraints.HORIZONTAL;
                
                innerGBC.gridx = 0;
                innerGBC.gridy = 0;
                innerGBC.anchor = GridBagConstraints.NORTHWEST;
                innerGBC.weightx = 0;
                JCheckBox checkBox = new JCheckBox();
                checkBoxes[i] = checkBox;
                racketPanel.add(checkBox, innerGBC);
                
                innerGBC.gridx = 1;
                innerGBC.weightx = 1.0;
                
                JLabel headerLabel = new JLabel(
                    "<html><b>Owner:</b> " + owner.getPlayerName() + 
                    " &nbsp;&nbsp;&nbsp;&nbsp; <b>Racket:</b> " + racket.getRacketModelName() + "</html>"
                );
                headerLabel.setFont(labelFont);
                headerLabel.setForeground(new Color(0, 123, 255));
                racketPanel.add(headerLabel, innerGBC);
                
                innerGBC.gridy = 1;
                JTextArea racketInfo = new JTextArea(racket.stringsToString());
                racketInfo.setEditable(false);
                racketInfo.setFont(listFont);
                racketInfo.setBackground(racketPanel.getBackground());
                racketInfo.setBorder(new EmptyBorder(5, 0, 0, 0));
                racketPanel.add(racketInfo, innerGBC);

                listGBC.gridy = i;
                mainListContainer.add(racketPanel, listGBC);
            }
            listGBC.gridy = allRacketsToString.size();
            listGBC.weighty = 1.0; 
            mainListContainer.add(Box.createVerticalGlue(), listGBC);
        }
        
        JScrollPane scrollPane = new JScrollPane(mainListContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        buttonPanel.setBackground(panelColor);
        
        Dimension buttonSize = new Dimension(250, 50);

        backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        submitButton = new JButton("Mark Selected as Strung");
        submitButton.setPreferredSize(new Dimension(300, 50));
        submitButton.setFont(buttonFont);
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        if (allRacketsToString.isEmpty()) {
            submitButton.setEnabled(false);
        }
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
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