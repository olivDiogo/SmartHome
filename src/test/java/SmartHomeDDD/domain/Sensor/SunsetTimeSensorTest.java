package SmartHomeDDD.domain.Sensor;

import SmartHome.domain.Gps;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorName;
import SmartHomeDDD.valueObject.SensorTypeID;
import org.junit.jupiter.api.Test;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        Gps gps = new Gps(0, 0);
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
        Gps gps = new Gps(0, 0);
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
        Gps gps = new Gps(0, 0);
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
        Gps gps = new Gps(0, 0);
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
        Gps gps = new Gps(0, 0);
        String expectedMessage = "SensorName cannot be null";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenGpsIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
        SensorName sensorName = new SensorName("sensorName");
        Gps gps = null;
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
        Gps gps = new Gps(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        LocalTime expectedSunsetTime = SunTimes.compute().on(LocalDateTime.now()).at(gps.getLatitude(), gps.getLongitude()).execute().getSet().toLocalTime();
        SunriseSunsetTimeValue expected = new SunriseSunsetTimeValue(expectedSunsetTime);

        //Act
        SunriseSunsetTimeValue sunsetTime = (SunriseSunsetTimeValue) sunsetTimeSensor.getValue();
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
        Gps gps = new Gps(0, 0);
        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
        LocalDate date = LocalDate.now().plusDays(5);
        LocalTime expectedSunsetTime = SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute().getSet().toLocalTime();
        SunriseSunsetTimeValue expected = new SunriseSunsetTimeValue(expectedSunsetTime);

        //Act
        SunriseSunsetTimeValue sunsetTime = (SunriseSunsetTimeValue) sunsetTimeSensor.getValue(date);
        //Assert
        assertEquals(expected.toString(), sunsetTime.toString());
    }


}