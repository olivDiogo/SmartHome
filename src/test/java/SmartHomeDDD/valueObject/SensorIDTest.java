package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SensorIDTest {
    /**
     * Tests the correct instantiation of a SensorID
     */
    @Test
    public void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String sensorID = "SensorXPTO";

        // Act
        DeviceID result = new DeviceID(sensorID);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests if the exception is thrown with a null sensorID
     */
    @Test
    public void shouldThrowException_whenSensorIdIsNull(){
        // Arrange
        String sensorID = null;
        String expectedMessage = "The value of 'sensorID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorID(sensorID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with a blank sensorID
     */
    @Test
    public void shouldThrowException_whenSensorIdIsBlank(){
        // Arrange
        String sensorID = " ";
        String expectedMessage = "The value of 'sensorID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorID(sensorID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with an empty sensorID
     */
    @Test
    public void shouldThrowException_whenSensorIdIsEmpty(){
        // Arrange
        String sensorID = "";
        String expectedMessage = "The value of 'sensorID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorID(sensorID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the ID getter
     */
    @Test
    public void shouldGetSensorID(){
        // Arrange
        String idDescription = "HXPTO";
        SensorID sensorID = new SensorID(idDescription);

        String expected = "HXPTO";

        // Act
        String result = sensorID.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if a sensorID is equal to itself
     */
    @Test
    public void shouldReturnTrue_whenSensorIdIsEqualToItself(){
        // Arrange
        String idDescription = "HXPTO";
        SensorID sensorID = new SensorID(idDescription);

        // Act
        boolean result = sensorID.equals(sensorID);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if a sensorID1 is equal to a sensorID2 if the ID of both is the same
     */
    @Test
    public void shouldReturnTrue_whenSensorIdIsEqualToOtherSensorId(){
        // Arrange
        String idDescription = "HXPTO";
        SensorID sensorID1 = new SensorID(idDescription);
        SensorID sensorID2 = new SensorID(idDescription);

        // Act
        boolean result = sensorID1.equals(sensorID2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if a sensorID1 is not equal to a sensorID2
     */
    @Test
    public void shouldReturnTrue_whenSensorIdIsNotEqualToAnotherSensorId(){
        // Arrange
        String idDescription1 = "HXPTO";
        SensorID sensorID1 = new SensorID(idDescription1);

        String idDescription2 = "HRTHD";
        SensorID sensorID2 = new SensorID(idDescription2);

        // Act
        boolean result = sensorID1.equals(sensorID2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the sensorID is returned as an hashCode
     */
    @Test
    public void shouldReturnHashCode(){
        // Arrange
        String idDescription = "HXPTO";
        SensorID sensorID = new SensorID(idDescription);

        int expected = idDescription.hashCode();

        // Act
        int result = sensorID.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if the sensorID is returned as a string
     */
    @Test
    public void shouldReturnSensorID(){
        // Arrange
        String idDescription = "HXPTO";
        SensorID sensorID = new SensorID(idDescription);

        String expected = idDescription;

        // Act
        String result = sensorID.toString();

        // Assert
        assertEquals(expected, result);
    }
}
