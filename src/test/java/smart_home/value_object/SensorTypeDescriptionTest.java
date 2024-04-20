package smart_home.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeDescriptionTest {
    @Test
    void shouldInstantiateSensorTypeDescriptionWhenDescriptionIsValid() {
        //Arrange
        String description = "Temperature Sensor";
        //Act
        SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
        //Assert
        assertNotNull(sensorTypeDescription);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDescriptionIsNull() {
        //Arrange
        String description = null;
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenDescriptionIsBlank() {
        //Arrange
        String description = " ";
        String expectedMessage = "The value of 'description' should not null, blank, or empty.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenDescriptionIsMoreThan50Characters() {
        //Arrange
        String description = "Temperature Sensor Temperature Sensor Temperature Sensor Temperature Sensor";
        String expectedMessage = "The description cannot have more than 50 characters.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorTypeDescription(description));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnDescriptionWhenGetIDIsCalled() {
        //Arrange
        String description = "Temperature Sensor";
        SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
        //Act
        String result = sensorTypeDescription.getID();
        //Assert
        assertEquals(description, result);
    }
    @Test
    void shouldReturnTrueWhenTwoSensorTypeDescriptionAreEqual() {
        //Arrange
        String description = "Temperature Sensor";
        SensorTypeDescription sensorTypeDescription1 = new SensorTypeDescription(description);
        SensorTypeDescription sensorTypeDescription2 = new SensorTypeDescription(description);
        //Act
        boolean result = sensorTypeDescription1.equals(sensorTypeDescription2);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenTwoSensorTypeDescriptionAreNotEqual() {
        //Arrange
        String description1 = "Temperature Sensor";
        String description2 = "Humidity Sensor";
        SensorTypeDescription sensorTypeDescription1 = new SensorTypeDescription(description1);
        SensorTypeDescription sensorTypeDescription2 = new SensorTypeDescription(description2);
        //Act
        boolean result = sensorTypeDescription1.equals(sensorTypeDescription2);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectIsNotSensorTypeDescription() {
        //Arrange
        String description = "Temperature Sensor";
        SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
        //Act
        boolean result = sensorTypeDescription.equals(new Object());
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnHashCodeWhenHashCodeIsCalled() {
        //Arrange
        String description = "Temperature Sensor";
        SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
        //Act
        int result = sensorTypeDescription.hashCode();
        //Assert
        assertEquals(description.hashCode(), result);
    }
}