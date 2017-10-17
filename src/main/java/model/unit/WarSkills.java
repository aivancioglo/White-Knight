package model.unit;

import model.support.allEnums.Skill;

import java.util.HashMap;
import java.util.Map;

import static model.support.Constants.FIVE;
import static model.support.Constants.TEN;
import static model.support.allEnums.Skill.*;
import static model.support.allEnums.Skill.FOOTS_FIGHT_SKILL;

public class WarSkills {
    private Map<String, Double> skills = new HashMap<>();

    {
        skills.put(MAIN_BATTLE_EXPERIENCE.type, 0.0);
        skills.put(ONE_HANDED_WEAPON_SKILL.type, 0.0);
        skills.put(TWO_HANDED_WEAPON_SKILL.type, 0.0);
        skills.put(SHIELD_BLOCK_SKILL.type, 0.0);
        skills.put(ARMOR_SKILL.type, 0.0);
        skills.put(HAND_TO_HAND_FIGHT_SKILL.type, 0.0);

        skills.put(KNIFE_SKILL.type, 0.0);
        skills.put(SWORD_SKILL.type, 0.0);
        skills.put(AXE_SKILL.type, 0.0);
        skills.put(MACE_SKILL.type, 0.0);
        skills.put(HAMMER_SKILL.type, 0.0);

        skills.put(TWO_HANDED_SWORD_SKILL.type, 0.0);
        skills.put(TWO_HANDED_AXE_SKILL.type, 0.0);
        skills.put(TWO_HANDED_MACE_SKILL.type, 0.0);
        skills.put(TWO_HANDED_HAMMER_SKILL.type, 0.0);
        skills.put(STAFF_SKILL.type, 0.0);
        skills.put(SPEAR_SKILL.type, 0.0);
        skills.put(HALBERD_SKILL.type, 0.0);

        skills.put(LIGHT_SHIELD_SKILL.type, 0.0);
        skills.put(MIDDLE_SHIELD_SKILL.type, 0.0);
        skills.put(HEAVY_SHIELD_SKILL.type, 0.0);

        skills.put(CLOTH_ARMOR_SKILL.type, 0.0);
        skills.put(LEATHER_ARMOR_SKILL.type, 0.0);
        skills.put(CHAIN_ARMOR_SKILL.type, 0.0);
        skills.put(SCALY_ARMOR_SKILL.type, 0.0);
        skills.put(LAT_ARMOR_SKILL.type, 0.0);

        skills.put(HANDS_FIGHT_SKILL.type, 0.0);
        skills.put(FOOTS_FIGHT_SKILL.type, 0.0);
        skills.put(RIGHT_HAND_FIGHT_SKILL.type, 0.0);
        skills.put(LEFT_HAND_FIGHT_SKILL.type, 0.0);
        skills.put(RIGHT_FOOT_FIGHT_SKILL.type, 0.0);
        skills.put(LEFT_FOOT_FIGHT_SKILL.type, 0.0);
    }

    public Map<String, Double> getSkills() {
        return skills;
    }

    public WarSkills setSkill(Skill type, double value) throws Exception {
        switch (type) {
            case HANDS_FIGHT_SKILL:
            case FOOTS_FIGHT_SKILL:
                setSkillValue(type, value);
                return setHandToHandFightSkill();

            case CLOTH_ARMOR_SKILL:
            case CHAIN_ARMOR_SKILL:
            case LEATHER_ARMOR_SKILL:
            case SCALY_ARMOR_SKILL:
            case LAT_ARMOR_SKILL:
                setSkillValue(type, value);
                return setArmorSkill();

            case LIGHT_SHIELD_SKILL:
            case MIDDLE_SHIELD_SKILL:
            case HEAVY_SHIELD_SKILL:
                setSkillValue(type, value);
                return setShieldBlockSkill();

            case KNIFE_SKILL:
            case SWORD_SKILL:
            case AXE_SKILL:
            case MACE_SKILL:
            case HAMMER_SKILL:
                setSkillValue(type, value);
                return setOneHandedWeaponSkill();

            case TWO_HANDED_SWORD_SKILL:
            case TWO_HANDED_AXE_SKILL:
            case TWO_HANDED_MACE_SKILL:
            case TWO_HANDED_HAMMER_SKILL:
            case HALBERD_SKILL:
            case SPEAR_SKILL:
            case STAFF_SKILL:
                setSkillValue(type, value);
                return setTwoHandedWeaponSkill();

            default:
                throw new Exception("Invalid type.");
        }
    }

    public WarSkills addDiffToSkill(Skill type, double diff) throws Exception {
        switch (type) {
            case HANDS_FIGHT_SKILL:
            case FOOTS_FIGHT_SKILL:
                increaseSkill(type, diff);
                return setHandToHandFightSkill();

            case CLOTH_ARMOR_SKILL:
            case CHAIN_ARMOR_SKILL:
            case LEATHER_ARMOR_SKILL:
            case SCALY_ARMOR_SKILL:
            case LAT_ARMOR_SKILL:
                increaseSkill(type, diff);
                return setArmorSkill();

            case LIGHT_SHIELD_SKILL:
            case MIDDLE_SHIELD_SKILL:
            case HEAVY_SHIELD_SKILL:
                increaseSkill(type, diff);
                return setShieldBlockSkill();

            case KNIFE_SKILL:
            case SWORD_SKILL:
            case AXE_SKILL:
            case MACE_SKILL:
            case HAMMER_SKILL:
                increaseSkill(type, diff);
                return setOneHandedWeaponSkill();

            case TWO_HANDED_SWORD_SKILL:
            case TWO_HANDED_AXE_SKILL:
            case TWO_HANDED_MACE_SKILL:
            case TWO_HANDED_HAMMER_SKILL:
            case HALBERD_SKILL:
            case SPEAR_SKILL:
            case STAFF_SKILL:
                increaseSkill(type, diff);
                return setTwoHandedWeaponSkill();

            default:
                throw new Exception("Invalid type.");
        }
    }

