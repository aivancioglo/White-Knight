package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class TwoHandedHammer extends Weapon {
    public TwoHandedHammer(double weight, int quality) {
        super(WeaponType.TWO_HANDED_HAMMER, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality * 0.65);
        damage.put(DmgType.PIERCING, quality * 0.35);
    }
}
