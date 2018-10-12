package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.*
import model.support.enums.Specification.*
import model.support.enums.WeaponType.HAMMER

class Hammer(weight: Double, quality: Int) : OneHandedWeapon(HAMMER, HAMMER_SKILL, ONE_HANDED_WEAPON_SKILL, weight, quality, true) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.10,
                CUT to 0.0,
                SLASH to 0.0,
                PIERCING to quality * 0.90,
                CRUSH to 0.0)
    }
}
