package model.equipment.armor;

import model.equipment.Equipment;
import model.support.allEnums.ArmorMaterial;
import model.support.allEnums.ArmorType;
import model.support.allEnums.DmgType;
import model.support.allEnums.EquipmentType;

import java.util.HashMap;
import java.util.Map;

import static model.support.Constants.*;
import static model.support.allEnums.ArmorMaterial.*;

public abstract class Armor extends Equipment {
    private ArmorMaterial material;
    private ArmorType armorType;

    Map<DmgType, Double> defense = new HashMap<>();

    Armor(EquipmentType equipmentType, ArmorType armorType, ArmorMaterial material, double weight, int quality) {
        super(equipmentType);
        this.armorType = armorType;
        this.material = material;
        this.weight = weight;
        this.quality = quality;

        statistic();
    }

    public ArmorMaterial getMaterial() {
        return material;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public double getPrice() {
        return price;
    }

    public double getQuality() {
        return quality;
    }

    public double getWeight() {
        return weight;
    }

    public Map<DmgType, Double> getDefense() {
        return defense;
    }

    private void statistic() {
        switch (material) {
            case CLOTH:
                setClothArmor(CLOTH.coefficient);
                price = (quality / FIVE - weight * TWO) * CLOTH.coefficient;
                break;
            case LEATHER:
                setLeatherArmor(LEATHER.coefficient);
                price = (quality / FIVE - weight * TWO) * LEATHER.coefficient;
                break;
            case CHAIN:
                setChainArmor(CHAIN.coefficient);
                price = (quality / FIVE - weight * TWO) * CHAIN.coefficient;
                break;
            case SCALY:
                setScalyArmor(SCALY.coefficient);
                price = (quality / FIVE - weight * TWO) * SCALY.coefficient;
                break;
            case LAT:
                setLatArmor(LAT.coefficient);
                price = (quality / FIVE - weight * TWO) * LAT.coefficient;
                break;
        }
    }

    private void setClothArmor(double modifier) {
        defense.put(DmgType.BLUNT, quality * 0.25 * modifier);
        defense.put(DmgType.CUT, quality * 0.25 * modifier);
        defense.put(DmgType.SLASH, quality * 0.15 * modifier);
        defense.put(DmgType.PIERCING, quality * 0.05 * modifier);
        defense.put(DmgType.CRUSH, quality * 0.30 * modifier);
    }

    private void setLeatherArmor(double modifier) {
        defense.put(DmgType.BLUNT, quality * 0.30 * modifier);
        defense.put(DmgType.CUT, quality * 0.25 * modifier);
        defense.put(DmgType.SLASH, quality * 0.15 * modifier);
        defense.put(DmgType.PIERCING, quality * 0.05 * modifier);
        defense.put(DmgType.CRUSH, quality * 0.25 * modifier);
    }

    private void setChainArmor(double modifier) {
        defense.put(DmgType.BLUNT, quality * 0.15 * modifier);
        defense.put(DmgType.CUT, quality * 0.35 * modifier);
        defense.put(DmgType.SLASH, quality * 0.10 * modifier);
        defense.put(DmgType.PIERCING, quality * 0.20 * modifier);
        defense.put(DmgType.CRUSH, quality * 0.20 * modifier);
    }

    private void setScalyArmor(double modifier) {
        defense.put(DmgType.BLUNT, quality * 0.25 * modifier);
        defense.put(DmgType.CUT, quality * 0.30 * modifier);
        defense.put(DmgType.SLASH, quality * 0.10 * modifier);
        defense.put(DmgType.PIERCING, quality * 0.15 * modifier);
        defense.put(DmgType.CRUSH, quality * 0.20 * modifier);
    }

    private void setLatArmor(double modifier) {
        defense.put(DmgType.BLUNT, quality * 0.20 * modifier);
        defense.put(DmgType.CUT, quality * 0.35 * modifier);
        defense.put(DmgType.SLASH, quality * 0.15 * modifier);
        defense.put(DmgType.PIERCING, quality * 0.25 * modifier);
        defense.put(DmgType.CRUSH, quality * 0.05 * modifier);
    }
}
