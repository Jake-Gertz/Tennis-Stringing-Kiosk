package tennis.stringing.kiosk.Tennis_Kiosk_GUI;

import javax.swing.SwingUtilities;

public class TennisKioskGUI {
public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KioskLandingPage ();
            }
        });
    }
}
