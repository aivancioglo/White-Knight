package model.equipment.shield;

import model.support.allEnums.DmgType;
import model.support.allEnums.EquipmentType;
import model.support.allEnums.ShieldType;

import static model.support.Constants.FIVE;
import static model.support.Constants.THREE;

public class HeavyShield extends Shield {
    public HeavyShield(double weight, int quality) {
        super(EquipmentType.SHIELD, ShieldType.HEAVY_SHIELD, weight, quality);
        price = quality / FIVE * THREE - weight * 2;
        setDefense();
    }

    private void setDefense() {
        defense.put(DmgType.BLUNT, quality * 0.20 * 3);
        defense.put(DmgType.CUT, quality * 0.20 * 3);
        defense.put(DmgType.SLASH, quality * 0.20 * 3);
        defense.put(DmgType.PIERCING, quality * 0.20 * 3);
        defense.put(DmgType.CRUSH, quality * 0.20 * 3);
    }
}
