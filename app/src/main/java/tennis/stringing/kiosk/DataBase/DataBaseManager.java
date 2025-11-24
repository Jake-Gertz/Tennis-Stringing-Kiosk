package tennis.stringing.kiosk.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;

public class DataBaseManager {
    private final String KIOSK_INFORMATION_FILE = "kioskInformation.csv";
    private final String PLAYER_INFORMATION_FILE = "players.csv";
    private final String RACKET_INFORMATION_FILE = "rackets.csv";
    private final String STRING_INFORMATION_FILE = "string.csv";
    private final String STRINGER_INFORMATION_FILE = "stringers.csv";
    private final String RACKETS_STRING_INFORMATION_FILE = "racketsString.csv";

    public TennisKiosk loadKiosk() {
        TennisKiosk kioskToLoad = new TennisKiosk();

        return kioskToLoad;
    }

    public void storeKiosk(TennisKiosk kiosk) {
        /*
        * This section clears the Players, Rackets.
        * and Rackets String Files so that later our 
        * helper methods can just append to them.
        */
        File playerInformationFile = new File(PLAYER_INFORMATION_FILE);
        File racketInformationFile = new File(RACKET_INFORMATION_FILE);
        File racketsStringInformationFile = new File(RACKETS_STRING_INFORMATION_FILE);

        try (PrintWriter playerinformationPW = new PrintWriter(playerInformationFile); 
             PrintWriter racketInformationPW = new PrintWriter(racketInformationFile);
             PrintWriter racketsStringInformationPW = new PrintWriter(racketsStringInformationFile)) {
                // Don't have to do anthing here since just calling then closing the PW
                // clears the file

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }


        /* 
        * The secion that stores the infomation in the kioskInformation file 
        * the format of the file is a csv in the order as follows
        * number of stringers, number of strings, admin ID number
        * all fields are ints
        */
        File kioskInformationFile = new File(KIOSK_INFORMATION_FILE);

        try (PrintWriter kioskInformationPW = new PrintWriter(kioskInformationFile)) {
            kioskInformationPW.println(kiosk.getNumberOfStringers() + "," + kiosk.getNumberOfStrings() + "," + kiosk.getAdminID());

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        /*
        * This section is the section that writes all available strings into the string.csv file.
        * all strings are formated in a csv with the following format
        * String brand, string name, in stock, length in stock
        */
        File stringInformationFile = new File(STRING_INFORMATION_FILE);

        try (PrintWriter stringInformationPW = new PrintWriter(stringInformationFile)) {
            LinkedList<TennisString> availableString = kiosk.getLinkedListOfString();

            for (TennisString ts: availableString) {
                stringInformationPW.println(ts.getStringBrand().toString() + "," + ts.getStringName() + "," + ts.getInStock() + "," + ts.getLengthInStock());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        /*
        * This section writes this kiosks stringers to the 
        * stringers.csv file in the following format
        * Stringer name, strung rackets, # of players, userID
        */
        File stringerInformationFile = new File(STRINGER_INFORMATION_FILE);

        try (PrintWriter stringerInformationPW = new PrintWriter(stringerInformationFile)) {
            LinkedList<TennisStringer> stringers = kiosk.getStringers();

            for (TennisStringer ts: stringers) {
                stringerInformationPW.println(ts.getStringerName() + "," + ts.getStrungRackets() + "," + ts.getNumberOfPlayers() + "," + ts.getUserID());
                writePlayers(ts);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    private void writePlayers(TennisStringer stringer) {
        try (PrintWriter playerInformationPW = new PrintWriter(new FileOutputStream(PLAYER_INFORMATION_FILE, true))) {
            LinkedList<TennisPlayer> players = stringer.getPlayers();

            for(TennisPlayer tp: players) {
                playerInformationPW.println(tp.getFirstName() + "," + tp.getLastName() + "," + tp.getTotalStrungRackets() + ","
                                            + tp.getUserID() + "," + (tp.getNumberOfRacketsToPickUp() + tp.getNumberOfRacketsToString()));
                writeRackets(tp);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }

    private void writeRackets(TennisPlayer player) {
        try (PrintWriter racketInformationPW = new PrintWriter(new FileOutputStream(RACKET_INFORMATION_FILE, true))) {
            LinkedList<TennisRacket> rackets = player.getAllRackets();

            for (TennisRacket tr: rackets) {
                racketInformationPW.println(tr.getRacketBrand() + "," + tr.getRacketModelName() + "," + tr.getMainTension()
                                            + "," + tr.getCrossTension() + "," + tr.getLastStrung().toString());
                writeString(tr);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    private void writeString(TennisRacket racket) {
        try (PrintWriter racketsStringInformationPW = new PrintWriter(new FileOutputStream(RACKETS_STRING_INFORMATION_FILE, true))) {
            TennisString mainString = racket.getMainString();
            TennisString crossString = racket.getCrossString();

            racketsStringInformationPW.println(mainString.getStringBrand().toString() + "," + mainString.getStringName() 
                                               + "," + mainString.getInStock() + "," + mainString.getLengthInStock());
            racketsStringInformationPW.println(crossString.getStringBrand().toString() + "," + crossString.getStringName() 
                                               + "," + crossString.getInStock() + "," + crossString.getLengthInStock());

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
 