import model.Arena
import model.Character
import model.equipment.shield.LightShield
import model.equipment.weapon.Sword
import model.support.enums.BattleStyle
import model.support.enums.BattleStyle.*
import view.Console

fun main(args: Array<String>) {
    val avatar = Character(who = "Аватар", whom = "Аватара", toWhom = "Аватару", byWhom = "Аватаром", aboutWhom = "Аватаре")
    val logan = Character(who = "Логан", whom = "Логана", toWhom = "Логану", byWhom = "Логаном", aboutWhom = "Логане", battleStyle = MODERATE)

//    avatar.increaseSkill(HANDS_FIGHT_SKILL, 24.0)
//    avatar.increaseSkill(KNIFE_SKILL, 12.0)
//    avatar.increaseSkill(CLOTH_ARMOR_SKILL, 2.0)
//    avatar.increaseConcentration(2.0)
//    avatar.increaseSuppleness(3.0)
    avatar.increaseStrength(4.0)
//    avatar.increaseAgility(15.0)
//    avatar.increaseQuickness(35.0)
//    avatar.equipRightHand(Sword(1.5, 10))
    avatar.equipLeftHand(LightShield(1.5, 30))
//    avatar.equip(Helm(SCALY, 1.0, 10))
//    avatar.equip(RightShoulder(SCALY, 0.5, 30))
//    avatar.equip(LeftShoulder(SCALY, 0.5, 30))
//    avatar.equip(Gloves(SCALY, 0.5, 5))
//    avatar.equip(Body(SCALY, 2.5, 25))
//    avatar.equip(RightLeg(SCALY, 0.5, 15))
//    avatar.equip(LeftLeg(SCALY, 0.5, 15))
//    avatar.equip(Boots(SCALY, 1.5, 15))

//    DataBase.printToFile(avatar)
//    avatar.increaseInjuries(9.0)

    logan.increaseAgility(3.0)
//    logan.increaseTiredness(150.0)
//    logan.equip(Body(LEATHER, 2.5, 30))
//    logan.equipLeftHand(LightShield(2.0, 50))

    Arena.battleOf(avatar, logan, 1500)

    Console.print(avatar)
    Console.print(logan)
}