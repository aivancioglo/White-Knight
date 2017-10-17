package model.equipment.weapon;

import model.equipment.Equipment;
import model.support.allEnums.DmgType;
import model.support.allEnums.EquipmentType;
import model.support.allEnums.WeaponType;

import java.util.HashMap;
import java.util.Map;

import static model.support.Constants.FIVE;
import static model.support.Constants.TWO;

public abstract class Weapon extends Equipment {
    protected WeaponType weaponType;
    protected boolean isOneHanded;

    protected Map<DmgType, Double> damage = new HashMap<>();

    {
        damage.put(DmgType.BLUNT, 0.0);
        damage.put(DmgType.CUT, 0.0);
        damage.put(DmgType.SLASH, 0.0);
        damage.put(DmgType.PIERCING, 0.0);
        damage.put(DmgType.CRUSH, 0.0);
    }

    public Weapon(WeaponType weaponType, double weight, int quality) {
        super(EquipmentType.WEAPON);
        this.weaponType = weaponType;
        this.weight = weight;
        this.quality = quality;
        this.price = quality / FIVE - weight * TWO;
    }

    public double getPrice() {
        return price;
    }

    public double getQuality() {
        return quality;
    }

    public double getWeight() {
        return weight;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public boolean isOneHanded() {
        return isOneHanded;
    }

    public Map<DmgType, Double> getDamage() {
        return damage;
    }
}
