package SmartHome.sensors;

import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SunriseSensorValueTest {
    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateSunriseTimeValue() {
        //Arrange
        LocalTime time = LocalTime.of(0, 0, 0);
        //Act
        new SunsetTimeValue(time);
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeValue(time));
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
        SunriseTimeValue sunriseTimeValue = new SunriseTimeValue(time);
        String expected = "Sunrise Time: 05:05:20";
        //Act
        String actual = sunriseTimeValue.toString();
        //Assert
        assertEquals(expected, actual);
    }

    /**
     * See if the clone method works.
     */
    @Test
    void shouldReturnClone(){
        //Arrange
        LocalTime time = LocalTime.of(5, 5, 20);
        SunriseTimeValue sunriseTimeValue = new SunriseTimeValue(time);
        String expected = "Sunrise Time: 05:05:20";
        //Act
        Value clonedResult =  sunriseTimeValue.clone();
        //Assert
        assertEquals(clonedResult.toString(),expected);
    }
}
