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
    private final String KIOSK_INFORMATION_FILE = "kioskInformation.csv";
    private final String PLAYER_INFORMATION_FILE = "players.csv";
    private final String RACKET_INFORMATION_FILE = "rackets.csv";
    private final String STRING_INFORMATION_FILE = "string.csv";
    private final String STRINGER_INFORMATION_FILE = "stringers.csv";
    private final String RACKETS_STRING_INFORMATION_FILE = "racketsString.csv";

    private TennisKiosk kioskToLoad = new TennisKiosk();

    public TennisKiosk loadKiosk() {
        File kioskInformationFile = new File(KIOSK_INFORMATION_FILE);
        File playerInformationFile = new File(PLAYER_INFORMATION_FILE);
        File racketInformationFile = new File(RACKET_INFORMATION_FILE);
        File stringInformationFile = new File(STRING_INFORMATION_FILE);
        File stringerInformationFile = new File(STRINGER_INFORMATION_FILE);
        File racketsStringInformationFile = new File(RACKETS_STRING_INFORMATION_FILE);

        kioskToLoad = new TennisKiosk();
        int numStringersInKiosk = 0;
        int numStringsInKiosk = 0;

        try (Scanner kioskInformationScanner = new Scanner(kioskInformationFile);
             Scanner lineScanner = new Scanner(kioskInformationScanner.nextLine())) {
                lineScanner.useDelimiter(",");
                numStringersInKiosk = Integer.parseInt(lineScanner.next());
                numStringsInKiosk = Integer.parseInt(lineScanner.next());
                kioskToLoad.setAdminID(Integer.parseInt(lineScanner.next()));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        readStrings(numStringsInKiosk, stringInformationFile);

        readKioskUsers(numStringersInKiosk, stringerInformationFile, playerInformationFile, racketInformationFile, racketsStringInformationFile);

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
 