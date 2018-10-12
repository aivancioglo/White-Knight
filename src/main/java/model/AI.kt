package model

import model.equipment.Equipment.Companion.NONE
import model.equipment.shield.Shield
import model.equipment.weapon.TwoHandedWeapon
import model.support.Constants.FIVE
import model.support.Constants.MAX
import model.support.Constants.NINETY
import model.support.Constants.PERCENTAGE
import model.support.Constants.TEN
import model.support.Constants.TWENTY
import model.support.enums.AttackStyle.*
import model.support.enums.AttackType.*
import model.support.enums.BodyPart.LEFT_HAND
import model.support.enums.BodyPart.RIGHT_HAND
import model.support.enums.DefenseStyle
import model.support.enums.DefenseStyle.*
import model.support.enums.HitPower.*
import model.support.enums.Skill.MAIN_BATTLE_EXPERIENCE
import model.support.enums.Specification.BLUNT

object AI {
    fun calculatingYourAttackStyle(your: Character, enemy: Character) = when {
        your.stamina(PERCENTAGE) > NINETY -> AGGRESSIVE_OFFENSIVE
        your.stamina(PERCENTAGE) > TEN && your.health() > enemy.maxPossibleDamageTo(your) -> AGGRESSIVE_OFFENSIVE
        your.stamina(PERCENTAGE) > enemy.stamina(PERCENTAGE) &&
                your.health() > enemy.maxPossibleDamageTo(your) -> AGGRESSIVE_OFFENSIVE
        enemy.onTheKnees && your.stamina(PERCENTAGE) > FIVE -> AGGRESSIVE_OFFENSIVE
        enemy.stamina(PERCENTAGE) < FIVE && your.stamina(PERCENTAGE) > TWENTY -> FORCING
        enemy.stamina(MAX) < your.stamina(MAX) -> when {
            your.minDefenseDuration() < enemy.minAttackDuration() -> when {
                your.stamina() > enemy.stamina() -> AGGRESSIVE_OFFENSIVE
                your.stamina(PERCENTAGE) > NINETY -> AGGRESSIVE_OFFENSIVE
                else -> MODERATE
            }
            your.stamina() > enemy.stamina() && your.stamina(PERCENTAGE) > TEN -> AGGRESSIVE_OFFENSIVE
            else -> FORCING
        }
        enemy.stamina(MAX) > your.stamina(MAX) -> when {
            your.minDefenseDuration() < enemy.minAttackDuration() -> when {
                your.stamina() > enemy.stamina() -> AGGRESSIVE_OFFENSIVE
                your.stamina(PERCENTAGE) > NINETY -> AGGRESSIVE_OFFENSIVE
                else -> MODERATE
            }
            your.stamina() > enemy.stamina() && your.stamina(PERCENTAGE) > TEN -> AGGRESSIVE_OFFENSIVE
            else -> FORCING
        }
        else -> MODERATE
    }

    fun calculatingYourDefenseStyle(your: Character, enemy: Character): DefenseStyle {
        val shieldCanBePenetrated = if (your.shieldExist())
            your.shieldDefense(BLUNT) < enemy.maxPossibleDamageTo(your)
        else
            true

        return if (your.stamina() > enemy.stamina()) {
            if (shieldCanBePenetrated)
                DODGE_MAINLY
            else
                SHIELD_MAINLY
        } else {
            if (shieldCanBePenetrated)
                DODGE_MAINLY
            else
                STAMINA_DURABILITY
        }
    }

