package model.equipment.armor

import model.support.enums.ArmorMaterial
import model.support.enums.ArmorType.HELM
import model.support.enums.BodyPart
import model.support.enums.BodyPart.HEAD
import model.support.enums.EquipmentType.ARMOR

class Helm(material: ArmorMaterial, weight: Double, quality: Int) : Armor(ARMOR, HELM, material, HEAD, weight, quality)