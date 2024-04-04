package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.SolarIrradianceSensor.SolarIrradianceSensor;
import SmartHomeDDD.domain.Sensor.SolarIrradianceSensor.SolarIrradianceValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SolarIrradianceSensorTest {

    /**
     * Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid.
     */
    @Test
    public void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        // Act
        SolarIrradianceSensor sensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(sensor);

    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the deviceID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        String expectedMessage = "DeviceID cannot be null";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the modelPath is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        String expectedMessage = "ModelPath cannot be null";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the sensorName is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        String expectedMessage = "SensorName cannot be null";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = null;

        String expectedMessage = "SensorTypeID cannot be null";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is not SolarIrradiance.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSolarIrradiance() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("NotSolarIrradiance");

        String expectedMessage = "SensorTypeID must be SolarIrradiance";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the getter for the sensorID.
     */
    @Test
    public void shouldReturnSensorID_whenGetSensorIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID sensorID = solarIrradianceSensor.getID();

        // Assert
        assertNotNull(sensorID);

    }

    /**
     * Tests the getter for the deviceID.
     */
    @Test
    public void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = solarIrradianceSensor.getDeviceID();

        // Assert
        assertEquals(result,deviceID);
    }

    /**
     * Tests the getter for the modelPath.
     */
    @Test
    public void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = solarIrradianceSensor.getModelPath();

        // Assert
        assertEquals(result,modelPath);
    }

    /**
     * Tests the getter for the sensorName.
     */
    @Test
    public void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = solarIrradianceSensor.getName();

        // Assert
        assertEquals(result,sensorName);
    }

    /**
     * Tests the getter for the sensorTypeID.
     */
    @Test
    public void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = solarIrradianceSensor.getSensorTypeID();

        // Assert
        assertEquals(result,sensorTypeID);
    }

    /**
     * Tests the getter for the sensorValue.
     */
    @Test
    public void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        String expectedValue = "4500";

        // Act
        SolarIrradianceValue result = solarIrradianceSensor.getValue();

        // Assert
        assertEquals(result.toString(),expectedValue);
    }

}
