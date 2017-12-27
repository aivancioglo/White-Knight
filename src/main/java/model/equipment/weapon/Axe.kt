package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.AXE_SKILL
import model.support.enums.Skill.ONE_HANDED_WEAPON_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.AXE

class Axe(weight: Double, quality: Int) : Weapon(AXE, AXE_SKILL, ONE_HANDED_WEAPON_SKILL, weight, quality, true) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.25,
                CUT to 0.0,
                SLASH to quality * 0.75,
                PIERCING to 0.0,
                CRUSH to 0.0)
    }
}
