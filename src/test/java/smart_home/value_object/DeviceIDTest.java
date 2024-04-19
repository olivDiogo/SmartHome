package smart_home.value_object;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceIDTest {

    /**
     * Tests the correct instantiation of a DeviceID
     */
    @Test
    public void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String deviceID = "DeviceXPTO";

        // Act
        DeviceID deviceID1 = new DeviceID(deviceID);

        // Assert
        assertNotNull(deviceID1);
    }

    /**
     * Tests if the exception is thrown with a null deviceID
     */
    @Test
    public void shouldThrowException_whenDeviceIdIsNull(){
        // Arrange
        String deviceID = null;
        String expectedMessage = "The value of 'deviceID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DeviceID(deviceID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with a blank deviceID
     */
    @Test
    public void shouldThrowException_whenDeviceIdIsBlank(){
        // Arrange
        String deviceID = " ";
        String expectedMessage = "The value of 'deviceID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DeviceID(deviceID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with an empty deviceID
     */
    @Test
    public void shouldThrowException_whenDeviceIdIsEmpty(){
        // Arrange
        String deviceID = "";
        String expectedMessage = "The value of 'deviceID' should not null, blank, or empty.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DeviceID(deviceID)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the ID getter
     */
    @Test
    public void shouldGetDeviceID(){
        // Arrange
        String idDescription = "HXPTO";
        DeviceID deviceID = new DeviceID(idDescription);

        String expected = "HXPTO";

        // Act
        String result = deviceID.getID();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if a deviceID is equal to itself
     */
    @Test
    public void shouldReturnTrue_whenDeviceIdIsEqualToItself(){
        // Arrange
        String idDescription = "HXPTO";
        DeviceID deviceID = new DeviceID(idDescription);

        // Act
        boolean result = deviceID.equals(deviceID);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if a deviceID1 is equal to a deviceID2 if the ID of both is the same
     */
    @Test
    public void shouldReturnTrue_whenDeviceIdIsEqualToOtherDeviceId(){
        // Arrange
        String idDescription = "HXPTO";
        DeviceID deviceID1 = new DeviceID(idDescription);
        DeviceID deviceID2 = new DeviceID(idDescription);

        // Act
        boolean result = deviceID1.equals(deviceID2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if a deviceID1 is not equal to a deviceID2
     */
    @Test
    public void shouldReturnTrue_whenDeviceIdIsNotEqualToAnotherDeviceId(){
        // Arrange
        String idDescription1 = "HXPTO";
        DeviceID deviceID1 = new DeviceID(idDescription1);

        String idDescription2 = "HRTHD";
        DeviceID deviceID2 = new DeviceID(idDescription2);

        // Act
        boolean result = deviceID1.equals(deviceID2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the deviceID is returned as an hashCode
     */
    @Test
    public void shouldReturnHashCode(){
        // Arrange
        String idDescription = "HXPTO";
        DeviceID deviceID = new DeviceID(idDescription);

        int expected = idDescription.hashCode();

        // Act
        int result = deviceID.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if the deviceID is returned as a string
     */
    @Test
    public void shouldReturnDeviceIDAsString(){
        // Arrange
        String idDescription = "HXPTO";
        DeviceID deviceID = new DeviceID(idDescription);

        String expected = idDescription;

        // Act
        String result = deviceID.toString();

        // Assert
        assertEquals(expected, result);
    }

}
