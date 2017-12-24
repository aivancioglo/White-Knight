package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.LEFT_LEG_ARMOR
import model.support.enums.BodyPart
import model.support.enums.BodyPart.LEFT_LEG
import model.support.enums.EquipmentType.ARMOR

class LeftLeg(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, LEFT_LEG_ARMOR, material, LEFT_LEG, weight, quality)