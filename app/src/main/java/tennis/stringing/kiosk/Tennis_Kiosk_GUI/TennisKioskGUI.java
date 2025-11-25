package tennis.stringing.kiosk.Tennis_Kiosk_GUI;

import javax.swing.SwingUtilities;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.DataBase.DataBaseManager;

public class TennisKioskGUI {
    public static void main(String[] args) {
        DataBaseManager dbManager = new DataBaseManager();
        TennisKiosk loadedKiosk = dbManager.loadKiosk();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KioskLandingPage (loadedKiosk);
            }
        });
    }
}
