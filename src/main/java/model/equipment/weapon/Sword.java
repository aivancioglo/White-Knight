package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Sword extends Weapon {
    public Sword(double weight, int quality) {
        super(WeaponType.SWORD, weight, quality);
        isOneHanded = true;
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.CUT, quality * 0.15);
        damage.put(DmgType.SLASH, quality * 0.50);
        damage.put(DmgType.PIERCING, quality * 0.35);
    }
}
