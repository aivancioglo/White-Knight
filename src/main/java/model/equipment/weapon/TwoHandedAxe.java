package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class TwoHandedAxe extends Weapon {
    public TwoHandedAxe(double weight, int quality) {
        super(WeaponType.TWO_HANDED_AXE, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality * 0.25);
        damage.put(DmgType.SLASH, quality * 0.75);
    }
}
