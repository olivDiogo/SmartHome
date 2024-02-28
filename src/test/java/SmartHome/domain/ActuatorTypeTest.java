package SmartHome.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

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
        new ActuatorType(strDescription);

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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(strDescription));

        // Assert
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(strDescription));

        // Assert
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getDescription() {
        // Arrange
        String strDescription = "Temperature";
        ActuatorType actuatorType = new ActuatorType(strDescription);

        // Act
        String result = actuatorType.getDescription();

        // Assert
        Assertions.assertEquals(result, strDescription);
    }
}
