package view

import model.Character
import model.equipment.armor.Armor
import model.support.Constants.MAX
import model.support.enums.ArmorType.*
import model.support.enums.BodyPart.*
import model.support.enums.Skill
import model.support.enums.Specification

object Console {
    fun print(character: Character) {
        println()
        println("=================")
        println("Character ${character.who}")
        println("=================")
        println()
        println("Is alive: ${character.isAlive}")
        println()
        println("Concentration: ${character.concentration()}")
        println("Strength: ${character.strength()}")
        println("Agility: ${character.agility()}")
        println("Suppleness: ${character.suppleness()}")
        println("Quickness: ${character.quickness()}")
        println()
        println("Health: ${character.health()} / ${character.health(MAX)}")
        println("Stamina: ${character.stamina()} / ${character.stamina(MAX)}")
        println()
        println("Action left: ${character.action}")
        println("Stamina per dodge: ${character.neededStaminaToDodge}")
        println("Dodge frequency: ${character.dodgeFrequency()}/${character.dodgeFrequency(MAX)}")
        println("Dodge duration: ${character.dodgeDuration()}/${character.dodgeDuration(MAX)}")

        println()
        println("=================")
        println("Hand-to-hand:")
        println("=================")
        println()
        println("Damage:")
        Specification.values().forEach { println("$it = ${character.handToHandDamage(it)} (${character.handToHandCriticalDamage(it)})") }
        println()
        println("Stamina per attack: ${character.neededStaminaForHandToHandAttack}")
        println("Attack frequency: ${character.handToHandActionFrequency()}/${character.handToHandActionFrequency(MAX)}")
        println("Attack duration: ${character.handToHandAttackDuration()}/${character.handToHandAttackDuration(MAX)}")
        println("Defense duration: ${character.handToHandDefenseDuration()}/${character.handToHandDefenseDuration(MAX)}")

        if (character.primaryWeaponExist()) {
            println()
            println("=================")
            println(character.primaryWeapon().weaponType.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.primaryWeapon().weight}")
            println("Quality: ${character.primaryWeapon().quality}")
            println("Price: ${character.primaryWeapon().price}")
            println()
            println("Damage:")
            character.primaryWeapon().damage.forEach({ k, v -> println("$k = $v") })
            println()
            println("Stamina per use: ${character.neededStaminaForPrimaryWeaponAttack}")
            println("Attack frequency: ${character.primaryWeaponUsingFrequency()}/${character.primaryWeaponUsingFrequency(MAX)}")
            println("Attack duration: ${character.primaryWeaponAttackDuration()}/${character.primaryWeaponAttackDuration(MAX)}")
            println("Defense duration: ${character.primaryWeaponDefenseDuration()}/${character.primaryWeaponDefenseDuration(MAX)}")

            println()
            println("=================")
            println("Primary Weapon Damage:")
            println("=================")
            println()
            Specification.values().forEach { println("$it = ${character.primaryWeaponDamage(it)} (${character.primaryWeaponCriticalDamage(it)})") }
        }

        if (character.secondaryWeaponExist()) {
            println()
            println("=================")
            println(character.secondaryWeapon().weaponType.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.secondaryWeapon().weight}")
            println("Quality: ${character.secondaryWeapon().quality}")
            println("Price: ${character.secondaryWeapon().price}")
            println()
            println("Damage:")
            character.secondaryWeapon().damage.forEach({ k, v -> println("$k = $v") })
            println()
            println("Stamina per use: ${character.neededStaminaForSecondaryWeaponAttack}")
            println("Attack frequency: ${character.secondaryWeaponUsingFrequency()}/${character.secondaryWeaponUsingFrequency(MAX)}")
            println("Attack duration: ${character.secondaryWeaponAttackDuration()}/${character.secondaryWeaponAttackDuration(MAX)}")
            println("Defense duration: ${character.secondaryWeaponDefenseDuration()}/${character.secondaryWeaponDefenseDuration(MAX)}")

            println()
            println("=================")
            println("Secondary Weapon Damage:")
            println("=================")
            println()
            Specification.values().forEach { println("$it = ${character.secondaryWeaponDamage(it)} (${character.secondaryWeaponCriticalDamage(it)})") }
        }

        if (character.shieldExist()) {
            println()
            println("=================")
            println(character.shield().shieldType.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.shield().weight}")
            println("Quality: ${character.shield().quality}")
            println("Price: ${character.shield().price}")
            println()
            println("Stamina per use: ${character.neededStaminaForShieldUsing}")
            println("Using frequency: ${character.shieldUsingFrequency()}")
            println("Using duration: ${character.shieldUsingDuration()}")
            println()
            println("Defense:")
            character.shield().defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(HELM)) {
            println()
            println("=================")
            println(HELM.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(HEAD).weight}")
            println("Quality: ${character.equipmentOf(HEAD).quality}")
            println("Price: ${character.equipmentOf(HEAD).price}")
            println()
            println("Defense:")
            (character.equipmentOf(HEAD) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(RIGHT_SHOULDER_ARMOR)) {
            println()
            println("=================")
            println(RIGHT_SHOULDER_ARMOR.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(RIGHT_SHOULDER).weight}")
            println("Quality: ${character.equipmentOf(RIGHT_SHOULDER).quality}")
            println("Price: ${character.equipmentOf(RIGHT_SHOULDER).price}")
            println()
            println("Defense:")
            (character.equipmentOf(RIGHT_SHOULDER) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(LEFT_SHOULDER_ARMOR)) {
            println()
            println("=================")
            println(LEFT_SHOULDER_ARMOR.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(LEFT_SHOULDER).weight}")
            println("Quality: ${character.equipmentOf(LEFT_SHOULDER).quality}")
            println("Price: ${character.equipmentOf(LEFT_SHOULDER).price}")
            println()
            println("Defense:")
            (character.equipmentOf(LEFT_SHOULDER) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(GLOVES)) {
            println()
            println("=================")
            println(GLOVES.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(HANDS).weight}")
            println("Quality: ${character.equipmentOf(HANDS).quality}")
            println("Price: ${character.equipmentOf(HANDS).price}")
            println()
            println("Defense:")
            (character.equipmentOf(HANDS) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(BODY_ARMOR)) {
            println()
            println("=================")
            println(BODY_ARMOR.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(BODY).weight}")
            println("Quality: ${character.equipmentOf(BODY).quality}")
            println("Price: ${character.equipmentOf(BODY).price}")
            println()
            println("Defense:")
            (character.equipmentOf(BODY) as Armor).defense.forEach { k, v -> println("$k = $v") }
        }

        if (character.has(RIGHT_LEG_ARMOR)) {
            println()
            println("=================")
            println(RIGHT_LEG_ARMOR.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(RIGHT_LEG).weight}")
            println("Quality: ${character.equipmentOf(RIGHT_LEG).quality}")
            println("Price: ${character.equipmentOf(RIGHT_LEG).price}")
            println()
            println("Defense:")
            (character.equipmentOf(RIGHT_LEG) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(LEFT_LEG_ARMOR)) {
            println()
            println("=================")
            println(LEFT_LEG_ARMOR.toString().replace("_", " "))
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(LEFT_LEG).weight}")
            println("Quality: ${character.equipmentOf(LEFT_LEG).quality}")
            println("Price: ${character.equipmentOf(LEFT_LEG).price}")
            println()
            println("Defense:")
            (character.equipmentOf(LEFT_LEG) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        if (character.has(BOOTS)) {
            println()
            println("=================")
            println("$BOOTS")
            println("=================")
            println()
            println("Weight: ${character.equipmentOf(FOOTS).weight}")
            println("Quality: ${character.equipmentOf(FOOTS).quality}")
            println("Price: ${character.equipmentOf(FOOTS).price}")
            println()
            println("Defense:")
            (character.equipmentOf(FOOTS) as Armor).defense.forEach({ k, v -> println("$k = $v") })
        }

        println()
        println("=================")
        println("Total Equipment:")
        println("=================")
        println()
        println("Weight: ${character.equipmentWeight}")
        println("Price: ${character.equipmentPrice}")
        println()
        println("Defense:")
        Specification.values().forEach { println("$it = ${character.totalDefense(it)}") }

        println("")
        println("=================")
        println("Skills:")
        println("=================")
        println()
        Skill.values().forEach { println("$it = ${character.skill(it)}") }
        println()
    }

//    fun printComparison(first: Character, second: Character) {
//        println()
//        println("=================")
//        println("Main Parameters:")
//        println("=================")
//        println()
//        println("Dodge chance: ")
//    }
}
