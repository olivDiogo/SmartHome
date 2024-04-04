package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.SunriseTimeSensor.SunriseSunsetTimeValue;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SunriseSunsetTimeValueTest {
    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateSunriseTimeValue() {
        //Arrange
        LocalTime time = LocalTime.of(0, 0, 0);
        //Act
        SunriseSunsetTimeValue sunriseSunsetTimeValue = new SunriseSunsetTimeValue(time);
        //Assert
        assertNotNull(sunriseSunsetTimeValue);
    }

    /**
     * See if the constructor throws an exception when the time is null.
     */
    @Test
    void shouldThrowExceptionWhenTimeIsNull() {
        //Arrange
        LocalTime time = null;
        String expectedMessage = "Time is required";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseSunsetTimeValue(time));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the toString method works.
     */
    @Test
    void shouldReturnSunsetTime() {
        //Arrange
        LocalTime time = LocalTime.of(5, 5, 20);
        SunriseSunsetTimeValue sunriseTimeValue = new SunriseSunsetTimeValue(time);
        String expected = "Sunrise Time: 05:05:20";
        //Act
        String actual = sunriseTimeValue.toString();
        //Assert
        assertEquals(expected, actual);
    }
}
