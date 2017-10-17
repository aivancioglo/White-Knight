package model.equipment.shield;

import model.support.allEnums.DmgType;
import model.support.allEnums.EquipmentType;
import model.support.allEnums.ShieldType;

import static model.support.Constants.FIVE;

public class LightShield extends Shield {
    public LightShield(double weight, int quality) {
        super(EquipmentType.SHIELD, ShieldType.LIGHT_SHIELD, weight, quality);
        price = quality / FIVE - weight * 2;
        setDefense();
    }

    private void setDefense() {
        defense.put(DmgType.BLUNT, quality * 0.20);
        defense.put(DmgType.CUT, quality * 0.20);
        defense.put(DmgType.SLASH, quality * 0.20);
        defense.put(DmgType.PIERCING, quality * 0.20);
        defense.put(DmgType.CRUSH, quality * 0.20);
    }
}
