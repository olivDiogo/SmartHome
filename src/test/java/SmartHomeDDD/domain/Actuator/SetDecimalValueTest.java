package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetDecimalActuator.SetDecimalValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetDecimalValueTest {

    @Test
    void shouldInstantiateSetDecimalValue() {
        // Act
        SetDecimalValue setDecimalValue = new SetDecimalValue(1.5);

        // Assert
        assertNotNull(setDecimalValue);
    }

    @Test
    void shouldReturnStringValue() {
        // Arrange
        double value = 1.5;
        SetDecimalValue setDecimalValue = new SetDecimalValue(value);

        String expected = "1.5";

        // Act
        String result = setDecimalValue.toString();

        // Assert
        assertEquals(expected, result);
    }

}