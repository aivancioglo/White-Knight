package model.equipment

import model.support.enums.EquipmentType

abstract class Equipment(val equipmentType: EquipmentType, weight: Double = 0.0, quality: Int = 0, price: Double = 0.0) {
    var weight = weight
        protected set
    var quality = quality
        protected set
    var price = if (price < 0) 0.0 else price
        protected set

    companion object {
        val NONE = object : Equipment(EquipmentType.NONE) {}
    }
}
