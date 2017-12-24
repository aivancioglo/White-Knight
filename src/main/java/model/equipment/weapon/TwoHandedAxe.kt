package model.equipment.weapon

import model.support.enums.Skill.TWO_HANDED_AXE_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.TWO_HANDED_AXE

class TwoHandedAxe(weight: Double, quality: Int) : Weapon(TWO_HANDED_AXE, TWO_HANDED_AXE_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.25,
                CUT to 0.0,
                SLASH to quality * 0.75,
                PIERCING to 0.0,
                CRUSH to 0.0)

    }
}
