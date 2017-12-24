package model.support.enums

import model.support.Constants

enum class EquipmentType {
    ARMOR, WEAPON, SHIELD, NONE
}

enum class ArmorMaterial(var coefficient: Int) {
    CLOTH(Constants.ONE),
    LEATHER(Constants.TWO),
    CHAIN(Constants.THREE),
    SCALY(Constants.FOUR),
    LAT(Constants.FIVE)
}

enum class ArmorType {
    HELM, RIGHT_SHOULDER_ARMOR, LEFT_SHOULDER_ARMOR, GLOVES, BODY_ARMOR, RIGHT_LEG_ARMOR, LEFT_LEG_ARMOR, BOOTS
}

enum class WeaponType {
    KNIFE, SWORD, AXE, MACE, HAMMER,

    TWO_HANDED_SWORD, TWO_HANDED_AXE, TWO_HANDED_MACE,
    TWO_HANDED_HAMMER, SPEAR, HALBERD, STAFF
}

enum class ShieldType {
    LIGHT_SHIELD, MIDDLE_SHIELD, HEAVY_SHIELD;
}
