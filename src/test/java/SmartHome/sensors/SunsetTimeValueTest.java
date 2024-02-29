package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SunsetTimeValueTest {
    @Test
    void shouldInstantiateSunsetTimeValue() {
        //Arrange
        int timeInSeconds = 50;
        //Act
        new SunsetTimeValue(timeInSeconds);
    }
    @Test
    void shouldThrowExceptionWhenTimeIsNegative() {
        //Arrange
        int timeInSeconds = -1;
        String expectedMessage = "Invalid time range";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeValue(timeInSeconds));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldThrowExceptionWhenTimeIsGreaterThan86400() {
        //Arrange
        int timeInSeconds = 86401;
        String expectedMessage = "Invalid time range";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeValue(timeInSeconds));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnTimeInHHMMSSFormat() {
        //Arrange
        int timeInSeconds = 3661;
        String expectedTime = "01:01:01";
        //Act
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(timeInSeconds);
        //Assert
        String actualTime = sunsetTimeValue.toString();
        assertEquals(expectedTime, actualTime);
    }
    @Test
    void shouldReturnTimeForMidnight() {
        //Arrange
        int timeInSeconds = 0;
        String expectedTime = "00:00:00";
        //Act
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(timeInSeconds);
        //Assert
        String actualTime = sunsetTimeValue.toString();
        assertEquals(expectedTime, actualTime);
    }
    @Test
    void shouldReturnTimeForMidday() {
        //Arrange
        int timeInSeconds = 43200;
        LocalTime expectedTime = LocalTime.of(12, 0, 0);
        //Act
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(timeInSeconds);
        //Assert
        LocalTime actualTime = sunsetTimeValue.toLocalTime();
        assertEquals(expectedTime, actualTime);
    }
    @Test
    void shouldReturnTimeForMidnightInLocalTime() {
        //Arrange
        int timeInSeconds = 0;
        LocalTime expectedTime = LocalTime.of(0, 0, 0);
        //Act
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(timeInSeconds);
        //Assert
        LocalTime actualTime = sunsetTimeValue.toLocalTime();
        assertEquals(expectedTime, actualTime);
    }


}