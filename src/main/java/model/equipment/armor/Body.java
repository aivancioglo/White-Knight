package model.equipment.armor;

import model.support.allEnums.ArmorMaterial;
import model.support.allEnums.ArmorType;
import model.support.allEnums.EquipmentType;

public class Body extends Armor {
    public Body(ArmorMaterial material, double weight, int quality) {
        super(EquipmentType.ARMOR, ArmorType.BODY, material, weight, quality);
    }
}
