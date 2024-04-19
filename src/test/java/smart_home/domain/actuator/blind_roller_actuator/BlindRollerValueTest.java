package smart_home.domain.actuator.blind_roller_actuator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class BlindRollerValueTest {

    /**
     * Test constructor
     */
    @Test
    public void shouldCreateBlindRollerValue() {
        // Arrange
        int value = 100;

        // Act
        BlindRollerValue blindRollerValue =  new BlindRollerValue(value);

        // Assert
        assertNotNull(blindRollerValue);
    }

    /**
     * Test constructor
     */
    @Test
    public void shouldCreateBlindRollerWithValueZero() {
        // Arrange
        int value = 0;

        // Act
        BlindRollerValue blindRollerValue =  new BlindRollerValue(value);

        // Assert
        assertNotNull(blindRollerValue);
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
