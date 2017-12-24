package model.equipment.weapon

import model.support.enums.Skill.HAMMER_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.HAMMER

class Hammer(weight: Double, quality: Int) : Weapon(HAMMER, HAMMER_SKILL, weight, quality, true) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.10,
                CUT to 0.0,
                SLASH to 0.0,
                PIERCING to quality * 0.90,
                CRUSH to 0.0)
    }
}
