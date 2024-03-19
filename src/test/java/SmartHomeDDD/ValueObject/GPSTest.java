package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class GPSTest {

    @Test
    void  shouldReturnExpectedGPSWhenGivenValidParameters(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -8.61099;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldThrowExceptionWhenLatitudeIsInvalid(){
        //Arrange
        double latitude = 91;
        double longitude = -8.61099;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid latitude.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenLongitudeIsInvalid(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -181;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid longitude.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLatitudeHasMoreThanFiveDecimalNumbers(){
        //Arrange
        double latitude = 41.149611;
        double longitude = -8.61099;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid latitude.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLongitudeHasMoreThanFiveDecimalNumbers(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -8.610991;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid longitude.", exception.getMessage());
    }
}
