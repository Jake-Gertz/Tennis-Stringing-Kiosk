package tennis.stringing.kiosk.Kiosk_User_Objects;

import java.util.LinkedList;

import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisRacket;

/**
 * The TennisPlayer class is used to represent a 
 * tennis player / user of the tennis stringing kiosk.
 * A TennisPlayer has the following atributes
 * 
 * A first name
 * A last name
 * A total number of strung rackets
 * A user ID
 * A list of rackets to pick up (rackets that have been strung)
 * A list of rackets to string (rackets that need to be restrung)
 * 
 * @author Jake Gertz
 * @date 11/13/2025
 * @version 1.0
 */
public class TennisPlayer {
    private String firstName;
    private String lastName;
    private int totalStrungRackets;
    private int userID;

    private LinkedList<TennisRacket> racketsToPickUp;
    private LinkedList<TennisRacket> racketsToString;

    /**
        Constants included in class:

            DEFAULT_USER_ID, DEFAULT_STRUNG_RACKETS

            Default user ID represents the default userID a TennisPlayer
                will be assigned if no user ID is specified at time of creation.

            Default strung rackets represents the default number of rackets a tennis 
                player will been assigned if no number of strung rackets is provided.

     */
    private final static int DEFAULT_USER_ID = 0001;
    private final static int DEFAULT_STRUNG_RACKETS = 0;


    /**
     * This constructor allows for the creation of a TennisPlayer object 
     * initalized with a first name, a last name, a number of total strung
     * rackets, and a user ID.
     * 
     * @param firstName A String representing this tennis players first name
     * @param lastName A String representing this tennis players last name
     * @param totalStrungRackets An int representing how many rackets this tennis player has had strung
     * @param userID An int representing this tennis players user ID
     */
    public TennisPlayer(String firstName, String lastName, int totalStrungRackets, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalStrungRackets = totalStrungRackets;
        this.userID = userID;

        racketsToPickUp = new LinkedList<TennisRacket>();
        racketsToString = new LinkedList<TennisRacket>();
    }

    /**
     * This overloaded constructor allows for the creation of a tennis
     * player given just a first name, last name, and userID. 
     * The number of strung rackets will be initialized with a default value.
     * 
     * @param firstName A String representing this tennis players first name
     * @param lastName A string representing this tennis players last name
     * @param userID An int representing this tennis players user ID
     */
    public TennisPlayer(String firstName, String lastName, int userID) {
        this(firstName, lastName, DEFAULT_STRUNG_RACKETS, userID);
    }

    /**
     * This overloaded constructor allows for the creation of a tennis
     * player gieven just a first name and a last name. Both 
     * user ID and number of rackets strung will be initalized with
     * the default value of each.
     * 
     * @param firstName A String representing this tennis players first name
     * @param lastName A String representing this tennis players last name
     */
    public TennisPlayer(String firstName, String lastName) {
        this(firstName, lastName, DEFAULT_STRUNG_RACKETS, DEFAULT_USER_ID);
    }

    /**
     * A getter that returns this tennis players first name
     * 
     * @return A String representing this tennis players first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * A getter that returns this tennis players last name
     * 
     * @return A String representing this tennis players last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * A getter that returns a tennis players full name (first and last)
     * 
     * @return A String formated (firstName lastName)
     */
    public String getPlayerName() {
        return (firstName + " " + lastName);
    }

    /**
     * A getter that returns number of rackets strung by/for this tennis player.
     * 
     * @return An int denoting number of strung rackets
     */
    public int getTotalStrungRackets() {
        return totalStrungRackets;
    }

