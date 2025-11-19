package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;

import java.awt.*;

public class PickUpRacket extends JFrame implements ActionListener {

    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton backButton;
    private JButton submitButton;
    private JCheckBox[] checkBoxes;
    private LinkedList<TennisRacket> racketsToPickUp;
    private Font displayFont = new Font("Monospaced", Font.PLAIN, 12);

    public PickUpRacket(TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisPlayer = thisPlayer;
        this.thisKiosk = thisKiosk;

        this.racketsToPickUp = thisPlayer.getRacketsToPickUp();
        this.checkBoxes = new JCheckBox[racketsToPickUp.size()];

        this.setTitle("Pick Up Rackets");
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
        this.add(new JLabel("Select Rackets to Pick Up"), gbc);

        JPanel mainListPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        if (racketsToPickUp.isEmpty()) {
            mainListPanel.add(new JLabel("You have no rackets to pick up."));
        } else {
            for (int i = 0; i < racketsToPickUp.size(); i++) {
                TennisRacket racket = racketsToPickUp.get(i);

                JPanel racketPanel = new JPanel(new BorderLayout(10, 10));
                racketPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                JCheckBox checkBox = new JCheckBox();
                checkBoxes[i] = checkBox;
                racketPanel.add(checkBox, BorderLayout.WEST);

                JTextArea racketInfo = new JTextArea(racket.toString());
                racketInfo.setEditable(false);
                racketInfo.setFont(displayFont);
                racketInfo.setWrapStyleWord(true);
                racketInfo.setLineWrap(true);
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
        
        submitButton = new JButton("Pick Up Selected Rackets");
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
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();

        } else if (e.getSource() == submitButton) {
            int pickedUpCount = 0;
            
            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    TennisRacket racketToPickUp = this.racketsToPickUp.get(i);
                    
                    thisPlayer.pickUpRacket(racketToPickUp);
                    pickedUpCount++;
                }
            }

            if (pickedUpCount > 0) {
                JOptionPane.showMessageDialog(this, pickedUpCount + " racket(s) picked up successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No rackets were selected.");
            }
            new TennisKioskUserPage(thisPlayer, thisKiosk);
            this.dispose();
        }
    }
}
