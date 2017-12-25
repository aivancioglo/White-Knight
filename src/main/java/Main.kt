import model.Arena
import model.Character
import model.equipment.armor.*
import model.equipment.shield.LightShield
import model.equipment.weapon.Sword
import model.support.enums.ArmorMaterial
import model.support.enums.ArmorMaterial.*
import model.support.enums.AttackStyle
import model.support.enums.AttackStyle.*
import model.support.enums.AttackType
import model.support.enums.AttackType.*
import model.support.enums.Specification
import view.Console

fun main(args: Array<String>) {
    val avatar = Character(who = "Аватар", whom = "Аватара", toWhom = "Аватару", byWhom = "Аватаром", aboutWhom = "Аватаре")
    val logan = Character(who = "Логан", whom = "Логана", toWhom = "Логану", byWhom = "Логаном", aboutWhom = "Логане")
    val knight = Character(who = "Добрыня", whom = "Добрыню", toWhom = "Добрыне", byWhom = "Добрыней", aboutWhom = "Добрыне")

//    avatar.increaseSkill(HANDS_FIGHT_SKILL, 24.0)
//    avatar.increaseSkill(KNIFE_SKILL, 12.0)
//    avatar.increaseSkill(CLOTH_ARMOR_SKILL, 2.0)
//    avatar.increaseConcentration(2.0)
//    avatar.increaseSuppleness(3.0)
    avatar.increaseStrength(15.0)
//    avatar.increaseAgility(15.0)
//    avatar.increaseQuickness(5.0)
    avatar.equipRightHand(Sword(1.5, 10))
    avatar.equipLeftHand(LightShield(2.0, 60))
    avatar.equip(Helm(SCALY, 1.0, 30))
    avatar.equip(RightShoulder(SCALY, 0.5, 15))
    avatar.equip(LeftShoulder(SCALY, 0.5, 15))
    avatar.equip(Gloves(SCALY, 0.3, 3))
    avatar.equip(Body(SCALY, 2.5, 75))
    avatar.equip(RightLeg(SCALY, 0.5, 15))
    avatar.equip(LeftLeg(SCALY, 0.5, 15))
    avatar.equip(Boots(SCALY, 1.2, 36))

//    DataBase.printToFile(avatar)
//    avatar.increaseInjuries(9.0)

    logan.increaseStrength(5.0)
    logan.increaseQuickness(35.0)
//    logan.increaseAgility(7.0)
//    logan.increaseTiredness(150.0)
//    logan.equip(Body(LEATHER, 2.5, 30))
    logan.equipLeftHand(LightShield(2.0, 60))

    knight.increaseQuickness(10.0)

    val winner = Arena.battleOf(avatar, logan, 100, "FirstRound")
    Arena.battleOf(winner, knight, 100, "SecondRound")

    Console.print(avatar)
    Console.print(logan)
    Console.print(knight)
}