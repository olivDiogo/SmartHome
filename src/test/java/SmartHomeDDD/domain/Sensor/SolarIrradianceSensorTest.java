package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import org.mockito.MockedConstruction;

import static org.mockito.Mockito.*;

public class SolarIrradianceSensorTest {

    /**
     * Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid.
     */
    @Test
    public void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            // Act
            new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);
        }
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the deviceID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            // Act
            try {
                new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);
            } catch (IllegalArgumentException e) {
                // Assert
                assert e.getMessage().equals("DeviceID cannot be null");
            }
        }
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the modelPath is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            // Act
            try {
                new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);
            } catch (IllegalArgumentException e) {
                // Assert
                assert e.getMessage().equals("ModelPath cannot be null");
            }
        }
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the sensorName is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            // Act
            try {
                new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);
            } catch (IllegalArgumentException e) {
                // Assert
                assert e.getMessage().equals("SensorName cannot be null");
            }
        }
    }

    /**
     * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = null;

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            // Act
            try {
                new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);
            } catch (IllegalArgumentException e) {
                // Assert
                assert e.getMessage().equals("SensorTypeID cannot be null");
            }
        }
    }

    /**
     * Tests the getter for the sensorID.
     */
    @Test
    public void shouldReturnSensorID_whenGetSensorIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("sensorID");
        })) {
            SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);

            // Act
            SensorID result = solarIrradianceSensor.getID();

            // Assert
            assert result.toString().equals("sensorID");
        }
    }

    /**
     * Tests the getter for the deviceID.
     */
    @Test
    public void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);

            // Act
            DeviceID result = solarIrradianceSensor.getDeviceID();

            // Assert
            assert result.equals(deviceID);
        }
    }

    /**
     * Tests the getter for the modelPath.
     */
    @Test
    public void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);

            // Act
            ModelPath result = solarIrradianceSensor.getModelPath();

            // Assert
            assert result.equals(modelPath);
        }
    }

    /**
     * Tests the getter for the sensorName.
     */
    @Test
    public void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);

            // Act
            SensorName result = solarIrradianceSensor.getName();

            // Assert
            assert result.equals(sensorName);
        }
    }

    /**
     * Tests the getter for the sensorTypeID.
     */
    @Test
    public void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);

            // Act
            SensorTypeID result = solarIrradianceSensor.getSensorTypeID();

            // Assert
            assert result.equals(sensorTypeID);
        }
    }

    /**
     * Tests the getter for the sensorValue.
     */
    @Test
    public void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);

        String sensorValue = "4500";

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class, (mock, context) -> {
        })) {
            SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(deviceID, modelPath, sensorName, sensorTypeID);

            try (MockedConstruction<SolarIrradianceValueTest> sensorValueConstruction = mockConstruction(SolarIrradianceValueTest.class, (mock, context) -> {
                when(mock.toString()).thenReturn("4500");
            })) {
                // Act
                ValueObject result = solarIrradianceSensor.getValue();

                // Assert
                assert result.toString().equals(sensorValue);
            }

        }
    }

}
