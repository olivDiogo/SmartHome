package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SunriseTimeSensorTest {

  /** See if the constructor works. */
  @Test
  void shouldInstantiateSunriseTimeSensor() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    // Act
    new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
  }

  /** See if the constructor throws an exception when the SensorTypeID parameter is null. */
  @Test
  void shouldThrowExceptionWhenSensorTypeIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;
    GPS gps = new GPS(GPSLatitude, GPSLongitude);
    String expectedMessage = "SensorTypeID cannot be null.";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    // Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * See if the constructor throws an exception when the SensorTypeID parameter is not SunriseTime.
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeIsNotSunriseTime() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "NotSunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);
    String expectedMessage = "SensorTypeID must be 'SunriseTime'.";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    // Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /** See if the constructor throws an exception when the GPS parameter is null. */
  @Test
  void shouldThrowExceptionWhenGpsAttributeIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = null;
    String expectedMessage = "GPS cannot be null.";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
    // Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /** See if the constructor throws an exception when the DeviceID parameter is null. */
  @Test
  void shouldThrowExceptionWhenDeviceIDAttributeIsNull() {
    // Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    String expectedMessage = "DeviceID cannot be null.";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
    // Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /** See if the constructor throws an exception when the Model path parameter is null. */
  @Test
  void shouldThrowExceptionWhenModelPathAttributeIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    String expectedMessage = "ModelPath cannot be null.";

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
    // Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /** See if the constructor throws an exception when the SensorName parameter is null. */
  @Test
  void shouldThrowExceptionWhenSensorNameAttributeIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);
    String expectedMessage = "SensorName cannot be null";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
    // Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /** See if the getSensorName method works. */
  @Test
  void shouldReturnSensorName() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    // Act
    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
    SensorName result = sunriseTimeSensor.getName();

    // Assert
    assertEquals(result, sensorName);
  }

  /** See if the getSensorTypeID method works. */
  @Test
  void shouldReturnSensorTypeID() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    // Act
    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
    SensorTypeID result = sunriseTimeSensor.getSensorTypeID();

    // Assert
    assertEquals(result, sensorTypeID);
  }

  /** See if the getDeviceID method works. */
  @Test
  void shouldReturnDeviceID() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    // Act
    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
    DeviceID result = sunriseTimeSensor.getDeviceID();

    // Assert
    assertEquals(result, deviceID);
  }

  /** See if the getModelPath method works. */
  @Test
  void shouldReturnModelPath() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    // Act
    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
    ModelPath result = sunriseTimeSensor.getModelPath();

    // Assert
    assertEquals(result, modelPath);
  }

  /** See if the getID method works. */
  @Test
  void shouldReturnSensorID() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    // Act
    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(
            deviceID, modelPath, sensorTypeID, sensorName, gps);
    SensorID result = sunriseTimeSensor.getID();

    // Assert
    assertNotNull(result);
  }

  /** See if the getValue method works for the current day. */
  @Test
  void shouldReturnSunriseTimeValueForCurrentDay() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

    // Act
    ValueObject result = sunriseTimeSensor.getValue();

    // Assert
    assertNotNull(result);
  }

  /** See if the getValue method works for a specific day. */
  @Test
  void shouldReturnSunriseTimeValueForSpecificDay() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;
    String expected = "Sunrise Time: 12:11:54";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    LocalDate date = LocalDate.of(2021, 10, 10);

    SunriseTimeSensor sunriseTimeSensor =
        new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

    // Act
    ValueObject result = sunriseTimeSensor.getValue(date);

    // Assert
    assertNotNull(result);
    assertEquals(expected, result.toString());
  }
}
