package tennis.stringing.kiosk.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import tennis.stringing.kiosk.TennisKiosk;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisPlayer;
import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisRacketBrand;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisStringBrand;


/**
 * Manages the persistence layer for the Tennis Stringing Kiosk application.
 *
 * This class is responsible for loading the entire kiosk state from and storing it
 * to multiple CSV files. It handles the hierarchical structure of the application data:
 * Kiosk -> Stringers -> Players -> Rackets/Strings.
 *
 * The file structure used for persistence includes:
 *
 * kioskInformation.csv: General Kiosk details (counts, admin ID).
 * string.csv: Global inventory of available tennis strings.
 * stringers.csv: List of all stringers.
 * players.csv: List of all players, nested under stringers (sequential read).
 * rackets.csv: List of all rackets, nested under players (sequential read).
 * racketsString.csv: Main and cross string details for each racket (sequential read).
 *
 * @author Jake Gertz
 * @date 11/24/2025
 * @version 1.0
 */
public class DataBaseManager {
    //TODO update brand names in the data base
    private final String KIOSK_INFORMATION_FILE = "./data/kioskInformation.csv";
    private final String PLAYER_INFORMATION_FILE = "./data/players.csv";
    private final String RACKET_INFORMATION_FILE = "./data/rackets.csv";
    private final String STRING_INFORMATION_FILE = "./data/string.csv";
    private final String STRINGER_INFORMATION_FILE = "./data/stringers.csv";
    private final String RACKETS_STRING_INFORMATION_FILE = "./data/racketsString.csv";

    private TennisKiosk kioskToLoad;

    /**
     * A constructor that creates a new DataBaseManager
     * object whith a black TennisKiosk to be loaded
     * 
     */
    public DataBaseManager() {
        kioskToLoad = new TennisKiosk();
    }

