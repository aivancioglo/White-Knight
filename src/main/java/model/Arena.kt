package model

import model.support.enums.AttackType.*
import model.support.enums.DefenseType
import model.support.enums.DefenseType.*
import java.io.BufferedWriter
import java.io.File

object Arena {
    private var step: Int = 1

    fun battleOf(your: Character, enemy: Character, steps: Int) {
        your.newStep(enemy)
        enemy.newStep(your)

        File("db/print/Arena.txt").bufferedWriter().use { writer ->
            writer.writeln("Начинается бой!")
            writer.newLine()

            if (your.hasNextTechnique() || enemy.hasNextTechnique()) {

                var youFirst = if (your.hasNextTechnique() && enemy.hasNextTechnique())
                    your.nextTechniqueInfo().attackDuration < enemy.nextTechniqueInfo().attackDuration
                else your.hasNextTechnique()

                if (youFirst) {
                    writer.writeln("${your.who} оказывается быстрее. У него есть преимущество вначале.")

                    while (your.isAlive && enemy.isAlive) {
                        stepFight(writer, your, enemy)
                        if (step > steps) {
                            writer.write("Вреия боя истекло!")
                            return
                        }
                    }
                } else {
                    writer.writeln("${enemy.who} оказывается быстрее. У него есть преимущество вначале.")

                    while (enemy.isAlive && your.isAlive) {
                        stepFight(writer, enemy, your)
                        if (step > steps) {
                            writer.write("Вреия боя истекло!")
                            return
                        }
                    }
                }
            }
        }

        step = 0
    }

    private fun stepFight(writer: BufferedWriter, first: Character, second: Character) {
        var defenderHealth: Double
        var firstOnTheKnees = first.onTheKnees
        var secondOnTheKnees = second.onTheKnees
        var result: DefenseType

        writer.writeln("$step: ${first.who} (${first.health()}/${first.stamina()}) / ${second.who} (${second.health()}/${second.stamina()})")

        if (first.wantToAttack) {
            while (first.hasNextTechnique()) {
                defenderHealth = second.health()

                writer.write("    ${first.who} атакует ${
                when (first.nextTechniqueInfo().type) {
                    RIGHT_HAND_ATTACK -> "правой рукой"
                    LEFT_HAND_ATTACK -> "левой рукой"
                    PRIMARY_WEAPON_ATTACK -> "первичным оружием"
                    SECONDARY_WEAPON_ATTACK -> "вторичным оружием"
                }} ${second.whom}")

                result = second.defenseFrom(first.nextTechniqueUsing())

                when (result) {
                    COUNTERATTACK -> writer.writeln(", но ${second.who} контратакует.")
                    BLOCK -> {
                        if (!secondOnTheKnees && second.onTheKnees) {
                            secondOnTheKnees = true
                            writer.writeln(". ${second.toWhom} удаётся заблокировать щитом удар ${first.whom}, но он валится на землю без сил.")
                        } else
                            writer.writeln(", но ${second.toWhom} удаётся заблокировать щитом удар ${first.whom}.")
                    }
                    DODGE -> {
                        if (!secondOnTheKnees && second.onTheKnees) {
                            secondOnTheKnees = true
                            writer.writeln(". ${second.who} уклоняется от удара ${first.whom}, но валится на землю без сил.")
                        } else
                            writer.writeln(", но ${second.who} уклоняется от удара ${first.whom}.")
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
                            !second.isAlive -> writer.writeln(" и наносит ${defenderHealth - second.health()} урон(а). ${second.who} падает при смерти... Бой окончен!")
                        }
                    }
                }

                if (!second.isAlive) {
                    first.newStep()
                    return
                }
            }
        }

        if (second.wantToAttack) {
            while (second.hasNextTechnique()) {
                defenderHealth = first.health()

                writer.write("    ${second.who} атакует ${
                when (second.nextTechniqueInfo().type) {
                    RIGHT_HAND_ATTACK -> "правой рукой"
                    LEFT_HAND_ATTACK -> "левой рукой"
                    PRIMARY_WEAPON_ATTACK -> "первичным оружием"
                    SECONDARY_WEAPON_ATTACK -> "вторичным оружием"
                }} ${first.whom}")

                result = first.defenseFrom(second.nextTechniqueUsing())

                when (result) {
                    COUNTERATTACK -> writer.writeln(", но ${first.who} контратакует.")
                    BLOCK -> {
                        if (!firstOnTheKnees && first.onTheKnees) {
                            firstOnTheKnees = true
                            writer.writeln(". ${first.toWhom} удаётся заблокировать щитом удар ${second.whom}, но он валится на землю без сил.")
                        } else
                            writer.writeln(", но ${first.toWhom} удаётся заблокировать щитом удар ${second.whom}.")
                    }
                    DODGE -> {
                        if (!firstOnTheKnees && first.onTheKnees) {
                            firstOnTheKnees = true
                            writer.writeln(". ${first.who} уклоняется от удара ${second.whom}, но валится на землю без сил.")
                        } else
                            writer.writeln(", но ${first.who} уклоняется от удара ${second.whom}.")
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
                            !first.isAlive -> writer.writeln(" и наносит ${defenderHealth - first.health()} урон(а). ${first.who} падает при смерти... Бой окончен!")
                        }
                    }
                }

                if (!first.isAlive) {
                    second.newStep()
                    return
                }
            }
        }

        step++
        first.newStep(second)

        if (firstOnTheKnees && !first.onTheKnees)
            writer.writeln("${first.who} поднимается на ноги.")

        second.newStep(first)

        if (secondOnTheKnees && !second.onTheKnees)
            writer.writeln("${second.who} поднимается на ноги.")

        writer.newLine()
    }

    private fun BufferedWriter.writeln(line: String) {
        write(line)
        newLine()
    }
}