    public double valueOf(Skill skill) {
        return skills.get(skill.type);
    }

    public double valueOf(String skill) {
        return skills.get(skill);
    }

    private WarSkills setSkillValue(Skill type, double value) {
        if (value < 0)
            skills.put(type.type, 0.0);
        else
            skills.put(type.type, value);

        return this;
    }

    private WarSkills increaseSkill(Skill type, double diff) {
        if (skills.get(type.type) + diff < 0)
            skills.put(type.type, 0.0);
        else
            skills.put(type.type, skills.get(type.type) + diff);

        return this;
    }

    private WarSkills setMainBattleExperience() {
        double oneHandedWeaponSkill = valueOf(ONE_HANDED_WEAPON_SKILL);
        double twoHandedWeaponSkill = valueOf(TWO_HANDED_WEAPON_SKILL);
        double shieldBlockSkill = valueOf(SHIELD_BLOCK_SKILL);
        double armorSkill = valueOf(ARMOR_SKILL);
        double handToHandFightSkill = valueOf(HAND_TO_HAND_FIGHT_SKILL);

        double mainBattleExperience = oneHandedWeaponSkill + twoHandedWeaponSkill + shieldBlockSkill + armorSkill + handToHandFightSkill;
        mainBattleExperience = mainBattleExperience / FIVE;
        skills.put(MAIN_BATTLE_EXPERIENCE.type, mainBattleExperience);

        return this;
    }

    private WarSkills setOneHandedWeaponSkill() {
        double knifeSkill = valueOf(KNIFE_SKILL);
        double swordSkill = valueOf(SWORD_SKILL);
        double axeSkill = valueOf(AXE_SKILL);
        double maceSkill = valueOf(MACE_SKILL);
        double hammerSkill = valueOf(HAMMER_SKILL);

        double oneHandedWeaponSkill = (knifeSkill + swordSkill + axeSkill + maceSkill + hammerSkill) / TEN;
        skills.put(ONE_HANDED_WEAPON_SKILL.type, oneHandedWeaponSkill);

        return setMainBattleExperience();
    }

    private WarSkills setTwoHandedWeaponSkill() {
        double twoHandedSwordSkill = valueOf(TWO_HANDED_SWORD_SKILL);
        double twoHandedAxeSkill = valueOf(TWO_HANDED_AXE_SKILL);
        double twoHandedMaceSkill = valueOf(TWO_HANDED_MACE_SKILL);
        double twoHandedHammerSkill = valueOf(TWO_HANDED_HAMMER_SKILL);
        double halberdSkill = valueOf(HALBERD_SKILL);
        double spearSkill = valueOf(SPEAR_SKILL);
        double staffSkill = valueOf(STAFF_SKILL);

        double oneHandedWeaponSkill = (twoHandedSwordSkill + twoHandedAxeSkill +
                twoHandedMaceSkill + twoHandedHammerSkill + halberdSkill + spearSkill + staffSkill) / TEN;

        skills.put(ONE_HANDED_WEAPON_SKILL.type, oneHandedWeaponSkill);

        return setMainBattleExperience();
    }

    private WarSkills setShieldBlockSkill() {
        double lightShieldBlockSkill = valueOf(LIGHT_SHIELD_SKILL);
        double middleShieldBlockSkill = valueOf(MIDDLE_SHIELD_SKILL);
        double heavyShieldBlockSkill = valueOf(HEAVY_SHIELD_SKILL);

        double shieldBlockSkill = (lightShieldBlockSkill + middleShieldBlockSkill + heavyShieldBlockSkill) / TEN;
        skills.put(SHIELD_BLOCK_SKILL.type, shieldBlockSkill);

        return setMainBattleExperience();
    }

    private WarSkills setArmorSkill() {
        double clothArmorSkill = valueOf(CLOTH_ARMOR_SKILL);
        double leatherArmorSkill = valueOf(LEATHER_ARMOR_SKILL);
        double chainArmorSkill = valueOf(CHAIN_ARMOR_SKILL);
        double scalyArmorSkill = valueOf(SCALY_ARMOR_SKILL);
        double latArmorSkill = valueOf(LAT_ARMOR_SKILL);

        double armorSkill = (clothArmorSkill + leatherArmorSkill + chainArmorSkill + scalyArmorSkill + latArmorSkill) / TEN;
        skills.put(ARMOR_SKILL.type, armorSkill);

        return setMainBattleExperience();
    }

    private WarSkills setHandToHandFightSkill() {
        double handsFightSkill = valueOf(HANDS_FIGHT_SKILL);
        double footsFightSkill = valueOf(FOOTS_FIGHT_SKILL);

        double handToHandFightSkill = (handsFightSkill + footsFightSkill) / TEN;
        skills.put(HAND_TO_HAND_FIGHT_SKILL.type, handToHandFightSkill);

        return setMainBattleExperience();
    }
}
