package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Mace extends Weapon {
    public Mace(double weight, int quality) {
        super(WeaponType.MACE, weight, quality);
        isOneHanded = true;
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality * 0.35);
        damage.put(DmgType.CRUSH, quality * 0.65);
    }
}
