package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ActuatorTypeTest {

    /**
     * Tests the instantiation of the ActuatorType
     * when the description is valid.
     */
    @Test
    void whenDescriptionIsValid_thenInstantiateActuatorType() {
        // Arrange
        String strDescription = "Temperature";

        // Act
        new ActuatorType(strDescription, Unit.Temperature);

        // Assert
    }

    /**
     * Tests the instantiation of the ActuatorType
     * when the description is null.
     */
    @Test
    void whenDescriptionIsNull_thenThrowsException() {
        // Arrange
        String strDescription = null;

        String expectedMessage = "Please enter a valid description for the actuator type.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(strDescription, Unit.Temperature));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of the ActuatorType
     * when the description is empty.
     */
    @Test
    void whenDescriptionIsEmpty_thenThrowsException() {
        // Arrange
        String strDescription = "";

        String expectedMessage = "Please enter a valid description for the actuator type.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(strDescription, Unit.Temperature));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of the ActuatorType
     * when the description is null.
     */
    @Test
    void whenUnitIsNull_thenThrowsException() {
        // Arrange
        String strDescription = "Temperature";

        String expectedMessage = "Please enter a valid unit for the actuator type.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(strDescription, null));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
