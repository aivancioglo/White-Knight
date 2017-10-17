package model.support.allEnums;

public enum WeaponType {
    KNIFE("KNIFE"),
    SWORD("SWORD"),
    AXE("AXE"),
    MACE("MACE"),
    HAMMER("HAMMER"),

    TWO_HANDED_SWORD("TWO_HANDED_SWORD"),
    TWO_HANDED_AXE("TWO_HANDED_AXE"),
    TWO_HANDED_MACE("TWO_HANDED_MACE"),
    TWO_HANDED_HAMMER("TWO_HANDED_HAMMER"),
    SPEAR("SPEAR"),
    HALBERD("HALBERD"),
    STAFF("STAFF");

    public String type;

    WeaponType(String type) {
        this.type = type;
    }
}