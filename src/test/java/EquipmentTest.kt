import model.Character
import model.equipment.Equipment.Companion.NONE
import model.equipment.armor.*
import model.equipment.shield.HeavyShield
import model.equipment.shield.LightShield
import model.equipment.shield.Shield
import model.equipment.weapon.*
import model.support.enums.ArmorMaterial.CLOTH
import model.support.enums.ArmorMaterial.LAT
import model.support.enums.BodyPart.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("EquipmentTest")
class EquipmentTest {
    private val character = Character(who = "Warrior")

    // Weapons
    private val axe = Axe(1.0, 1)
    private val sword = Sword(1.0, 1)
    private val staff = Staff(1.0, 1)
    private val spear = Spear(1.0, 1)
    private val buckler = LightShield(1.0, 1)
    private val torch = HeavyShield(1.0, 1)

    // Armors
    private val helm = Helm(CLOTH, 1.0, 1)
    private val knightHelm = Helm(LAT, 1.0, 1)
    private val body = Body(CLOTH, 1.0, 1)
    private val knightBody = Body(LAT, 1.0, 1)
    private val rightShoulder = RightShoulder(CLOTH, 1.0, 1)
    private val knightRightShoulder = RightShoulder(LAT, 1.0, 1)
    private val leftShoulder = LeftShoulder(CLOTH, 1.0, 1)
    private val knightLeftShoulder = LeftShoulder(LAT, 1.0, 1)
    private val rightLeg = RightLeg(CLOTH, 1.0, 1)
    private val knightRightLeg = RightLeg(LAT, 1.0, 1)
    private val leftLeg = LeftLeg(CLOTH, 1.0, 1)
    private val knightLeftLeg = LeftLeg(LAT, 1.0, 1)
    private val boots = Boots(CLOTH, 1.0, 1)
    private val knightBoots = Boots(LAT, 1.0, 1)
    private val gloves = Gloves(CLOTH, 1.0, 1)
    private val knightGloves = Gloves(LAT, 1.0, 1)

    @Test
    @DisplayName("Equip right one-handed weapon when character has no equipped weapon/shield")
    fun test1() {
        character.equipRightHand(axe)

        assertTrue(character.equipmentOf(RIGHT_HAND) is Weapon)
        assertTrue((character.equipmentOf(RIGHT_HAND) as Weapon) is Axe)
    }

    @Test
    @DisplayName("Equip left one-handed weapon when character has no equipped weapon/shield")
    fun test2() {
        character.equipLeftHand(axe)

        assertTrue(character.equipmentOf(LEFT_HAND) is Weapon)
        assertTrue((character.equipmentOf(LEFT_HAND) as Weapon) is Axe)
    }

    @Test
    @DisplayName("Equip two-handed weapon on right hand, when character has no equipped weapon/shield")
    fun test3() {
        character.equipRightHand(spear)

        assertTrue(character.equipmentOf(RIGHT_HAND) is Weapon)
        assertTrue((character.equipmentOf(RIGHT_HAND) as Weapon) is Spear)
        assertTrue(character.equipmentOf(LEFT_HAND) == NONE)
    }

    @Test
    @DisplayName("Equip two-handed weapon on left hand, when character has no equipped weapon/shield")
    fun test4() {
        character.equipLeftHand(spear)

        assertTrue(character.equipmentOf(LEFT_HAND) is Weapon)
        assertTrue((character.equipmentOf(LEFT_HAND) as Weapon) is Spear)
        assertTrue(character.equipmentOf(RIGHT_HAND) == NONE)
    }

    @Test
    @DisplayName("Equip right one-handed weapon when character has weapon on right hand")
    fun test5() {
        character.equipRightHand(sword)

        assertTrue((character.equipmentOf(RIGHT_HAND) as Weapon) is Sword)

        character.equipRightHand(axe)

        assertTrue((character.equipmentOf(RIGHT_HAND) as Weapon) is Axe)
    }

    @Test
    @DisplayName("Equip left one-handed weapon when character has weapon on left hand")
    fun test6() {
        character.equipLeftHand(sword)

        assertTrue((character.equipmentOf(LEFT_HAND) as Weapon) is Sword)

        character.equipLeftHand(axe)

        assertTrue((character.equipmentOf(LEFT_HAND) as Weapon) is Axe)
    }

    @Test
    @DisplayName("Equip two-handed weapon on right hand, when character has two handed weapon")
    fun test7() {
        character.equipRightHand(spear)

        assertTrue((character.equipmentOf(RIGHT_HAND) as Weapon) is Spear)
        assertTrue(character.equipmentOf(LEFT_HAND) == NONE)

        character.equipRightHand(staff)

        assertTrue((character.equipmentOf(RIGHT_HAND) as Weapon) is Staff)
        assertTrue(character.equipmentOf(LEFT_HAND) == NONE)
    }

