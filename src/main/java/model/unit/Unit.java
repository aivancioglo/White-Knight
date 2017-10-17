package model.unit;

import model.equipment.armor.Armor;
import model.equipment.shield.Shield;
import model.equipment.weapon.Weapon;
import model.support.allEnums.ArmorType;
import model.support.allEnums.DmgType;
import model.support.allEnums.EquipmentType;
import model.support.allEnums.Skill;

import java.util.HashMap;
import java.util.Map;

import static model.support.allEnums.Skill.*;

public class Unit {
    private Characteristics characteristics;
    private WarSkills skills = new WarSkills();
    private Weapon weapon;
    private Shield shield;
    private Map<ArmorType, Armor> armors = new HashMap<>();

    public Unit(String name) {
        characteristics = new Characteristics(1, 1, 1, name, true);
        calculateAdditionalParams();
        cure();
    }

    public Unit(String name, boolean isMan) {
        characteristics = new Characteristics(1, 1, 1, name, isMan);
        calculateAdditionalParams();
        cure();
    }

    public Unit(int concentration, int strength, int agility, String name, boolean isMan) {
        characteristics = new Characteristics(concentration, strength, agility, name, isMan);
        calculateAdditionalParams();
        cure();
    }

    public Map<String, Double> skills() {
        return skills.getSkills();
    }

    public double valueOf(Skill skill) {
        return skills().get(skill.type);
    }

    public String name() {
        return characteristics.name;
    }

    public double health() {
        return characteristics.health;
    }

    public double maxHealth() {
        return characteristics.maxHealth;
    }

    public double stamina() {
        return characteristics.stamina;
    }

    public double maxStamina() {
        return characteristics.maxStamina;
    }

    public int concentration() {
        return characteristics.concentration;
    }

    public int strength() {
        return characteristics.strength;
    }

    public int agility() {
        return characteristics.agility;
    }

    public Weapon weapon() {
        return weapon;
    }

    public Shield shield() {
        return shield;
    }

    public Map<ArmorType, Armor> armors() {
        return armors;
    }

    public double equipmentWeight() {
        return characteristics.equipmentWeight;
    }

    public Map<DmgType, Double> weaponDamage() {
        return characteristics.weaponDamage;
    }

    public Map<DmgType, Double> criticalWeaponDamage() {
        return characteristics.criticalWeaponDamage;
    }

    public double neededStaminaToWeaponAttack() {
        return characteristics.neededStaminaForWeaponAttack;
    }

    public double weaponAttackSpeed() {
        return characteristics.weaponAttackSpeed;
    }

    public double handToHandDamage() {
        return characteristics.handToHandDamage;
    }

    public double criticalHandToHandDamage() {
        return characteristics.criticalHandToHandDamage;
    }

    public double neededStaminaForHandToHandAttack() {
        return characteristics.neededStaminaForHandToHandAttack;
    }

    public double handToHandAttackSpeed() {
        return characteristics.handToHandAttackSpeed;
    }

    public Map<DmgType, Double> totalDefense() {
        return characteristics.totalDefense;
    }

    public Unit setConcentration(int concentration) {
        characteristics.concentration = concentration;
        return this;
    }

    public Unit setStrength(int strength) {
        characteristics.strength = strength;
        return this;
    }

    public Unit setAgility(int agility) {
        characteristics.agility = agility;
        return this;
    }

    public Unit setHealth(double health) {
        characteristics.health = health;
        return this;
    }

    public Unit setStamina(double stamina) {
        characteristics.stamina = stamina;
        return this;
    }

    public Unit setSkill(Skill skill, double value) throws Exception {
        skills.setSkill(skill, value);
        return calculateAdditionalParams();
    }

    public Unit addToSkill(Skill skill, double add) throws Exception {
        skills.addDiffToSkill(skill, add);
        return calculateAdditionalParams();
    }

