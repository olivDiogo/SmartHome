package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceTest {

    @Test
    void seeIfConstructorWorks() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act & Assert
        assertNotNull(device);
    }

    @Test
    void constructorThrowsIllegalArgumentException() {
        // Arrange
        String expected = "Please enter a valid name for the device type.";
        String name = " ";
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Device(name));
        // Assert
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void deactivateDeviceSuccessfully() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        boolean actualResult = device.deactivateDevice();
        // Assert
        assertTrue(actualResult);
    }

    @Test
    void getNameShouldReturnName() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        String actualResult = device.getName();
        // Assert
        assertEquals(deviceName, actualResult);
    }

    @Test
    void getStatusShouldReturnStatus() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        boolean actualResult = device.getStatus();
        // Assert
        assertFalse(actualResult);
    }

    @Test
    void getDeviceIdShouldReturnDeviceId() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        UUID deviceID = device.getDeviceId();
        // Assert
        assertNotNull(deviceID);
    }

    @Test
    void getDeviceFunctionalitiesShouldReturnList() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        Sensor sensorDouble = mock(Sensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(sensorDouble.getSensorType()).thenReturn(sensorTypeDouble);
        when(sensorTypeDouble.getDescription()).thenReturn("Temperature");

        device.addSensorToDevice(sensorDouble);

        // Act
        List<String> functionalities = device.getDeviceFunctionalities();

        // Assert
        assertEquals(1, functionalities.size());
    }

    /**
     * Test if the method getActuatorList returns a list with success.
     */
    @Test
    void getActuatorListShouldReturnList() {
        //Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);

        int expected = 0;

        //Act
        int result = device.getActuatorList().size();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void successfullyAddSensor() throws InstantiationException {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        Sensor sensorDouble = mock(Sensor.class);
        String sensorModel = "model";
        SensorFactory sensorFactory = mock(SensorFactory.class);

        when(catalogueDouble.getSensor(sensorModel, sensorFactory)).thenReturn(sensorDouble);

        // Act
        Sensor actualResult = device.addSensor(sensorModel, catalogueDouble, sensorFactory);

        // Assert
        assertEquals(sensorDouble, actualResult);
    }

    @Test
    void addSensorReturnsNull() throws InstantiationException {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        String sensorModel = "model";

        when(catalogueDouble.getSensor(sensorModel, new SensorFactory())).thenReturn(null);

        // Act
        Sensor actualResult = device.addSensor(sensorModel, catalogueDouble, new SensorFactory());

        // Assert
        assertNull(actualResult);
    }

    @Test
    void successfullyAddSensorToDeviceList() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        Sensor sensorDouble = mock(Sensor.class);
        String expected = "_sensors=[" + sensorDouble + "]";

        // Act
        device.addSensorToDevice(sensorDouble);

        // Assert
        assertTrue(device.toString().contains(expected));
    }

    @Test
    void addNullSensorToDeviceShouldReturnEmptyList() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        Sensor sensor = null;
        String expected = "_sensors=[]";

        // Act
        device.addSensorToDevice(sensor);

        // Assert
        assertTrue(device.toString().contains(expected));
    }


}