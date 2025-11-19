package tennis.stringing.kiosk;

import java.util.LinkedList;

import tennis.stringing.kiosk.Kiosk_User_Objects.TennisStringer;
import tennis.stringing.kiosk.Racket_Object_Dependencies.TennisString;

/**
 * The TennisKiosk.TennisKiosk class is mean to help reprsent a phyiscal tennis kiosk. This class holds
 * a list of stringers that can be picked by users to string their rackets, a list of 
 * available string, a total number of rackets needing to be strung, a total number of 
 * rackets needing to be picked up, and an admin password for accessing the admin settings
 * of the kiosk (implimented in the TennisKiosk.TennisKiosk GUI).
 * 
 * @author Jake Gertz
 * @date 11/14/2025
 * @version 1.0
 */
public class TennisKiosk {
    LinkedList<TennisStringer> stringers;
    LinkedList<TennisString> availableString;
    int racketsNeedingStrung;
    int racketsNeedingPickedUp;
    int adminID;

    /**
     * This constructor allows for the creation of a TennisKiosk.TennisKiosk if you already
     * have a linked list full of you TennisStringer objects, a linked list of 
     * TennisString objects, and the admin password you would like to use for this 
     * tennis kiosk. Both the rackets needing strung and the rackets neeing 
     * picked up fields will be updated based on the total number of each field
     * you TennisStringer objects in the stringers list have.
     * 
     * @param stringers A linked list of TennisStringer objects
     * @param availableString A linked list of TennisString objects
     * @param adminPassword An int representing the admin password for this kiosk
     */
    public TennisKiosk(LinkedList<TennisStringer> stringers, LinkedList<TennisString> availableString, int adminID) {
        this.stringers = stringers;
        this.availableString = availableString;
        updatePickUpAndStrungCount();
        this.adminID = adminID;
    }

    /**
     * This constructor allows you to create a brand new TennisKiosk.TennisKiosk object, this
     * kiosk will be initalized with a blank list of stringers, a blank list of available
     * string, and an admin ID of 5555.
     */
    public TennisKiosk () {
        this(new LinkedList<TennisStringer>(), new LinkedList<TennisString>(), 9999);
    }

    /**
     * This method will return a shallow copy of the linked list
     * containing this kiosks TennisStringer objects.
     * 
     * @return A LinkedList<TennisStringer>
     */
    public LinkedList<TennisStringer> getStringers() {
        LinkedList<TennisStringer> retList = new LinkedList<TennisStringer>();

        for (TennisStringer ts: stringers) {
            retList.addLast(ts);
        }

        return retList;
    }

    /**
     * This method will return a shallow copy of the linked list
     * containing this kiosks TennisString objects.
     * 
     * @return A LinkedList<TennisStringer>
     */
    public LinkedList<TennisString> getString() {
        LinkedList<TennisString> retList = new LinkedList<TennisString>();

        for(TennisString ts: availableString) {
            retList.addLast(ts);
        }

        return retList;
    }

    /**
     * A getter for the number of rackets still needing to be strung 
     * by all the stringers that are a part of this kiosk.
     * 
     * @return An int represting how many rackets need to be strung
     */
    public int getRacketsNeedingStrung() {
        return racketsNeedingStrung;
    }

    /**
     * A getter for the number of rackets still needing to be picked up
     * by all the stringers tennis players who are a part of this kiosk.
     * 
     * @return An int representing rackets needing to be picked up
     */
    public int getRacketsNeedingPickedUp() {
        return racketsNeedingPickedUp;
    }

    /**
     * A getter for the admin password of this TennisKiosk.TennisKiosk
     * 
     * @return An int represting this kiosks admin password.
     */
    public int getAdminID() {
        return adminID;
    }

    /**
     * A setter for the field denoting how many rackets need to be strung
     * by the stringers of this kiosk.
     * 
     * @param racketsNeedingStrung An int number of rackets needing to be strung
     */
    public void setRacketsNeedingStrung(int racketsNeedingStrung) {
        this.racketsNeedingStrung = racketsNeedingStrung;
    }

    /**
     * A setter for the field denoting how many rackets need to be picked
     * up by the tennis players assigned to all of the tennis stringers
     * that are a part of this kiosk.
     * 
     * @param racketsNeedingPickedUp An int number of rackets needing to be picked up
     */
    public void setRacketsNeedingPickedUp(int racketsNeedingPickedUp) {
        this.racketsNeedingPickedUp = racketsNeedingPickedUp;
    }

    /**
     * A setter for this kiosks admin password
     * 
     * @param adminPassword An int to set this kiosks admin password to
     */
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    /**
     * This method allows the LinkedList<TennisStringer> stringers to be
     * updated to a new linked list holding TennisStringer objects.
     * 
     * @param stringers A LinkedList<TennisStringer> holding the entire updated list of stringers
     */
    public void updateStringerList(LinkedList<TennisStringer> stringers) {
        this.stringers = stringers;
    }

    /**
     * This method allows for the LinkedList<TennisString> availableString to be 
     * updated to a new linked list holding TennisString objects.
     * 
     * @param stringList A LinkedList<TennisString> holding the new list of available string
     */
    public void updateTennisStringList(LinkedList<TennisString> stringList) {
        availableString = stringList;
    }

    /** 
     * This method updates both the racketsNeedingStrung field and the 
     * racketsNeeingPickedUp field to match the current number of each
     * held by all the stringers that are a part of this kiosk. 
     */
    public void updatePickUpAndStrungCount() {
        racketsNeedingStrung = 0;
        racketsNeedingPickedUp = 0;
        for(TennisStringer s: stringers) {
            racketsNeedingStrung += s.getRacketsToString();
            racketsNeedingPickedUp += s.getRacketsToPickUp();
        }
    }

    /**
     * This method allows a TennisStringer object to be added
     * to the stringers linked list of this kiosk.
     * 
     * @param stringer TennisStringer object you wish to add to the stringers list
     */
    public void addStringer(TennisStringer stringer) {
        stringers.addLast(stringer);
    }

    /**
     * This method allows a TennisString object to be added 
     * to the avalibl string linked list of this kiosk.
     * 
     * @param string TennisString object you wish to add to the availible string list
     */
    public void addTennisString(TennisString string) {
        availableString.addLast(string);
    }

    /**
     * This method allows for the removal of a TennisStringer object
     * from the list stringers held by this kiosk.
     * 
     * @param stringer The TennisStringer object you wish to remove from the list
     * @return A boolean, True if the removal was successful, False otherwise 
     */
    public boolean removeStringer(TennisStringer stringer) {
        boolean retBool = stringers.remove(stringer);

        if(retBool) {
            updatePickUpAndStrungCount();
        }

        return retBool;
    }

    /**
     * This method allows for the removal of a TennisString object
     * from the list of avalible string held by this kiosk.
     * 
     * @param string The TennisString object to remove from the list
     * @return A boolean, True if the removal was successful, False otherwise
     */
    public boolean removeString(TennisString string) {
        return availableString.remove(string);
    }

}