    @Test
    @DisplayName("Equip two-handed weapon on left hand, when character has two handed weapon")
    fun test8() {
        character.equipLeftHand(spear)

        assertTrue((character.equipmentOf(LEFT_HAND) as Weapon) is Spear)
        assertTrue(character.equipmentOf(RIGHT_HAND) == NONE)

        character.equipLeftHand(staff)

        assertTrue((character.equipmentOf(LEFT_HAND) as Weapon) is Staff)
        assertTrue(character.equipmentOf(RIGHT_HAND) == NONE)
    }

    @Test
    @DisplayName("Equip shield on right hand when character has no equipped weapon/shield")
    fun test9() {
        character.equipRightHand(buckler)

        assertTrue(character.equipmentOf(RIGHT_HAND) is Shield)
        assertTrue((character.equipmentOf(RIGHT_HAND) as Shield) is LightShield)
    }

    @Test
    @DisplayName("Equip shield on left hand when character has no equipped weapon/shield")
    fun test10() {
        character.equipLeftHand(buckler)

        assertTrue(character.equipmentOf(LEFT_HAND) is Shield)
        assertTrue((character.equipmentOf(LEFT_HAND) as Shield) is LightShield)
    }

    @Test
    @DisplayName("Equip shield on right hand when character has shield on right hand")
    fun test11() {
        character.equipRightHand(buckler)

        assertTrue((character.equipmentOf(RIGHT_HAND) as Shield) is LightShield)

        character.equipRightHand(torch)

        assertTrue((character.equipmentOf(RIGHT_HAND) as Shield) is HeavyShield)
    }

    @Test
    @DisplayName("Equip shield on left hand when character has shield on left hand")
    fun test12() {
        character.equipLeftHand(buckler)

        assertTrue((character.equipmentOf(LEFT_HAND) as Shield) is LightShield)

        character.equipLeftHand(torch)

        assertTrue((character.equipmentOf(LEFT_HAND) as Shield) is HeavyShield)
    }

    @Test
    @DisplayName("Equip helm when character has no armor")
    fun test13() {
        character.equip(helm)

        assertTrue(character.equipmentOf(HEAD) is Helm)
    }

    @Test
    @DisplayName("Equip helm when character has equipped helm")
    fun test14() {
        character.equip(helm)

        assertTrue((character.equipmentOf(HEAD) as Helm).material == CLOTH)

        character.equip(knightHelm)

        assertTrue((character.equipmentOf(HEAD) as Helm).material == LAT)
    }

    @Test
    @DisplayName("Equip right shoulder when character has no armor")
    fun test15() {
        character.equip(rightShoulder)

        assertTrue(character.equipmentOf(RIGHT_SHOULDER) is RightShoulder)
    }

    @Test
    @DisplayName("Equip right shoulder when character has equipped right shoulder")
    fun test16() {
        character.equip(rightShoulder)

        assertTrue((character.equipmentOf(RIGHT_SHOULDER) as RightShoulder).material == CLOTH)

        character.equip(knightRightShoulder)

        assertTrue((character.equipmentOf(RIGHT_SHOULDER) as RightShoulder).material == LAT)
    }

    @Test
    @DisplayName("Equip left shoulder when character has no armor")
    fun test17() {
        character.equip(leftShoulder)

        assertTrue(character.equipmentOf(LEFT_SHOULDER) is LeftShoulder)
    }

    @Test
    @DisplayName("Equip left shoulder when character has equipped left shoulder")
    fun test18() {
        character.equip(leftShoulder)

        assertTrue((character.equipmentOf(LEFT_SHOULDER) as LeftShoulder).material == CLOTH)

        character.equip(knightLeftShoulder)

        assertTrue((character.equipmentOf(LEFT_SHOULDER) as LeftShoulder).material == LAT)
    }

    @Test
    @DisplayName("Equip body when character has no armor")
    fun test19() {
        character.equip(body)

        assertTrue(character.equipmentOf(BODY) is Body)
    }

    @Test
    @DisplayName("Equip body when character has equipped body")
    fun test20() {
        character.equip(body)

        assertTrue((character.equipmentOf(BODY) as Body).material == CLOTH)

        character.equip(knightBody)

        assertTrue((character.equipmentOf(BODY) as Body).material == LAT)
    }

    @Test
    @DisplayName("Equip right leg when character has no armor")
    fun test21() {
        character.equip(rightLeg)

        assertTrue(character.equipmentOf(RIGHT_LEG) is RightLeg)
    }

    @Test
    @DisplayName("Equip right leg when character has equipped right leg")
    fun test22() {
        character.equip(rightLeg)

        assertTrue((character.equipmentOf(RIGHT_LEG) as RightLeg).material == CLOTH)

        character.equip(knightRightLeg)

        assertTrue((character.equipmentOf(RIGHT_LEG) as RightLeg).material == LAT)
    }

