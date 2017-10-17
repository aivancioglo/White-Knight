package model.unit;

import model.equipment.armor.Armor;
import model.equipment.shield.Shield;
import model.equipment.weapon.Weapon;
import model.support.allEnums.ArmorType;
import model.support.allEnums.DmgType;

import java.util.HashMap;
import java.util.Map;

import static model.support.Constants.*;
import static model.support.allEnums.Skill.*;

public class Characteristics {
    public int strength;
    public int agility;
    public int concentration;

    public final String name;
    public final boolean isMan;
    public final boolean isRightHanded;

    public double health;
    public double stamina;

    public double maxHealth;
    public double maxStamina;

    public double equipmentWeight;

    public double handToHandDamage;
    public double criticalHandToHandDamage;

    public Map<DmgType, Double> weaponDamage = new HashMap<>();
    public Map<DmgType, Double> criticalWeaponDamage = new HashMap<>();

    public Map<DmgType, Double> totalDefense = new HashMap<>();

    public double neededStaminaForHandToHandAttack;
    public double neededStaminaForWeaponAttack;
    public double weaponAttackSpeed;
    public double handToHandAttackSpeed;
    public double attackDuration;
    public double neededStaminaToDodge;
    public double dodgeFrequency;

    {
        totalDefense.put(DmgType.SLASH, 0.0);
        totalDefense.put(DmgType.CUT, 0.0);
        totalDefense.put(DmgType.PIERCING, 0.0);
        totalDefense.put(DmgType.BLUNT, 0.0);
        totalDefense.put(DmgType.CRUSH, 0.0);
    }

    public Characteristics(int concentration, int strength, int agility, String name, boolean isMan) {
        this.concentration = concentration;
        this.strength = strength;
        this.agility = agility;
        this.name = name;
        this.isMan = isMan;

        isRightHanded = Math.random() < 0.98;
    }

    public Characteristics calculateMaxHealth() {
        maxHealth = strength * TEN;
        return this;
    }

    public Characteristics calculateMaxStamina() {
        maxStamina = strength * TWENTY + agility * TEN;
        return this;
    }

    public Characteristics calculateHandToHandDamage(double handsToHandFightSkill) {
        handToHandDamage = (strength * FIVE) * handsToHandFightSkill * (agility / TEN) + ONE;
        return calculateCriticalHandToHandDamage();
    }

    public Characteristics calculateCriticalHandToHandDamage() {
        criticalHandToHandDamage = handToHandDamage * (ONE + concentration / FIVE);
        return calculateNeededStaminaForHandToHandAttack()
                .calculateHandToHandAttackSpeed();
    }

    public Characteristics calculateEquipmentWeight(Weapon weapon, Shield shield, Map<ArmorType, Armor> armors) {
        System.out.println("Calculate equipment weight.");
        double[] weight = new double[]{0.0};

        if (weapon != null)
            weight[0] += weapon.getWeight();

        if (shield != null)
            weight[0] += shield.getWeight();

        armors.forEach((k, v) -> weight[0] += v.getWeight());
        equipmentWeight = weight[0];
        System.out.println("Equipment weight: " + equipmentWeight + ".");
        return calculateNeededStaminaForWeaponAttack(weapon)
                .calculateNeededStaminaForHandToHandAttack()
                .calculateHandToHandAttackSpeed();
    }

    public Characteristics calculateWeaponDamage(Weapon weapon, double skill) {
        if (weapon == null) {
            weaponDamage = new HashMap<>();
            return calculateCriticalWeaponDamage(weapon)
                    .calculateNeededStaminaForWeaponAttack(weapon);
        }

        weaponDamage = new HashMap<>();
        weapon.getDamage().forEach((k, v) -> weaponDamage.put(k, v / FIVE * (strength * FIVE + skill + agility / TEN)));
        return calculateCriticalWeaponDamage(weapon)
                .calculateNeededStaminaForWeaponAttack(weapon);
    }

