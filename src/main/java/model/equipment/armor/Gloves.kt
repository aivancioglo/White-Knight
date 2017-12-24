package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.GLOVES
import model.support.enums.BodyPart
import model.support.enums.BodyPart.HANDS
import model.support.enums.EquipmentType.ARMOR

class Gloves(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, GLOVES, material, HANDS, weight, quality)