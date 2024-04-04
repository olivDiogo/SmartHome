package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SunsetTimeSensorTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWhenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        String expectedMessage = "DeviceID cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = null;
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        String expectedMessage = "SensorTypeID cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNotSunsetTime() {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("Wrong Sensor TypeID");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        String expectedMessage = "SensorTypeID must be 'SunsetTime'.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = null;
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        String expectedMessage = "ModelPath cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorNameIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = null;
        GPS gps = new GPS(0, 0);
        String expectedMessage = "SensorName cannot be null";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGPSIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = null;
        String expectedMessage = "GPS cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnSunsetTimeForCurrentDay() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        LocalTime expectedSunsetTime = Objects.requireNonNull(SunTimes.compute().on(LocalDate.now()).at(gps.getLatitude(), gps.getLongitude()).execute().getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        SunsetTimeSensorValue expected = new SunsetTimeSensorValue(expectedSunsetTime);

        //Act
        SunsetTimeSensorValue sunsetTime = (SunsetTimeSensorValue) sunsetTimeSensor.getValue();
        //Assert
        assertEquals(expected.toString(), sunsetTime.toString());
    }
    @Test
    void shouldReturnSunsetTimeForGivenDay() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        LocalDate date = LocalDate.now().plusDays(5);
        LocalTime expectedSunsetTime = Objects.requireNonNull(SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute().getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        SunsetTimeSensorValue expected = new SunsetTimeSensorValue(expectedSunsetTime);

        //Act
        SunsetTimeSensorValue sunsetTime = (SunsetTimeSensorValue) sunsetTimeSensor.getValue(date);
        //Assert
        assertEquals(expected.toString(), sunsetTime.toString());
    }
    @Test
    void shouldReturnDeviceIDWhenGetDeviceIDIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        //Act
        DeviceID actualDeviceID = sunsetTimeSensor.getDeviceID();
        //Assert
        assertEquals(deviceID, actualDeviceID);
    }
    @Test
    void shouldReturnSensorTypeIDWhenGetSensorTypeIDIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        //Act
        SensorTypeID actualSensorTypeID = sunsetTimeSensor.getSensorTypeID();
        //Assert
        assertEquals(sensorTypeID, actualSensorTypeID);
    }
    @Test
    void shouldReturnSensorNameWhenGetSensorNameIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        //Act
        SensorName actualSensorName = sunsetTimeSensor.getName();
        //Assert
        assertEquals(sensorName, actualSensorName);
    }
    @Test
    void shouldReturnModelPathWhenGetModelPathIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        //Act
        ModelPath actualModelPath = sunsetTimeSensor.getModelPath();
        //Assert
        assertEquals(modelPath, actualModelPath);
    }
    @Test
    void shouldReturnSensorIDWhenGetSensorIDIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        GPS gps = new GPS(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        //Act
        SensorID actualSensorID = sunsetTimeSensor.getID();
        //Assert
        assertTrue(sunsetTimeSensor.toString().contains(actualSensorID.toString()));
    }


}