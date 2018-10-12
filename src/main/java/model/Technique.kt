package model

import model.support.enums.AttackType
import model.support.enums.AttackType.*
import model.support.enums.HitPower
import model.support.enums.Skill.HANDS_FIGHT_SKILL
import model.support.enums.Specification

data class Technique(val character: Character, val type: AttackType, val power: HitPower, val specification: Specification) {
    val damage = damage() * power.mod
    val criticalDamage = criticalDamage() * power.mod
    val neededStamina = neededStamina() * power.mod
    val frequency = frequency() * power.mod
    val attackDuration = attackDuration() * power.mod
    val defenseDuration = defenceDuration() * power.mod
    val skillType = skillType()

    fun canBeUsed() = type.canUse(character, power, specification)

    private fun damage() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> character.handToHandDamage(specification)
        PRIMARY_WEAPON_ATTACK -> character.primaryWeaponDamage(specification)
        SECONDARY_WEAPON_ATTACK -> character.secondaryWeaponDamage(specification)
    }

    private fun criticalDamage() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> character.handToHandCriticalDamage(specification)
        PRIMARY_WEAPON_ATTACK -> character.primaryWeaponCriticalDamage(specification)
        SECONDARY_WEAPON_ATTACK -> character.secondaryWeaponCriticalDamage(specification)
    }

    private fun neededStamina() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> character.neededStaminaForHandToHandAttack
        PRIMARY_WEAPON_ATTACK -> character.neededStaminaForPrimaryWeaponAttack
        SECONDARY_WEAPON_ATTACK -> character.neededStaminaForSecondaryWeaponAttack
    }

    private fun frequency() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> character.handToHandActionFrequency()
        PRIMARY_WEAPON_ATTACK -> character.primaryWeaponUsingFrequency()
        SECONDARY_WEAPON_ATTACK -> character.secondaryWeaponAttackDuration()
    }

    private fun attackDuration() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> character.handToHandAttackDuration()
        PRIMARY_WEAPON_ATTACK -> character.primaryWeaponAttackDuration()
        SECONDARY_WEAPON_ATTACK -> character.secondaryWeaponAttackDuration()
    }

    private fun defenceDuration() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> character.handToHandDefenseDuration()
        PRIMARY_WEAPON_ATTACK -> character.primaryWeaponDefenseDuration()
        SECONDARY_WEAPON_ATTACK -> character.secondaryWeaponDefenseDuration()
    }

    private fun skillType() = when (type) {
        RIGHT_HAND_ATTACK, LEFT_HAND_ATTACK -> HANDS_FIGHT_SKILL
        PRIMARY_WEAPON_ATTACK -> character.primaryWeapon().skillType
        SECONDARY_WEAPON_ATTACK -> character.secondaryWeapon().skillType
    }
}