    public Characteristics calculateCriticalWeaponDamage(Weapon weapon) {
        if (weapon == null) {
            criticalWeaponDamage = new HashMap<>();
            return calculateWeaponAttackSpeed(weapon);
        }

        criticalWeaponDamage = new HashMap<>();
        weapon.getDamage().forEach((k, v) -> criticalWeaponDamage.put(k, weaponDamage.get(k) * (ONE + concentration / FIVE)));
        return calculateWeaponAttackSpeed(weapon);
    }

    public Characteristics calculateTotalDefense(Shield shield, Map<ArmorType, Armor> armors, WarSkills skills) {
        clearTotalDefense();

        armors.forEach((t, a) ->
                a.getDefense().forEach((k, v) -> {
                    totalDefense.put(k,
                            (skills.valueOf(MAIN_BATTLE_EXPERIENCE) * THREE + skills.valueOf(ARMOR_SKILL) * TWO + skills.valueOf(a.getMaterial().type) + TWENTY) /
                                    HUNDRED * v + totalDefense.get(k));
                }));

        if (shield != null)
            shield.getDefense().forEach((k, v) -> totalDefense.put(k,
                    (skills.valueOf(MAIN_BATTLE_EXPERIENCE) * THREE + skills.valueOf(SHIELD_BLOCK_SKILL) * TWO + skills.valueOf(shield.getShieldType().type) + TWENTY) /
                            HUNDRED * v + totalDefense.get(k)));

        return this;
    }

    public Characteristics calculateNeededStaminaForHandToHandAttack() {
        neededStaminaForHandToHandAttack = equipmentWeight / TWO - strength + ONE;

        if (neededStaminaForHandToHandAttack < ONE)
            neededStaminaForHandToHandAttack = ONE;

        return this;
    }

    public Characteristics calculateNeededStaminaForWeaponAttack(Weapon weapon) {
        if (weapon == null) {
            neededStaminaForWeaponAttack = 0;
            return this;
        }

        neededStaminaForWeaponAttack = weapon.getWeight() + ((equipmentWeight - weapon.getWeight()) / TWO) - strength + ONE;

        if (neededStaminaForWeaponAttack < ONE)
            neededStaminaForWeaponAttack = ONE;

        return this;
    }

    public Characteristics calculateHandToHandAttackSpeed() {
        System.out.print("Calculate had-to-hand attack speed: ");
        double y = (equipmentWeight / TWO - strength) / TEN;

        if (y < 0) {
            y = 0;
        }

        handToHandAttackSpeed = (agility / FIVE + ONE / TWO) - y;

        System.out.println(handToHandAttackSpeed);
        return this;
    }

    public Characteristics calculateWeaponAttackSpeed(Weapon weapon) {

        if (weapon == null) {
            System.out.println("Calculate attack speed without weapon.");
            weaponAttackSpeed = 0;
            return this;
        }

        System.out.println("Calculate attack speed with " + weapon.getWeaponType() + ".");

        double a = weapon.getWeight() / TEN;
        double b;

        if (a <= 0) {
            b = (equipmentWeight - weapon.getWeight()) / TWO + a;

            if (b <= 0)
                weaponAttackSpeed = (agility / TEN + ONE / TWO);
            else
                weaponAttackSpeed = (agility / TEN + ONE / TWO) + (b / TEN);

        } else if (a > 0) {
            b = (equipmentWeight - weapon.getWeight()) / TWO;

            if (b <= 0)
                weaponAttackSpeed = (agility / TEN + ONE / TWO) - (a / TEN);
            else
                weaponAttackSpeed = (agility / TEN + ONE / TWO) - (a / TEN) + (b / TEN);
        }

        return this;
    }

    public Characteristics calculateAttackDuration() {
        return this;
    }

    public Characteristics calculateNeededStaminaToDodge() {
        return this;
    }

    public Characteristics calculateDodgeFrequency() {
        return this;
    }

    private Characteristics clearTotalDefense() {
        totalDefense = new HashMap<>();

        totalDefense.put(DmgType.SLASH, 0.0);
        totalDefense.put(DmgType.CUT, 0.0);
        totalDefense.put(DmgType.PIERCING, 0.0);
        totalDefense.put(DmgType.BLUNT, 0.0);
        totalDefense.put(DmgType.CRUSH, 0.0);

        return this;
    }
}