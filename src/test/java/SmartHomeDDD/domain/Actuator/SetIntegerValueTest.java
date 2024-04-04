package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerValue;
import org.junit.jupiter.api.Test;

public class SetIntegerValueTest {
    /**
     * Test for SetIntegerValue instantiation
     */
    @Test
    public void shouldInstantiateSetIntegerValue() {
        // Act
        new SetIntegerValue(1);
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
        assert(result.equals(expected));
    }
}
