package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.*
import model.support.enums.Specification.*
import model.support.enums.WeaponType.KNIFE

class Knife(weight: Double, quality: Int) : OneHandedWeapon(KNIFE, KNIFE_SKILL, ONE_HANDED_WEAPON_SKILL, weight, quality, true) {
    init {
        damage = mapOf(
                BLUNT to 0.0,
                CUT to quality * 0.50,
                SLASH to quality * 0.10,
                PIERCING to quality * 0.40,
                CRUSH to 0.0)
    }
}
