import model.unit.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Stamina {
    private static Unit unit;

    @Test
    @DisplayName("Check max stamina calculating.")
    public void maxStamina() {
        unit = new Unit("Unit");

        assertEquals(30, unit.maxStamina());

        unit.addToAgility(9);

        assertEquals(120, unit.maxStamina());
    }
}
