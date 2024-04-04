package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.SetDecimalActuator.SetDecimalActuatorLimits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetDecimalActuatorLimitsTest {

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

}