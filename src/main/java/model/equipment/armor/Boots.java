package model.equipment.armor;

import model.support.allEnums.ArmorMaterial;
import model.support.allEnums.ArmorType;
import model.support.allEnums.EquipmentType;

public class Boots extends Armor {
    public Boots(ArmorMaterial material, double weight, int quality) {
        super(EquipmentType.ARMOR, ArmorType.BOOTS, material, weight, quality);
    }
}
