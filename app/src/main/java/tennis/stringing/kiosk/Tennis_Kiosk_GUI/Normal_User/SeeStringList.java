package tennis.stringing.kiosk.Tennis_Kiosk_GUI.Normal_User;

import java.util.LinkedList;
import javax.swing.*;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

public class SeeStringList extends JFrame implements ActionListener {
    private TennisPlayer thisPlayer;
    private TennisKiosk thisKiosk;

    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolKit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JButton backButton;
    
    private Font displayFont = new Font("Consolas", Font.PLAIN, 16);
    private Font titleFont = new Font("SansSerif", Font.BOLD, 32); 
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
    
    private Color backgroundColor = new Color(240, 248, 255);
    private Color panelColor = Color.WHITE;
    private Color secondaryColor = new Color(108, 117, 125);

    public SeeStringList (TennisPlayer thisPlayer, TennisKiosk thisKiosk) {
        this.thisPlayer = thisPlayer;
        this.thisKiosk = thisKiosk;

        this.setTitle("Available Kiosk Strings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        
        this.getContentPane().setBackground(backgroundColor);
        
        this.setLayout(new GridBagLayout()); 

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(panelColor);
        centerPanel.setPreferredSize(new Dimension(1000, 750)); 
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 30, 20, 30);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel titleLabel = new JLabel("Available Strings in Stock");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(40, 40, 40));
        centerPanel.add(titleLabel, gbc);

        JPanel stringsPanel = new JPanel(new GridLayout(0, 3, 20, 20)); 
        stringsPanel.setBackground(panelColor);

        LinkedList<TennisString> availableStrings = thisKiosk.getLinkedListOfString(); 

        if (availableStrings.isEmpty()) {
            JLabel emptyLabel = new JLabel("There are no current strings in stock.");
            emptyLabel.setFont(displayFont);
            stringsPanel.add(emptyLabel);
        } else {
            for (TennisString string : availableStrings) {
                JTextArea stringInfo = new JTextArea(string.toString());
                stringInfo.setEditable(false);
                stringInfo.setFont(displayFont);
                stringInfo.setBackground(new Color(245, 245, 245));
                stringInfo.setWrapStyleWord(true);
                stringInfo.setLineWrap(true);
                
                stringInfo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1), 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                stringsPanel.add(stringInfo);
            }
        }
        
        JScrollPane stringScrollPane = new JScrollPane(stringsPanel);
        stringScrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Stock Details", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.PLAIN, 14), new Color(80, 80, 80)
        ));
        
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(stringScrollPane, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(panelColor);
        
        Dimension buttonSize = new Dimension(220, 50);
        
        backButton = new JButton("Back to Main Menu");
        backButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setBackground(secondaryColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(buttonPanel, gbc);

        GridBagConstraints wrapperGBC = new GridBagConstraints();
        wrapperGBC.weightx = 1.0; 
        wrapperGBC.weighty = 1.0;
        wrapperGBC.anchor = GridBagConstraints.CENTER;
        this.add(centerPanel, wrapperGBC);

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