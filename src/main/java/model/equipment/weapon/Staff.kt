package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.STAFF_SKILL
import model.support.enums.Skill.TWO_HANDED_WEAPON_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.STAFF

class Staff(weight: Double, quality: Int) : TwoHandedWeapon(STAFF, STAFF_SKILL, TWO_HANDED_WEAPON_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to quality.toDouble(),
                CUT to 0.0,
                SLASH to 0.0,
                PIERCING to 0.0,
                CRUSH to 0.0)
    }
}
