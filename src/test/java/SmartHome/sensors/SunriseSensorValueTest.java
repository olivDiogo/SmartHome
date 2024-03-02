package SmartHome.sensors;

import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class SunriseSensorValueTest {
    @Test
    void shouldInstantiateSunriseTimeValue() {
        //Arrange
        LocalTime time = LocalTime.of(0, 0, 0);
        //Act
        new SunsetTimeValue(time);
    }
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
