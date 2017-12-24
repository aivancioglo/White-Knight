package model.support

import model.Character
import model.equipment.armor.Armor
import model.support.Constants.MAX
import model.support.enums.ArmorType.*
import model.support.enums.BodyPart
import model.support.enums.Skill
import model.support.enums.Specification
import java.io.*

object DataBase {
    fun printToFile(character: Character) {
        BufferedWriter(FileWriter(File("db/print/" + character.who + ".txt"))).use { writer ->
            writer.write("=================")
            writer.newLine()
            writer.write("Main Parameters:")
            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.newLine()
            writer.write("Is alive: ${character.isAlive}")
            writer.newLine()
            writer.newLine()
            writer.write("Concentration: ${character.concentration()}")
            writer.newLine()
            writer.write("Strength: ${character.strength()}")
            writer.newLine()
            writer.write("Agility: ${character.agility()}")
            writer.newLine()
            writer.write("Suppleness: ${character.suppleness()}")
            writer.newLine()
            writer.write("Quickness: ${character.quickness()}")
            writer.newLine()
            writer.newLine()
            writer.write("Health: ${character.health()} / ${character.health(Constants.MAX)}")
            writer.newLine()
            writer.write("Stamina: ${character.stamina()} / ${character.stamina(Constants.MAX)}")
            writer.newLine()
            writer.newLine()
            writer.write("Stamina per dodge: ${character.neededStaminaToDodge}")
            writer.newLine()
            writer.write("Dodge frequency: ${character.dodgeFrequency()}/${character.dodgeFrequency(Constants.MAX)}")
            writer.newLine()
            writer.write("Dodge duration: ${character.dodgeDuration()}/${character.dodgeDuration(MAX)}")
            writer.newLine()

            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.write("Hand-to-hand:")
            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.newLine()
            writer.write("Damage:")
            writer.newLine()
            Specification.values().forEach {
                writer.write("$it = ${character.handToHandDamage(it)} (${character.handToHandCriticalDamage(it)})")
                writer.newLine()
            }
            writer.newLine()
            writer.write("Stamina per attack: ${character.neededStaminaForHandToHandAttack}")
            writer.newLine()
            writer.write("Attack frequency: ${character.handToHandActionFrequency()}/${character.handToHandActionFrequency(MAX)}")
            writer.newLine()
            writer.write("Attack duration: ${character.handToHandAttackDuration()}/${character.handToHandAttackDuration(MAX)}")
            writer.newLine()
            writer.write("Defense duration: ${character.handToHandDefenseDuration()}/${character.handToHandDefenseDuration(MAX)}")
            writer.newLine()

            if (character.primaryWeaponExist()) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(character.primaryWeapon().weaponType.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.primaryWeapon().weight}")
                writer.newLine()
                writer.write("Quality: ${character.primaryWeapon().quality}")
                writer.newLine()
                writer.write("Price: ${character.primaryWeapon().price}")
                writer.newLine()
                writer.newLine()
                writer.write("Damage:")
                writer.newLine()
                character.primaryWeapon().damage.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
                writer.newLine()
                writer.write("Stamina per use: ${character.neededStaminaForPrimaryWeaponAttack}")
                writer.newLine()
                writer.write("Attack frequency: ${character.primaryWeaponUsingFrequency()}/${character.primaryWeaponUsingFrequency(MAX)}")
                writer.newLine()
                writer.write("Attack duration: ${character.primaryWeaponAttackDuration()}/${character.primaryWeaponAttackDuration(MAX)}")
                writer.newLine()
                writer.write("Defense duration: ${character.primaryWeaponDefenseDuration()}/${character.primaryWeaponDefenseDuration(MAX)}")
                writer.newLine()

                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write("Primary Weapon Damage:")
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                Specification.values().forEach {
                    writer.write("$it = ${character.primaryWeaponDamage(it)} (${character.primaryWeaponCriticalDamage(it)})")
                    writer.newLine()
                }
            }

            if (character.secondaryWeaponExist()) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(character.secondaryWeapon().weaponType.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.secondaryWeapon().weight}")
                writer.newLine()
                writer.write("Quality: ${character.secondaryWeapon().quality}")
                writer.newLine()
                writer.write("Price: ${character.secondaryWeapon().price}")
                writer.newLine()
                writer.newLine()
                writer.write("Damage:")
                writer.newLine()
                character.secondaryWeapon().damage.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
                writer.newLine()
                writer.write("Stamina per use: ${character.neededStaminaForSecondaryWeaponAttack}")
                writer.newLine()
                writer.write("Attack frequency: ${character.secondaryWeaponUsingFrequency()}/${character.secondaryWeaponUsingFrequency(MAX)}")
                writer.newLine()
                writer.write("Attack duration: ${character.secondaryWeaponAttackDuration()}/${character.secondaryWeaponAttackDuration(MAX)}")
                writer.newLine()
                writer.write("Defense duration: ${character.secondaryWeaponDefenseDuration()}/${character.secondaryWeaponDefenseDuration(MAX)}")
                writer.newLine()

                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write("Secondary Weapon Damage:")
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                Specification.values().forEach {
                    writer.write("$it = ${character.secondaryWeaponDamage(it)} (${character.secondaryWeaponCriticalDamage(it)})")
                    writer.newLine()
                }
            }

