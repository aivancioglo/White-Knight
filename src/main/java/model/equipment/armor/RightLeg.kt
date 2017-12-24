package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.RIGHT_LEG_ARMOR
import model.support.enums.BodyPart
import model.support.enums.BodyPart.RIGHT_LEG
import model.support.enums.EquipmentType.ARMOR

class RightLeg(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, RIGHT_LEG_ARMOR, material, RIGHT_LEG, weight, quality)