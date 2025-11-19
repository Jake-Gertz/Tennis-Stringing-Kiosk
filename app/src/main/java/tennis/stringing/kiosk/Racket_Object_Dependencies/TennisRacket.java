package tennis.stringing.kiosk.Racket_Object_Dependencies;

import java.time.LocalDate;

import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisRacketBrand;
import tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums.TennisStringBrand;

/**
 * The tennis racket class is used to represent a tennis racket object and to 
 * hold all relevant information that would be required by a stringer or user 
 * regarding the tennis racket. A Tennis racket has atributes of the following 
 * denotion.
 * 
 * Racket brand
 * Main string tension
 * Cross string tension
 * Type of tennis string for the Mains
 * Type of tennis string for the Crosses
 * Last date strung
 * 
 * @author Jake Gertz
 * @date 11/11/2025
 * @version 1.0
 */
public class TennisRacket {
    private TennisRacketBrand racketBrand;
    private String racketModelName;
    private int mainTension;
    private int crossTension;
    private TennisString mainString;
    private TennisString crossString;
    private LocalDate lastStrung;

    /**
        Constants included in class:

            DEFAULT_TENSION, DEFAULT_STRING, DEFAULT_BRAND

            Default tension represents the default tension you want to assign to a
                racket if there is no provided tension value.

            Default string represents the default string you want to assign to a 
                racket if a prefered string is not specified.

            Default brand represents the default tennis racket brand you want to
                assign to a racket if no brand is specified.
     */
    private static final int DEFAULT_TENSION = 54;
    private static final TennisString DEFAULT_STRING = new TennisString(TennisStringBrand.DEFAULT, "DEFAULT");
    private static final TennisRacketBrand DEFAULT_BRAND = TennisRacketBrand.DEFAULT;

    /**
     * This constructor allows the creation of a tennis racket object.
     * 
     * @param racketBrand brand of racket to assign to this object
     * @param mainString brand of string wanted for this rackets main strings
     * @param crossString brand of string wanted for this rackets cross strings
     * @param mainTension tension prefered for this rackets main strings
     * @param crossTension tension prefered for this rackets cross strings
     */
    public TennisRacket(TennisRacketBrand racketBrand, TennisString mainString, TennisString crossString, int mainTension, int crossTension) {
        this.racketBrand = racketBrand;
        this.mainString = mainString;
        this.crossString = crossString;
        this.mainTension = mainTension;
        this.crossTension = crossTension;
        this.racketModelName = "DEFAULT";
    }

    /**
     * This overloaded consructor allows the creation of a racket object without a
     * specified brand.
     * 
     * @param mainString brand of string wanted for this rackets main strings
     * @param crossString brand of string wanted for this rackets cross strings
     * @param mainTension tension prefered for this rackets main strings
     * @param crossTension tension prefered for this rackets cross strings
     */
    public TennisRacket(TennisString mainString, TennisString crossString, int mainTension, int crossTension) {
        this(DEFAULT_BRAND, mainString, crossString, mainTension, crossTension);
    }

    /**
     * This overloaded constructor allows the creation of a racket object with just 
     * a single provided tension and string argument.
     * 
     * @param tension Tension for mains crosses 
     * @param string Tennis strings for mains and crosses 
     */
    public TennisRacket(int tension, TennisString string) {
        this(string, string, tension, tension);
    }

    /**
     * Allows for the creation of a tennis racket object with all default options.
     */
    public TennisRacket() {
        this(DEFAULT_BRAND, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_TENSION, DEFAULT_TENSION);
    }

    /**
     * A getter that returns the TennisRacketBrand object the racket holds
     * 
     * @return this rackets TennisRacketBrand
     */
    public TennisRacketBrand getRacketBrand() {
        return racketBrand;
    }

    /**
     * A getter that returns the String representing this rackets
     * model name.
     * 
     * @return A String from this rackets racketModelName field
     */
    public String getRacketModelName() {
        return racketModelName;
    }

    /**
     * A getter that returns the toString of the racket brand this racket holds
     * 
     * @return A string representing the brand of racket this object is
     */
    public String racketBrandToString() {
        return racketBrand.toString();
    }

    /**
     * A getter that returns the main strings TennisString object of this racket
     * 
     * @return Returns a TennisString object
     */
    public TennisString getMainString() {
        return mainString;
    }


    /**
     * A getter that returns the cross strings TennisString object of this racket
     * 
     * @return Returns a TennisString object
     */
    public TennisString getCrossString() {
        return crossString;
    }

    /**
     * Returns a Strings representing the main and cross strings of this racket.
     * 
     * Format:
     *     Main String:    "main strings"
     *     Cross String:    "cross strings"
     * 
     * @return Returns a String 
     */
    public String tennisStringsToString() {
        return ("Main String:    " + mainString.toString() + "\n\nCross String:    " + crossString.toString());
    }

    /**
     * Returns an int representing the main strings tension of this racket
     * 
     * @return An int 
     */
    public int getMainTension() {
        return mainTension;
    }

