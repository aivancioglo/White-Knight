package model.equipment.weapon

import model.support.enums.Skill.SPEAR_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.SPEAR

class Spear(weight: Double, quality: Int) : Weapon(SPEAR, SPEAR_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to 0.0,
                CUT to 0.0,
                SLASH to 0.0,
                PIERCING to quality.toDouble(),
                CRUSH to 0.0)
    }
}
