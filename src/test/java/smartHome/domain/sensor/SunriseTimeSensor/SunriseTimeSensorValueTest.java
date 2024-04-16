package smartHome.domain.sensor.SunriseTimeSensor;

import org.junit.jupiter.api.Test;
import smartHome.domain.sensor.sunriseTimeSensor.SunriseTimeSensorValue;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SunriseTimeSensorValueTest {
    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateSunriseTimeValue() {
        //Arrange
        LocalTime time = LocalTime.of(0, 0, 0);
        //Act
        SunriseTimeSensorValue sunriseSunsetTimeValue = new SunriseTimeSensorValue(time);
        //Assert
        assertNotNull(sunriseSunsetTimeValue);
    }

    /**
     * See if the constructor throws an exception when the time is null.
     */
    @Test
    void shouldThrowException_WhenTimeIsNull() {
        //Arrange
        LocalTime time = null;
        String expectedMessage = "Time is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensorValue(time));

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
        SunriseTimeSensorValue sunriseTimeValue = new SunriseTimeSensorValue(time);
        String expected = "Sunrise Time: 05:05:20";
        //Act
        String actual = sunriseTimeValue.toString();
        //Assert
        assertEquals(expected, actual);
    }
    /**
     * Tests the equals method when the objects are the same.
     */
    @Test
    void shouldReturnTrue_WhenComparingSameObject() {
        //Arrange
        int hours = 5;
        int minutes = 5;
        int seconds = 20;

        LocalTime time = LocalTime.of(hours, minutes, seconds);
        SunriseTimeSensorValue sunriseTimeValue = new SunriseTimeSensorValue(time);

        //Act
        boolean result = sunriseTimeValue.equals(sunriseTimeValue);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests the equals method when the objects are different.
     */
    @Test
    void shouldReturnFalse_WhenComparingDifferentObject() {
        //Arrange
        int hours = 5;
        int minutes = 5;
        int seconds = 20;

        int hours2 = 6;
        int minutes2 = 6;
        int seconds2 = 21;

        LocalTime time = LocalTime.of(hours, minutes, seconds);
        LocalTime time2 = LocalTime.of(hours2, minutes2, seconds2);

        SunriseTimeSensorValue sunriseTimeValue = new SunriseTimeSensorValue(time);
        SunriseTimeSensorValue sunriseTimeValue2 = new SunriseTimeSensorValue(time2);

        //Act
        boolean result = sunriseTimeValue.equals(sunriseTimeValue2);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests the equals method when the object is null.
     */
    @Test
    void shouldReturnFalse_WhenComparingWithNull() {
        //Arrange
        int hours = 5;
        int minutes = 5;
        int seconds = 20;

        LocalTime time = LocalTime.of(hours, minutes, seconds);
        SunriseTimeSensorValue sunriseTimeValue = new SunriseTimeSensorValue(time);

        //Act
        boolean result = sunriseTimeValue.equals(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Test hashCode method.
     */
    @Test
    void shouldReturnHashCode() {
        //Arrange
        int hours = 5;
        int minutes = 5;
        int seconds = 20;

        LocalTime time = LocalTime.of(hours, minutes, seconds);
        SunriseTimeSensorValue sunriseTimeValue = new SunriseTimeSensorValue(time);

        int expected = time.hashCode();

        //Act
        int result = sunriseTimeValue.hashCode();

        //Assert
        assertEquals(expected, result);

    }
}
