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
            writer.writeln("=================")
            writer.writeln("Main Parameters:")
            writer.writeln("=================")
            writer.newLine()
            writer.writeln("Is alive: ${character.isAlive}")
            writer.newLine()
            writer.writeln("Concentration: ${character.concentration()}")
            writer.writeln("Strength: ${character.strength()}")
            writer.writeln("Agility: ${character.agility()}")
            writer.writeln("Suppleness: ${character.suppleness()}")
            writer.writeln("Quickness: ${character.quickness()}")
            writer.newLine()
            writer.writeln("Health: ${character.health()} / ${character.health(Constants.MAX)}")
            writer.writeln("Stamina: ${character.stamina()} / ${character.stamina(Constants.MAX)}")
            writer.newLine()
            writer.writeln("Stamina per dodge: ${character.neededStaminaToDodge}")
            writer.writeln("Dodge frequency: ${character.dodgeFrequency()}/${character.dodgeFrequency(Constants.MAX)}")
            writer.newLine()
            writer.writeln("Dodge duration: ${character.dodgeDuration()}/${character.dodgeDuration(MAX)}")

            writer.newLine()
            writer.writeln("=================")
            writer.writeln("Hand-to-hand:")
            writer.writeln("=================")
            writer.newLine()
            writer.writeln("Damage:")
            Specification.values().forEach {
                writer.writeln("$it = ${character.handToHandDamage(it)} (${character.handToHandCriticalDamage(it)})")
            }
            writer.writeln("Stamina per attack: ${character.neededStaminaForHandToHandAttack}")
            writer.writeln("Attack frequency: ${character.handToHandActionFrequency()}/${character.handToHandActionFrequency(MAX)}")
            writer.writeln("Attack duration: ${character.handToHandAttackDuration()}/${character.handToHandAttackDuration(MAX)}")
            writer.writeln("Defense duration: ${character.handToHandDefenseDuration()}/${character.handToHandDefenseDuration(MAX)}")

            if (character.primaryWeaponExist()) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(character.primaryWeapon().weaponType.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.primaryWeapon().weight}")
                writer.writeln("Quality: ${character.primaryWeapon().quality}")
                writer.writeln("Price: ${character.primaryWeapon().price}")
                writer.newLine()
                writer.writeln("Damage:")
                character.primaryWeapon().damage.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
                writer.newLine()
                writer.writeln("Stamina per use: ${character.neededStaminaForPrimaryWeaponAttack}")
                writer.writeln("Attack frequency: ${character.primaryWeaponUsingFrequency()}/${character.primaryWeaponUsingFrequency(MAX)}")
                writer.writeln("Attack duration: ${character.primaryWeaponAttackDuration()}/${character.primaryWeaponAttackDuration(MAX)}")
                writer.writeln("Defense duration: ${character.primaryWeaponDefenseDuration()}/${character.primaryWeaponDefenseDuration(MAX)}")

                writer.newLine()
                writer.writeln("=================")
                writer.writeln("Primary Weapon Damage:")
                writer.writeln("=================")
                writer.newLine()
                Specification.values().forEach {
                    writer.writeln("$it = ${character.primaryWeaponDamage(it)} (${character.primaryWeaponCriticalDamage(it)})")
                }
            }

            if (character.secondaryWeaponExist()) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(character.secondaryWeapon().weaponType.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.secondaryWeapon().weight}")
                writer.writeln("Quality: ${character.secondaryWeapon().quality}")
                writer.writeln("Price: ${character.secondaryWeapon().price}")
                writer.newLine()
                writer.writeln("Damage:")
                character.secondaryWeapon().damage.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
                writer.newLine()
                writer.writeln("Stamina per use: ${character.neededStaminaForSecondaryWeaponAttack}")
                writer.writeln("Attack frequency: ${character.secondaryWeaponUsingFrequency()}/${character.secondaryWeaponUsingFrequency(MAX)}")
                writer.writeln("Attack duration: ${character.secondaryWeaponAttackDuration()}/${character.secondaryWeaponAttackDuration(MAX)}")
                writer.writeln("Defense duration: ${character.secondaryWeaponDefenseDuration()}/${character.secondaryWeaponDefenseDuration(MAX)}")

                writer.newLine()
                writer.writeln("=================")
                writer.writeln("Secondary Weapon Damage:")
                writer.writeln("=================")
                writer.newLine()
                Specification.values().forEach {
                    writer.writeln("$it = ${character.secondaryWeaponDamage(it)} (${character.secondaryWeaponCriticalDamage(it)})")
                }
            }

            if (character.shieldExist()) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(character.shield().shieldType.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.shield().weight}")
                writer.writeln("Quality: ${character.shield().quality}")
                writer.writeln("Price: ${character.shield().price}")
                writer.newLine()
                writer.writeln("Stamina per use: ${character.neededStaminaForShieldUsing}")
                writer.writeln("Using frequency: ${character.shieldUsingFrequency()}")
                writer.writeln("Using duration: ${character.shieldUsingDuration()}")
                writer.newLine()
                writer.writeln("Defense:")
                character.shield().defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(HELM)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(HELM.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.HEAD).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.HEAD).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.HEAD).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.HEAD) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(RIGHT_SHOULDER_ARMOR)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(RIGHT_SHOULDER_ARMOR.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.RIGHT_SHOULDER).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.RIGHT_SHOULDER).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.RIGHT_SHOULDER).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.RIGHT_SHOULDER) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(LEFT_SHOULDER_ARMOR)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(LEFT_SHOULDER_ARMOR.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.LEFT_SHOULDER).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.LEFT_SHOULDER).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.LEFT_SHOULDER).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.LEFT_SHOULDER) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(GLOVES)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(GLOVES.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.HANDS).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.HANDS).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.HANDS).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.HANDS) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(BODY_ARMOR)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(BODY_ARMOR.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.BODY).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.BODY).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.BODY).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.BODY) as Armor).defense.forEach { k, v ->
                    writer.writeln("$k = $v")
                }
            }

            if (character.has(RIGHT_LEG_ARMOR)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(RIGHT_LEG_ARMOR.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.RIGHT_LEG).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.RIGHT_LEG).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.RIGHT_LEG).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.RIGHT_LEG) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(LEFT_LEG_ARMOR)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln(LEFT_LEG_ARMOR.toString().replace("_", " "))
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.LEFT_LEG).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.LEFT_LEG).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.LEFT_LEG).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.LEFT_LEG) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            if (character.has(BOOTS)) {
                writer.newLine()
                writer.writeln("=================")
                writer.writeln("${BOOTS}")
                writer.writeln("=================")
                writer.newLine()
                writer.writeln("Weight: ${character.equipmentOf(BodyPart.FOOTS).weight}")
                writer.writeln("Quality: ${character.equipmentOf(BodyPart.FOOTS).quality}")
                writer.writeln("Price: ${character.equipmentOf(BodyPart.FOOTS).price}")
                writer.newLine()
                writer.writeln("Defense:")
                (character.equipmentOf(BodyPart.FOOTS) as Armor).defense.forEach({ k, v ->
                    writer.writeln("$k = $v")
                })
            }

            writer.newLine()
            writer.writeln("=================")
            writer.writeln("Total Equipment:")
            writer.writeln("=================")
            writer.newLine()
            writer.writeln("Weight: ${character.equipmentWeight}")
            writer.writeln("Price: ${character.equipmentPrice}")
            writer.newLine()
            writer.writeln("Defense:")
            Specification.values().forEach {
                writer.writeln("$it = ${character.totalDefense(it)}")
            }

            writer.newLine()
            writer.writeln("=================")
            writer.writeln("Skills:")
            writer.writeln("=================")
            writer.newLine()
            Skill.values().forEach {
                writer.writeln("$it = ${character.skill(it)}")
            }
        }
    }

    fun save(character: Character) : Character {
        try {
            BufferedWriter(FileWriter(File("db/memory/" + character.who + ".txt"))).use { writer ->
                writer.writeln(character.who)
                writer.write(character.concentration().toString() + ",")
                writer.write("${character.strength()},")
                writer.writeln("${character.agility()}")
                writer.write("${character.health()},")
                writer.writeln("${character.stamina()}")
//                character.skills.forEach { k, v ->
//                    try {
//                        writer.writeln("$k = $v,")
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
    
    private fun BufferedWriter.writeln(line: String) {
        write(line)
        newLine()
    }
}