    public Unit equipWeapon(Weapon weapon) {
        if (!weapon.isOneHanded() && shield == null || weapon.isOneHanded()) {
            System.out.println("Equip " + weapon.getWeaponType() + ".");
            this.weapon = weapon;

            characteristics
                    .calculateEquipmentWeight(weapon, shield, armors)
                    .calculateWeaponDamage(weapon, skills().get(weapon.getWeaponType().type));
        } else {
            System.out.println("Can not use two-handed weapon when shield is equipped.");
        }

        if (handToHandAttackSpeed() <= 0) {
            unequipWeapon();
            System.out.println("Can not equip the weapon. It's too heavy.");
        }

        return this;
    }

    public Unit unequipWeapon() {
        System.out.println("Unequip " + weapon.getWeaponType() + ".");
        weapon = null;

        characteristics
                .calculateEquipmentWeight(weapon, shield, armors)
                .calculateWeaponDamage(weapon, 0.0);

        return this;
    }

    public Unit equipShield(Shield shield) {
        if (weapon == null || weapon.isOneHanded()) {
            System.out.println("Equip " + shield.getShieldType() + ".");
            this.shield = shield;

            characteristics
                    .calculateEquipmentWeight(weapon, shield, armors)
                    .calculateTotalDefense(shield, armors, skills);

        } else {
            System.out.println("Can not use shield when two-handed weapon is equipped.");
        }

        if (handToHandAttackSpeed() <= 0) {
            unequipShild();
            System.out.println("Can not equip shield. It's too heavy.");
        }

            return this;
    }

    public Unit unequipShild() {
        System.out.println("Unequip " + shield.getShieldType() + ".");
        shield = null;
        characteristics
                .calculateEquipmentWeight(weapon, shield, armors)
                .calculateTotalDefense(shield, armors, skills);
        return this;
    }

    public Unit equipArmor(Armor armor) {
        if (armor.getEquipmentType() == EquipmentType.ARMOR) {
            System.out.println("Equip " + armor.getArmorType() + " armor.");
            armors.put(armor.getArmorType(), armor);
            characteristics
                    .calculateEquipmentWeight(weapon, shield, armors)
                    .calculateTotalDefense(shield, armors, skills);
        }

        if (handToHandAttackSpeed() <= 0) {
            unequipArmor(armor);
            System.out.println("Can not equip " + armor.getArmorType() + " armor. It's too heavy.");
        }


        return this;
    }

    public Unit unequipArmor(Armor armor) {
        if (armors.containsKey(armor.getArmorType())) {
            System.out.println("Unequip " + armor.getArmorType() + " armor.");
            armors.remove(armor.getArmorType());
            characteristics
                    .calculateEquipmentWeight(weapon, shield, armors)
                    .calculateTotalDefense(shield, armors, skills);
        }

        return this;
    }

    public Unit addToConcentration(int num) {
        if (concentration() + num < 1)
            setConcentration(1);
        else
            setConcentration(concentration() + num);

        return this;
    }

    public Unit addToStrength(int num) {
        if (strength() + num < 1)
            setStrength(1);
        else
            setStrength(strength() + num);

        return calculateDependOfStrengthParams();
    }

    public Unit addToAgility(int num) {
        if (agility() + num < 1)
            setAgility(1);
        else
            setAgility(agility() + num);

        return calculateDependOfAgilityParams();
    }

    private Unit calculateAdditionalParams() {
        return calculateDependOfStrengthParams()
                .calculateDependOfAgilityParams();
    }

    private Unit calculateDependOfStrengthParams() {
        characteristics
                .calculateMaxHealth()
                .calculateMaxStamina()
                .calculateHandToHandDamage(valueOf(HAND_TO_HAND_FIGHT_SKILL));

        return this;
    }

    private Unit calculateDependOfAgilityParams() {
        characteristics
                .calculateMaxStamina();

        return this;
    }

    private Unit setSelfCondition(int healthPercentage, int staminaPercentage) {
        if (healthPercentage < 101 && healthPercentage >= 0)
            setHealth(maxHealth() * healthPercentage);

        if (staminaPercentage < 101 && staminaPercentage >= 0)
            setStamina(maxStamina() * staminaPercentage);

        return this;
    }

    private Unit cure() {
        return setHealth(maxHealth())
                .setStamina(maxStamina());
    }
}