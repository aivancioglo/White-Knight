package model.equipment.weapon

import model.support.enums.Skill.TWO_HANDED_MACE_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.TWO_HANDED_MACE

class TwoHandedMace(weight: Double, quality: Int) : Weapon(TWO_HANDED_MACE, TWO_HANDED_MACE_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to 0.0,
                CUT to 0.0,
                SLASH to quality * 0.90,
                PIERCING to quality * 0.10,
                CRUSH to 0.0)

    }
}
