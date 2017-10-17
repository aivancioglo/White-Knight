package model.support.allEnums;

public enum ShieldType {
    LIGHT_SHIELD("LIGHT_SHIELD"),
    MIDDLE_SHIELD("MIDDLE_SHIELD"),
    HEAVY_SHIELD("HEAVY_SHIELD");

    public String type;

    ShieldType(String type) {
        this.type = type;
    }
}
