package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {
    @Test
    void shouldReturnExpectedAddressWhenGivenValidParameters() {
        // Arrange
        String street = "Main Street";
        String zipCode = "12345";
        int doorNumber = 1;
        String expectedResult = "Address{street='Main Street', zipCode='12345', doorNumber=1}";
        // Act
        Address address = new Address(street, zipCode, doorNumber);
        // Assert
        assertEquals(expectedResult, address.toString());
    }
    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        // Arrange
        String street = null;
        String zipCode = "12345";
        int doorNumber = 1;

        // Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, zipCode, doorNumber));
        assertEquals("Please enter a valid street for the house.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenStreetIsEmpty() {
        // Arrange
        String street = "";
        String zipCode = "12345";
        int doorNumber = 1;

        // Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, zipCode, doorNumber));
        assertEquals("Please enter a valid street for the house.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenZipCodeIsNull() {
        // Arrange
        String street = "Main Street";
        String zipCode = null;
        int doorNumber = 1;

        // Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, zipCode, doorNumber));
        assertEquals("Please enter a valid zip code for the house.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenZipCodeIsEmpty() {
        // Arrange
        String street = "Main Street";
        String zipCode = "";
        int doorNumber = 1;

        // Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, zipCode, doorNumber));
        assertEquals("Please enter a valid zip code for the house.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDoorNumberIsZero() {
        // Arrange
        String street = "Main Street";
        String zipCode = "12345";
        int doorNumber = 0;

        // Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, zipCode, doorNumber));
        assertEquals("Please enter a valid door number for the house.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDoorNumberIsNegative() {
        // Arrange
        String street = "Main Street";
        String zipCode = "12345";
        int doorNumber = -1;

        // Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, zipCode, doorNumber));
        assertEquals("Please enter a valid door number for the house.", exception.getMessage());
    }
    @Test
    void shouldReturnExpectedStreetWhenGetStreetIsCalled() {
        // Arrange
        String street = "Main Street";
        String zipCode = "12345";
        int doorNumber = 1;
        Address address = new Address(street, zipCode, doorNumber);
        String expectedResult = "Main Street";
        // Act
        String actualResult = address.getStreet();
        // Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void shouldReturnExpectedZipCodeWhenGetZipCodeIsCalled() {
        // Arrange
        String street = "Main Street";
        String zipCode = "12345";
        int doorNumber = 1;
        Address address = new Address(street, zipCode, doorNumber);
        String expectedResult = "12345";
        // Act
        String actualResult = address.getZipCode();
        // Assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void shouldReturnExpectedDoorNumberWhenGetDoorNumberIsCalled() {
        // Arrange
        String street = "Main Street";
        String zipCode = "12345";
        int doorNumber = 1;
        Address address = new Address(street, zipCode, doorNumber);
        int expectedResult = 1;
        // Act
        int actualResult = address.getDoorNumber();
        // Assert
        assertEquals(expectedResult, actualResult);
    }


}