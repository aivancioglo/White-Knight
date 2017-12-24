import model.Character
import model.equipment.armor.*
import model.equipment.shield.HeavyShield
import model.equipment.shield.LightShield
import model.equipment.weapon.Axe
import model.equipment.weapon.Spear
import model.equipment.weapon.Staff
import model.equipment.weapon.Sword
import model.support.enums.ArmorMaterial
import model.support.enums.BodyPart.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EquipmentWeightTest {
    private val character = Character(who = "Warrior")

    // Weapons
    private val axe = Axe(1.0, 1)
    private val sword = Sword(1.0, 1)
    private val staff = Staff(1.0, 1)
    private val spear = Spear(1.0, 1)
    private val buckler = LightShield(1.0, 1)
    private val torch = HeavyShield(1.0, 1)

    // Armors
    private val helm = Helm(ArmorMaterial.CLOTH, 1.0, 1)
    private val body = Body(ArmorMaterial.CLOTH, 1.0, 1)
    private val rightShoulder = RightShoulder(ArmorMaterial.CLOTH, 1.0, 1)
    private val leftShoulder = LeftShoulder(ArmorMaterial.CLOTH, 1.0, 1)
    private val rightLeg = RightLeg(ArmorMaterial.CLOTH, 1.0, 1)
    private val leftLeg = LeftLeg(ArmorMaterial.CLOTH, 1.0, 1)
    private val boots = Boots(ArmorMaterial.CLOTH, 1.0, 1)
    private val gloves = Gloves(ArmorMaterial.CLOTH, 1.0, 1)

    @Test
    @DisplayName("Equip weapon to right hand when no other equipment equipped and check the equipment weight")
    fun test1() {
        character.equipRightHand(axe)

        assertEquals(axe.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip weapon to left hand when no other equipment equipped and check the equipment weight")
    fun test2() {
        character.equipLeftHand(axe)

        assertEquals(axe.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip shield to right hand when no other equipment equipped and check the equipment weight")
    fun test3() {
        character.equipRightHand(buckler)

        assertEquals(buckler.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip shield to left hand when no other equipment equipped and check the equipment weight")
    fun test4() {
        character.equipLeftHand(buckler)

        assertEquals(buckler.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip helm when no other equipment equipped and check the equipment weight")
    fun test5() {
        character.equip(helm)

        assertEquals(helm.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip right shoulder when no other equipment equipped and check the equipment weight")
    fun test6() {
        character.equip(rightShoulder)

        assertEquals(rightShoulder.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip left shoulder when no other equipment equipped and check the equipment weight")
    fun test7() {
        character.equip(leftShoulder)

        assertEquals(leftShoulder.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip gloves when no other equipment equipped and check the equipment weight")
    fun test8() {
        character.equip(gloves)

        assertEquals(gloves.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip body when no other equipment equipped and check the equipment weight")
    fun test9() {
        character.equip(body)

        assertEquals(body.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip right leg when no other equipment equipped and check the equipment weight")
    fun test10() {
        character.equip(rightLeg)

        assertEquals(rightLeg.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip left leg when no other equipment equipped and check the equipment weight")
    fun test11() {
        character.equip(leftLeg)

        assertEquals(leftLeg.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip boots when no other equipment equipped and check the equipment weight")
    fun test12() {
        character.equip(boots)

        assertEquals(boots.weight, character.equipmentWeight)
    }

    @Test
    @DisplayName("Equip weapon to right hand when weapon is already equipped to right hand and check the equipment weight")
    fun test13() {
        character.equipRightHand(axe)
        character.unequip(RIGHT_HAND)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip weapon to left hand when no other equipment equipped and check the equipment weight")
    fun test14() {
        character.equipLeftHand(axe)
        character.unequip(LEFT_HAND)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip shield to right hand when no other equipment equipped and check the equipment weight")
    fun test15() {
        character.equipRightHand(buckler)
        character.unequip(RIGHT_HAND)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip shield to left hand when no other equipment equipped and check the equipment weight")
    fun test16() {
        character.equipLeftHand(buckler)
        character.unequip(LEFT_HAND)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip helm when no other equipment equipped and check the equipment weight")
    fun test17() {
        character.equip(helm)
        character.unequip(HEAD)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip right shoulder when no other equipment equipped and check the equipment weight")
    fun test18() {
        character.equip(rightShoulder)
        character.unequip(RIGHT_SHOULDER)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip left shoulder when no other equipment equipped and check the equipment weight")
    fun test19() {
        character.equip(leftShoulder)
        character.unequip(LEFT_SHOULDER)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip gloves when no other equipment equipped and check the equipment weight")
    fun test20() {
        character.equip(gloves)
        character.unequip(HANDS)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip body when no other equipment equipped and check the equipment weight")
    fun test21() {
        character.equip(body)
        character.unequip(BODY)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip right leg when no other equipment equipped and check the equipment weight")
    fun test22() {
        character.equip(rightLeg)
        character.unequip(RIGHT_LEG)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip left leg when no other equipment equipped and check the equipment weight")
    fun test23() {
        character.equip(leftLeg)
        character.unequip(LEFT_LEG)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip boots when no other equipment equipped and check the equipment weight")
    fun test24() {
        character.equip(boots)
        character.unequip(FOOTS)

        assertTrue(character.equipmentWeight == 0.0)
    }

    @Test
    @DisplayName("Equip all when no other equipment equipped and check the equipment weight")
    fun test25() {
        character.equip(helm)
        character.equip(rightShoulder)
        character.equip(leftShoulder)
        character.equip(gloves)
        character.equip(body)
        character.equip(rightLeg)
        character.equip(leftLeg)
        character.equip(boots)

        assertTrue(character.equipmentWeight
                == helm.weight + rightShoulder.weight
                + leftShoulder.weight + gloves.weight
                + body.weight + rightLeg.weight
                + leftLeg.weight + boots.weight)
    }
}