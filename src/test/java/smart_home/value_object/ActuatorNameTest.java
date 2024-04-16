package smart_home.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class ActuatorNameTest {

    /**
     * Test the instantiation of a new actuator name.
     */
    @Test
    void shouldInstantiateANewActuatorName() {
        //Arrange
        String actuatorName = "Switch Actuator";

        //Act
        ActuatorName result = new ActuatorName(actuatorName);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test the instantiation of a new actuator name with a null value.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameIsNull() {

        String actuatorName = null;
        String expectedMessage = "The actuator name cannot be null, blank, or empty.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Test the instantiation of a new actuator name with a blank value.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameIsBlank() {

        String actuatorName = " ";
        String expectedMessage = "The actuator name cannot be null, blank, or empty.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Test the instantiation of a new actuator name with an empty value.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameIsEmpty() {

        String actuatorName = "";
        String expectedMessage = "The actuator name cannot be null, blank, or empty.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Test the instantiation of a new actuator name with special characters.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorNameContainsSpecialCharacters() {

        String actuatorName = "Switch Actuator!";
        String expectedMessage = "The actuator name can only contain letters and numbers.";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorName(actuatorName)
        );

        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Test the instantiation of a new actuator name with a valid value.
     */
    @Test
    void shouldGetActuatorName() {

        String actuatorName = "Switch Actuator";
        ActuatorName actuatorNameVO = new ActuatorName(actuatorName);

        String result = actuatorNameVO.getActuatorName();

        assertTrue(result.equals(actuatorName));
    }

    /**
     * Test the equals method when comparing two equal actuator names.
     */
    @Test
    void shouldReturnTrue_whenComparingTwoEqualActuatorNames() {
        // Arrange
        String actuatorName = "Switch Actuator";
        ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
        ActuatorName actuatorNameVO2 = new ActuatorName(actuatorName);

        // Act
        boolean result = actuatorNameVO.equals(actuatorNameVO2);

        // Assert
        assertTrue(result);
    }

    /**
     * Test the equals method when comparing two different actuator names.
     */
    @Test
    void shouldReturnFalse_whenComparingTwoDifferentActuatorNames() {
        // Arrange
        String actuatorName = "Switch Actuator";
        String actuatorName2 = "Light Actuator";
        ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
        ActuatorName actuatorNameVO2 = new ActuatorName(actuatorName2);

        // Act
        boolean result = actuatorNameVO.equals(actuatorNameVO2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test the equals method when comparing an actuator name with a different object.
     */
    @Test
    void shouldReturnFalse_whenComparingActuatorNameWithDifferentObject() {
        // Arrange
        String actuatorName = "Switch Actuator";
        ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
        Object object = new Object();

        // Act
        boolean result = actuatorNameVO.equals(object);

        // Assert
        assertFalse(result);
    }

    /**
     * Test the hashCode method.
     */
    @Test
    void shouldReturnHashCode_whenCallingHashCode() {
        // Arrange
        String actuatorName = "Switch Actuator";
        ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
        int expectedHashCode = actuatorName.hashCode();

        // Act
        int result = actuatorNameVO.hashCode();

        // Assert
        assertEquals(expectedHashCode, result);
    }

}
