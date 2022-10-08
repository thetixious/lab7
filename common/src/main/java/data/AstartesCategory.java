package data;

import java.io.Serializable;

/**
 * Enumeration with marine category constants.
 */

public enum AstartesCategory implements Serializable {
    DREADNOUGHT,
    ASSAULT,
    TACTICAL;

    /**
     *
     * @return list of constant
     */
    public static String Listing() {
        String list = "";
        for (AstartesCategory item : values()) {
            list += item + " ";
        }
        return list;
    }

}
