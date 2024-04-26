package smarthome.domain.value_object;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorModelName;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorModelNameTest {

    /**
     * Test the constructor of the class ActuatorModelName.
     */
    @Test
    void shouldInstantiateActuatorModelName_whenGivenValidParameters() {
        // Arrange
        ActuatorModelName ActuatorModelName;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName = new ActuatorModelName(name);

        // Assert
        assertNotNull(ActuatorModelName);
    }

    /**
     * Test the constructor of the class ActuatorModelName with a null name.
     */
    @Test
    void shouldThrowException_whenNameIsNull() {
        // Arrange
        String name = null;

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelName(name));
        assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test the constructor of the class ActuatorModelName with a name that has more than 5 characters.
     */
    @Test
    void shouldThrowException_whenNameContainsSpecialCharacters() {
        // Arrange
        String name = "ActuatorModelName!";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelName(name));
        assertEquals("The device name can only contain letters and numbers.", exception.getMessage());
    }

    /**
     * Test if the constructor throws IllegalArgumentException when name is blank
     */
    @Test
    void shouldThrowException_whenNameIsEmpty() {
        // Arrange
        String name = "";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelName(name));
        assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test the getActuatorModelName method of the class ActuatorModel
     */
    @Test
    void shouldReturnActuatorModelName_whenGetActuatorModelNameIsCalled() {
        // Arrange
        ActuatorModelName ActuatorModelName;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName = new ActuatorModelName(name);

        // Assert
        assertEquals(name, ActuatorModelName.getActuatorModelName());
    }

    /**
     * Tests if equals method returns true with same object
     */
    @Test
    void shouldReturnTrue_whenComparingActuatorModelNameWithItself() {
        // Arrange
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName actuatorModelName = new ActuatorModelName(name);

        // Assert
        assertTrue(actuatorModelName.equals(actuatorModelName));
    }

    /**
     * Test the equals method of the class ActuatorModelName.
     */
    @Test
    void shouldReturnTrue_whenComparingTwoEqualActuatorModelName() {
        // Arrange
        ActuatorModelName actuatorModelName1;
        ActuatorModelName actuatorModelName2;
        String name = "ActuatorModelName";

        // Act
        actuatorModelName1 = new ActuatorModelName(name);
        actuatorModelName2 = new ActuatorModelName(name);

        // Assert
        assertTrue(actuatorModelName1.equals(actuatorModelName2));
    }

    /**
     * Test the equals method of the class ActuatorModelName with two different Actuator model names.
     */
    @Test
    void shouldReturnFalse_whenComparingTwoDifferentActuatorModelName() {
        // Arrange
        ActuatorModelName ActuatorModelName1;
        ActuatorModelName ActuatorModelName2;
        String name1 = "ActuatorModelName1";
        String name2 = "ActuatorModelName2";

        // Act
        ActuatorModelName1 = new ActuatorModelName(name1);
        ActuatorModelName2 = new ActuatorModelName(name2);

        // Assert
        assertFalse(ActuatorModelName1.equals(ActuatorModelName2));
    }

    /**
     * Test the equals method of the class ActuatorModelName with the same Actuator model name.
     */
    @Test
    void shouldReturnFalse_whenComparingActuatorModelNameWithDifferentObject() {
        // Arrange
        ActuatorModelName ActuatorModelName;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName = new ActuatorModelName(name);

        // Assert
        assertFalse(ActuatorModelName.equals(name));
    }

    /**
     * Test the hashCode method of the class ActuatorModelName.
     */
    @Test
    void shouldReturnHashCode_whenCallingHashCode() {
        // Arrange
        String name = "ActuatorModelName";
        ActuatorModelName actuatorModelName = new ActuatorModelName(name);

        int expectedHashCode = name.hashCode();

        // Act
        int result = actuatorModelName.hashCode();

        // Assert
        assertEquals(expectedHashCode, result);
    }
}