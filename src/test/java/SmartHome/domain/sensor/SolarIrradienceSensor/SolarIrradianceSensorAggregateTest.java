package smartHome.domain.sensor.SolarIrradienceSensor;

import smartHome.domain.sensor.solarIrradianceSensor.SolarIrradianceSensor;
import smartHome.domain.sensor.solarIrradianceSensor.SolarIrradianceValue;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SolarIrradianceSensorAggregateTest {

    /** Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid. */
    @Test
    void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        // Act
        SolarIrradianceSensor sensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(sensor);
    }

    /** Tests the instantiation of SolarIrradianceSensor when the deviceID is null. */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        String expectedMessage = "DeviceID cannot be null";

        // Act
        Exception e =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
                        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /** Tests the instantiation of SolarIrradianceSensor when the modelPath is null. */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        String expectedMessage = "ModelPath cannot be null";

        // Act
        Exception e =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
                        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /** Tests the instantiation of SolarIrradianceSensor when the sensorName is null. */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        String expectedMessage = "SensorName cannot be null";

        // Act
        Exception e =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
                        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /** Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is null. */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = null;

        String expectedMessage = "SensorTypeID cannot be null";

        // Act
        Exception e =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
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
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSolarIrradiance() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("NotSolarIrradiance");

        String expectedMessage = "SensorTypeID must be SolarIrradiance";

        // Act
        Exception e =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
                        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /** Tests the getter for the sensorID. */
    @Test
    void shouldReturnSensorID_whenGetSensorIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID sensorID = solarIrradianceSensor.getID();

        // Assert
        assertNotNull(sensorID);
    }

    /** Tests the getter for the deviceID. */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = solarIrradianceSensor.getDeviceID();

        // Assert
        assertEquals(result, deviceID);
    }

    /** Tests the getter for the modelPath. */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = solarIrradianceSensor.getModelPath();

        // Assert
        assertEquals(result, modelPath);
    }

    /** Tests the getter for the sensorName. */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = solarIrradianceSensor.getName();

        // Assert
        assertEquals(result, sensorName);
    }

    /** Tests the getter for the sensorTypeID. */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = solarIrradianceSensor.getSensorTypeID();

        // Assert
        assertEquals(result, sensorTypeID);
    }

    /** Tests the getter for the sensorValue. */
    @Test
    void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        String expectedValue = "4500";

        // Act
        SolarIrradianceValue result = solarIrradianceSensor.getValue();

        // Assert
        assertEquals(result.toString(), expectedValue);
    }

    /**
     * Tests the equals method of SolarIrradianceSensor when the object is an instance of
     * SolarIrradianceSensor.
     */
    @Test
    void shouldReturnTrue_whenEqualsIsCalledWithSolarIrradianceSensor() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = solarIrradianceSensor.equals(solarIrradianceSensor);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method of SolarIrradianceSensor when the object is not an instance of
     * SolarIrradianceSensor.
     */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithNonSolarIrradianceSensor() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor1 =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        SolarIrradianceSensor solarIrradianceSensor2 =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = solarIrradianceSensor1.equals(solarIrradianceSensor2);

        // Assert
        assertFalse(result);
    }

    /** Tests returning hash code. */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorName sensorName = new SensorName("sensorName");
        SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

        SolarIrradianceSensor solarIrradianceSensor =
                new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
        int expected = solarIrradianceSensor.getID().hashCode();
        // Act
        int result = solarIrradianceSensor.hashCode();

        // Assert
        assertEquals(expected, result);
    }
}
