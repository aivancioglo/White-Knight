package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class TwoHandedMace extends Weapon {
    public TwoHandedMace(double weight, int quality) {
        super(WeaponType.TWO_HANDED_MACE, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.PIERCING, quality * 0.10);
        damage.put(DmgType.SLASH, quality * 0.90);
    }
}
