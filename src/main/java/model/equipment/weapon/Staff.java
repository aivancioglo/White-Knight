package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Staff extends Weapon {
    public Staff(double weight, int quality) {
        super(WeaponType.STAFF, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality);
    }
}
