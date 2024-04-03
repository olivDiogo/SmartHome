package SmartHomeDDD.domain.Sensor;


import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class TemperatureSensorTest {

    /**
     * Tests the instantiation of TemperatureSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateTemperatureSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(sensor);
    }

    /**
     * Tests the instantiation of TemperatureSensor when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("DeviceID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of TemperatureSensor when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("ModelPath is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of TemperatureSensor when the sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorName is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of TemperatureSensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorTypeID is required", exception.getMessage());
    }

    /**
     * Tests the getter for the sensorID.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID result = sensor.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the getter for the sensor name.
     */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = sensor.getName();

        // Assert
        assertEquals(sensorName, result);
    }

    /**
     * Tests the getter for the model path.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = sensor.getModelPath();

        // Assert
        assertEquals(modelPath, result);
    }

    /**
     * Tests the getter for the device ID.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = sensor.getDeviceID();

        // Assert
        assertEquals(deviceID, result);
    }

    /**
     * Tests the getter for the sensor type ID.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = sensor.getSensorTypeID();

        // Assert
        assertEquals(sensorTypeID, result);
    }

    /**
     * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Temperature'.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotTemperature() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "NotTemperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorTypeID must be of type 'Temperature'", exception.getMessage());
    }

    /**
     * Tests the getter for the sensor value.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        TemperatureSensorValue result = sensor.getValue();

        // Assert
        double value = Double.parseDouble(result.toString());
        assertTrue(value >= -273.15);
    }
}