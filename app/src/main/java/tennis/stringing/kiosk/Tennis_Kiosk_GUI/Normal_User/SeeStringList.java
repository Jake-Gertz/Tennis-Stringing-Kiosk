package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeeStringList extends JFrame implements ActionListener {
    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton backButton;
    private Font displayFont = new Font("Monospaced", Font.PLAIN, 12);

    public SeeStringList (TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisPlayer = thisPlayer;
        this.thisKiosk = thisKiosk;

        this.setTitle("Available Kiosk Strings");
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
        this.add(new JLabel("Available Strings in Stock"), gbc);

        JPanel stringsPanel = new JPanel(new GridLayout(0, 4, 10, 10));

        LinkedList<TennisString> availableStrings = thisKiosk.getString(); 

        if (availableStrings.isEmpty()) {
            stringsPanel.add(new JLabel("There are no current strings in stock."));
        } else {
            for (TennisString string : availableStrings) {
                JTextArea stringInfo = new JTextArea(string.toString());
                stringInfo.setEditable(false);
                stringInfo.setFont(displayFont);
                stringInfo.setWrapStyleWord(true);
                stringInfo.setLineWrap(true);
                stringInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                stringsPanel.add(stringInfo);
            }
        }
        
        JScrollPane stringScrollPane = new JScrollPane(stringsPanel);
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(stringScrollPane, gbc);

        gbc.gridy = 2;
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
