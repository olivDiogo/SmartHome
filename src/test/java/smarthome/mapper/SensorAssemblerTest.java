package smarthome.mapper;

import org.junit.jupiter.api.Test;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.dto.SensorDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorAssemblerTest {

  /**
   * Test that the method domainToDTO converts a Sensor to a SensorDTO.
   */
  @Test
  void shouldConvertSensorToSensorDTO_WhenSensorIsValid() {
    // Arrange
    String deviceID = "123";
    String modelPath = "SmartHome.sensors.DewPointSensor";
    String sensorTypeID = "321";
    String sensorID = "432";
    String sensorName = "dewPoint";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.toString()).thenReturn(deviceID);

    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.toString()).thenReturn(modelPath);

    SensorTypeID sensorTypeDouble = mock(SensorTypeID.class);
    when(sensorTypeDouble.toString()).thenReturn(sensorTypeID);

    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.toString()).thenReturn(sensorID);

    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.toString()).thenReturn(sensorName);

    ISensor sensorDouble = mock(ISensor.class);
    when(sensorDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorDouble.getSensorTypeID()).thenReturn(sensorTypeDouble);
    when(sensorDouble.getID()).thenReturn(sensorIDDouble);
    when(sensorDouble.getName()).thenReturn(sensorNameDouble);

    SensorAssembler sensorAssembler = new SensorAssembler();
    String expected =
        deviceID + " " + modelPath + " " + sensorTypeID + " " + sensorID + " " + sensorName;

    // Act
    SensorDTO sensorDTO = sensorAssembler.domainToDTO(sensorDouble);

    // Assert
    assertEquals(expected, sensorDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an exception when the Sensor is null.
   */
  @Test
  void shouldThrowException_WhenSensorIsNull() {
    // Arrange
    ISensor sensor = null;

    SensorAssembler sensorAssembler = new SensorAssembler();

    String expected = "Sensor is required";

    // Act & Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorAssembler.domainToDTO(sensor);
            });

    String result = exception.getMessage();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test that the method domainToDTO throws an exception when the list of Sensors is null.
   */
  @Test
  void shouldConvertSensorListToListOfSensorsDTOList() throws EmptyReturnException {
    // Arrange
    String deviceID = "123";
    String modelPath = "SmartHome.sensors.DewPointSensor";
    String sensorTypeID = "321";
    String sensorID = "432";
    String sensorName = "dewPoint";

    String deviceID2 = "12";
    String modelPath2 = "SmartHome.sensors.DewPointSensor";
    String sensorTypeID2 = "321";
    String sensorID2 = "43212";
    String sensorName2 = "dewPoint2";

    // sensor1
    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.toString()).thenReturn(deviceID);

    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.toString()).thenReturn(modelPath);

    SensorTypeID sensorTypeDouble = mock(SensorTypeID.class);
    when(sensorTypeDouble.toString()).thenReturn(sensorTypeID);

    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.toString()).thenReturn(sensorID);

    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.toString()).thenReturn(sensorName);

    ISensor sensorDouble = mock(ISensor.class);
    when(sensorDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorDouble.getSensorTypeID()).thenReturn(sensorTypeDouble);
    when(sensorDouble.getID()).thenReturn(sensorIDDouble);
    when(sensorDouble.getName()).thenReturn(sensorNameDouble);

    // sensor2
    DeviceID deviceIDDouble2 = mock(DeviceID.class);
    when(deviceIDDouble2.toString()).thenReturn(deviceID2);

    ModelPath modelPathDouble2 = mock(ModelPath.class);
    when(modelPathDouble2.toString()).thenReturn(modelPath2);

    SensorTypeID sensorTypeDouble2 = mock(SensorTypeID.class);
    when(sensorTypeDouble2.toString()).thenReturn(sensorTypeID2);

    SensorID sensorIDDouble2 = mock(SensorID.class);
    when(sensorIDDouble2.toString()).thenReturn(sensorID2);

    SensorName sensorNameDouble2 = mock(SensorName.class);
    when(sensorNameDouble2.toString()).thenReturn(sensorName2);

    ISensor sensorDouble2 = mock(ISensor.class);
    when(sensorDouble2.getDeviceID()).thenReturn(deviceIDDouble2);
    when(sensorDouble2.getModelPath()).thenReturn(modelPathDouble2);
    when(sensorDouble2.getSensorTypeID()).thenReturn(sensorTypeDouble2);
    when(sensorDouble2.getID()).thenReturn(sensorIDDouble2);
    when(sensorDouble2.getName()).thenReturn(sensorNameDouble2);

    SensorAssembler sensorAssembler = new SensorAssembler();

    List<ISensor> sensors = List.of(sensorDouble, sensorDouble2);

    SensorDTO sensorDTO1 = new SensorDTO(deviceID, modelPath, sensorTypeID, sensorID, sensorName);
    SensorDTO sensorDTO2 = new SensorDTO(deviceID2, modelPath2, sensorTypeID2, sensorID2,
        sensorName2);
    List<SensorDTO> expected = List.of(sensorDTO1, sensorDTO2);

    // Act
    List<SensorDTO> sensorsDTO = sensorAssembler.domainToDTO(sensors);

    // Assert
    assertEquals(expected.toString(), sensorsDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an exception when the list of Sensors is null.
   */
  @Test
  void shouldThrowException_WhenListOfSensorsIsNull() {
    // Arrange
    List<ISensor> sensors = null;

    SensorAssembler sensorAssembler = new SensorAssembler();

    // Act and Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorAssembler.domainToDTO(sensors);
            });

    String expected = "The list of sensors cannot be null.";

    String result = exception.getMessage();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test that the method domainToDTO throws an exception when the list of Sensors is empty.
   */
  @Test
  void shouldThrowException_WhenListOfSensorsIsEmpty() {
    // Arrange
    List<ISensor> sensors = List.of();

    SensorAssembler sensorAssembler = new SensorAssembler();

    // Act and Assert
    Exception exception =
        assertThrows(
            EmptyReturnException.class,
            () -> {
              sensorAssembler.domainToDTO(sensors);
            });

    String expected = "The list of sensors is empty.";

    String result = exception.getMessage();

    // Assert
    assertEquals(expected, result);
  }

}
