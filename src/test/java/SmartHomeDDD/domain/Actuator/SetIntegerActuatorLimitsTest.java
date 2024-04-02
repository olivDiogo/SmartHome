package SmartHomeDDD.domain.Actuator;

import org.junit.jupiter.api.Test;

public class SetIntegerActuatorLimitsTest {
    /**
     * Tests if the object is instantiated when the limits are valid
     */
    @Test
    public void shouldInstantiateSetIntegerActuatorLimits_whenLimitsAreValid() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit = 100;

        // Act
        new SetIntegerActuatorLimits(lowerLimit, upperLimit);
    }

    /**
     * Tests if the object is instantiated when the limits are invalid
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenLimitsAreInvalid() {
        // Arrange
        int lowerLimit = 100;
        int upperLimit = 0;

        // Act
        try {
            new SetIntegerActuatorLimits(lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("Lower limit cannot be greater than upper limit");
        }
    }

    /**
     * Tests getLowerLimit
     */
    @Test
    public void shouldReturnLowerLimit_whenGetLowerLimitIsCalled() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit = 100;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        // Act
        int result = setIntegerActuatorLimits.getLowerLimit();

        // Assert
        assert result == lowerLimit;
    }

    /**
     * Tests getUpperLimit
     */
    @Test
    public void shouldReturnUpperLimit_whenGetUpperLimitIsCalled() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit = 100;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        // Act
        int result = setIntegerActuatorLimits.getUpperLimit();

        // Assert
        assert result == upperLimit;
    }

    /**
     * Tests equals when the object is the same
     */
    @Test
    public void shouldReturnTrue_whenEqualsIsCalledWithSameObject() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit = 100;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        // Act
        boolean result = setIntegerActuatorLimits.equals(setIntegerActuatorLimits);

        // Assert
        assert result;
    }

    /**
     * Tests equals when the object is different
     */
    @Test
    public void shouldReturnFalse_whenEqualsIsCalledWithDifferentObject() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit = 100;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        // Act
        boolean result = setIntegerActuatorLimits.equals(new Object());

        // Assert
        assert !result;
    }

    /**
     * Tests equals when the object is different but the limits are the same
     */
    @Test
    public void shouldReturnTrue_whenEqualsIsCalledWithDifferentObjectButSameLimits() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit = 100;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);
        SetIntegerActuatorLimits setIntegerActuatorLimits2 = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        // Act
        boolean result = setIntegerActuatorLimits.equals(setIntegerActuatorLimits2);

        // Assert
        assert result;
    }

    /**
     * Tests equals when the object is different and the lower limit is different
     */
    @Test
    public void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentLowerLimit() {
        // Arrange
        int lowerLimit1 = 0;
        int lowerLimit2 = 1;
        int upperLimit = 100;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit1, upperLimit);
        SetIntegerActuatorLimits setIntegerActuatorLimits2 = new SetIntegerActuatorLimits(lowerLimit2, upperLimit);

        // Act
        boolean result = setIntegerActuatorLimits.equals(setIntegerActuatorLimits2);

        // Assert
        assert !result;
    }

    /**
     * Tests equals when the object is different and the upper limit is different
     */
    @Test
    public void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentUpperLimit() {
        // Arrange
        int lowerLimit = 0;
        int upperLimit1 = 100;
        int upperLimit2 = 101;
        SetIntegerActuatorLimits setIntegerActuatorLimits = new SetIntegerActuatorLimits(lowerLimit, upperLimit1);
        SetIntegerActuatorLimits setIntegerActuatorLimits2 = new SetIntegerActuatorLimits(lowerLimit, upperLimit2);

        // Act
        boolean result = setIntegerActuatorLimits.equals(setIntegerActuatorLimits2);

        // Assert
        assert !result;
    }
}
