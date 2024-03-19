package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

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
    void shouldReturnExpectedLatitudeWhenIsOnPositiveBoundary() {
        //Arrange
        double latitude = 90.0;
        double longitude = -8.0;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }
    @Test
    void shouldReturnExpectedLatitudeWhenIsOnNegativeBoundary() {
        //Arrange
        double latitude = -90.0;
        double longitude = -8.0;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldReturnExpectedLongitudeWhenIsOnPositiveBoundary() {
        //Arrange
        double latitude = 41.14961;
        double longitude = 180.0;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldReturnExpectedLongitudeWhenIsOnNegativeBoundary() {
        //Arrange
        double latitude = 41.14961;
        double longitude = -180.0;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }


    @Test
    void shouldReturnExpectedLongitudeLimitPositiveBoundary() {
        //Arrange
        double latitude = 41.14961;
        double longitude = 179.99999;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldReturnExpectedLongitudeLimitNegativeBoundary() {
        //Arrange
        double latitude = 41.14961;
        double longitude = -179.99999;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldReturnExpectedLatitudeLimitPositiveBoundary() {
        //Arrange
        double latitude = 89.99999;
        double longitude = -8.61099;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldReturnExpectedLatitudeLimitNegativeBoundary() {
        //Arrange
        double latitude = -89.99999;
        double longitude = -8.61099;

        //Act
        GPS gps = new GPS(latitude, longitude);
    }

    @Test
    void shouldThrowExceptionWhenLatitudeItsAboveBoundary(){
        //Arrange
        double latitude = 91.00001;
        double longitude = -8.61099;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid latitude.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenLatitudeItsBelowBoundary(){
        //Arrange
        double latitude = -90.00001;
        double longitude = -8.61099;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid latitude.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLongitudeItsAboveBoundary(){
        //Arrange
        double latitude = 41.14961;
        double longitude = 181.00001;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        assertEquals("Please enter a valid longitude.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLongitudeItsBelowBoundary(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -180.00001;

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

    @Test
    void shouldReturnTrueWhenComparingTwoEqualGPS(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -8.61099;
        GPS gps1 = new GPS(latitude, longitude);
        GPS gps2 = new GPS(latitude, longitude);

        //Act
        boolean isEquals = gps1.equals(gps2);

        //Assert
        assertTrue(isEquals);
    }

    @Test
    void shouldReturnFalseWhenComparingTwoDifferentGPS(){
        //Arrange
        double latitude1 = 41.14961;
        double longitude1 = -8.61099;
        double latitude2 = 41.14961;
        double longitude2 = -8.61098;
        GPS gps1 = new GPS(latitude1, longitude1);
        GPS gps2 = new GPS(latitude2, longitude2);

        //Act
        boolean isEquals = gps1.equals(gps2);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void shouldReturnFalseWhenComparingWithNull(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -8.61099;
        GPS gps = new GPS(latitude, longitude);

        //Act
        boolean isEquals = gps.equals(null);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void shouldReturnTrueWhenComparingWithItself(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -8.61099;
        GPS gps = new GPS(latitude, longitude);

        //Act
        boolean isEquals = gps.equals(gps);

        //Assert
        assertTrue(isEquals);
    }

    //test toString

    @Test
    void shouldReturnExpectedStringWhenCallingToString(){
        //Arrange
        double latitude = 41.14961;
        double longitude = -8.61099;

        GPS gps = new GPS(latitude, longitude);

        String expected = "GPS{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';

        //Act
        String result = gps.toString();

        //Assert
        assertEquals(expected, result);
    }



}
