package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Spear extends Weapon {
    public Spear(double weight, int quality) {
        super(WeaponType.SPEAR, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.PIERCING, quality);
    }

}
