package model.support.enums

import model.Character
import model.equipment.Equipment.Companion.NONE
import model.support.Constants.PERCENTAGE
import model.support.Constants.THIRTY
import model.support.enums.BodyPart.LEFT_HAND
import model.support.enums.BodyPart.RIGHT_HAND

enum class AttackType {
    RIGHT_HAND_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification)
                = character.equipmentOf(RIGHT_HAND) == NONE &&
                character.neededStaminaForHandToHandAttack * power.mod < character.stamina() &&
                character.stamina(PERCENTAGE) > THIRTY &&
                character.handToHandActionFrequency() * power.mod + character.allTechniquesFrequency() <= character.action &&
                character.handToHandDamage(specification) > 0
    },

    LEFT_HAND_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification)
                = character.equipmentOf(LEFT_HAND) == NONE &&
                character.neededStaminaForHandToHandAttack * power.mod < character.stamina() &&
                character.stamina(PERCENTAGE) > THIRTY &&
                character.handToHandActionFrequency() * power.mod + character.allTechniquesFrequency() <= character.action &&
                character.handToHandDamage(specification) > 0
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
        override fun canUse(character: Character, power: HitPower, specification: Specification) = character.primaryWeaponExist() &&
                character.neededStaminaForPrimaryWeaponAttack * power.mod < character.stamina() &&
                character.stamina(PERCENTAGE) > THIRTY &&
                character.primaryWeaponUsingFrequency() * power.mod + character.allTechniquesFrequency() <= character.action &&
                character.primaryWeaponDamage(specification) > 0
    },

    SECONDARY_WEAPON_ATTACK {
        override fun canUse(character: Character, power: HitPower, specification: Specification) = character.secondaryWeaponExist() &&
                character.neededStaminaForSecondaryWeaponAttack * power.mod < character.stamina() &&
                        character.stamina(PERCENTAGE) > THIRTY &&
                        character.secondaryWeaponUsingFrequency() * power.mod + character.allTechniquesFrequency() <= character.action &&
                        character.secondaryWeaponDamage(specification) > 0
    };

//    PRIMARY_KNIFE_ATTACK {
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification): Boolean {
//
//            isPrimaryWeapon = if (character.isRightHanded) true
//            else !(!character.isRightHanded && character.equipmentOf(LEFT_HAND) is Weapon)
//
//            return character.equipmentOf(RIGHT_HAND) is Knife &&
//                    ((isPrimaryWeapon && character.neededStaminaForPrimaryWeaponAttack * (power.mod * 1.5) < character.stamina()) ||
//                            (!isPrimaryWeapon && character.neededStaminaForSecondaryWeaponAttack * (power.mod * 1.5) < character.stamina())) &&
//                    character.stamina(PERCENTAGE) > THIRTY &&
//                    character.handToHandActionFrequency() >= power.mod &&
//                    character.handToHandDamage(specification) > 0
//        }
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {}
//    },
//
//    SECONDARY_KNIFE_ATTACK {
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification): Boolean {
//
//            isPrimaryWeapon = if (character.isRightHanded) false
//            else (!character.isRightHanded && character.equipmentOf(LEFT_HAND) is Weapon)
//
//            return character.equipmentOf(LEFT_HAND) is Knife &&
//                    ((isPrimaryWeapon && character.neededStaminaForPrimaryWeaponAttack * (power.mod * 1.5) < character.stamina()) ||
//                            (!isPrimaryWeapon && character.neededStaminaForSecondaryWeaponAttack * (power.mod * 1.5) < character.stamina())) &&
//                    character.stamina(PERCENTAGE) > THIRTY &&
//                    character.handToHandActionFrequency() >= power.mod &&
//                    character.handToHandDamage(specification) > 0
//        }
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {}
//    },

//    RIGHT_SWORD_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    LEFT_SWORD_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    RIGHT_AXE_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    LEFT_AXE_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    RIGHT_MACE_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    LEFT_MACE_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    RIGHT_HAMMER_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    LEFT_HAMMER_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    TWO_HANDED_SWORD_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    TWO_HANDED_AXE_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    TWO_HANDED_MACE_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    TWO_HANDED_HAMMER_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    SPEAR_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    HALBERD_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    },
//
//    STAFF_ATTACK {
//        override val equipmentType = WEAPON
//
//        override fun canBeUsed(character: Character, power: HitPower, specification: Specification) = false
//
//        override fun use(character: Character, power: HitPower, specification: Specification) {
//
//        }
//    };

    protected var isPrimaryWeapon = false

    abstract fun canUse(character: Character, power: HitPower, specification: Specification): Boolean
}