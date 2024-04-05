package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceStatusTest {

    /**
     * Tests the DeviceStatus constructor with a true device status.
     */
    @Test
    public void shouldGetValidObject_whenUsingDeviceStatusAsTrue(){
        // Arrange
        boolean validDeviceStatus = true;

        // Act
        DeviceStatus deviceStatus = new DeviceStatus(validDeviceStatus);

        // Assert
        assertNotNull(deviceStatus);
    }

    /**
     * Tests the DeviceStatus constructor with a false device status.
     */
    @Test
    public void shouldGetValidObject_whenUsingDeviceStatusAsFalse(){
        // Arrange
        boolean validDeviceStatus = false;

        // Act
        DeviceStatus result = new DeviceStatus(validDeviceStatus);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the equals method with the same entry
     */
    @Test
    public void shouldReturnTrue_WhenStatusIsTheSameAsTrue(){
        // Arrange
        boolean status = true;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        // Act
        boolean result = deviceStatus.equals(deviceStatus);
        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method with a different entry
     */
    @Test
    public void shouldReturnFalse_WhenStatusIsDifferent(){
        // Arrange
        boolean status = true;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        DeviceStatus deviceStatus2 = new DeviceStatus(false);
        // Act
        boolean result = deviceStatus.equals(deviceStatus2);
        // Assert
        assertFalse(result);
    }

    /**
     * Tests toString method when status is true
     */
    @Test
    public void shouldReturnON_whenStatusIsTrue(){
        // Arrange
        boolean status = true;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        // Act
        String result = deviceStatus.toString();
        // Assert
        assertEquals("ON", result);
    }

    /**
     * Tests toString method when status is false
     */
    @Test
    public void shouldReturnOFF_whenStatusIsFalse(){
        // Arrange
        boolean status = false;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        // Act
        String result = deviceStatus.toString();
        // Assert
        assertEquals("OFF", result);
    }

    /**
     * Tests the hashCode method
     */
    @Test
    public void shouldReturnHashCode_whenUsingHashCodeMethod(){
        // Arrange
        boolean status = true;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        // Act
        int result = deviceStatus.hashCode();
        // Assert
        assertEquals(Boolean.hashCode(status), result);
    }

    /**
     * Tests the getter method when status is true
     */
    @Test
    public void shouldReturnTrue_whenUsingGetterMethod(){
        // Arrange
        boolean status = true;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        // Act
        boolean result = deviceStatus.getStatus();
        // Assert
        assertEquals(status, result);
    }

    /**
     * Tests the getter method when status is false
     */
    @Test
    public void shouldReturnFalse_whenUsingGetterMethod(){
        // Arrange
        boolean status = false;
        DeviceStatus deviceStatus = new DeviceStatus(status);
        // Act
        boolean result = deviceStatus.getStatus();
        // Assert
        assertEquals(status, result);
    }

}