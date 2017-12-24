import model.Character
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StrengthTest {

    @Test
    fun test1() {
        val character = Character(who = "Warrior")

        Assertions.assertEquals(1, character.strength())
    }
}