package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Stringer_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

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

    public RemovePlayer(TennisStringer thisStringer, TennisKiosk thisKiosk) {
        this.thisStringer = thisStringer;
        this.thisKiosk = thisKiosk;
        this.assignedPlayers = thisStringer.getPlayers();

        this.setTitle("Remove Player from Roster");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Select Player to Remove"), gbc);

        gbc.gridy = 1;
        DefaultComboBoxModel<TennisPlayer> model = new DefaultComboBoxModel<>();
        for (TennisPlayer p : assignedPlayers) {
            model.addElement(p);
        }
        playerSelector = new JComboBox<>(model);
        playerSelector.addActionListener(this);
        this.add(playerSelector, gbc);

        gbc.gridy = 2;
        infoLabel = new JLabel(" ");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(infoLabel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        removeButton = new JButton("Remove Player");
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(this);
        buttonPanel.add(removeButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(buttonPanel, gbc);

        if (assignedPlayers.isEmpty()) {
            playerSelector.setEnabled(false);
            removeButton.setEnabled(false);
            infoLabel.setText("You have no players assigned to remove.");
        } else {
            updateInfoLabel();
        }

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
            infoLabel.setForeground(Color.RED);
            removeButton.setEnabled(false);
        } else {
            infoLabel.setText("This player can be safely removed.");
            infoLabel.setForeground(Color.BLACK);
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
