package smart_home.domain;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor.switch_sensor.SwitchSensorValue;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import smart_home.value_object.SensorID;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class LogAggregateTest {
    /**
     * Test the constructor of the Log class.
     * The constructor should create a new Log instance with the specified device ID, sensor ID, timestamp, and value.
     */
    @Test
    void shouldCreateLogInstance_WhenValidArgumentsAreProvided() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act
        Log log = new Log(deviceID, sensorID, localDateTime, value);

        // Assert
        assertNotNull(log);
    }

    /**
     * Test the constructor of the Log class.
     * The constructor should create a new Log instance with the specified log ID, device ID, sensor ID, timestamp, and value.
     */
    @Test
    void shouldCreateLogInstance_WhenValidArgumentsAreProvidedForTheSecondConstructor() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Assert
        assertNotNull(log);
    }

    /**
     * Test the constructor of the Log class.
     * The constructor should throw an IllegalArgumentException when a null log ID is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullLogIDIsProvided() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(null, deviceID, sensorID, localDateTime, value);
        });

        // Assert
        assertEquals("Log ID is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The second constructor should throw an IllegalArgumentException when a null device ID is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullDeviceIDIsProvidedForTheSecondConstructor() {
        // Arrange
        LogID logID = new LogID("logID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(logID, null, sensorID, localDateTime, value);
        });

        // Assert
        assertEquals("Device ID is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The second constructor should throw an IllegalArgumentException when a null sensor ID is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullSensorIDIsProvidedForTheSecondConstructor() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(logID, deviceID, null, localDateTime, value);
        });

        // Assert
        assertEquals("Sensor ID is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The second constructor should throw an IllegalArgumentException when a null local date time is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullLocalDateTimeIsProvidedForTheSecondConstructor() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(logID, deviceID, sensorID, null, value);
        });

        // Assert
        assertEquals("Timestamp is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The second constructor should throw an IllegalArgumentException when a null value is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullValueIsProvidedForTheSecondConstructor() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(logID, deviceID, sensorID, localDateTime, null);
        });

        // Assert
        assertEquals("Value is required", exception.getMessage());
    }




    /**
     * Test the constructor of the Log class.
     * The constructor should throw an IllegalArgumentException when a null device ID is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullDeviceIDIsProvided() {
        // Arrange
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(null, sensorID, localDateTime, value);
        });

        // Assert
        assertEquals("Device ID is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The constructor should throw an IllegalArgumentException when a null sensor ID is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullSensorIDIsProvided() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(deviceID, null, localDateTime, value);
        });

        // Assert
        assertEquals("Sensor ID is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The constructor should throw an IllegalArgumentException when a null local date time is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullLocalDateTimeIsProvided() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        String value = new SwitchSensorValue(true).toString();

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(deviceID, sensorID, null, value);
        });

        // Assert
        assertEquals("Timestamp is required", exception.getMessage());
    }

    /**
     * Test the constructor of the Log class.
     * The constructor should throw an IllegalArgumentException when a null value is provided.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenNullValueIsProvided() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Log(deviceID, sensorID, localDateTime, null);
        });

        // Assert
        assertEquals("Value is required", exception.getMessage());
    }

    /**
     * Test the getID method of the Log class.
     * The getID method should return the log ID of the Log instance.
     */
    @Test
    void shouldReturnLogID_WhenGetIDIsCalled() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        LogID result = log.getID();

        // Assert
        assertEquals(logID, result);
    }

    /**
     * Test the getDeviceID method of the Log class.
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        DeviceID result = log.getDeviceID();

        // Assert
        assertEquals(deviceID, result);
    }

    /**
     * Test the getSensorID method of the Log class.
     */
    @Test
    void shouldReturnSensorID_WhenGetSensorIDIsCalled() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        SensorID result = log.getSensorID();

        // Assert
        assertEquals(sensorID, result);
    }

    /**
     * Test the getTimeStamp method of the Log class.
     */
    @Test
    void shouldReturnTimeStamp_WhenGetTimeStampIsCalled() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        LocalDateTime result = log.getTimeStamp();

        // Assert
        assertEquals(localDateTime, result);
    }

    /**
     * Test the getValue method of the Log Class.
     */
    @Test
    void shouldReturnValue_WhenGetValueIsCalled(){
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        String result = log.getValue();

        // Assert
        assertEquals(value, result);
    }

    /**
     * Test the equals method of the Log class when two Log instances are equal.
     */
    @Test
    void shouldReturnTrue_WhenTwoLogInstancesAreEqual() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log1 = new Log(logID, deviceID, sensorID, localDateTime, value);
        Log log2 = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        boolean result = log1.equals(log2);

        // Assert
        assertEquals(true, result);
    }

    /**
     * Test the equals method of the Log class when two Log instances are not equal.
     */
    @Test
    void shouldReturnFalse_WhenTwoLogInstancesAreNotEqual() {
        // Arrange
        LogID logID = new LogID("logID1");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        boolean result = log.equals(new Object());

        // Assert
        assertEquals(false, result);
    }

    /**
     * Test the hashcode method of the Log class.
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeIsCalled() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        // Act
        int result = log.hashCode();

        // Assert
        assertEquals(logID.hashCode(), result);
    }

    /**
     * Test the toString method of the Log class.
     */
    @Test
    void shouldReturnString_WhenToStringIsCalled() {
        // Arrange
        LogID logID = new LogID("logID");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorID sensorID = new SensorID("sensorID");
        LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
        String value = new SwitchSensorValue(true).toString();
        Log log = new Log(logID, deviceID, sensorID, localDateTime, value);

        String expected = logID + " " + deviceID + " " + sensorID + " " + localDateTime + " " + value;

        // Act
        String result = log.toString();

        // Assert
        assertEquals(expected, result);
    }
}