    fun calculatingYourTechniques(your: Character, enemy: Character) {
        val mostEffectiveRightHandSpecification = your.mostEffectiveSpecificationAgainst(enemy, RIGHT_HAND_ATTACK)
        val mostEffectiveLeftHandSpecification = your.mostEffectiveSpecificationAgainst(enemy, LEFT_HAND_ATTACK)
        val mostEffectivePrimaryWeaponSpecification = if (your.primaryWeaponExist())
            your.mostEffectiveSpecificationAgainst(enemy, PRIMARY_WEAPON_ATTACK)
        else
            BLUNT
        val mostEffectiveSecondaryWeaponSpecification = if (your.secondaryWeaponExist())
            your.mostEffectiveSpecificationAgainst(enemy, SECONDARY_WEAPON_ATTACK)
        else
            BLUNT

        var comboAdded = true

        while (comboAdded)
            if (your.minAttackDuration() < enemy.minDefenseDuration()) {
                if (your.primaryWeaponExist() && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, PRIMARY_WEAPON_ATTACK, LOW, mostEffectivePrimaryWeaponSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(PRIMARY_WEAPON_ATTACK, LOW, mostEffectivePrimaryWeaponSpecification)

                if (your.secondaryWeaponExist() && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, SECONDARY_WEAPON_ATTACK, LOW, mostEffectiveSecondaryWeaponSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(SECONDARY_WEAPON_ATTACK, LOW, mostEffectiveSecondaryWeaponSpecification)

                if (your.primaryWeaponExist() && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, PRIMARY_WEAPON_ATTACK, MEDIUM, mostEffectivePrimaryWeaponSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(PRIMARY_WEAPON_ATTACK, MEDIUM, mostEffectivePrimaryWeaponSpecification)

                if (your.secondaryWeaponExist() && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, SECONDARY_WEAPON_ATTACK, MEDIUM, mostEffectiveSecondaryWeaponSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(SECONDARY_WEAPON_ATTACK, MEDIUM, mostEffectiveSecondaryWeaponSpecification)

                if (your.primaryWeaponExist() && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, PRIMARY_WEAPON_ATTACK, HIGH, mostEffectivePrimaryWeaponSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(PRIMARY_WEAPON_ATTACK, HIGH, mostEffectivePrimaryWeaponSpecification)

                if (your.secondaryWeaponExist() && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, SECONDARY_WEAPON_ATTACK, HIGH, mostEffectiveSecondaryWeaponSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(SECONDARY_WEAPON_ATTACK, HIGH, mostEffectiveSecondaryWeaponSpecification)

                if (your.equipmentOf(RIGHT_HAND) == NONE && your.equipmentOf(LEFT_HAND) !is TwoHandedWeapon && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, RIGHT_HAND_ATTACK, LOW, mostEffectiveRightHandSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(RIGHT_HAND_ATTACK, LOW, mostEffectiveRightHandSpecification)

                if (your.equipmentOf(LEFT_HAND) == NONE && your.equipmentOf(RIGHT_HAND) !is TwoHandedWeapon && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, LEFT_HAND_ATTACK, LOW, mostEffectiveLeftHandSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(LEFT_HAND_ATTACK, LOW, mostEffectiveLeftHandSpecification)

                if (your.equipmentOf(RIGHT_HAND) == NONE && your.equipmentOf(LEFT_HAND) !is TwoHandedWeapon && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, RIGHT_HAND_ATTACK, MEDIUM, mostEffectiveRightHandSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(RIGHT_HAND_ATTACK, MEDIUM, mostEffectiveRightHandSpecification)

                if (your.equipmentOf(LEFT_HAND) == NONE && your.equipmentOf(RIGHT_HAND) !is TwoHandedWeapon && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, LEFT_HAND_ATTACK, MEDIUM, mostEffectiveLeftHandSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(LEFT_HAND_ATTACK, MEDIUM, mostEffectiveLeftHandSpecification)

                if (your.equipmentOf(RIGHT_HAND) == NONE && your.equipmentOf(LEFT_HAND) !is TwoHandedWeapon && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, RIGHT_HAND_ATTACK, HIGH, mostEffectiveRightHandSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(RIGHT_HAND_ATTACK, HIGH, mostEffectiveRightHandSpecification)

                if (your.equipmentOf(LEFT_HAND) == NONE && your.equipmentOf(RIGHT_HAND) !is TwoHandedWeapon && (your.skill(MAIN_BATTLE_EXPERIENCE) <= enemy.skill(MAIN_BATTLE_EXPERIENCE) ||
                        enemy.minDefenseDuration() >
                                Technique(your, LEFT_HAND_ATTACK, HIGH, mostEffectiveLeftHandSpecification).attackDuration))
                    comboAdded = your.addToNextCombo(LEFT_HAND_ATTACK, HIGH, mostEffectiveLeftHandSpecification)
            } else {
                if (your.primaryWeaponExist())
                    comboAdded = your.addToNextCombo(PRIMARY_WEAPON_ATTACK, LOW, mostEffectivePrimaryWeaponSpecification)

                if (your.secondaryWeaponExist())
                    comboAdded = your.addToNextCombo(SECONDARY_WEAPON_ATTACK, LOW, mostEffectiveSecondaryWeaponSpecification)

                if (your.equipmentOf(RIGHT_HAND) == NONE)
                    comboAdded = your.addToNextCombo(RIGHT_HAND_ATTACK, LOW, mostEffectiveRightHandSpecification)

                if (your.equipmentOf(LEFT_HAND) == NONE)
                    comboAdded = your.addToNextCombo(LEFT_HAND_ATTACK, LOW, mostEffectiveLeftHandSpecification)
            }
    }
}