    @Test
    @DisplayName("Equip left leg when character has no armor")
    fun test23() {
        character.equip(leftLeg)

        assertTrue(character.equipmentOf(LEFT_LEG) is LeftLeg)
    }

    @Test
    @DisplayName("Equip left leg when character has equipped left leg")
    fun test24() {
        character.equip(leftLeg)

        assertTrue((character.equipmentOf(LEFT_LEG) as LeftLeg).material == CLOTH)

        character.equip(knightLeftLeg)

        assertTrue((character.equipmentOf(LEFT_LEG) as LeftLeg).material == LAT)
    }

    @Test
    @DisplayName("Equip boots when character has no armor")
    fun test25() {
        character.equip(boots)

        assertTrue(character.equipmentOf(FOOTS) is Boots)
    }

    @Test
    @DisplayName("Equip boots when character has equipped boots")
    fun test26() {
        character.equip(boots)

        assertTrue((character.equipmentOf(FOOTS) as Boots).material == CLOTH)

        character.equip(knightBoots)

        assertTrue((character.equipmentOf(FOOTS) as Boots).material == LAT)
    }

    @Test
    @DisplayName("Equip gloves when character has no armor")
    fun test27() {
        character.equip(gloves)

        assertTrue(character.equipmentOf(HANDS) is Gloves)
    }

    @Test
    @DisplayName("Equip gloves when character has equipped gloves")
    fun test28() {
        character.equip(gloves)

        assertTrue((character.equipmentOf(HANDS) as Gloves).material == CLOTH)

        character.equip(knightGloves)

        assertTrue((character.equipmentOf(HANDS) as Gloves).material == LAT)
    }

    @Test
    @DisplayName("Unequip right hand weapon when one-handed weapon equipped on right hand")
    fun test29() {
        character.equipRightHand(axe)
        character.unequip(RIGHT_HAND)

        assertTrue(character.equipmentOf(RIGHT_HAND) == NONE)
    }

    @Test
    @DisplayName("Unequip left hand weapon when one-handed weapon equipped on left hand")
    fun test30() {
        character.equipLeftHand(axe)
        character.unequip(LEFT_HAND)

        assertTrue(character.equipmentOf(LEFT_HAND) == NONE)
    }

    @Test
    @DisplayName("Unequip right hand weapon when two-handed weapon equipped on right hand")
    fun test31() {
        character.equipRightHand(spear)
        character.unequip(RIGHT_HAND)

        assertTrue(character.equipmentOf(RIGHT_HAND) == NONE)
        assertTrue(character.equipmentOf(LEFT_HAND) == NONE)
    }

    @Test
    @DisplayName("Unequip left hand weapon when two-handed weapon equipped on left hand")
    fun test32() {
        character.equipLeftHand(spear)
        character.unequip(LEFT_HAND)

        assertTrue(character.equipmentOf(RIGHT_HAND) == NONE)
        assertTrue(character.equipmentOf(LEFT_HAND) == NONE)
    }

    @Test
    @DisplayName("Unequip helm when helm is equipped")
    fun test33() {
        character.equip(helm)
        character.unequip(HEAD)

        assertTrue(character.equipmentOf(HEAD) == NONE)
    }

    @Test
    @DisplayName("Unequip body when body is equipped")
    fun test34() {
        character.equip(body)
        character.unequip(BODY)

        assertTrue(character.equipmentOf(BODY) == NONE)
    }

    @Test
    @DisplayName("Unequip right shoulder when right shoulder is equipped")
    fun test35() {
        character.equip(rightShoulder)
        character.unequip(RIGHT_SHOULDER)

        assertTrue(character.equipmentOf(RIGHT_SHOULDER) == NONE)
    }

    @Test
    @DisplayName("Unequip left shoulder when left shoulder is equipped")
    fun test36() {
        character.equip(leftShoulder)
        character.unequip(LEFT_SHOULDER)

        assertTrue(character.equipmentOf(LEFT_SHOULDER) == NONE)
    }

    @Test
    @DisplayName("Unequip gloves when gloves is equipped")
    fun test37() {
        character.equip(gloves)
        character.unequip(HANDS)

        assertTrue(character.equipmentOf(HANDS) == NONE)
    }

    @Test
    @DisplayName("Unequip right leg when right leg is equipped")
    fun test38() {
        character.equip(rightLeg)
        character.unequip(RIGHT_LEG)

        assertTrue(character.equipmentOf(RIGHT_LEG) == NONE)
    }

    @Test
    @DisplayName("Unequip left leg when left leg is equipped")
    fun test39() {
        character.equip(leftLeg)
        character.unequip(LEFT_LEG)

        assertTrue(character.equipmentOf(LEFT_LEG) == NONE)
    }

    @Test
    @DisplayName("Unequip boots when boots is equipped")
    fun test40() {
        character.equip(boots)
        character.unequip(FOOTS)

        assertTrue(character.equipmentOf(FOOTS) == NONE)
    }
}