package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;

import java.awt.*;

public class RemovePlayer extends JFrame implements ActionListener {

    private TennisStringer thisStringer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JComboBox<TennisPlayer> playerSelector;
    private JButton removeButton;
    private JButton backButton;
    private JLabel infoLabel;
    
    private LinkedList<TennisPlayer> assignedPlayers;

    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font labelFont = new Font("SansSerif", Font.PLAIN, 18);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    private Font infoFont = new Font("SansSerif", Font.BOLD | Font.ITALIC, 14);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color dangerColor = new Color(220, 53, 69);
    private Color secondaryColor = new Color(108, 117, 125);

    public RemovePlayer(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;
        this.assignedPlayers = thisStringer.getPlayers();

        this.setTitle("Remove Player from Roster");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.getContentPane().setBackground(backgroundColor); 
        this.setLayout(new GridBagLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(650, 450)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel titleLabel = new JLabel("Remove Player from Roster");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(dangerColor);
        centerPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 25, 15);
        JLabel instructionLabel = new JLabel("Select the player you wish to remove.");
        instructionLabel.setFont(labelFont);
        centerPanel.add(instructionLabel, gbc);
        
        JPanel selectionPanel = new JPanel(new GridBagLayout());
        selectionPanel.setBackground(panelColor);
        selectionPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Player Selection", TitledBorder.LEFT, TitledBorder.TOP, labelFont, new Color(80, 80, 80)
        ));
        
        GridBagConstraints selectGBC = new GridBagConstraints();
        selectGBC.insets = new Insets(10, 15, 10, 15);
        selectGBC.gridx = 0;
        selectGBC.gridy = 0;
        
        DefaultComboBoxModel<TennisPlayer> model = new DefaultComboBoxModel<>();
        for (TennisPlayer p : assignedPlayers) {
            model.addElement(p);
        }
        playerSelector = new JComboBox<>(model);
        playerSelector.setFont(labelFont);
        playerSelector.setPreferredSize(new Dimension(300, 35));
        playerSelector.addActionListener(this);
        selectionPanel.add(playerSelector, selectGBC);
        
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(selectionPanel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 15, 20, 15);
        infoLabel = new JLabel(" ");
        infoLabel.setFont(infoFont);
        centerPanel.add(infoLabel, gbc);
        
        JPanel buttonPanel = new JPanel(new GridBagLayout()); 
        buttonPanel.setBackground(panelColor);
        
        Dimension buttonSize = new Dimension(180, 45);
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.insets = new Insets(0, 20, 0, 20);
        
        buttonGBC.gridx = 0;
        buttonGBC.gridy = 0;
        backButton = new JButton("Back");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton, buttonGBC);
        
        buttonGBC.gridx = 1;
        buttonGBC.gridy = 0;
        removeButton = new JButton("Remove Player");
        removeButton.setPreferredSize(buttonSize);
        removeButton.setFont(buttonFont);
        removeButton.setBackground(dangerColor);
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(this);
        buttonPanel.add(removeButton, buttonGBC);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);
        centerPanel.add(buttonPanel, gbc);
        
        if (assignedPlayers.isEmpty()) {
            playerSelector.setEnabled(false);
            removeButton.setEnabled(false);
            infoLabel.setText("You have no players assigned to remove.");
            infoLabel.setForeground(Color.BLACK);
        } else {
            updateInfoLabel();
        }
        
        GridBagConstraints wrapperGBC = new GridBagConstraints();
        wrapperGBC.weightx = 1.0; 
        wrapperGBC.weighty = 1.0;
        wrapperGBC.anchor = GridBagConstraints.CENTER;
        this.add(centerPanel, wrapperGBC); 

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void updateInfoLabel() {
        TennisPlayer selected = (TennisPlayer) playerSelector.getSelectedItem();
        if (selected == null) return;

        int toStr = selected.getNumberOfRacketsToString();
        int toPickUp = selected.getNumberOfRacketsToPickUp();

        if (toStr > 0 || toPickUp > 0) {
            infoLabel.setText("Cannot remove: Player has " + toStr + " racket(s) to string and " + toPickUp + " to pick up.");
            infoLabel.setForeground(dangerColor);
            removeButton.setEnabled(false);
        } else {
            infoLabel.setText("This player can be safely removed.");
            infoLabel.setForeground(new Color(50, 150, 50));
            removeButton.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new TennisStringerPage(thisStringer, thisKiosk);
            this.dispose();

        } else if (e.getSource() == playerSelector) {
            updateInfoLabel();

        } else if (e.getSource() == removeButton) {
            TennisPlayer selectedPlayer = (TennisPlayer) playerSelector.getSelectedItem();

            if (selectedPlayer.getNumberOfRacketsToString() > 0 || selectedPlayer.getNumberOfRacketsToPickUp() > 0) {
                JOptionPane.showMessageDialog(this, "Error: This player still has outstanding rackets.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int choice = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to remove " + selectedPlayer.getPlayerName() + " from your roster?",
                "Confirm Removal", 
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                thisStringer.removePlayer(selectedPlayer);
                JOptionPane.showMessageDialog(this, "Player removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new TennisStringerPage(thisStringer, thisKiosk);
                this.dispose();
            }
        }
    }
}