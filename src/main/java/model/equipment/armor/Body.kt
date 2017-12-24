package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.BODY_ARMOR
import model.support.enums.BodyPart
import model.support.enums.BodyPart.*
import model.support.enums.EquipmentType.ARMOR

class Body(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, BODY_ARMOR, material, BODY, weight, quality)