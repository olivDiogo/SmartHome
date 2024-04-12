package smartHome.domain.sensor.SunriseTimeSensor;

import org.junit.jupiter.api.Test;
import smartHome.ddd.IValueObject;
import smartHome.domain.sensor.sunriseTimeSensor.SunriseTimeSensor;
import smartHome.valueObject.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SunriseTimeSensorAggregateTest {

    /**
     * See if the constructor works.
     */
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
        SunriseTimeSensor result = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Assert
        assertNotNull(result);
    }

    /**
     * See if the constructor throws an exception when the SensorTypeID parameter is null.
     */
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the constructor throws an exception when the GPS parameter is null.
     */
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the constructor throws an exception when the DeviceID parameter is null.
     */
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the constructor throws an exception when the Model path parameter is null.
     */
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the constructor throws an exception when the SensorName parameter is null.
     */
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

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the getSensorName method works.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        SensorName result = sunriseTimeSensor.getName();

        // Assert
        assertEquals(result, sensorName);
    }

    /**
     * See if the getSensorTypeID method works.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        SensorTypeID result = sunriseTimeSensor.getSensorTypeID();

        // Assert
        assertEquals(result, sensorTypeID);
    }

    /**
     * See if the getDeviceID method works.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        DeviceID result = sunriseTimeSensor.getDeviceID();

        // Assert
        assertEquals(result, deviceID);
    }

    /**
     * See if the getModelPath method works.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        ModelPath result = sunriseTimeSensor.getModelPath();

        // Assert
        assertEquals(result, modelPath);
    }

    /**
     * See if the getID method works.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        SensorID result = sunriseTimeSensor.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * See if the getValue method works for the current day.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        IValueObject result = sunriseTimeSensor.getValue();

        // Assert
        assertNotNull(result);
    }

    /**
     * See if the getValue method works for a specific day.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        IValueObject result = sunriseTimeSensor.getValue(date);

        // Assert
        assertEquals(expected, result.toString());
    }

    /**
     * Tests method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenComparedToItself() {
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        boolean result = sunriseTimeSensor.equals(sunriseTimeSensor);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests method equals when the instance is compared to another instance with the same ID.
     */
    @Test
    void shouldReturnFalse_WhenThereTwoDifferentObjects() {
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        SunriseTimeSensor sunriseTimeSensor2 = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        boolean result = sunriseTimeSensor.equals(sunriseTimeSensor2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests equals when instances is compared to a null object.
     */
    @Test
    void shouldReturnFalse_WhenComparedToNull() {
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        // Act
        boolean result = sunriseTimeSensor.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests equals when instances is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_WhenComparedToDifferentClass() {
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        Object obj = new Object();

        // Act
        boolean result = sunriseTimeSensor.equals(obj);

        // Assert
        assertFalse(result);
    }

    /**
     * Test hashCode method.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        SensorID sensorID = sunriseTimeSensor.getID();
        int expected = sensorID.hashCode();

        // Act
        int result = sunriseTimeSensor.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled() {
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

        String expected = "SunriseTimeSensor:" +
                ", sensorID=" + sunriseTimeSensor.getID() +
                ", sensorName=" + sensorName +
                ", modelPath=" + modelPath +
                ", sensorTypeID=" + sensorTypeID +
                ", deviceID=" + deviceID +
                ", gps=" + gps;

        // Act
        String result = sunriseTimeSensor.toString();

        // Assert
        assertEquals(expected, result);
    }


}