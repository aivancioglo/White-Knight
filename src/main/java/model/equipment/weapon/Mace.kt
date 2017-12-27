package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.MACE_SKILL
import model.support.enums.Skill.ONE_HANDED_WEAPON_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.MACE

class Mace(weight: Double, quality: Int) : Weapon(MACE, MACE_SKILL, ONE_HANDED_WEAPON_SKILL, weight, quality, true) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.35,
                CUT to 0.0,
                SLASH to 0.0,
                PIERCING to 0.0,
                CRUSH to quality * 0.65)
    }
}
