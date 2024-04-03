package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class HumiditySensorTest {

    /**
     * Tests the instantiation of HumiditySensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateHumiditySensor_whenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
        }
    }

    /**
     * Tests the instantiation of HumiditySensor when the deviceID is null.
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
                new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("DeviceID is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of HumiditySensor when the modelPath is null.
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
                new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("ModelPath is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of HumiditySensor when the sensorTypeID is null.
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
                new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("SensorTypeID is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of HumiditySensor when the sensorName is null.
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
                new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("SensorName is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the getID method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorID sensorID = mock(SensorID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            SensorID result = humiditySensor.getID();

            // Assert
            List<SensorID> constructed = sensorIDConstruction.constructed();
            assertEquals(constructed.get(0), result);
        }
    }

    /**
     * Tests the getModelPath method of HumiditySensor.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            ModelPath result = humiditySensor.getModelPath();

            // Assert
            assertEquals(modelPath, result);
        }
    }

    /**
     * Tests the getName method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorName_whenGetNameIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            SensorName result = humiditySensor.getName();

            // Assert
            assertEquals(sensorName, result);
        }
    }

    /**
     * Tests the getSensorTypeID method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            SensorTypeID result = humiditySensor.getSensorTypeID();

            // Assert
            assertEquals(sensorTypeID, result);
        }
    }

    /**
     * Tests the getDeviceID method of HumiditySensor.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            DeviceID result = humiditySensor.getDeviceID();

            // Assert
            assertEquals(deviceID, result);
        }
    }

    /**
     * Tests the getSensorValue method of HumiditySensor.
     */
    @Test
    void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        {
            HumiditySensor humiditySensor;
            try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
                humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
            }
            try(MockedConstruction<HumiditySensorValue> sensorValueConstruction = mockConstruction(HumiditySensorValue.class, (mock, context) -> {})) {
                // Act
                HumiditySensorValue result = humiditySensor.getValue();
                // Assert
                List<HumiditySensorValue> constructed = sensorValueConstruction.constructed();
                assertEquals(constructed.get(0), result);
            }
        }
    }

}