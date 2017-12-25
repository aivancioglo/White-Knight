package model

import model.equipment.weapon.*
import model.support.Constants.FIFTY
import model.support.enums.AttackType.*
import model.support.enums.DefenseType
import model.support.enums.DefenseType.*
import java.io.BufferedWriter
import java.io.File

object Arena {
    private var step: Int = 1

    fun battleOf(your: Character, enemy: Character, steps: Int = FIFTY, file: String = "Arena"): Character {
        your.newStep(enemy)
        enemy.newStep(your)

        File("db/print/$file.txt").bufferedWriter().use { writer ->
            if (your.hasNextTechnique() || enemy.hasNextTechnique()) {
                writer.writeln("Начинается бой!")
                writer.newLine()

                val youFirst = if (your.hasNextTechnique() && enemy.hasNextTechnique())
                    your.nextTechniqueInfo().attackDuration < enemy.nextTechniqueInfo().attackDuration
                else your.hasNextTechnique()

                if (youFirst) {
                    writer.writeln("${your.who} оказывается быстрее. У него есть преимущество вначале.")

                    while (your.isAlive && enemy.isAlive) {
                        stepFight(writer, your, enemy)
                        if (step > steps) {
                            writer.write("Вреия боя истекло! Никто не вышел победителем!")
                            throw Exception("Вреия боя истекло! Никто не вышел победителем!")
                        }
                    }
                } else {
                    writer.writeln("${enemy.who} оказывается быстрее. У него есть преимущество вначале.")

                    while (enemy.isAlive && your.isAlive) {
                        stepFight(writer, enemy, your)
                        if (step > steps) {
                            writer.write("Вреия боя истекло! Никто не вышел победителем!")
                            throw Exception("Вреия боя истекло! Никто не вышел победителем!")
                        }
                    }
                }
            }
        }

        step = 1

        if (!your.isAlive || !enemy.isAlive) {
            return if (your.isAlive)
                your
            else
                enemy
        } else
            throw Exception("Вреия боя истекло! Никто не вышел победителем!")
    }

