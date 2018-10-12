package model.support.enums

import model.Character
import model.equipment.Equipment.Companion.NONE
import model.support.enums.BodyPart.LEFT_HAND
import model.support.enums.BodyPart.RIGHT_HAND

enum class AttackType {
    RIGHT_HAND_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification) = character.equipmentOf(RIGHT_HAND) == NONE && character.wantToAttack &&
                character.neededStaminaForHandToHandAttack * power.mod < character.stamina() - character.allTechniquesStaminaNeeded() &&
                character.handToHandDamage(specification) > 0 &&
                character.handToHandActionFrequency() * power.mod + character.allTechniquesFrequency() <= character.attackAction
    },

    LEFT_HAND_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification) = character.equipmentOf(LEFT_HAND) == NONE && character.wantToAttack &&
                character.neededStaminaForHandToHandAttack * power.mod < character.stamina() - character.allTechniquesStaminaNeeded() &&
                character.handToHandDamage(specification) > 0 &&
                character.handToHandActionFrequency() * power.mod + character.allTechniquesFrequency() <= character.attackAction
    },

//    FOOT_ATTACK {
//        override val equipmentType = EquipmentType.NONE
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification)
//                = character.neededStaminaForHandToHandAttack * (power.mod * 1.5) < character.stamina() &&
//                character.stamina(PERCENTAGE) > THIRTY &&
//                character.handToHandActionFrequency() >= (power.mod * 1.5) &&
//                character.handToHandDamage(specification) > 0
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },

    PRIMARY_WEAPON_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification) = character.primaryWeaponExist() && character.wantToAttack &&
                character.neededStaminaForPrimaryWeaponAttack * power.mod < character.stamina() - character.allTechniquesStaminaNeeded() &&
                character.primaryWeaponDamage(specification) > 0 &&
                character.primaryWeaponUsingFrequency() * power.mod + character.allTechniquesFrequency() <= character.attackAction
    },

    SECONDARY_WEAPON_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification) = character.secondaryWeaponExist() && character.wantToAttack &&
                character.neededStaminaForSecondaryWeaponAttack * power.mod < character.stamina() - character.allTechniquesStaminaNeeded() &&
                character.secondaryWeaponDamage(specification) > 0 &&
                character.secondaryWeaponUsingFrequency() * power.mod + character.allTechniquesFrequency() <= character.attackAction
    };

    abstract fun canUse(character: Character, power: HitPower, specification: Specification): Boolean
}