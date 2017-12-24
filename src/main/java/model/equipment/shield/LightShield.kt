package model.equipment.shield

import model.support.Constants.FIVE
import model.support.enums.EquipmentType.SHIELD
import model.support.enums.ShieldType.LIGHT_SHIELD
import model.support.enums.Specification

class LightShield(
        weight: Double,
        quality: Int
) : Shield(
        SHIELD,
        LIGHT_SHIELD,
        weight,
        quality,
        quality / FIVE - weight * 2
) {
    init {
        with(defense) {
            put(Specification.BLUNT, quality * 0.20)
            put(Specification.CUT, quality * 0.20)
            put(Specification.SLASH, quality * 0.20)
            put(Specification.PIERCING, quality * 0.20)
            put(Specification.CRUSH, quality * 0.20)
        }
    }
}
