package model.equipment.weapon

import model.support.enums.Skill
import model.support.enums.WeaponType

abstract class TwoHandedWeapon(
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