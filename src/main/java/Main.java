import model.equipment.armor.Body;
import model.equipment.armor.Boots;
import model.equipment.armor.Gloves;
import model.equipment.armor.Helm;
import model.equipment.shield.HeavyShield;
import model.equipment.weapon.Sword;
import model.support.allEnums.ArmorMaterial;
import model.support.allEnums.Skill;
import model.unit.Unit;
import view.Console;

public class Main {
    public static void main(String[] args) throws Exception {
        Unit avatar = new Unit("Avatar");
//        avatar.addToSkill(Skill.MIDDLE_SHIELD_SKILL, 5);
//        avatar.addToSkill(Skill.SWORD_SKILL, 12);
//        avatar.addToSkill(Skill.LAT_ARMOR_SKILL, 2);
//        avatar.addToConcentration(1);
        avatar.addToAgility(30);
//        avatar.addToStrength(3);
//        avatar.equipWeapon(new Sword(3.5, 700));
//        avatar.equipShield(new HeavyShield(5, 500));
//        avatar.equipArmor(new Helm(ArmorMaterial.LAT, 2.5, 250));
        avatar.equipArmor(new Body(ArmorMaterial.LAT, 30, 1000));
//        avatar.equipArmor(new Gloves(ArmorMaterial.LAT, 3, 300));
//        avatar.equipArmor(new Boots(ArmorMaterial.LAT, 5, 500));

        Console.print(avatar);
    }
}