    /**
     * A getter that returns a tennis players user ID
     * 
     * @return An int representing a tennis players user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * A setter for this tennis players first name
     * 
     * @param firstName The string to set as this tennis players first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * A setter for this tennis players last name 
     * 
     * @param lastName The string to set as this tennis players last name 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * A setter to set this tennis players first and last name
     * at the same time
     * 
     * @param firstName The string to set as this players first name
     * @param lastName The string to set as this players last name 
     */
    public void setPlayerName(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * A setter to set the total number of strung rackets for this
     * tennis player
     * 
     * @param totalStrungRackets An int representing total number of strung rackets
     */
    public void setTotalStrungRackets(int totalStrungRackets) {
        this.totalStrungRackets = totalStrungRackets;
    }

    /**
     * A method to increment the strung rackets of this tennis player
     * by one when called
     */
    public void incementTotalStrungRackets() {
        totalStrungRackets++;
    }

    /**
     * A setter to update hte userID of this tennis player
     * 
     * @param userID An int to update this tennis players user ID to
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * A getter that returns a copy of the list of rackets 
     * this tennis player still needs strung.
     * 
     * @return A LinkedList<TennisRacket> of rackets still needing to be strung
     */
    public LinkedList<TennisRacket> getRacketsToString() {
        LinkedList<TennisRacket> copyArray = new LinkedList<TennisRacket>();

        for (TennisRacket tr: racketsToString) {
            copyArray.addLast(tr);
        }

        return copyArray;
    }
    
    /**
     * A method that returns the toString method of every 
     * TennisRacket object held within the rackets to string
     * linked list.
     * 
     * @return A string representing all the rackets still needing stringing
     */
    public String racketsToStringToString() {
        StringBuilder retString = new StringBuilder("Rackets to String:\n");

        for (TennisRacket tr: racketsToString) {
            retString.append(tr.toString() + "\n");
        }

        return retString.toString();
    }

    /**
     * This getter allows the size of the rackets to string
     * list to be returned without calling .size() after
     * calling getRacketsToString() which first copies the 
     * linked list.
     * 
     * @return An int field representing how many rackets need to be strung
     */
    public int getNumberOfRacketsToString() {
        return racketsToString.size();
    }

    /**
     * A getter that returns a copy of the list of rackets
     * this tennis player needs to pick up / have been strung.
     * 
     * @return A LinkedList<TennisRacket> of rackest already strung
     */
    public LinkedList<TennisRacket> getRacketsToPickUp() {
        LinkedList<TennisRacket> copyArray = new LinkedList<TennisRacket>();

        for (TennisRacket tr: racketsToPickUp) {
            copyArray.addLast(tr);
        }

        return copyArray;
    }

    /**
     * A method that returns the toString method of every 
     * TennisRacket object held within the rackets to pick up
     * linked list.
     * 
     * @return A string representing all the rackets already strung
     */
    public String racketsToPickUpToString() {
        StringBuilder retString = new StringBuilder("Rackets to Pick Up:\n");

        for (TennisRacket tr: racketsToPickUp) {
            retString.append(tr.toString() + "\n");
        }

        return retString.toString();
    }

    /**
     * This getter allows the size of the rackets to pick up
     * list to be returned without calling .size() after
     * calling getRacketsToPickUp() which first copies the 
     * linked list.
     * 
     * @return An int field representing how many rackets need to be picked up
     */
    public int getNumberOfRacketsToPickUp() {
        return racketsToPickUp.size();
    }

    /**
     * This method calls the to string on both racketsToPickUp and racketsToString
     * 
     * @return A string showing both rackets to string and rackets to pick up.
     */
    public String racketsToString() {
        StringBuilder retString = new StringBuilder(racketsToStringToString());
        retString.append("\n" + racketsToPickUpToString());

        return retString.toString();
    }

    /**
     * A method to add a TennisRacket object to the 
     * linked list holding all the rackets that need to be 
     * strung for this tennis player.
     * 
     * @param racket A TennisRacket object to add to the racketsToString list
     */
    public void addRacketToString(TennisRacket racket) {
        racketsToString.addLast(racket);
    }

    /**
     * A method to add a TennisRacket object to the
     * linked list holding all the rackets that need to be
     * picked up for this tennis player.
     * 
     * @param racket A TennisRacket object to add to the racketsToPickUp list
     */
    public void addRacketToPickUp(TennisRacket racket) {
        racketsToPickUp.addLast(racket);
    }

    /**
     * A method to call when a racket within the racketsToString
     * list has been strung.
     * 
     * This method will attempt to remove a TennisRacket object 
     * from the racketsToString list and if the removal was successful
     * it will then add the TennisRacket object to the rackets to pick up 
     * list.
     * 
     * @param racket The TennisRacket object that has been strung
     * @return A boolean: True if found in racketsToString list, False otherwise
     */
    public Boolean strungRacket(TennisRacket racket) {
        Boolean retBool = racketsToString.remove(racket);
        if (retBool) {
            addRacketToPickUp(racket);
        }

        return retBool;
    }

    /**
     * A method to call when a player picks up a finished racket.
     * This removes the racket from the racketsToPickUp list.
     *
     * @param racket The TennisRacket object to pick up.
     * @return A boolean: True if found and removed, False otherwise.
     */
    public boolean pickUpRacket(TennisRacket racket) {
        return racketsToPickUp.remove(racket);
    }

    /**
     * This method returns this tennis player object as 
     * a string with the following format.
     * 
     * Format:
     *     PlayerName (ID: playerID)
     * 
     * 
     */
    @Override
    public String toString() {
        return getPlayerName() + " (ID: " + getUserID() + ")";
    }

}
