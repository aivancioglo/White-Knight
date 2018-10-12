package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.WeaponType

abstract class OneHandedWeapon(
        weaponType: WeaponType,
        skillType: Skill,
        skillGroup: Skill,
        weight: Double,
        quality: Int,
        isOneHanded: Boolean
) : Weapon(
        weaponType,
        skillType,
        skillGroup,
        weight,
        quality,
        isOneHanded
)