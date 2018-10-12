package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.TWO_HANDED_HAMMER_SKILL
import model.support.enums.Skill.TWO_HANDED_WEAPON_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.TWO_HANDED_HAMMER

class TwoHandedHammer(weight: Double, quality: Int) : TwoHandedWeapon(TWO_HANDED_HAMMER, TWO_HANDED_HAMMER_SKILL, TWO_HANDED_WEAPON_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.65,
                CUT to 0.0,
                SLASH to 0.0,
                PIERCING to quality * 0.35,
                CRUSH to 0.0)

    }
}
