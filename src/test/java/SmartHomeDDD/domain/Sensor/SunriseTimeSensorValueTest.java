package SmartHomeDDD.domain.Sensor;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SunriseTimeSensorValueTest {
    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateSunriseTimeValue() throws InstantiationException {
        //Arrange
        LocalTime time = LocalTime.of(0, 0, 0);
        //Act
        new SunriseTimeSensorValue(time);
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensorValue(time));
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
        SunriseTimeSensorValue sunriseTimeValue = new SunriseTimeSensorValue(time);
        String expected = "Sunrise Time: 05:05:20";
        //Act
        String actual = sunriseTimeValue.toString();
        //Assert
        assertEquals(expected, actual);
    }
}
