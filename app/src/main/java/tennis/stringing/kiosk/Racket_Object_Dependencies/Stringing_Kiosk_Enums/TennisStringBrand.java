package tennis.stringing.kiosk.Racket_Object_Dependencies.Stringing_Kiosk_Enums;

/**
 * Represents the tennis string brands that are suported by the TennisStringingKiosk.
 * This enum allows clear way to tell what brand of string a user chose as well
 * as a way to set a String description of each enumerated object.
 * 
 * @author Jake Gertz
 * @date 11/12/2025
 * @version 1.0
 */
public enum TennisStringBrand {
    BABOLAT("Babolat"),
    WILSON("Wilson"),
    HEAD("Head"),
    YONEX("Yonex"),
    TECNIFIBRE("Tecnifibre"),
    LUXILON("Luxilon"),
    SOLINCO("Solinco"),
    PRINCE("Prince"),
    GAMMMA("Gamma"),
    TOURNA("Tourna"),
    TOROLINE("Toroline"),
    DEFAULT("Default");

    private final String description;

    /**
     * A constructor that allows a String description to be attached to each enumerated object.
     * 
     * @param description Name of the tennis brand represented by the Enumerated object
     */
    TennisStringBrand(String description){
        this.description = description;
    }

    /**
     * Allows the String description of each object to be retreived.
     *
     * @return The String description of the Enumerated object.
     */
    @Override
    public String toString(){
        return this.description;
    }
}
