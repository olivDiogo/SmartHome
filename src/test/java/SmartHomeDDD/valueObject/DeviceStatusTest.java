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
        new DeviceStatus(validDeviceStatus);
    }

    /**
     * Tests the DeviceStatus constructor with a false device status.
     */
    @Test
    public void shouldGetValidObject_whenUsingDeviceStatusAsFalse(){
        // Arrange
        boolean validDeviceStatus = false;

        // Act
        new DeviceStatus(validDeviceStatus);
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
}