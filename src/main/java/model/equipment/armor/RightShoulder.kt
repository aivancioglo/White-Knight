package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.RIGHT_SHOULDER_ARMOR
import model.support.enums.BodyPart
import model.support.enums.BodyPart.RIGHT_SHOULDER
import model.support.enums.EquipmentType.ARMOR

class RightShoulder(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, RIGHT_SHOULDER_ARMOR, material, RIGHT_SHOULDER, weight, quality)