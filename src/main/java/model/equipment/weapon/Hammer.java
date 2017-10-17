package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Hammer extends Weapon {
    public Hammer(double weight, int quality) {
        super(WeaponType.HAMMER, weight, quality);
        isOneHanded = true;
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality * 0.10);
        damage.put(DmgType.PIERCING, quality * 0.90);
    }
}
