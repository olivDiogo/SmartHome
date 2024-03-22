package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorModelNameTest {

    /**
     * Test the constructor of the class ActuatorModelName.
     */
    @Test
    public void shouldInstantiateActuatorModelName_whenGivenValidParameters() {
        // Arrange
        ActuatorModelName ActuatorModelName;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName = new ActuatorModelName(name);

        // Assert
        assertNotNull(ActuatorModelName);
    }

    /**
     * Test the constructor of the class ActuatorModelName with an empty name.
     */
    @Test
    public void shouldThrowException_whenNameIsEmpty() {
        // Arrange
        String name = " ";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelName(name));
        assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test the constructor of the class ActuatorModelName with a null name.
     */
    @Test
    public void shouldThrowException_whenNameIsNull() {
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
    public void shouldThrowException_whenNameContainsSpecialCharacters() {
        // Arrange
        String name = "ActuatorModelName!";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelName(name));
        assertEquals("The device name can only contain letters and numbers.", exception.getMessage());
    }

    /**
     * Test the getActuatorModelName method of the class ActuatorModel
     */
    @Test
    public void shouldReturnActuatorModelName_whenGetActuatorModelNameIsCalled() {
        // Arrange
        ActuatorModelName ActuatorModelName;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName = new ActuatorModelName(name);

        // Assert
        assertEquals(name, ActuatorModelName.getActuatorModelName());
    }

    /**
     * Test the equals method of the class ActuatorModelName.
     */
    @Test
    public void shouldReturnTrue_whenComparingTwoEqualActuatorModelName() {
        // Arrange
        ActuatorModelName ActuatorModelName1;
        ActuatorModelName ActuatorModelName2;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName1 = new ActuatorModelName(name);
        ActuatorModelName2 = new ActuatorModelName(name);

        // Assert
        assertTrue(ActuatorModelName1.equals(ActuatorModelName2));
    }

    /**
     * Test the equals method of the class ActuatorModelName with two different Actuator model names.
     */
    @Test
    public void shouldReturnFalse_whenComparingTwoDifferentActuatorModelName() {
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
    public void shouldReturnFalse_whenComparingActuatorModelNameWithDifferentObject() {
        // Arrange
        ActuatorModelName ActuatorModelName;
        String name = "ActuatorModelName";

        // Act
        ActuatorModelName = new ActuatorModelName(name);

        // Assert
        assertFalse(ActuatorModelName.equals(name));
    }
}