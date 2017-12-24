package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.BOOTS
import model.support.enums.BodyPart
import model.support.enums.BodyPart.FOOTS
import model.support.enums.EquipmentType.ARMOR

class Boots(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, BOOTS, material, FOOTS, weight, quality)