package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class SwitchSensorTest {
    /**
     * Tests the instantiation of SwitchSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateSwitchSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        }
    }

    /**
     * Tests the instantiation of SwitchSensor when the deviceID is null.
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
                new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("DeviceID is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of SwitchSensor when the modelPath is null.
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
                new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("ModelPath is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of SwitchSensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        DeviceID deviceID = mock(DeviceID.class);
        SensorTypeID sensorTypeID = null;

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            // Act
            try {
                new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("SensorTypeID is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the instantiation of SwitchSensor when the sensorName is null.
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
                new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            } catch (IllegalArgumentException e) {
                // Assert
                assertEquals("SensorName is required", e.getMessage());
            }
        }
    }

    /**
     * Tests the getID method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            SensorID result = switchSensor.getID();

            // Assert
            List<SensorID> constructed = sensorIDConstruction.constructed();
            assertEquals(constructed.get(0), result);
        }
    }

    /**
     * Tests the getSensorName method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            SensorName result = switchSensor.getName();

            // Assert
            assertEquals(sensorName, result);
        }
    }

    /**
     * Tests the getModelPath method of SwitchSensor.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            ModelPath result = switchSensor.getModelPath();

            // Assert
            assertEquals(modelPath, result);
        }
    }

    /**
     * Tests the getSensorTypeID method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            SensorTypeID result = switchSensor.getSensorTypeID();

            // Assert
            assertEquals(sensorTypeID, result);
        }
    }

    /**
     * Tests the getDeviceID method of SwitchSensor.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
            // Act
            DeviceID result = switchSensor.getDeviceID();

            // Assert
            assertEquals(deviceID, result);
        }
    }

    /**
     * Tests the getValue method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        SwitchSensor switchSensor;
        try(MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {})) {
            switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        }
        try(MockedConstruction<SwitchSensorValue> sensorValueConstruction = mockConstruction(SwitchSensorValue.class, (mock, context) -> {})) {
            // Act
            SwitchSensorValue result = (SwitchSensorValue) switchSensor.getValue();

            // Assert
            List<SwitchSensorValue> constructed = sensorValueConstruction.constructed();
            assertEquals(constructed.get(0), result);
        }
    }
}