package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.Skill.TWO_HANDED_SWORD_SKILL
import model.support.enums.Skill.TWO_HANDED_WEAPON_SKILL
import model.support.enums.Specification.*
import model.support.enums.WeaponType.TWO_HANDED_SWORD

class TwoHandedSword(weight: Double, quality: Int) : Weapon(TWO_HANDED_SWORD, TWO_HANDED_SWORD_SKILL, TWO_HANDED_WEAPON_SKILL, weight, quality, false) {
    init {
        damage = mapOf(
                BLUNT to 0.0,
                CUT to quality * 0.15,
                SLASH to quality * 0.50,
                PIERCING to quality * 0.35,
                CRUSH to 0.0)
    }
}