    /**
     * The loadKiosk() method is used to recover the data from the data
     * base. All the data is read and put into a TennisKiosk object which 
     * is then returned.
     * 
     * @return TennisKiosk object holding all the information for this TennisStringingKiosk
     */
    public TennisKiosk loadKiosk() {
        // Set up all the files objects that will need to be touched 
        // in the loading of this kiosk
        File kioskInformationFile = new File(KIOSK_INFORMATION_FILE);
        File playerInformationFile = new File(PLAYER_INFORMATION_FILE);
        File racketInformationFile = new File(RACKET_INFORMATION_FILE);
        File stringInformationFile = new File(STRING_INFORMATION_FILE);
        File stringerInformationFile = new File(STRINGER_INFORMATION_FILE);
        File racketsStringInformationFile = new File(RACKETS_STRING_INFORMATION_FILE);

        // makes sure that the kioskToLoad TennisKiosk object is blank before adding 
        // all the fields stored in the data base to it
        kioskToLoad = new TennisKiosk();
        int numStringersInKiosk = 0;
        int numStringsInKiosk = 0;

        // Places a scanner on the file that holds the top level information for our TennisKiosk
        try (Scanner kioskInformationScanner = new Scanner(kioskInformationFile)) {
                if (kioskInformationScanner.hasNextLine()) {
                    Scanner lineScanner = new Scanner(kioskInformationScanner.nextLine());
                    lineScanner.useDelimiter(",");
                    numStringersInKiosk = Integer.parseInt(lineScanner.next());
                    numStringsInKiosk = Integer.parseInt(lineScanner.next());
                    kioskToLoad.setAdminID(Integer.parseInt(lineScanner.next()));
                    lineScanner.close();
                }
    
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // A call to the private helper method readStrings
        readStrings(numStringsInKiosk, stringInformationFile);

        // A call to the private helper method that reads kiosk users from the data base.
        // Kiosk users include both stringers and players
        readKioskUsers(numStringersInKiosk, stringerInformationFile, playerInformationFile, racketInformationFile, racketsStringInformationFile);

        return kioskToLoad;
    }

    /**
     * StoreKiosk is a method that allows the current state of the tennisKiosk to be store
     * within the data base so it can be accessed later and the data is persistent
     * even if the program is closed.
     * 
     * @param kiosk the current TennisKiosk object you wish to store in the database
     */
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
        * number of stringers (int), number of strings (int), admin ID number (int)
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
        * String brand (TennisStringBrand.toString()), string name (String), in stock (boolean), length in stock (int)
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
        * Stringer name (String), strung rackets (int), # of players (int), userID (int)
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

    /**
     * The resetKiosk method allows for the data base holding information about this 
     * tennis kiosk to be erased. The method touches each file within the data directory
     * and erases all its contents hence restoring the data base to factory default
     */
    public void resetKiosk() {
        try (PrintWriter pw1 = new PrintWriter(new File(KIOSK_INFORMATION_FILE));
             PrintWriter pw2 = new PrintWriter(new File(PLAYER_INFORMATION_FILE));
             PrintWriter pw3 = new PrintWriter(new File(RACKET_INFORMATION_FILE));
             PrintWriter pw4 = new PrintWriter(new File(STRING_INFORMATION_FILE));
             PrintWriter pw5 = new PrintWriter(new File(STRINGER_INFORMATION_FILE));
             PrintWriter pw6 = new PrintWriter(new File(RACKETS_STRING_INFORMATION_FILE))) {

                // No Need for any into here since we are just calling the PW to clear out all
                // information and files that were previously associated with this database
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * This private helper method allows each tennis player belonging to a certain 
     * TennisStringer object to be stored one by one in the data base.
     * Tennis Players are store in the data base as a CSV formated in the following way
     * first name (string), last name (string), total strung rackets (int), user ID (int), total rackets to pick up and drop off (int)
     * 
     * @param stringer The TennisStringer whos player objects you wish to store in the data base
     */
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

    /**
     * writeRackets is a private helper method that allows all TennisRacket objects
     * belonging to a certain TennisPlayer object to be writen to the data base.
     * Racket objects are stored to the data base in a CSV file formatted in the following way
     * racket brand(TennisRacketBrand.toString()), racket model name (String), main tension (int), cross tension (int), last strung date (local date)
     * 
     * @param player The TennisPlayer object whos rackets you want to write to the data base
     */
    private void writeRackets(TennisPlayer player) {
        try (PrintWriter racketInformationPW = new PrintWriter(new FileOutputStream(RACKET_INFORMATION_FILE, true))) {
            LinkedList<TennisRacket> racketsToString = player.getRacketsToString();
            LinkedList<TennisRacket> racketsToPickUp = player.getRacketsToPickUp();

            for (TennisRacket tr: racketsToString) {
                racketInformationPW.println(tr.getRacketBrand() + "," + tr.getRacketModelName() + "," + tr.getMainTension()
                                            + "," + tr.getCrossTension() + "," + tr.getLastStrung().toString()
                                            + "," + false);
                writeString(tr);
            }

            for (TennisRacket tr: racketsToPickUp){
                racketInformationPW.println(tr.getRacketBrand() + "," + tr.getRacketModelName() + "," + tr.getMainTension()
                                            + "," + tr.getCrossTension() + "," + tr.getLastStrung().toString()
                                            + "," + true);
                writeString(tr);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * writeString is a private helper method that writes the string a certain
     * TennisRacket object is using for its mains and crosses to the data base.
     * TennisString objects are stored in the data base as a CSV with the following format
     * string brand (TennisStringBrand.toString()), string name (String), in stock status (boolean), length in stock (int)
     * 
     * @param racket The TennisRacket object whos strings need to be added to the data base 
     */
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

    /**
     * The private helper method readStrings is a method that allows the strings
     * held in the data base to be added to this tennis Kiosk objects avaiableStrings
     * LinkedList<TennisString>
     * 
     * @param numberOfStrings an int representing how many strings the tennis kiosk should have
     * @param fileToRead A File object pointing to the data base file holding the strings this kiosk has
     */
    private void readStrings(int numberOfStrings, File fileToRead) {
        try (Scanner kioskStringScanner = new Scanner(fileToRead)) {
            for (int i = 0; i < numberOfStrings && kioskStringScanner.hasNextLine(); i++) {
                Scanner lineScanner = new Scanner(kioskStringScanner.nextLine());
                lineScanner.useDelimiter(",");
                TennisStringBrand stringBrand = TennisStringBrand.valueOf(lineScanner.next());
                String stringName = lineScanner.next();
                boolean inStock = Boolean.parseBoolean(lineScanner.next());
                int lengthInStock = Integer.parseInt(lineScanner.next());

                TennisString stringToAdd = new TennisString(stringBrand, stringName, inStock, lengthInStock);

                kioskToLoad.addTennisString(stringToAdd);

                lineScanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * The readKioskUsers method allows the tennis kiosk to recover its list of 
     * TennisStringer objects, the tennis stringers TennisPlayer objects, the 
     * tennis players TennisRacket objects, and the tennis rackets TennisString 
     * objects. This allows the TennisKiosk to be fully reconstructed from the 
     * data base.
     * 
     * @param numStringersInKiosk An int represeting how many stringers are in this TennisKiosk
     * @param stringerFile A File object pointing to the data base file holding all TennisStringer objects
     * @param playerFile A File object pointing to the data base file holding all TennisPlayer objects
     * @param racketFile A file object pointing to the data base file holding all TennisRacket objects
     * @param racketStringFile A file object pointing to the data base file holding all TennisString objects held by TennisRackets
     */
    private void readKioskUsers(int numStringersInKiosk, File stringerFile, File playerFile, File racketFile, File racketStringFile) {
        try (Scanner stringerFileScanner = new Scanner(stringerFile);
             Scanner playerFileScanner = new Scanner(playerFile);
             Scanner racketFileScanner = new Scanner(racketFile);
             Scanner racketsStringFileScanner = new Scanner(racketStringFile)) {

            for (int i = 0; i < numStringersInKiosk && stringerFileScanner.hasNextLine(); i++) {
                Scanner stringerLineScanner = new Scanner(stringerFileScanner.nextLine());
                stringerLineScanner.useDelimiter(",");

                String stringerName = stringerLineScanner.next();
                int strungRackets = Integer.parseInt(stringerLineScanner.next());
                int numberOfPlayers = Integer.parseInt(stringerLineScanner.next());
                int userID = Integer.parseInt(stringerLineScanner.next());

                TennisStringer stringerToAdd = new TennisStringer(strungRackets, stringerName);
                stringerToAdd.setUserID(userID);

                kioskToLoad.addStringer(stringerToAdd);

                for (int j = 0; j < numberOfPlayers && playerFileScanner.hasNextLine(); j++) {
                    Scanner playerLineScanner = new Scanner(playerFileScanner.nextLine());
                    playerLineScanner.useDelimiter(",");

                    String firstName = playerLineScanner.next();
                    String lastName = playerLineScanner.next();
                    int totalStrungRackets = Integer.parseInt(playerLineScanner.next());
                    int playerID = Integer.parseInt(playerLineScanner.next());
                    int numberOfRackets = Integer.parseInt(playerLineScanner.next());

                    TennisPlayer playerToAdd = new TennisPlayer(firstName, lastName, totalStrungRackets, playerID);

                    stringerToAdd.addPlayer(playerToAdd);

                    playerLineScanner.close();

                    for (int k = 0; k < numberOfRackets && racketFileScanner.hasNextLine(); k++) {
                        Scanner racketLineScanner = new Scanner(racketFileScanner.nextLine());
                        racketLineScanner.useDelimiter(",");

                        TennisRacketBrand racketBrand = TennisRacketBrand.valueOf(racketLineScanner.next());
                        String modelName = racketLineScanner.next();
                        int mainTension = Integer.parseInt(racketLineScanner.next());
                        int crossTension = Integer.parseInt(racketLineScanner.next());
                        LocalDate lastStrung = LocalDate.parse(racketLineScanner.next());
                        boolean readyForPickUp = Boolean.parseBoolean(racketLineScanner.next());
                        TennisString mainString = null;
                        TennisString crossString = null;

                        for (int l = 0; l < 2 && racketsStringFileScanner.hasNextLine(); l++) {
                            Scanner racketsStringLineScanner = new Scanner(racketsStringFileScanner.nextLine());
                            racketsStringLineScanner.useDelimiter(",");

                            TennisStringBrand stringBrand = TennisStringBrand.valueOf(racketsStringLineScanner.next());
                            String stringName = racketsStringLineScanner.next();
                            boolean inStock = Boolean.parseBoolean(racketsStringLineScanner.next());
                            int lengthInStock = Integer.parseInt(racketsStringLineScanner.next());

                            TennisString stringToAdd = new TennisString(stringBrand, stringName, inStock, lengthInStock);

                            if (l == 0){
                                mainString = stringToAdd;
                            } else {
                                crossString = stringToAdd;
                            }

                            racketsStringLineScanner.close();
                        }

                        TennisRacket racketToAdd = new TennisRacket(racketBrand, mainString, crossString, mainTension, crossTension);
                        racketToAdd.setRacketModelName(modelName);
                        racketToAdd.setLastStrungDate(lastStrung);

                        if (readyForPickUp) {
                            playerToAdd.addRacketToPickUp(racketToAdd);
                        } else {
                            playerToAdd.addRacketToString(racketToAdd);
                        }

                        racketLineScanner.close();
                    }
                }

                stringerLineScanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }
}
 