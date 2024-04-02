package SmartHomeDDD.domain.Actuator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BlindRollerValueTest {

    /**
     * Test constructor
     */
    @Test
    public void shouldCreateBlindRollerValue() {
        // Act
        new BlindRollerValue(1);
    }

    /**
     * Throws exception if value is less than 0
     */
    @Test
    public void shouldThrowException_WhenValueIsLessThanZero() {
        // Arrange
        int value = -1;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new BlindRollerValue(value));
    }

    /**
     * Throws exception if value is greater than 100
     */
    @Test
    public void shouldThrowException_WhenValueIsGreaterThanHundred() {
        // Arrange
        int value = 101;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new BlindRollerValue(value));
    }

    /**
     * Test for method toString
     */
    @Test
    public void shouldReturnStringValue() {
        // Arrange
        int value = 1;
        BlindRollerValue blindRollerValue = new BlindRollerValue(value);

        String expected = "1";

        // Act
        String result = blindRollerValue.toString();

        // Assert
        assertEquals(expected, result);
    }




}
