package tennis.stringing.kiosk.Racket_Object_Dependencies;

import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisStringBrand;

/**
 * The TennisString class is used to represent a string that is 
 * used within a tennis racket. A TennisString object has the
 * following fields
 * 
 * The brand of the string
 * The name of the string
 * If the string is in stock or not
 * How much of the string is in stock
 * 
 * @author Jake Gertz
 * @date 11/13/2025
 * @version 1.0
 */
public class TennisString {
    private TennisStringBrand stringBrand;
    private String stringName;
    private Boolean inStock;
    private int lengthInStock;

    /**
     * A construtor allowing for the creation of a TennisString object initialized with
     * the brand of the string, the name of the string, weather the string is in stock or not,
     * and the length of string left in stock.
     * 
     * @param stringBrand A TennisStringBrand object representing this strings brand 
     * @param stringName A String representing the name of this string
     * @param inStock A boolean represeting if this string is in stock (true) or not (fasle)
     * @param lengthInStock An int representing how many feet of this string is left in stock
     */
    public TennisString(TennisStringBrand stringBrand, String stringName, Boolean inStock, int lengthInStock) {
        this.stringBrand = stringBrand;
        this.stringName = stringName;
        this.inStock = inStock;
        this.lengthInStock = lengthInStock;
    }

    /**
     * An overloaded constructor that allows a TennisString object to be created
     * with only the brand and name of the string. The in stock and length in 
     * stock perameters are set to false and 0 respectivly in the event this 
     * constructor is called
     * 
     * @param stringBrand A TennisStringBrand object representing this strings brand
     * @param stringName A String representing the name of this string
     */
    public TennisString(TennisStringBrand stringBrand, String stringName) {
        this(stringBrand, stringName, false, 0);
    }

    /**
     * A setter that allows the inStock boolean to be updated
     * 
     * @param inStock A boolean: True for in stock, False for out of stock
     */
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    /**
     * A method that allows for a provided length of string to be subtracted from the length
     * in stock. If the length in stock is less than or equal to 0 after the subtraction 
     * the in stock boolean is updated to fasle and the length in stock is set to 0.
     * 
     * @param lengthToSubtract An int representing how many feet to subtract from the length in stock
     * @return An int representing how much string is in stock after the subtraction 
     */
    public int subtractLengthInStock(int lengthToSubtract) {
        lengthInStock -= lengthToSubtract;

        if (lengthInStock < 1) {
            inStock = false;
            lengthInStock = 0;
        }

        return lengthInStock;
    }

    /**
     * A method that allows for a provided length of string to be added to the length in
     * stock. If after the addition the length of string in stock is greater than 0 feet
     * updates the in stock boolean to true.
     * 
     * @param lengthToAdd An int representing how many feet to add to length in stock
     * @return An int representing how many feet of string is in stock after the addtion
     */
    public int addLengthInStock(int lengthToAdd) {
        lengthInStock += lengthToAdd;

        if(lengthInStock > 0) {
            inStock = true;
        }

        return (lengthInStock);
    }

    /**
     * A getter that returns a boolean representing weather the string is in stock or not
     * 
     * @return A boolean: True for in stock, False for out of stock
     */
    public boolean getInStock() {
        return inStock;
    }

    /**
     * A getter that returns the length of string left in stock. If the string is not
     * in stock will return 0
     * 
     * @return An int representing the length in stock, returns 0 if not in stock
     */
    public int getLengthInStock() {
        return (inStock ? lengthInStock : 0);
    }

    /**
     * Returns a String represeting this tennis string
     * 
     * Format: 
     *     String Brand:    "brand name"
     *     StringName:    "string name"
     * 
     * @return A String represnting this TennisString object
     */
    @Override
    public String toString() {
        return ("String Brand:    " + stringBrand.toString() + "\nString Name:    " + stringName);
    }

}

