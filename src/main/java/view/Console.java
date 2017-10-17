package view;

import model.support.allEnums.ArmorType;
import model.unit.Unit;

public abstract class Console {
    public static void print(Unit unit) {
        System.out.println("");
        System.out.println("=================");
        System.out.println("Main Parameters:");
        System.out.println("=================");
        System.out.println();
        System.out.println("Concentration: " + unit.concentration());
        System.out.println("Strength: " + unit.strength());
        System.out.println("Agility: " + unit.agility());
        System.out.println();
        System.out.println("Health: " + unit.health() + "/" + unit.maxHealth());
        System.out.println("Stamina: " + unit.stamina() + "/" + unit.maxStamina());
        System.out.println();
        System.out.println("Equipment weight: " + unit.equipmentWeight());
        System.out.println();
        System.out.println("Needed stamina for weapon attack: " + unit.neededStaminaToWeaponAttack() + "/sec");
        System.out.println("Needed stamina for hand-to-hand attack: " + unit.neededStaminaForHandToHandAttack() + "/sec");
        System.out.println();
        System.out.println("Hand-to-hand attack speed: " + unit.handToHandAttackSpeed() + "/sec");
        System.out.println("Weapon attack speed: " + unit.weaponAttackSpeed() + "/sec");

        System.out.println("");
        System.out.println("=================");
        System.out.println("Total Defense:");
        System.out.println("=================");
        System.out.println();
        unit.totalDefense().forEach((k, v) -> System.out.println(k + " = " + v));

        if (unit.weapon() != null) {
            System.out.println("");
            System.out.println("=================");
            System.out.println("Weapon:");
            System.out.println("=================");
            System.out.println();
            System.out.println("Type: " + unit.weapon().getWeaponType().type);
            System.out.println("Weight: " + unit.weapon().getWeight());
            System.out.println("Quality: " + unit.weapon().getQuality());
            System.out.println("Price: " + unit.weapon().getPrice());
            System.out.println();
            unit.weapon().getDamage().forEach((k, v) -> System.out.println(k + " = " + v));

            System.out.println("");
            System.out.println("=================");
            System.out.println("Weapon Damage:");
            System.out.println("=================");
            System.out.println();
            unit.weaponDamage().forEach((k, v) -> System.out.println(k + " = " + v + " (" + unit.criticalWeaponDamage().get(k) + ")"));
        }

        if (unit.shield() != null) {
            System.out.println("");
            System.out.println("=================");
            System.out.println("Shield:");
            System.out.println("=================");
            System.out.println();
            System.out.println("Type: " + unit.shield().getShieldType().type);
            System.out.println("Weight: " + unit.shield().getWeight());
            System.out.println("Quality: " + unit.shield().getQuality());
            System.out.println("Price: " + unit.shield().getPrice());
            System.out.println();
            unit.shield().getDefense().forEach((k, v) -> System.out.println(k + " = " + v));
        }

        if (unit.armors().containsKey(ArmorType.HELM)) {
            System.out.println("");
            System.out.println("=================");
            System.out.println("Helm:");
            System.out.println("=================");
            System.out.println();
            System.out.println("Type: " + ArmorType.HELM);
            System.out.println("Weight: " + unit.armors().get(ArmorType.HELM).getWeight());
            System.out.println("Quality: " + unit.armors().get(ArmorType.HELM).getQuality());
            System.out.println("Price: " + unit.armors().get(ArmorType.HELM).getPrice());
            System.out.println();
            unit.armors().get(ArmorType.HELM).getDefense().forEach((k, v) -> System.out.println(k + " = " + v));
        }

        if (unit.armors().containsKey(ArmorType.BODY)) {
            System.out.println("");
            System.out.println("=================");
            System.out.println("Body:");
            System.out.println("=================");
            System.out.println();
            System.out.println("Type: " + ArmorType.BODY);
            System.out.println("Weight: " + unit.armors().get(ArmorType.BODY).getWeight());
            System.out.println("Quality: " + unit.armors().get(ArmorType.BODY).getQuality());
            System.out.println("Price: " + unit.armors().get(ArmorType.BODY).getPrice());
            System.out.println();
            unit.armors().get(ArmorType.BODY).getDefense().forEach((k, v) -> System.out.println(k + " = " + v));
        }

        if (unit.armors().containsKey(ArmorType.GLOVES)) {
            System.out.println("");
            System.out.println("=================");
            System.out.println("Gloves:");
            System.out.println("=================");
            System.out.println();
            System.out.println("Type: " + ArmorType.GLOVES);
            System.out.println("Weight: " + unit.armors().get(ArmorType.GLOVES).getWeight());
            System.out.println("Quality: " + unit.armors().get(ArmorType.GLOVES).getQuality());
            System.out.println("Price: " + unit.armors().get(ArmorType.GLOVES).getPrice());
            System.out.println();
            unit.armors().get(ArmorType.GLOVES).getDefense().forEach((k, v) -> System.out.println(k + " = " + v));
        }

        if (unit.armors().containsKey(ArmorType.BOOTS)) {
            System.out.println("");
            System.out.println("=================");
            System.out.println("Boots:");
            System.out.println("=================");
            System.out.println();
            System.out.println("Type: " + ArmorType.BOOTS);
            System.out.println("Weight: " + unit.armors().get(ArmorType.BOOTS).getWeight());
            System.out.println("Quality: " + unit.armors().get(ArmorType.BOOTS).getQuality());
            System.out.println("Price: " + unit.armors().get(ArmorType.BOOTS).getPrice());
            System.out.println();
            unit.armors().get(ArmorType.BOOTS).getDefense().forEach((k, v) -> System.out.println(k + " = " + v));
        }

        System.out.println("");
        System.out.println("=================");
        System.out.println("Hand-to-hand Damage:");
        System.out.println("=================");
        System.out.println();
        System.out.println(unit.handToHandDamage() + " (" + unit.criticalHandToHandDamage() + ")");

//        System.out.println("");
//        System.out.println("=================");
//        System.out.println("Skills:");
//        System.out.println("=================");
//        System.out.println();
//        unit.skills().forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
