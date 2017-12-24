import model.Character
import model.equipment.armor.Body
import model.equipment.armor.Helm
import model.support.Constants.HUNDRED
import model.support.enums.ArmorMaterial
import model.support.enums.Specification
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TotalDefenseTest {
    private val character = Character(who = "Warrior")

    // Armors
    private val clothHelm = Helm(ArmorMaterial.CLOTH, 1.0, HUNDRED)
    private val leatherHelm = Helm(ArmorMaterial.LEATHER, 1.0, HUNDRED)
    private val chainHelm = Helm(ArmorMaterial.CHAIN, 1.0, HUNDRED)
    private val scalyHelm = Helm(ArmorMaterial.SCALY, 1.0, HUNDRED)
    private val latHelm = Helm(ArmorMaterial.LAT, 1.0, HUNDRED)
    private val body = Body(ArmorMaterial.CLOTH, 1.0, HUNDRED)

    @Test
    @DisplayName("Equip cloth armor when character has no equipment and check total defense")
    fun test1() {
        character.equip(clothHelm)

        Specification.values().forEach { Assertions.assertEquals(clothHelm.defense[it], character.totalDefense(it)) }
    }

    @Test
    @DisplayName("Equip leather armor when character has no equipment and check total defense")
    fun test2() {
        character.equip(leatherHelm)

        Specification.values().forEach { Assertions.assertEquals(leatherHelm.defense[it], character.totalDefense(it)) }
    }

    @Test
    @DisplayName("Equip chain armor when character has no equipment and check total defense")
    fun test3() {
        character.equip(chainHelm)

        Specification.values().forEach { Assertions.assertEquals(chainHelm.defense[it], character.totalDefense(it)) }
    }

    @Test
    @DisplayName("Equip scaly armor when character has no equipment and check total defense")
    fun test4() {
        character.equip(scalyHelm)

        Specification.values().forEach { Assertions.assertEquals(scalyHelm.defense[it], character.totalDefense(it)) }
    }

    @Test
    @DisplayName("Equip lat armor when character has no equipment and check total defense")
    fun test5() {
        character.equip(latHelm)

        Specification.values().forEach { Assertions.assertEquals(latHelm.defense[it], character.totalDefense(it)) }
    }

    @Test
    @DisplayName("Equip a few armors when character has no equipment and check total defense")
    fun test6() {
        character.equip(latHelm)
        character.equip(body)

        Specification.values().forEach { Assertions.assertEquals(latHelm.defense[it]!!.plus(body.defense[it]!!), character.totalDefense(it)) }
    }
}