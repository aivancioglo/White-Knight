package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.LEFT_SHOULDER_ARMOR
import model.support.enums.BodyPart
import model.support.enums.BodyPart.LEFT_SHOULDER
import model.support.enums.EquipmentType.ARMOR

class LeftShoulder(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, LEFT_SHOULDER_ARMOR, material, LEFT_SHOULDER, weight, quality)