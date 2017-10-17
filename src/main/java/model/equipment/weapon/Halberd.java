package model.equipment.weapon;

import model.support.allEnums.DmgType;
import model.support.allEnums.WeaponType;

public class Halberd extends Weapon {
    public Halberd(double weight, int quality) {
        super(WeaponType.HALBERD, weight, quality);
        setDamage();
    }

    private void setDamage() {
        damage.put(DmgType.BLUNT, quality * 0.05);
        damage.put(DmgType.CUT, quality * 0.05);
        damage.put(DmgType.PIERCING, quality * 0.35);
        damage.put(DmgType.SLASH, quality * 0.55);
    }
}
