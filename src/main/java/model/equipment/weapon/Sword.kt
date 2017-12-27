package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.ONE_HANDED_WEAPON_SKILL
import model.support.enums.Skill.SWORD_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.SWORD

class Sword(weight: Double, quality: Int) : Weapon(SWORD, SWORD_SKILL, ONE_HANDED_WEAPON_SKILL, weight, quality, true) {
    init {
        damage = mapOf(
                BLUNT to 0.0,
                CUT to quality * 0.15,
                SLASH to quality * 0.50,
                PIERCING to quality * 0.35,
                CRUSH to 0.0)

    }
}
