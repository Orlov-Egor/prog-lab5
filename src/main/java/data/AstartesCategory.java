package data;

public enum AstartesCategory {
    DREADNOUGHT,
    ASSAULT,
    TACTICAL,
    CHAPLAIN,
    APOTHECARY;

    public static String nameList() {
        String nameList = "";
        for (AstartesCategory category : values()) {
            nameList += category.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