            if (character.shieldExist()) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(character.shield().shieldType.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.shield().weight}")
                writer.newLine()
                writer.write("Quality: ${character.shield().quality}")
                writer.newLine()
                writer.write("Price: ${character.shield().price}")
                writer.newLine()
                writer.newLine()
                writer.write("Stamina per use: ${character.neededStaminaForShieldUsing}")
                writer.newLine()
                writer.write("Using frequency: ${character.shieldUsingFrequency()}")
                writer.newLine()
                writer.write("Using duration: ${character.shieldUsingDuration()}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                character.shield().defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(HELM)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(HELM.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.HEAD).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.HEAD).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.HEAD).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.HEAD) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(RIGHT_SHOULDER_ARMOR)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(RIGHT_SHOULDER_ARMOR.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.RIGHT_SHOULDER).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.RIGHT_SHOULDER).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.RIGHT_SHOULDER).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.RIGHT_SHOULDER) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(LEFT_SHOULDER_ARMOR)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(LEFT_SHOULDER_ARMOR.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.LEFT_SHOULDER).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.LEFT_SHOULDER).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.LEFT_SHOULDER).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.LEFT_SHOULDER) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(GLOVES)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(GLOVES.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.HANDS).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.HANDS).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.HANDS).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.HANDS) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(BODY_ARMOR)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(BODY_ARMOR.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.BODY).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.BODY).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.BODY).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.BODY) as Armor).defense.forEach { k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                }
            }

            if (character.has(RIGHT_LEG_ARMOR)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(RIGHT_LEG_ARMOR.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.RIGHT_LEG).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.RIGHT_LEG).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.RIGHT_LEG).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.RIGHT_LEG) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(LEFT_LEG_ARMOR)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write(LEFT_LEG_ARMOR.toString().replace("_", " "))
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.LEFT_LEG).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.LEFT_LEG).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.LEFT_LEG).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.LEFT_LEG) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            if (character.has(BOOTS)) {
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.write("${BOOTS}")
                writer.newLine()
                writer.write("=================")
                writer.newLine()
                writer.newLine()
                writer.write("Weight: ${character.equipmentOf(BodyPart.FOOTS).weight}")
                writer.newLine()
                writer.write("Quality: ${character.equipmentOf(BodyPart.FOOTS).quality}")
                writer.newLine()
                writer.write("Price: ${character.equipmentOf(BodyPart.FOOTS).price}")
                writer.newLine()
                writer.newLine()
                writer.write("Defense:")
                writer.newLine()
                (character.equipmentOf(BodyPart.FOOTS) as Armor).defense.forEach({ k, v ->
                    writer.write("$k = $v")
                    writer.newLine()
                })
            }

            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.write("Total Equipment:")
            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.newLine()
            writer.write("Weight: ${character.equipmentWeight}")
            writer.newLine()
            writer.write("Price: ${character.equipmentPrice}")
            writer.newLine()
            writer.newLine()
            writer.write("Defense:")
            writer.newLine()
            Specification.values().forEach {
                writer.write("$it = ${character.totalDefense(it)}")
                writer.newLine()
            }

            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.write("Skills:")
            writer.newLine()
            writer.write("=================")
            writer.newLine()
            writer.newLine()
            Skill.values().forEach {
                writer.write("$it = ${character.skill(it)}")
                writer.newLine()
            }
        }
    }

    fun save(character: Character) : Character {
        try {
            BufferedWriter(FileWriter(File("db/memory/" + character.who + ".txt"))).use { writer ->
                writer.write(character.who)
                writer.newLine()
                writer.write(character.concentration().toString() + ",")
                writer.write("${character.strength()},")
                writer.write("${character.agility()}")
                writer.newLine()
                writer.write("${character.health()},")
                writer.write("${character.stamina()}")
                writer.newLine()
//                character.skills.forEach { k, v ->
//                    try {
//                        writer.write("$k = $v,")
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return character
    }

    fun load(character: Character) : Character {
        try {
            BufferedReader(FileReader(File("db/" + character.who + ".txt"))).use { reader ->
                reader.readLine()

//                var data = reader.readLine().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                character.addToConcentration(Integer.parseInt(data[0]) - 1)
//                character.addToStrength(Integer.parseInt(data[1]) - 1)
//                character.addToAgility(Integer.parseInt(data[2]) - 1)

//                data = reader.readLine().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                character.health() = data[0].toDouble()
//                character.stamina() = data[1].toDouble()
//
//                data = reader.readLine().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                for (i in 0 until character.size) {
//                    character.skills.put(Skill.valueOf(data[i].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]).type, java.lang.Double.valueOf(data[i].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]))
//                }
            }
        } catch (e: IOException) {
            println("Can't load " + character.who + ".txt. File doesn't has!")
        }

        return character
    }

    fun delete(character: Character) : Character {
        File("db/" + character.who + ".txt").delete()

        return character
    }
}
