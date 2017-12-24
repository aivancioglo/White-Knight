package model.equipment.shield

import model.support.Constants.FIVE
import model.support.Constants.TWO
import model.support.enums.EquipmentType.SHIELD
import model.support.enums.ShieldType.MIDDLE_SHIELD
import model.support.enums.Specification

class MiddleShield(
        weight: Double,
        quality: Int
) : Shield(
        SHIELD,
        MIDDLE_SHIELD,
        weight,
        quality,
        quality / FIVE * TWO - weight * 2
) {
    init {
        with(defense) {
            put(Specification.BLUNT, quality * 0.20 * 2.0)
            put(Specification.CUT, quality * 0.20 * 2.0)
            put(Specification.SLASH, quality * 0.20 * 2.0)
            put(Specification.PIERCING, quality * 0.20 * 2.0)
            put(Specification.CRUSH, quality * 0.20 * 2.0)
        }
    }
}