    private fun stepFight(writer: BufferedWriter, first: Character, second: Character) {
        var defenderHealth: Double
        var firstOnTheKnees = first.onTheKnees
        var secondOnTheKnees = second.onTheKnees
        var result: DefenseType

        writer.writeln("$step: ${first.who} (${first.health()}/${first.stamina()}) / ${second.who} (${second.health()}/${second.stamina()})")

        while (first.hasNextTechnique()) {
            defenderHealth = second.health()

            writer.write("    ${first.who} атакует ${
            when (first.nextTechniqueInfo().type) {
                RIGHT_HAND_ATTACK -> "правой рукой"
                LEFT_HAND_ATTACK -> "левой рукой"
                PRIMARY_WEAPON_ATTACK -> {
                    when (first.primaryWeapon()) {
                        is Axe -> "топором"
                        is Halberd -> "алебардой"
                        is Hammer -> "молотом"
                        is Knife -> "ножом"
                        is Mace -> "булавой"
                        is Spear -> "копьём"
                        is Staff -> "посохом"
                        is Sword -> "мечём"
                        is TwoHandedAxe -> "двуручным топором"
                        is TwoHandedHammer -> "двуручным молотов"
                        is TwoHandedMace -> "двуручной булавой"
                        is TwoHandedSword -> "двуручным мечём"
                        else -> "чем-то в руке"
                    }
                }
                SECONDARY_WEAPON_ATTACK -> {
                    when (first.secondaryWeapon()) {
                        is Axe -> "топором"
                        is Halberd -> "алебардой"
                        is Hammer -> "молотом"
                        is Knife -> "ножом"
                        is Mace -> "булавой"
                        is Spear -> "копьём"
                        is Staff -> "посохом"
                        is Sword -> "мечём"
                        is TwoHandedAxe -> "двуручным топором"
                        is TwoHandedHammer -> "двуручным молотов"
                        is TwoHandedMace -> "двуручной булавой"
                        is TwoHandedSword -> "двуручным мечём"
                        else -> "чем-то в руке"
                    }
                }
            }} ${second.whom}")

            result = second.defenseFrom(first.nextTechniqueUsing())

            when (result) {
                COUNTERATTACK -> writer.writeln(", но ${second.who} контратакует.")
                DODGE -> {
                    if (!secondOnTheKnees && second.onTheKnees) {
                        secondOnTheKnees = true
                        writer.writeln(". ${second.who} уклоняется, но валится на землю без сил.")
                    } else
                        writer.writeln(", но ${second.who} уклоняется.")
                }
                BLOCK -> {
                    if (!secondOnTheKnees && second.onTheKnees) {
                        secondOnTheKnees = true
                        writer.writeln(". ${second.toWhom} удаётся заблокировать его щитом, но он валится на землю без сил.")
                    } else
                        writer.writeln(", но ${second.toWhom} удаётся заблокировать его щитом.")
                }
                PARTIAL_BLOCK -> {
                    if (!secondOnTheKnees && second.onTheKnees) {
                        secondOnTheKnees = true
                        writer.write(". ${second.who} блокируется щитом, но ${first.who} пробивает блок")
                        if (defenderHealth > second.health())
                            writer.writeln(", нанося ${defenderHealth - second.health()} урон(а). От удара ${second.who} валится с ног.")
                        else
                            writer.writeln(". Хоть и броня защищает от урона, ${second.who} валится с ног от удара.")
                    } else {
                        writer.write(". ${second.who} блокируется щитом, но ${first.who} пробивает блок")
                        if (defenderHealth > second.health())
                            writer.writeln(" и наносит ${defenderHealth - second.health()} урон(а).")
                        else
                            writer.writeln(", хоть и броня защищает от урона.")
                    }
                }
                ARMOR_DEFENSE -> {
                    if (!secondOnTheKnees && second.onTheKnees) {
                        secondOnTheKnees = true
                        writer.writeln(". ${second.who} не получает урона благодаря броне, но валится на землю без сил.")
                    } else
                        writer.writeln(", но ${second.who} не получает урона благодаря броне.")
                }
                FAILURE -> {
                    when {
                        second.isAlive -> {
                            if (!secondOnTheKnees && second.onTheKnees) {
                                secondOnTheKnees = true
                                writer.writeln(" и сильным ударом валит его на землю, нанося ${defenderHealth - second.health()} урон(а).")
                            } else
                                writer.writeln(" и наносит ему ${defenderHealth - second.health()} урон(а).")
                        }
                        !second.isAlive -> writer.writeln(" и наносит ${defenderHealth - second.health()} урон(а).\n\n${second.who} падает при смерти... Бой окончен!")
                    }
                }
            }

            if (!second.isAlive) {
                first.newStep()
                return
            }
        }

        while (second.hasNextTechnique()) {
            defenderHealth = first.health()

            writer.write("    ${second.who} атакует ${
            when (second.nextTechniqueInfo().type) {
                RIGHT_HAND_ATTACK -> "правой рукой"
                LEFT_HAND_ATTACK -> "левой рукой"
                PRIMARY_WEAPON_ATTACK -> {
                    when (second.primaryWeapon()) {
                        is Axe -> "топором"
                        is Halberd -> "алебардой"
                        is Hammer -> "молотом"
                        is Knife -> "ножом"
                        is Mace -> "булавой"
                        is Spear -> "копьём"
                        is Staff -> "посохом"
                        is Sword -> "мечём"
                        is TwoHandedAxe -> "двуручным топором"
                        is TwoHandedHammer -> "двуручным молотов"
                        is TwoHandedMace -> "двуручной булавой"
                        is TwoHandedSword -> "двуручным мечём"
                        else -> "чем-то в руке"
                    }
                }
                SECONDARY_WEAPON_ATTACK -> {
                    when (second.secondaryWeapon()) {
                        is Axe -> "топором"
                        is Halberd -> "алебардой"
                        is Hammer -> "молотом"
                        is Knife -> "ножом"
                        is Mace -> "булавой"
                        is Spear -> "копьём"
                        is Staff -> "посохом"
                        is Sword -> "мечём"
                        is TwoHandedAxe -> "двуручным топором"
                        is TwoHandedHammer -> "двуручным молотов"
                        is TwoHandedMace -> "двуручной булавой"
                        is TwoHandedSword -> "двуручным мечём"
                        else -> "чем-то в руке"
                    }
                }
            }} ${first.whom}")

            result = first.defenseFrom(second.nextTechniqueUsing())

            when (result) {
                COUNTERATTACK -> writer.writeln(", но ${first.who} контратакует.")
                DODGE -> {
                    if (!firstOnTheKnees && first.onTheKnees) {
                        firstOnTheKnees = true
                        writer.writeln(". ${first.who} уклоняется, но валится на землю без сил.")
                    } else
                        writer.writeln(", но ${first.who} уклоняется.")
                }
                BLOCK -> {
                    if (!firstOnTheKnees && first.onTheKnees) {
                        firstOnTheKnees = true
                        writer.writeln(". ${first.toWhom} удаётся заблокировать его щитом, но он валится на землю без сил.")
                    } else
                        writer.writeln(", но ${first.toWhom} удаётся заблокировать его щитом.")
                }
                PARTIAL_BLOCK -> {
                    if (!firstOnTheKnees && first.onTheKnees) {
                        firstOnTheKnees = true
                        writer.write(". ${first.who} блокируется щитом, но ${second.who} пробивает блок")
                        if (defenderHealth > first.health())
                            writer.writeln(", нанося ${defenderHealth - first.health()} урон(а). От удара ${first.who} валится с ног.")
                        else
                            writer.writeln(". Хоть и броня защищает от урона, ${first.who} валится с ног от удара.")
                    } else {
                        writer.write(". ${first.who} блокируется щитом, но ${second.who} пробивает блок")
                        if (defenderHealth > first.health())
                            writer.writeln(" и наносит ${defenderHealth - first.health()} урон(а).")
                        else
                            writer.writeln(", хоть и броня защищает от урона.")
                    }
                }
                ARMOR_DEFENSE -> {
                    if (!firstOnTheKnees && first.onTheKnees) {
                        firstOnTheKnees = true
                        writer.writeln(". ${first.who} не получает урона благодаря броне, но валится на землю без сил.")
                    } else
                        writer.writeln(", но ${first.who} не получает урона благодаря броне.")
                }
                FAILURE -> {
                    when {
                        first.isAlive -> {
                            if (!firstOnTheKnees && first.onTheKnees) {
                                firstOnTheKnees = true
                                writer.writeln(" и сильным ударом валит его на землю, нанося ${defenderHealth - second.health()} урон(а).")
                            } else
                                writer.writeln(" и наносит ему ${defenderHealth - first.health()} урон(а).")
                        }
                        !first.isAlive -> writer.writeln(" и наносит ${defenderHealth - first.health()} урон(а).\n\n${first.who} падает при смерти... Бой окончен!")
                    }
                }
            }

            if (!first.isAlive) {
                second.newStep()
                return
            }
        }

        step++

        first.newStep(second)

        if (firstOnTheKnees && !first.onTheKnees)
            writer.writeln("    ${first.who} поднимается на ноги.")

        second.newStep(first)

        if (secondOnTheKnees && !second.onTheKnees)
            writer.writeln("    ${second.who} поднимается на ноги.")

        writer.newLine()
    }

    private fun BufferedWriter.writeln(line: String) {
        write(line)
        newLine()
    }
}