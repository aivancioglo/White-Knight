package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Axe extends Weapon {
    public Axe(double weight, int quality) {
        super(WeaponType.AXE, weight, quality);
        isOneHanded = true;
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality * 0.25);
        damage.put(DmgType.SLASH, quality * 0.75);
    }
}
