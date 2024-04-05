package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorModelIDTest {

    @Test
    void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String actuatorModelIDDescription = "switch";

        // Act
        ActuatorModelID result = new ActuatorModelID(actuatorModelIDDescription);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowException_whenActuatorModelIDIsNull() {
        // Arrange
        String actuatorModelIDDescription = null;
        String expectedMessage = "The value of 'actuatorModelID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorModelID(actuatorModelIDDescription)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowException_whenActuatorModelIDIsBlank() {
        // Arrange
        String actuatorModelIDDescription = " ";
        String expectedMessage = "The value of 'actuatorModelID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorModelID(actuatorModelIDDescription)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowException_whenActuatorModelIDIsEmpty() {
        // Arrange
        String actuatorModelIDDescription = "";
        String expectedMessage = "The value of 'actuatorModelID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorModelID(actuatorModelIDDescription)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnActuatorTypeID() {
        // Arrange
        String actuatorModelIDDescription = "switch";
        ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
        // Act
        String result = actuatorModelID.getId();
        // Assert
        assertEquals(actuatorModelIDDescription, result);
    }

    @Test
    void shouldReturnTrue_WhenActuatorTypeIDIsEqualToItself() {
        // Arrange
        String actuatorModelIDDescription = "switch";
        ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
        // Act
        boolean result = actuatorModelID.equals(actuatorModelID);
        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalse_whenComparingTwoDifferentActuatorModelID() {
        // Arrange
        String actuatorModelIDDescription = "switch";
        ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
        ActuatorModelID actuatorModelID2 = new ActuatorModelID("light");
        // Act
        boolean result = actuatorModelID.equals(actuatorModelID2);
        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnHashCode() {
        // Arrange
        String actuatorModelIDDescription = "switch";
        ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
        // Act
        int result = actuatorModelID.hashCode();
        // Assert
        assertEquals(actuatorModelIDDescription.hashCode(), result);
    }

    @Test
    void shouldReturnFalse_whenComparingActuatorModelIDToNull() {
        // Arrange
        String actuatorModelIDDescription = "switch";
        ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
        // Act
        boolean result = actuatorModelID.equals(null);
        // Assert
        assertFalse(result);
    }
}