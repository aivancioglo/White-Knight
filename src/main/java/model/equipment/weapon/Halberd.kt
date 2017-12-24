package model.equipment.weapon

import model.support.enums.Skill.HALBERD_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.HALBERD

class Halberd(weight: Double, quality: Int) : Weapon(HALBERD, HALBERD_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to quality * 0.05,
                CUT to quality * 0.05,
                SLASH to quality * 0.55,
                PIERCING to quality * 0.35,
                CRUSH to 0.0)
    }
}
