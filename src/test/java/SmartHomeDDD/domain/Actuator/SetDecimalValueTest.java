package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetDecimalActuator.SetDecimalValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test cases for the SetDecimalValue class.
 */
class SetDecimalValueTest {

    /**
     * Verifies that SetDecimalValue is correctly instantiated.
     */
    @Test
    void shouldInstantiateSetDecimalValue() {
        // Act
        SetDecimalValue setDecimalValue = new SetDecimalValue(1.5);

        // Assert
        assertNotNull(setDecimalValue);
    }

    /**
     * Verifies that the toString method returns the expected string representation of the decimal value.
     */
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


    /**
     * Verifies that the getValue method returns the expected decimal value.
     */
    @Test
    void shouldGetValue() {
        // Arrange
        double value = 1.5;
        SetDecimalValue setDecimalValue = new SetDecimalValue(value);

        // Act
        double result = setDecimalValue.getValue();

        // Assert
        assertEquals(value, result);
    }

}