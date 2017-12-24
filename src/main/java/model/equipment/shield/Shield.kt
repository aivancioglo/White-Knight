package model.equipment.shield

import model.equipment.Equipment
import model.support.enums.EquipmentType
import model.support.enums.ShieldType
import model.support.enums.Specification
import java.util.*

abstract class Shield(
        equipmentType: EquipmentType,
        val shieldType: ShieldType,
        weight: Double,
        quality: Int,
        price: Double
) : Equipment(
        equipmentType,
        weight,
        quality,
        price
) {

    var defense: MutableMap<Specification, Double> = HashMap()

    init {
        defense.put(Specification.BLUNT, 0.0)
        defense.put(Specification.CUT, 0.0)
        defense.put(Specification.SLASH, 0.0)
        defense.put(Specification.PIERCING, 0.0)
        defense.put(Specification.CRUSH, 0.0)
    }
}
