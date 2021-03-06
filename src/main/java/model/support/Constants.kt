package model.support

import model.equipment.Equipment.Companion.NONE
import model.support.enums.BodyPart.*
import model.support.enums.Skill.*
import model.support.enums.Specification.*

object Constants {
    val ONE = 1
    val TWO = 2
    val THREE = 3
    val FOUR = 4
    val FIVE = 5
    val TEN = 10
    val FIFTEEN = 15
    val TWENTY = 20
    val THIRTY = 30
    val FORTY = 40
    val FIFTY = 50
    val EIGHTY = 80
    val NINETY = 90
    val HUNDRED = 100

    val ACTUAL = "actual"
    val MAX = "max"
    val INJURIES = "forfeit"
    val TIREDNESS = "tiredness"
    val DEBUFF = "debuff"
    val USED = "used"
    val PERCENTAGE = "percentage"

    fun defaultStats() = hashMapOf(
            BLUNT to 0.0,
            CUT to 0.0,
            PIERCING to 0.0,
            CRUSH to 0.0,
            SLASH to 0.0)

    fun defaultEquipment() = hashMapOf(
            HEAD to NONE,
            BODY to NONE,
            RIGHT_SHOULDER to NONE,
            LEFT_SHOULDER to NONE,
            RIGHT_HAND to NONE,
            LEFT_HAND to NONE,
            HANDS to NONE,
            RIGHT_LEG to NONE,
            LEFT_LEG to NONE,
            FOOTS to NONE)

    fun defaultSkills() = hashMapOf(
            MAIN_BATTLE_EXPERIENCE to 0.0,
            ONE_HANDED_WEAPON_SKILL to 0.0,
            TWO_HANDED_WEAPON_SKILL to 0.0,
            SHIELD_BLOCK_SKILL to 0.0,
            ARMOR_SKILL to 0.0,
            HAND_TO_HAND_FIGHT_SKILL to 0.0,

            KNIFE_SKILL to 0.1,
            SWORD_SKILL to 0.1,
            AXE_SKILL to 0.1,
            MACE_SKILL to 0.1,
            HAMMER_SKILL to 0.1,

            TWO_HANDED_SWORD_SKILL to 0.1,
            TWO_HANDED_AXE_SKILL to 0.1,
            TWO_HANDED_MACE_SKILL to 0.1,
            TWO_HANDED_HAMMER_SKILL to 0.1,
            STAFF_SKILL to 0.1,
            SPEAR_SKILL to 0.1,
            HALBERD_SKILL to 0.1,

            LIGHT_SHIELD_SKILL to 0.1,
            MIDDLE_SHIELD_SKILL to 0.1,
            HEAVY_SHIELD_SKILL to 0.1,

            CLOTH_ARMOR_SKILL to 0.1,
            LEATHER_ARMOR_SKILL to 0.1,
            CHAIN_ARMOR_SKILL to 0.1,
            SCALY_ARMOR_SKILL to 0.1,
            LAT_ARMOR_SKILL to 0.1,

            HANDS_FIGHT_SKILL to 0.1,
            FOOTS_FIGHT_SKILL to 0.1,
            RIGHT_HAND_FIGHT_SKILL to 0.1,
            LEFT_HAND_FIGHT_SKILL to 0.1,
            RIGHT_FOOT_FIGHT_SKILL to 0.1,
            LEFT_FOOT_FIGHT_SKILL to 0.1)

}
