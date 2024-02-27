package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GpsTest {
    @Test
    void seeIfConstructorWorks() {
        //Arrange
        double latitude = 10;
        double longitude = 10;
        //Act
        Gps gps = new Gps(latitude, longitude);
        String expectedResult = "Gps{latitude=10.0, longitude=10.0}";
        //Assert
        assertEquals(expectedResult,gps.toString());
    }


    @Test
    void setLatitudeAboveUpperLimit() {
        //Arrange
        double latitude = 100;
        double longitude = 10;
        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Gps(latitude, longitude));
        //Assert message
        assertEquals("Please enter a valid latitude.", thrown.getMessage());
    }
    @Test
    void setLatitudeBelowLowerLimit() {
        //Arrange
        double latitude = -100;
        double longitude = 10;
        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Gps(latitude, longitude));
        //Assert message
        assertEquals("Please enter a valid latitude.", thrown.getMessage());
    }
    @Test
    void setLatituteToUpperLimit() {
        //Arrange
        double latitude = 90;
        double longitude = 10;
        //Act
        Gps gps = new Gps(latitude, longitude);
        //Assert
        assertNotNull(gps);
    }
    @Test
    void setLatituteToLowerLimit() {
        //Arrange
        double latitude = -90;
        double longitude = 10;
        //Act
        Gps gps = new Gps(latitude, longitude);
        //Assert
        assertNotNull(gps);
    }
    @Test
    void setLongitudeAboveUpperLimit() {
        //Arrange
        double latitude = 10;
        double longitude = 200;
        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Gps(latitude, longitude));
        //Assert message
        assertEquals("Please enter a valid longitude.", thrown.getMessage());
    }
    @Test
    void setLongitudeBelowLowerLimit() {
        //Arrange
        double latitude = 10;
        double longitude = -200;
        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Gps(latitude, longitude));
        //Assert message
        assertEquals("Please enter a valid longitude.", thrown.getMessage());
    }
    @Test
    void setLongitudeToUpperLimit() {
        //Arrange
        double latitude = 10;
        double longitude = 180;
        //Act
        Gps gps = new Gps(latitude, longitude);
        //Assert
        assertNotNull(gps);
    }
    @Test
    void setLongitudeToLowerLimit() {
        //Arrange
        double latitude = 10;
        double longitude = -180;
        //Act
        Gps gps = new Gps(latitude, longitude);
        //Assert
        assertNotNull(gps);
    }
    @Test
    void testForGetLatitude() {
        //Arrange
        double latitude = 10;
        double longitude = 10;
        Gps gps = new Gps(latitude, longitude);
        //Act
        double result = gps.getLatitude();
        //Assert
        assertEquals(10, result);
    }
    @Test
    void testForgetLongitude() {
        //Arrange
        double latitude = 10;
        double longitude = 10;
        Gps gps = new Gps(latitude, longitude);
        //Act
        double result = gps.getLongitude();
        //Assert
        assertEquals(10, result);
    }

}