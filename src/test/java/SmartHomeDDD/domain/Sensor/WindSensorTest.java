package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.WindSensor.WindSensor;
import SmartHomeDDD.domain.Sensor.WindSensor.WindSensorValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class WindSensorTest {

    /**
     * Tests the instantiation of WindSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateWindSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Assert
            assertEquals(deviceID, windSensor.getDeviceID());
        }
    }

    /**
     * Tests the instantiation of WindSensor when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            try {
                new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("DeviceID is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of WindSensor when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            try {
                new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("ModelPath is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of WindSensor when the sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            try {
                new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("SensorName is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of WindSensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = null;

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            try {
                new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("SensorTypeID is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the getter for the sensorID.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {

            WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            SensorID result = sensor.getID();
            // Assert
            List<SensorID> constructed = sensorIDConstruction.constructed();
            assertEquals(constructed.get(0), result);
        }
    }

    /**
     * Tests the getter for the sensor name.
     */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {

            WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            SensorName result = sensor.getName();
            // Assert
            assertEquals(sensorName, result);
        }
    }

    /**
     * Tests the getter for the model path.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {

            WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            ModelPath result = sensor.getModelPath();
            // Assert
            assertEquals(modelPath, result);
        }
    }

    /**
     * Tests the getter for the device ID.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {

            WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            DeviceID result = sensor.getDeviceID();
            // Assert
            assertEquals(deviceID, result);
        }
    }

    /**
     * Tests the getter for the sensor type ID.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {

            WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            SensorTypeID result = sensor.getSensorTypeID();
            // Assert
            assertEquals(sensorTypeID, result);
        }
    }

    /**
     * Tests the getter for the sensor value.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        {
            WindSensor sensor;
            try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
            })) {
                sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
            }
            try (MockedConstruction<WindSensorValue> sensorValueConstruction = mockConstruction(WindSensorValue.class, (mock, context) -> {
            })) {
                // Act
                WindSensorValue result = sensor.getValue();
                // Assert
                List<WindSensorValue> constructed = sensorValueConstruction.constructed();
                assertEquals(constructed.get(0), result);
            }
        }
    }

}