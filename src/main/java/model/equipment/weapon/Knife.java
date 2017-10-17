package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Knife extends Weapon {
    public Knife(double weight, int quality) {
        super(WeaponType.KNIFE, weight, quality);
        isOneHanded = true;
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.CUT, quality * 0.50);
        damage.put(DmgType.SLASH, quality * 0.10);
        damage.put(DmgType.PIERCING, quality * 0.40);
    }
}
