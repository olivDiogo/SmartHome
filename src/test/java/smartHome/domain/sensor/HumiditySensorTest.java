package smartHome.domain.sensor;

import smartHome.domain.sensor.humiditySensor.HumiditySensor;
import smartHome.domain.sensor.humiditySensor.HumiditySensorValue;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumiditySensorTest {

    /**
     * Tests the instantiation of HumiditySensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateHumiditySensor_whenConstructorArgumentsAreValid() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(humiditySensor);
    }

    /**
     * Tests the instantiation of HumiditySensor when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("DeviceID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensor when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        String deviceIDValue = "deviceID";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("ModelPath is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorTypeID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of HumiditySensor when the sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorName is required", exception.getMessage());
    }

    /**
     * Tests the getID method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID result = humiditySensor.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the getModelPath method of HumiditySensor.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = humiditySensor.getModelPath();

        // Assert
        assertEquals(modelPath, result);
    }

    /**
     * Tests the getName method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorName_whenGetNameIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = humiditySensor.getName();

        // Assert
        assertEquals(sensorName, result);
    }

    /**
     * Tests the getSensorTypeID method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = humiditySensor.getSensorTypeID();

        // Assert
        assertEquals(sensorTypeID, result);
    }

    /**
     * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Humidity'.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotHumidity() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorTypeID must be of type 'Humidity'", exception.getMessage());
    }

    /**
     * Tests the getDeviceID method of HumiditySensor.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = humiditySensor.getDeviceID();

        // Assert
        assertEquals(deviceID, result);
    }

    /**
     * Tests the getSensorValue method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        HumiditySensorValue result = humiditySensor.getValue();

        // Assert
        double value = Double.parseDouble(result.toString());
        assertTrue(value >= 0 && value <= 100);
    }
}