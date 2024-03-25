package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTypeDescriptionTest {

    /**
     * Tests the DeviceTypeDescription constructor with a valid description.
     */
    @Test
    void shouldGetValidObject_whenUsingValidDescription() {
        // Arrange
        String validDescription = "Lampada";

        // Act
        new DeviceTypeDescription(validDescription);

    }

    /**
     * Tests the DeviceTypeDescription constructor with a null description.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDescriptionNull() {
        // Arrange
        String nullDescription = null;
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceTypeDescription(nullDescription));

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Tests the DeviceTypeDescription constructor with a blank description.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDescriptionBlank() {
        // Arrange
        String blankDescription = " ";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceTypeDescription(blankDescription));

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }


    /**
     * Test the constructor with a blank description.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDescriptionEmpty() {
        // Arrange
        String emptyDescription = "";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceTypeDescription(emptyDescription));

        // Assert
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Test the constructor with a description that contains special characters.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDescriptionContainsSpecialCharacters() {
        // Arrange
        String description = "Lampada!";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceTypeDescription(description));

        // Assert
        String expectedMessage = "The description can only contain letters and numbers.";
        String exceptionMessage = exception.getMessage();

        assertTrue(exceptionMessage.contains(expectedMessage));
    }

    /**
     * Gets the description of the device type
     */
    @Test
    void shouldGetDescription_whenUsingGetDescription() {
        // Arrange
        String description = "Lampada";
        DeviceTypeDescription deviceTypeDescription = new DeviceTypeDescription(description);

        // Act
        String result = deviceTypeDescription.getDescription();

        // Assert
        assertEquals(description, result);
    }

    /**
     * Tests the equals method with the same object.
     */
    @Test
    void shouldReturnTrue_whenComparingSameObject() {
        // Arrange
        String description = "Lampada";
        DeviceTypeDescription deviceTypeDescription = new DeviceTypeDescription(description);

        // Act
        boolean result = deviceTypeDescription.equals(deviceTypeDescription);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method with the same description.
     */
    @Test
    void shouldReturnTrue_whenComparingSameDescription() {
        // Arrange
        String description = "Lampada";
        DeviceTypeDescription deviceTypeDescription1 = new DeviceTypeDescription(description);
        DeviceTypeDescription deviceTypeDescription2 = new DeviceTypeDescription(description);

        // Act
        boolean result = deviceTypeDescription1.equals(deviceTypeDescription2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method with a different object.
     */
    @Test
    void shouldReturnFalse_whenComparingDifferentObject() {
        // Arrange
        String description = "Lampada";
        String description2 = "Lampada 2";
        DeviceTypeDescription deviceTypeDescription1 = new DeviceTypeDescription(description);
        DeviceTypeDescription deviceTypeDescription2 = new DeviceTypeDescription(description2);

        // Act
        boolean result = deviceTypeDescription1.equals(deviceTypeDescription2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests the toString method.
     */
    @Test
    void shouldReturnDescription_whenUsingToString() {
        // Arrange
        String description = "Lampada";
        DeviceTypeDescription deviceTypeDescription = new DeviceTypeDescription(description);

        // Act
        String result = deviceTypeDescription.toString();

        // Assert
        assertEquals(description, result);
    }




}