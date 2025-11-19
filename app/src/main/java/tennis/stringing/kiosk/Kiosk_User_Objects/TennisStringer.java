package tennis.stringing.kiosk.Kiosk_User_Objects;

import java.util.LinkedList;

/**
 * The TennisStringer class is used to represent 
 * a tennis stringer who will be assigned to a
 * tennis players for which they are responsible for.
 * 
 * @author Jake Gertz
 * @date 11/14/2025
 * @version 1.0
 */
public class TennisStringer {
    private String stringerName;
    private int strungRackets;
    private LinkedList<TennisPlayer> players;
    private int userID;

    /**
     * Constants in this class
     *     
     *     STRUNG_RACKETS_DEFAULT
     *      
     *      Strung rackets default is a final int that 
     *          represents the default value that will 
     *          be assigned to a new TennisStringer objects
     *          strungRackets value when initalized.
     */
    private static final int STRUNG_RACKETS_DEFAULT = 0;
    private static final int DEFAULT_STRINGER_ID = 8888;

    /**
     * This constructor allows for the creation of a tennis stringer with
     * required integer peramerter strungRackets. This will set this
     * TennisStringer's strungRackets field to the input perameter as 
     * long as it is greater than 0, else strungRackets will be set to 0
     * and it will also initalize the linked list of players with a new
     * empty linked list.
     * 
     * @param strungRackets
     */
    public TennisStringer(int strungRackets, String stringerName) {
        this.stringerName = stringerName;
        this.strungRackets = (strungRackets > 0 ? strungRackets : 0);
        players = new LinkedList<TennisPlayer>();
        this.userID = DEFAULT_STRINGER_ID;
    }
    /**
     * This constructor allows for the creation of a tennis stringer with 
     * no requireed perameters. Will set strung rackets to STRUNG_RACKETS_DEFAULT 
     * and will initialize the players linked list as a new empyty list.
     */
    public TennisStringer() {
        this(STRUNG_RACKETS_DEFAULT, "DEFUALT");
    }

    /**
     * This getter returns the int field this TennisStringer
     * object holds which represents how many rackets they 
     * have strung.
     * 
     * @return An int representing strung rackets
     */
    public int getStrungRackets() {
        return strungRackets;
    }

    /**
     * Returns a copy of the players list which represents 
     * all of the TennisPlayer objects this stringer is
     * responsible! 
     * 
     * @return A shallow copy of LinkedList<TennisPlayer> players 
     */
    public LinkedList<TennisPlayer> getPlayers() {
        LinkedList<TennisPlayer> retList = new LinkedList<TennisPlayer>();

        for(TennisPlayer tp: players) {
            retList.addLast(tp);
        }

        return retList;
    }

    /**
     * This method returns how many total rackets to string 
     * a particular stringer has by checking how many 
     * rackets to string each player they are assigned has
     * in their rackets to string list.
     * 
     * @return An int representing the total number of rackets needing strung
     */
    public int getRacketsToString() {
        int retVal = 0;

        for(TennisPlayer tp: players) {
            retVal += tp.getNumberOfRacketsToString();
        }

        return retVal;
    }

    /**
     * This method returns how many total rackets to return 
     * a particular stringer has by checking how many rackets
     * to pick up each plyer the stringer is assigned to has 
     * in their rackets to pick up list.
     * 
     * @return An int representing the total number of rackets needing picked up
     */
    public int getRacketsToPickUp() {
        int retVal = 0;

        for (TennisPlayer tp: players) {
            retVal += tp.getNumberOfRacketsToPickUp();
        }

        return retVal;
    }


    /**
     * This setter allows for the setting of the strungRackets 
     * artribute of this TennisStringer object. If the input 
     * perameter is greater than 0 strungRackets will be set to 
     * the input perameter else it will be set to 0.
     * 
     * @param strungRackets an int  representing the number of strung rackets
     */
    public void setStrungRackets(int strungRackets) {
        this.strungRackets = (strungRackets > 0 ? strungRackets : 0);
    }

    /**
     * This setter allows for an entire LinkedList<TennisPlayer> 
     * to be passed in and this TennisStringer objects players list
     * will be updated to the passed in LinkedList<TennisPlayer>.
     * 
     * @param players A LinkedList<TennisPlayer> to update this players list to
     */
    public void setPlayersList(LinkedList<TennisPlayer> players) {
        this.players = players;
    }

    /**
     * This method allows a tennisPlayer to be added
     * to the players linked list of this TennisStringer.
     * 
     * @param player A TennisPlayer object to add to this stringers list of players
     */
    public void addPlayer(TennisPlayer player) {
        players.addLast(player);
    }

    /**
     * This method allows for the removal of a TennisPlayer
     * object from this TennisStringers list of players.
     * 
     * @param player The TennisPlayer object to remove from the list.
     * @return A boolean, True if the removal was successful, false otherwise.
     */
    public boolean removePlayer(TennisPlayer player) {
        return players.remove(player);
    }

    /**
     * This setter allows the userID of this stringer to be updated.
     * 
     * @param userID A 4 digit int representing the userID to update to
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This getter reutnrs the user ID of this TennisStringer object
     * 
     * @return The user ID of this stringer
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This method is a setter for the stringer name field 
     * that this TennisStringer object holds
     * 
     * @param stringerName A string representing this stringers name
     */
    public void setStringerName(String stringerName) {
        this.stringerName = stringerName;
    }

    /**
     * A getter that returns the name of this stringer
     * 
     * @return A String representing this stringers name
     */
    public String getStringerName() {
        return stringerName;
    }

    /**
     * This method is a toString that will return a string 
     * formated in the following way:
     *     "Stringer Name (ID: stringerID)"
     * 
     * @return A string representing this TennisStringer object
     */
    @Override
    public String toString() {
        return getStringerName() + " (ID: " + getUserID() + ")";
    }
}

