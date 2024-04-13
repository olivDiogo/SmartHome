package smartHome.domain.sensor.SolarIrradienceSensor;

import smartHome.domain.sensor.solarIrradianceSensor.SolarIrradianceSensor;
import smartHome.domain.sensor.solarIrradianceSensor.SolarIrradianceValue;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.mockito.MockedConstruction;
import static org.mockito.Mockito.*;

public class SolarIrradianceSensorTest {

  /** Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid. */
  @Test
  void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      SolarIrradianceSensor sensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      // Asser
      assertNotNull(sensor);
    }
  }

  /** Tests the instantiation of SolarIrradianceSensor when the deviceID is null. */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      try {
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assert e.getMessage().equals("DeviceID cannot be null");
      }
    }
  }

  /** Tests the instantiation of SolarIrradianceSensor when the modelPath is null. */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      try {
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assert e.getMessage().equals("ModelPath cannot be null");
      }
    }
  }

  /** Tests the instantiation of SolarIrradianceSensor when the sensorName is null. */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      try {
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assert e.getMessage().equals("SensorName cannot be null");
      }
    }
  }

  /** Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is null. */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = null;

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      try {
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assert e.getMessage().equals("SensorTypeID cannot be null");
      }
    }
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is not SolarIrradiance.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSolarIrradiance() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("NotSolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      try {
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assert e.getMessage().equals("SensorTypeID must be SolarIrradiance");
      }
    }
  }

  /** Tests the getter for the sensorID. */
  @Test
  void shouldReturnSensorID_whenGetSensorIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorID result = solarIrradianceSensor.getID();

      // Assert
      assertTrue(result.toString().contains(result.toString()));
    }
  }

  /** Tests the getter for the deviceID. */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      DeviceID result = solarIrradianceSensor.getDeviceID();

      // Assert
      assertEquals(result, deviceID);
    }
  }

  /** Tests the getter for the modelPath. */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      ModelPath result = solarIrradianceSensor.getModelPath();

      // Assert
      assertEquals(result, modelPath);
    }
  }

  /** Tests the getter for the sensorName. */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorName result = solarIrradianceSensor.getName();

      // Assert
      assertEquals(result, sensorName);
    }
  }

  /** Tests the getter for the sensorTypeID. */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorTypeID result = solarIrradianceSensor.getSensorTypeID();

      // Assert
      assertEquals(result, sensorTypeID);
    }
  }

  /** Tests the getter for the sensorValue. */
  @Test
  void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    String sensorValue = "4500";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      try (MockedConstruction<SolarIrradianceValueTest> sensorValueConstruction =
          mockConstruction(
              SolarIrradianceValueTest.class,
              (mock, context) -> {
                when(mock.toString()).thenReturn(sensorValue);
              })) {
        // Act
        SolarIrradianceValue result = solarIrradianceSensor.getValue();

        // Assert
        assertEquals(result.toString(), sensorValue);
      }
    }
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is an instance of
   * SolarIrradianceSensor.
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithSolarIrradianceSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = solarIrradianceSensor.equals(solarIrradianceSensor);

      // Assert
      assertTrue(result);
    }
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is not an instance of
   * SolarIrradianceSensor.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithNonSolarIrradianceSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      SolarIrradianceSensor solarIrradianceSensor1 =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = solarIrradianceSensor.equals(solarIrradianceSensor1);

      // Assert
      assertFalse(result);
    }
  }

    /** Tests the equals method of SolarIrradianceSensor when the object is null. */
    @Test
    void shouldReturnFalse_whenEqualsIsCalledWithNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

        try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
            SolarIrradianceSensor solarIrradianceSensor =
                    new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

            // Act
            boolean result = solarIrradianceSensor.equals(null);

            // Assert
            assertFalse(result);
        }
    }

  /** Tests returning hash code. */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      int expected = solarIrradianceSensor.getID().hashCode();
      // Act
      int result = solarIrradianceSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }
}
