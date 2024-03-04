package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SunsetTimeValueTest {
    @Test
    void shouldInstantiateSunsetTimeValue() {
        //Arrange
        LocalTime time = LocalTime.of(00, 0, 0);
        //Act
        new SunsetTimeValue(time);
    }
    @Test
    void shouldThrowExceptionWhenTimeIsNull() {
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
    void shouldReturnSunsetTime() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        String expected = "Sunset Time: 18:05:20";
        //Act
        String actual = sunsetTimeValue.toString();
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    void cloneInstanceShouldReturnSameInformation() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        //Act
        SunsetTimeValue clonedSunsetTimeValue = sunsetTimeValue.clone();
        //Assert
        assertEquals(sunsetTimeValue.toString(), clonedSunsetTimeValue.toString());
    }
    @Test
    void comparingCloneShouldReturnTrue() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        //Act
        SunsetTimeValue clonedSunsetTimeValue = sunsetTimeValue.clone();
        //Assert
        assertEquals(sunsetTimeValue, clonedSunsetTimeValue);
    }
    @Test
    void clonedInstanceShouldPointToDifferentMemoryLocation() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        //Act
        SunsetTimeValue clonedSunsetTimeValue = sunsetTimeValue.clone();
        //Assert
        assertNotSame(sunsetTimeValue.hashCode(), clonedSunsetTimeValue.hashCode());
    }
    @Test
    void shouldReturnTrueIfSameObject() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        //Act
        boolean actual = sunsetTimeValue.equals(sunsetTimeValue);
        //Assert
        assertTrue(actual);
    }
    @Test
    void shouldReturnFalseWhenComparedToNull() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        //Act
        boolean actual = sunsetTimeValue.equals(null);
        //Assert
        assertFalse(actual);
    }
    @Test
    void shouldReturnFalseIfObjectsHoldDifferentInformation() {
        //Arrange
        LocalTime time = LocalTime.of(18, 5, 20);
        LocalTime secondTime = LocalTime.of(18, 5, 21);

        SunsetTimeValue sunsetTimeValue = new SunsetTimeValue(time);
        SunsetTimeValue sunsetTimeValue2 = new SunsetTimeValue(secondTime);
        //Act
        boolean actual = sunsetTimeValue.equals(sunsetTimeValue2);
        //Assert
        assertFalse(actual);
    }



}