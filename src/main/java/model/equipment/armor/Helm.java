package model.equipment.armor;

import model.support.allEnums.ArmorMaterial;
import model.support.allEnums.ArmorType;
import model.support.allEnums.EquipmentType;

public class Helm extends Armor {
    public Helm(ArmorMaterial material, double weight, int quality) {
        super(EquipmentType.ARMOR, ArmorType.HELM, material, weight, quality);
    }
}
