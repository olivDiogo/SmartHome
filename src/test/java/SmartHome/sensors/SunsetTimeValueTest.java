package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SunsetTimeValueTest {
    @Test
    void shouldInstantiateSunsetTimeValue_WhenGivenValidData() {
        //Arrange
        LocalTime time = LocalTime.of(00, 0, 0);
        //Act
        new SunsetTimeValue(time);
    }
    @Test
    void shouldThrowException_WhenTimeIsNull() {
        //Arrange
        LocalTime time = null;
        String expectedMessage = "Time is required";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeValue(time));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldReturnSunsetTimeUpToMinutesInString_WhenCalledToString() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        String expected = "Sunset Time: 18:05:20";
        //Act
        String actual = sunsetTimeValue.toString();
        //Assert
        assertEquals(expected, actual);
    }

}