package SmartHome.controller;


import SmartHome.domain.House;
import SmartHome.domain.Location;
import SmartHome.domain.LocationFactory;
import SmartHome.domain.RoomFactory;
import SmartHome.dto.LocationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ConfigureHouseLocationControllerTest {

    @Test
    void seeIfConstructorWorks() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        // Act
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Assert
        assertNotNull(controller);
    }

    @Test
    void whenHouseIsNull_thenThrowsInstantiationException() {
        // Arrange
        House house = null;
        String expected = "Invalid arguments";
        // Act & Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new ConfigureHouseLocationController(house));
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expected));
    }

    @Test
    void defineHouseLocationSuccessfully() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = "City";
        int doorNumber = 1;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        Location location = house.configureLocation(street, zipCode, doorNumber, latitude, longitude);
        // Act
        LocationDTO result = controller.configureLocation(street, zipCode, doorNumber, latitude, longitude);
        // Assert
        assertEquals(result.toString(), location.toString());
    }


    @Test
    void defineHouseLocationWithNullStreetThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = null;
        String zipCode = "City";
        int doorNumber = 1;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test
    void defineHouseLocationWithEmptyStreetThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "";
        String zipCode = "City";
        int doorNumber = 1;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test
    void defineHouseLocationWithNullZipCodeThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = null;
        int doorNumber = 1;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test
    void defineHouseLocationWithEmptyZipCodeThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = "";
        int doorNumber = 1;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test
    void defineHouseLocationWithNegativeDoorNumberThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = "City";
        int doorNumber = -1;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test

    void defineHouseLocationWithZeroDoorNumberThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = "City";
        int doorNumber = 0;
        double latitude = 10.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test
    void defineHouseLocationWithInvalidLatitudeThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = "City";
        int doorNumber = 1;
        double latitude = 91.0;
        double longitude = 10.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

    @Test
    void defineHouseLocationWithInvalidLongitudeThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String street = "Street";
        String zipCode = "City";
        int doorNumber = 1;
        double latitude = 10.0;
        double longitude = -181.0;
        ConfigureHouseLocationController controller = new ConfigureHouseLocationController(house);
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.configureLocation(street, zipCode, doorNumber, latitude, longitude));
    }

}
