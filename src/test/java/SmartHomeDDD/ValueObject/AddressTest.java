package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    /**
     * Tests the correct instantiation of an Address
     */
    @Test
    void shouldReturnExpectedAddressWhenGivenValidParameters() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";

        //Act
        Address address = new Address(street, doorNumber);

    }

    /**
     * Tests if the exception is thrown with a null street.
     */
    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        //Arrange
        String street = null;
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with an empty street.
     */
    @Test
    void shouldThrowExceptionWhenStreetIsEmpty() {
        //Arrange
        String street = "";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with an invalid street.
     */
    @Test
    void shouldThrowExceptionWhenStreetIsInvalid() {
        //Arrange
        String street = "Isep Street__2";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with a street that contains a new line.
     */
    @Test
    void shouldThrowExceptionWhenStreetHasNewLine() {
        //Arrange
        String street = "\n";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with a street that contains a tab.
     */
    @Test
    void shouldThrowExceptionWhenStreetHasTab() {
        //Arrange
        String street = "\t";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with a null door number.
     */
    @Test
    void shouldThrowExceptionWhenDoorNumberIsNull() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = null;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with an empty door number.
     */
    @Test
    void shouldThrowExceptionWhenDoorNumberIsEmpty() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with an invalid door number.
     */
    @Test
    void shouldThrowExceptionWhenDoorNumberIsInvalid() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A__";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with a door number that contains a new line.
     */
    @Test
    void shouldThrowExceptionWhenDoorNumberHasNewLine() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "\n";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with a door number that contains a tab.
     */
    @Test
    void shouldThrowExceptionWhenDoorNumberHasTab() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "\t";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    /**
     * Tests if Address is equal to itself.
     */
    @Test
    void shouldReturnTrueEqualsWithSameObject() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        Address address = new Address(street, doorNumber);

        //Act
        boolean isEquals = address.equals(address);

        //Assert
        assertTrue(isEquals);
    }

    /**
     * Tests if Address is not equal to null.
     */
    @Test
    void shouldReturnFalseEqualsWithNull() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        Address address = new Address(street, doorNumber);

        //Act
        boolean isEquals = address.equals(null);

        //Assert
        assertFalse(isEquals);
    }

    /**
     * Tests if Address is equal to another Address with same street.
     */
    @Test
    void shouldReturnTrueEqualsWithSameStreet(){
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        Address address1 = new Address(street, doorNumber);
        Address address2 = new Address(street, doorNumber);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertTrue(isEquals);
    }

    /**
     * Tests if Address is not equal to another Address with different street.
     */
    @Test
    void shouldReturnFalseWithDifferentStreet(){
        //Arrange
        String street1 = "Isep Street 2";
        String street2 = "Isep Street 3";
        String doorNumber = "12 A";
        Address address1 = new Address(street1, doorNumber);
        Address address2 = new Address(street2, doorNumber);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertFalse(isEquals);
    }

    /**
     * Tests if Address is equal to another Address with same door number.
     */
    @Test
    void shouldReturnTrueEqualsWithSameDoorNumber(){
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        Address address1 = new Address(street, doorNumber);
        Address address2 = new Address(street, doorNumber);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertTrue(isEquals);
    }

    /**
     * Tests if Address is not equal to another Address with different door number.
     */
    @Test
    void shouldReturnFalseWithDifferentDoorNumber(){
        //Arrange
        String street = "Isep Street 2";
        String doorNumber1 = "12 A";
        String doorNumber2 = "12 B";
        Address address1 = new Address(street, doorNumber1);
        Address address2 = new Address(street, doorNumber2);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertFalse(isEquals);
    }

    /**
     * Tests if the street is returned correctly.
     */
    @Test
    void shouldReturnStreet(){
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        Address address = new Address(street, doorNumber);

        //Act
        String actualStreet = address.getStreet();

        //Assert
        assertEquals(street, actualStreet);
    }

    /**
     * Tests if the door number is returned correctly.
     */
    @Test
    void shouldReturnDoorNumber(){
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        Address address = new Address(street, doorNumber);

        //Act
        String actualDoorNumber = address.getDoorNumber();

        //Assert
        assertEquals(doorNumber, actualDoorNumber);
    }


}
