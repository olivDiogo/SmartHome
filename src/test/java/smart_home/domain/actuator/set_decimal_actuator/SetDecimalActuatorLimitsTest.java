package smart_home.domain.actuator.set_decimal_actuator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the SetDecimalActuatorLimits class.
 */
class SetDecimalActuatorLimitsTest {

    /**
     * Verifies that SetDecimalActuatorLimits is correctly instantiated when limits are valid.
     */
    @Test
    void shouldInstantiateSetDecimalActuatorLimits_whenLimitsAreValid() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        // Act
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Assert
        assertNotNull(setDecimalActuatorLimits);
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the lower limit is greater than the upper limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenLowerLimitIsGreaterThanUpperLimit() {
        // Arrange
        double lowerLimit = 9.5;
        double upperLimit = 1.5;

        String expectedMessage = "Lower limit cannot be greater than upper limit";

        // Act
        try {
            new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the upper limit is less than the lower limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenUpperLimitIsLessThanLowerLimit() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 1.4;

        String expectedMessage = "Lower limit cannot be greater than upper limit";

        // Act
        try {
            new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the lower limit is negative.
     */
    @Test
    void shouldReturnLowerLimit_whenGetLowerLimitIsCalled() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Act
        double result = setDecimalActuatorLimits.getLowerLimit();

        // Assert
        assertEquals(lowerLimit, result);
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the upper limit is negative.
     */
    @Test
    void shouldReturnUpperLimit_whenGetUpperLimitIsCalled() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Act
        double result = setDecimalActuatorLimits.getUpperLimit();

        // Assert
        assertEquals(upperLimit, result);
    }

    /**
     * Verifies that the equals method returns true when called with the same object.
     */
    @Test
    void shouldReturnTrue_whenEqualsIsCalledWithSameObject() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Act
        boolean result = setDecimalActuatorLimits.equals(setDecimalActuatorLimits);

        // Assert
        assertTrue(result);
    }

    /**
     * Verifies that the equals method returns false when called with a different object.
     */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithDifferentObject() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Act
        boolean result = setDecimalActuatorLimits.equals(new Object());

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that the equals method returns false when called with a different set of limits.
     */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithDifferentLimits() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        SetDecimalActuatorLimits setDecimalActuatorLimits2 = new SetDecimalActuatorLimits(1.4, 9.5);

        // Act
        boolean result = setDecimalActuatorLimits.equals(setDecimalActuatorLimits2);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that the equals method returns true when called with a different object having the same limits.
     */
    @Test
    void shouldReturnTrue_whenEqualsIsCalledWithDifferentObjectButSameLimits() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        SetDecimalActuatorLimits setDecimalActuatorLimits2 = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Act
        boolean result = setDecimalActuatorLimits.equals(setDecimalActuatorLimits2);

        // Assert
        assertTrue(result);
    }

    /**
     * Verifies that the equals method returns false when called with a different object having a different lower limit.
     */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentLowerLimit() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        SetDecimalActuatorLimits setDecimalActuatorLimits2 = new SetDecimalActuatorLimits(1.4, upperLimit);

        // Act
        boolean result = setDecimalActuatorLimits.equals(setDecimalActuatorLimits2);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that the equals method returns false when called with a different object having a different upper limit.
     */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentUpperLimit() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        SetDecimalActuatorLimits setDecimalActuatorLimits2 = new SetDecimalActuatorLimits(lowerLimit, 9.4);

        // Act
        boolean result = setDecimalActuatorLimits.equals(setDecimalActuatorLimits2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test if the toString method returns the expected string.
     */
    @Test void shouldReturnToString_whenToStringIsCalled() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        String expected = "Lower limit: " + lowerLimit + ", Upper limit: " + upperLimit;

        // Act
        String result = setDecimalActuatorLimits.toString();

        // Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        // Act
        int result = setDecimalActuatorLimits.hashCode();

        // Assert
        assertEquals(Double.hashCode(lowerLimit) + Double.hashCode(upperLimit), result);
    }
    @Test
    void shouldReturnTrue_whenEqualsIsTrueHashCodeShouldBeTheSame() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;
        SetDecimalActuatorLimits setDecimalActuatorLimits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        SetDecimalActuatorLimits setDecimalActuatorLimits2 = new SetDecimalActuatorLimits(lowerLimit, upperLimit);
        boolean expected = setDecimalActuatorLimits.equals(setDecimalActuatorLimits2);
        // Act
        boolean result = setDecimalActuatorLimits.hashCode() == setDecimalActuatorLimits2.hashCode();

        // Assert
        assertEquals(expected, result);
    }


}