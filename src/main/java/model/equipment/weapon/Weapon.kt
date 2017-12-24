package model.equipment.weapon

import model.equipment.Equipment
import model.support.Constants.FIVE
import model.support.Constants.TWO
import model.support.enums.EquipmentType.WEAPON
import model.support.enums.Skill
import model.support.enums.Specification
import model.support.enums.WeaponType

abstract class Weapon(
        val weaponType: WeaponType,
        val skillType: Skill,
        weight: Double,
        quality: Int,
        val isOneHanded: Boolean
) : Equipment(
        WEAPON,
        weight,
        quality,
        quality / FIVE - weight * TWO
) {
    var damage = mapOf<Specification, Double>()
        protected set
}
