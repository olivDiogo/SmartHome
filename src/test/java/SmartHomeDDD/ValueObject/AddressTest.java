package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    void shouldReturnExpectedAddressWhenGivenValidParameters() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A";

        //Act
        Address address = new Address(street, doorNumber);

    }

    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        //Arrange
        String street = null;
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsEmpty() {
        //Arrange
        String street = "";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsInvalid() {
        //Arrange
        String street = "Isep Street__2";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetHasNewLine() {
        //Arrange
        String street = "\n";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStreetHasTab() {
        //Arrange
        String street = "\t";
        String doorNumber = "12 A";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid street", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDoorNumberIsNull() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = null;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDoorNumberIsEmpty() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDoorNumberIsInvalid() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "12 A__";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDoorNumberHasNewLine() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "\n";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDoorNumberHasTab() {
        //Arrange
        String street = "Isep Street 2";
        String doorNumber = "\t";

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Address(street, doorNumber));
        assertEquals("Invalid door number", exception.getMessage());
    }
}