    /**
     * Returns an int representing the cross strings tension of this racket
     * 
     * @return An int
     */
    public int getCrossTension() {
        return crossTension;
    }

    /**
     * Returns a String representing the both the main and cross string tensions 
     * of this racket
     * 
     * Format:
     *     Main Tension:    "int tension"
     *     Cross Tension:    "int tension" 
     * 
     * @return A string represting the tension of this rackets strings
     */
    public String tensionsToString() {
        return ("Main Tension:    " + mainTension + "\nCross Tension:    " + crossTension);
    }

    /**
     * Returns a String representing the main strings tension and brand as well
     * as the cross strings tension and brand
     * 
     * Format:
     *     Main String:    "main string String"    Tension:    "int tension"
     *     Cross String:    "cross string String"    Tension:    "int tension"
     * 
     * @return A well formated string representing this rackets strings tension and brands
     */
    public String stringsToString() {
        StringBuilder retString = new StringBuilder();
        retString.append("Main String:    " + mainString.toString());
        retString.append("    Tension:    " + mainTension + " \n");
        retString.append("Cross String:    " + crossString.toString());
        retString.append("    Tension:    " + crossTension);

        return retString.toString();
    }

    /**
     * Returns the LocalDate object that represents the last time this 
     * racket was strung
     * 
     * @return A LocalDate object
     */
    public LocalDate getLastStrung() {
        return lastStrung;
    }

    /**
     * Returns a String representation of the LocalDate object this 
     * racket holds representing the last date the racket was strung.
     * 
     * @return A String representation of a LocalDate object
     */
    public String lastStrungToString() {
        return lastStrung.toString();
    }

    /**
     * A setter that allows you to update the TennisRacketBrand object
     * field named racketBrand that this racket holds
     * 
     * @param racketBrand A TennisRacketBrand object
     */
    public void setRacketBrand(TennisRacketBrand racketBrand){
        this.racketBrand = racketBrand;
    }

    /**
     * A setter that allows you to this rackets model name.
     * 
     * @param racketName
     */
    public void setRacketModelName(String racketModelName) {
        this.racketModelName = racketModelName;
    }

    /**
     * A setter that allows you to update the TennisString object
     * field named mainString that this racket holds
     * 
     * @param mainString A TennisString object you want to update the main strings with
     */
    public void setMainString(TennisString mainString) {
        this.mainString = mainString;
    }

    /**
     * A setter that allows you to update the TennisString object
     * field named crossString that this racket holds
     * 
     * @param crossString A TennisString object you want to update the cross strings with
     */
    public void setCrossString(TennisString crossString) {
        this.crossString = crossString;
    }

    /**
     * A setter that allows you to update the TennisString object
     * assigned to both mainString and crossString fields this
     * racket holds with the same TennisString object
     * 
     * @param strings A TennisString object you want to update this rackets strings with
     */
    public void setRacketStrings(TennisString strings) {
        setMainString(strings);
        setCrossString(strings);
    }

    /**
     * A setter that allows you to update the int field mainTension
     * that this racket holds
     * 
     * @param mainTension An int that you want to set mainTension to
     */
    public void setMainTension(int mainTension) {
        this.mainTension = mainTension;
    }

    /**
     * A setter that allows you to update the int field crossTension
     * that this racket holds
     * 
     * @param crossTension An int that you want to set crossTension to
     */
    public void setCrossTension(int crossTension) {
        this.crossTension = crossTension;
    }

    /**
     * A setter that allows you to update the int fields mainTension
     * and crossTension at the same time with the same int value
     * 
     * @param tensions An int that you want to update this rackets tension fields to
     */
    public void setRacketTension(int tensions) {
        setMainTension(tensions);
        setCrossTension(tensions);
    }

    /**
     * A setter that allows you to update the LocalDate object
     * this racket holds representing the last date this racket
     * was strung
     * 
     * @param date The LocalDate object you wish to update this racket with
     */
    public void setLastStrungDate(LocalDate date) {
        lastStrung = date;
    }

    /**
     * A setter that will set the lastStrung field of this racket to the 
     * current date
     */
    public void setLastStrung() {
        lastStrung = LocalDate.now();
    }

    /**
     * Returns a String represnting this racket object
     * 
     * Format:
     *     Racket Brand:    "racket brand"
     *     Racket Name:    "racket model name"
     * 
     *     Main String:    "main string String"    Tension:    "int tension"
     *     Cross String:    "cross string String"    Tension:    "int tension"
     * 
     *     Last Strung:    "last strung date"
     * 
     * @return A string representing this racket object 
     */
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder();
        retString.append("Racket Brand:    " + racketBrandToString() + "\n");
        retString.append("Racket Name:    " + racketModelName + "\n\n");
        retString.append(stringsToString() + " \n\n");
        if(lastStrung != null) {
            retString.append("Last Strung:    " + lastStrung.toString());
        } else {
            retString.append("Last Strung:    NO PRIOR DATA");
        }

        return retString.toString();
    }

}

