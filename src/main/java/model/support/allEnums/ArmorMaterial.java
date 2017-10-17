package model.support.allEnums;

public enum ArmorMaterial {
    CLOTH("CLOTH_ARMOR", 1),
    LEATHER("LEATHER_ARMOR", 2),
    CHAIN("CHAIN_ARMOR", 3),
    SCALY("SCALY_ARMOR", 4),
    LAT("LAT_ARMOR", 5);

    public String type;
    public int coefficient;

    ArmorMaterial(String type, int coefficient) {
        this.type = type;
        this.coefficient = coefficient;
    }
}
