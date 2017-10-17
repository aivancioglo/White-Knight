package model.equipment;

import model.support.allEnums.EquipmentType;

public abstract class Equipment {
    protected EquipmentType equipmentType;
    protected double quality;
    protected double price;
    protected double weight;

    public Equipment(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public double getQuality() {
        return quality;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }
}
