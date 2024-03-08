package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceTest {

    /**
     * Test for the constructor of the Device class.
     */
    @Test
    void shouldInstantiateDeviceWhenGivenValidParameter() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act & Assert
        assertNotNull(device);
    }

    /**
     * Test for the constructor of the Device class.
     * This test is used to check if the constructor throws an IllegalArgumentException when the name is empty.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        // Arrange
        String expected = "Please enter a valid name for the device type.";
        String name = " ";
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Device(name));
        // Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test for the deactivateDevice method.
     * This test is used to check if the method returns true when the device is deactivated.
     */
    @Test
    void shouldDeactivateDeviceWhenDeactivateDeviceIsCalled() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        boolean actualResult = device.deactivateDevice();
        // Assert
        assertTrue(actualResult);
    }

    /**
     * Test for the getName method.
     * This test is used to check if the method returns the name of the device.
     */
    @Test
    void shouldReturnNameWhenGetNameIsCalled() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        String actualResult = device.getName();
        // Assert
        assertEquals(deviceName, actualResult);
    }

    /**
     * Test for the getStatus method.
     * This test is used to check if the method returns the status of the device.
     */
    @Test
    void shouldReturnStatusWhenGetStatusIsCalled() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        boolean actualResult = device.getStatus();
        // Assert
        assertTrue(actualResult);
    }

    /**
     * Test for the getDeviceId method.
     * This test is used to check if the method returns the device id.
     */
    @Test
    void shouldReturnDeviceIdWhenGetDeviceIdIsCalled() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        UUID deviceID = device.getDeviceId();
        // Assert
        assertNotNull(deviceID);
    }

    /**
     * Test for the getDeviceFunctionalities method.
     * This test is used to check if the method returns a list of functionalities.
     */
    @Test
    void shouldReturnListOfFunctionalitiesWhenGetDeviceFunctionalitiesIsCalled() {
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
     *Test for addSensor method.
     * This test is used to check if the method returns a sensor when it is added to the device.
     */
    @Test
    void shouldAddSensorToDeviceWhenAddSensorIsCalledWithValidModel() throws InstantiationException {
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

    /**
     * Test for AddSensor method.
     * This test is used to check if the method returns null when the sensor is not added to the device.
     */
    @Test
    void shouldReturnNullWhenAddSensorIsCalledWithInvalidModel() throws InstantiationException {
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

    /**
     * Test for addSensorToDevice method.
     * This test is used to check if the method returns a sensor when it is added to the device.
     */
    @Test
    void shouldAddSensorToDeviceWhenAddSensorToDeviceIsCalledWithValidSensor() {
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

    /**
     * Test for the getDeviceFunctionalities method.
     * This test is used to check if the method returns an empty list when there are no functionalities.
     */
    @Test
    void shouldReturnEmptyListWhenAddSensorToDeviceIsCalledWithNullSensor() {
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

    /**
     *Test for GetSensorList method.
     * This test is used to check if the method returns a list of sensors.
     */
    @Test
    void shouldReturnListOfSensorsWhenGetSensorListIsCalled() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        Sensor sensorDouble = mock(Sensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        String strDescription = "Temperature";

        when(sensorDouble.getSensorType()).thenReturn(sensorTypeDouble);
        when(sensorTypeDouble.getDescription()).thenReturn(strDescription);

        device.addSensorToDevice(sensorDouble);

        // Act
        List<String> sensorList = device.getSensorList();

        // Assert
        assertEquals(1, sensorList.size());
    }

    /**
     * Test for the addActuator method.
     * @throws InstantiationException
     */
    @Test
    void shouldAddActuatorToDeviceWhenAddActuatorIsCalledWithValidModel() throws InstantiationException {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        Actuator actuatorDouble = mock(Actuator.class);
        String actuatorModel = "model";
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);

        when(catalogueDouble.getActuator(actuatorModel, actuatorFactory)).thenReturn(actuatorDouble);

        // Act
        Actuator actualResult = device.addActuator(actuatorModel, catalogueDouble, actuatorFactory);

        // Assert
        assertEquals(actuatorDouble, actualResult);
    }

    /**
     * Test for the addActuator method when the actuator is not added to the device.
     * @throws InstantiationException
     */
    @Test
    void shouldReturnNullWhenAddActuatorIsCalledWithInvalidModel() throws InstantiationException {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        String actuatorModel = "model";

        when(catalogueDouble.getActuator(actuatorModel, new ActuatorFactory())).thenReturn(null);

        // Act
        Actuator actualResult = device.addActuator(actuatorModel, catalogueDouble, new ActuatorFactory());

        // Assert
        assertNull(actualResult);
    }

    /**
     * Test for getActuatorList method.
     */

    @Test
    void shouldReturnListOfActuatorsWhenGetActuatorListIsCalled() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        Actuator actuatorDouble = mock(Actuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        String strDescription = "Temperature";

        when(actuatorDouble.getActuatorType()).thenReturn(actuatorTypeDouble);
        when(actuatorTypeDouble.getDescription()).thenReturn(strDescription);

        device.addActuatorToDevice(actuatorDouble);

        // Act
        List<String> actuatorList = device.getActuatorList();

        // Assert
        assertEquals(1, actuatorList.size());
    }

    @Test
    void shouldReturnEmptyListWhenGetActuatorListIsCalledWithNoActuators() {
        // Arrange
        String deviceName = "Device";
        Device device = new Device(deviceName);
        // Act
        List<String> actuatorList = device.getActuatorList();
        // Assert
        assertEquals(0, actuatorList.size());
    }

}