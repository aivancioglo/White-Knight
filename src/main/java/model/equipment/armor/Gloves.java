package model.equipment.armor;

import model.support.allEnums.ArmorMaterial;
import model.support.allEnums.ArmorType;
import model.support.allEnums.EquipmentType;

public class Gloves extends Armor {
    public Gloves(ArmorMaterial material, double weight, int quality) {
        super(EquipmentType.ARMOR, ArmorType.GLOVES, material, weight, quality);
    }
}
