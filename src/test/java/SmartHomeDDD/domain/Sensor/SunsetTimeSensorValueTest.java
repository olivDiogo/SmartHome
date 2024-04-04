package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.SunsetTimeSensor.SunsetTimeSensorValue;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SunsetTimeSensorValueTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWhenValueIsNull() {
        //Arrange
        LocalTime value = null;
        String expectedMessage = "Time is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensorValue(value));
        //Asset
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldCreateSunsetTimeSensorValue() {
        //Arrange
        LocalTime value = LocalTime.of(18, 30, 0);
        String expectedValue = "Sunset Time: 18:30:00";
        //Act
        SunsetTimeSensorValue sunsetTimeSensorValue = new SunsetTimeSensorValue(value);
        //Asset
        assertEquals(expectedValue, sunsetTimeSensorValue.toString());
    }

}