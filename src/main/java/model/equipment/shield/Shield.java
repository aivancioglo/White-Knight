package model.equipment.shield;

import model.equipment.Equipment;
import model.support.allEnums.DmgType;
import model.support.allEnums.EquipmentType;
import model.support.allEnums.ShieldType;

import java.util.HashMap;
import java.util.Map;

public abstract class Shield extends Equipment{
    private ShieldType shieldType;

    Map<DmgType, Double> defense = new HashMap<>();

    {
        defense.put(DmgType.BLUNT, 0.0);
        defense.put(DmgType.CUT, 0.0);
        defense.put(DmgType.SLASH, 0.0);
        defense.put(DmgType.PIERCING, 0.0);
        defense.put(DmgType.CRUSH, 0.0);
    }

    public Shield(EquipmentType equipmentType, ShieldType shieldType, double weight, double quality) {
        super(equipmentType);
        this.shieldType = shieldType;
        this.weight = weight;
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public double getQuality() {
        return quality;
    }

    public double getWeight() {
        return weight;
    }

    public ShieldType getShieldType() {
        return shieldType;
    }

    public Map<DmgType, Double> getDefense() {
        return defense;
    }
}
