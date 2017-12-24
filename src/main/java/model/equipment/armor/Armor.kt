package model.equipment.armor

import model.equipment.Equipment
import model.support.Constants.FIVE
import model.support.Constants.TWO
import model.support.enums.*
import model.support.enums.ArmorMaterial.*
import model.support.enums.Skill.*
import model.support.enums.Specification.*

abstract class Armor(
        equipmentType: EquipmentType,
        val armorType: ArmorType,
        val material: ArmorMaterial,
        val bodyPart: BodyPart,
        weight: Double,
        quality: Int
) : Equipment(
        equipmentType,
        weight,
        quality,
        (quality / FIVE - weight * TWO) * material.coefficient
) {
    val skillType : Skill
    var defense: HashMap<Specification, Double> = HashMap()

    init {
        this.weight = weight
        this.quality = quality

        when (material) {
            CLOTH -> {
                with(defense) {
                    put(BLUNT, quality * 0.25 * material.coefficient)
                    put(CUT, quality * 0.25 * material.coefficient)
                    put(SLASH, quality * 0.15 * material.coefficient)
                    put(PIERCING, quality * 0.05 * material.coefficient)
                    put(CRUSH, quality * 0.30 * material.coefficient)
                }

                skillType = CLOTH_ARMOR_SKILL
            }

            LEATHER-> {
                with(defense) {
                    put(BLUNT, quality * 0.30 * material.coefficient)
                    put(CUT, quality * 0.25 * material.coefficient)
                    put(SLASH, quality * 0.15 * material.coefficient)
                    put(PIERCING, quality * 0.05 * material.coefficient)
                    put(CRUSH, quality * 0.25 * material.coefficient)
                }

                skillType = LEATHER_ARMOR_SKILL
            }

            CHAIN -> {
                with(defense) {
                    put(BLUNT, quality * 0.15 * material.coefficient)
                    put(CUT, quality * 0.35 * material.coefficient)
                    put(SLASH, quality * 0.10 * material.coefficient)
                    put(PIERCING, quality * 0.20 * material.coefficient)
                    put(CRUSH, quality * 0.20 * material.coefficient)
                }

                skillType = CHAIN_ARMOR_SKILL
            }

            SCALY -> {
                with(defense) {
                    put(BLUNT, quality * 0.25 * material.coefficient)
                    put(CUT, quality * 0.30 * material.coefficient)
                    put(SLASH, quality * 0.10 * material.coefficient)
                    put(PIERCING, quality * 0.15 * material.coefficient)
                    put(CRUSH, quality * 0.20 * material.coefficient)
                }

                skillType = SCALY_ARMOR_SKILL
            }

            LAT -> {
                with(defense) {
                    put(BLUNT, quality * 0.20 * material.coefficient)
                    put(CUT, quality * 0.35 * material.coefficient)
                    put(SLASH, quality * 0.15 * material.coefficient)
                    put(PIERCING, quality * 0.25 * material.coefficient)
                    put(CRUSH, quality * 0.05 * material.coefficient)
                }

                skillType = LAT_ARMOR_SKILL
            }
        }
    }
}
