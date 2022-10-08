package data;

import java.io.Serializable;

/**
 * MeleeWeapon constant
 */
public enum MeleeWeapon implements Serializable {
    CHAIN_SWORD,
    POWER_SWORD,
    MANREAPER,
    POWER_BLADE,
    POWER_FIST;

    /**
     *
     * @return list of constant
     */
    public static String Listing() {
        String list = "";
        for (MeleeWeapon item : values()) {
            list += item + " ";
        }
        return list;
    }
}
