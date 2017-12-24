package model.equipment.shield

import model.support.Constants.FIVE
import model.support.Constants.THREE
import model.support.enums.EquipmentType.SHIELD
import model.support.enums.ShieldType.HEAVY_SHIELD
import model.support.enums.Specification

class HeavyShield(
        weight: Double,
        quality: Int
) : Shield(
        SHIELD,
        HEAVY_SHIELD,
        weight,
        quality,
        quality / FIVE * THREE - weight * 2
) {
    init {
        with(defense) {
            put(Specification.BLUNT, quality * 0.20 * 3.0)
            put(Specification.CUT, quality * 0.20 * 3.0)
            put(Specification.SLASH, quality * 0.20 * 3.0)
            put(Specification.PIERCING, quality * 0.20 * 3.0)
            put(Specification.CRUSH, quality * 0.20 * 3.0)
        }
    }
}
