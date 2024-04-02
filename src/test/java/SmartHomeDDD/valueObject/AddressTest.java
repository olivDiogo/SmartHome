package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressTest {

    /**
     * Tests the correct instantiation of an Address
     */
    @Test
    void shouldReturnExpectedAddressWhenGivenValidParameters() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);

        //Act
        Address address = new Address(street, doorNumber, postalCode, countryCode, factory);

    }

    /**
     * Tests if the exception is thrown with a null street.
     */
    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        //Arrange
        String street = null;
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
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
        String postalCode = "4000-123";
        String countryCode = "PT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
        assertEquals("Invalid door number", exception.getMessage());
    }

    /**
     * Tests if the exception is thrown with a null country code.
     */
    @Test
    void shouldThrowExceptionWhenCountryCodeIsNull() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = null;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
    }

    /**
     * Tests if the exception is thrown with an empty country code.
     */
    @Test
    void shouldThrowExceptionWhenCountryCodeIsEmpty() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
    }

    /**
     * Tests if the exception is thrown with a country code with more than 2 characters.
     */
    @Test
    void shouldThrowExceptionWhenCountryCodeHasMoreThanTwoCharacters() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PTT";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
    }

    /**
     * Tests if the exception is thrown with a country code with less than 2 characters.
     */
    @Test
    void shouldThrowExceptionWhenCountryCodeHasLessThanTwoCharacters() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "P";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
    }

    /**
     * Tests if the exception is thrown with a country code that contains a number.
     */
    @Test
    void shouldThrowExceptionWhenCountryCodeContainsNumber() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "P1";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber, countryCode, postalCode, mock(PostalCodeFactory.class)));
    }

    /**
     * Tests if Address is equal to itself.
     */
    @Test
    void shouldReturnTrueEqualsWithSameObject() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);

        Address address = new Address(street, doorNumber, postalCode, countryCode, factory);

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
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        Address address = new Address(street, doorNumber, postalCode, countryCode, factory);

        //Act
        boolean isEquals = address.equals(null);

        //Assert
        assertFalse(isEquals);
    }

    /**
     * Tests if Address is equal to another Address with same street.
     */
    @Test
    void shouldReturnTrueEqualsWithSameStreet() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCodeValue = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        PostalCode postalCode = mock(PostalCode.class);
        when(factory.createPostalCode(anyString(), anyString())).thenReturn(postalCode); //TODO: this is the correct use
        Address address1 = new Address(street, doorNumber, postalCodeValue, countryCode, factory);
        Address address2 = new Address(street, doorNumber, postalCodeValue, countryCode, factory);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertTrue(isEquals);
    }

    /**
     * Tests if Address is not equal to another Address with different street.
     */
    @Test
    void shouldReturnFalseWithDifferentStreet() {
        //Arrange
        String street1 = "Isep Street 2";
        String street2 = "Isep Street 3";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        Address address1 = new Address(street1, doorNumber, postalCode, countryCode, factory);
        Address address2 = new Address(street2, doorNumber, postalCode, countryCode, factory);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertFalse(isEquals);
    }

    /**
     * Tests if Address is equal to another Address with same door number.
     */
    @Test
    void shouldReturnTrueEqualsWithSameDoorNumber() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCodeValue = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        PostalCode postalCode = mock(PostalCode.class);
        when(factory.createPostalCode(anyString(), anyString())).thenReturn(postalCode);
        Address address1 = new Address(street, doorNumber, postalCodeValue, countryCode, factory);
        Address address2 = new Address(street, doorNumber, postalCodeValue, countryCode, factory);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertTrue(isEquals);
    }

    /**
     * Tests if Address is not equal to another Address with different door number.
     */
    @Test
    void shouldReturnFalseWithDifferentDoorNumber() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber1 = "12 A";
        String doorNumber2 = "12 B";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        Address address1 = new Address(street, doorNumber1, postalCode, countryCode, factory);
        Address address2 = new Address(street, doorNumber2, postalCode, countryCode, factory);

        //Act
        boolean isEquals = address1.equals(address2);

        //Assert
        assertFalse(isEquals);
    }

    /**
     * Tests if the street is returned correctly.
     */
    @Test
    void shouldReturnStreet() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        Address address = new Address(street, doorNumber, postalCode, countryCode, factory);

        //Act
        String actualStreet = address.getStreet();

        //Assert
        assertEquals(street, actualStreet);
    }

    /**
     * Tests if the door number is returned correctly.
     */
    @Test
    void shouldReturnDoorNumber() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        Address address = new Address(street, doorNumber, postalCode, countryCode, factory);

        //Act
        String actualDoorNumber = address.getDoorNumber();

        //Assert
        assertEquals(doorNumber, actualDoorNumber);
    }

    /**
     * Tests if the address is returned correctly.
     */

    @Test
    void shouldReturnAddressToString() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";
        String postalCode = "4000-123";
        String countryCode = "PT";
        PostalCodeFactory factory = mock(PostalCodeFactory.class);
        Address address = new Address(street, doorNumber, postalCode, countryCode, factory);

        String expected = "Isep Street 2, 12 A";

        //Act
        String actualAddress = address.toString();

        //Assert
        assertEquals(street + ", " + doorNumber, actualAddress);
    }

}
