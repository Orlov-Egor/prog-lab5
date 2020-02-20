package data;

public enum MeleeWeapon {
    CHAIN_SWORD,
    CHAIN_AXE,
    LIGHTING_CLAW,
    POWER_BLADE,
    POWER_FIST;

    public static String nameList() {
        String nameList = "";
        for (MeleeWeapon meleeWeapon : values()) {
            nameList += meleeWeapon.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
