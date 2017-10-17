package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class TwoHandedSword extends Weapon {
    public TwoHandedSword(double weight, int quality) {
        super(WeaponType.TWO_HANDED_SWORD, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.CUT, quality * 0.15);
        damage.put(DmgType.SLASH, quality * 0.50);
        damage.put(DmgType.PIERCING, quality * 0.35);
    }
}
