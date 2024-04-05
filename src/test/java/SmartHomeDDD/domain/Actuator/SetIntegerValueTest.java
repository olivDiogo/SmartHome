package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerValue;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SetIntegerValueTest {
    /**
     * Test for SetIntegerValue instantiation
     */
    @Test
    public void shouldInstantiateSetIntegerValue() {
        // Arrange
        int value = 1;

        // Act
        SetIntegerValue result = new SetIntegerValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test for method toString
     */
    @Test
    public void shouldReturnStringValue() {
        // Arrange
        int value = 1;
        SetIntegerValue setIntegerValue = new SetIntegerValue(value);

        String expected = "1";

        // Act
        String result = setIntegerValue.toString();

        // Assert
        assertEquals(result,expected);
    }
}
