package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeESImplTest {

    /**
     * Validate constructor with valid postal code.
     */
    @Test
    void shouldCreatePostalCode_WhenPostalCodeIsValid() {
        // Arrange
        String postalCode = "28039";
        // Act & Assert
        assertDoesNotThrow(() -> new PostalCodeESImpl(postalCode));
    }

    /**
     * Test getCode method.
     */
    @Test
    void shouldReturnPostalCode_WhenGetCodeIsCalled() {
        // Arrange
        String postalCode = "28039";
        PostalCodeESImpl postalCodeES = new PostalCodeESImpl(postalCode);
        // Act
        String result = postalCodeES.getCode();
        // Assert
        assertEquals(postalCode, result);
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnPostalCode_WhenToStringIsCalled() {
        // Arrange
        String postalCode = "28039";
        PostalCodeESImpl postalCodeES = new PostalCodeESImpl(postalCode);
        // Act
        String result = postalCodeES.toString();
        // Assert
        assertEquals(postalCode, result);
    }

    /**
     * Test validate method with valid postal code.
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIsValid() {
        // Arrange
        String postalCode = "28039";
        PostalCodeESImpl postalCodeES = new PostalCodeESImpl(postalCode);
        // Act
        boolean result = postalCodeES.validate(postalCode);
        // Assert
        assertTrue(result);
    }

    /**
     * Test validate method with invalid postal code.
     */
    @Test
    void shouldThrowException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "99039"; // Not a valid Spanish postal code prefix
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeESImpl(postalCode));
    }

    /**
     * Test constructor throws IllegalArgumentException for invalid postal code.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "00000"; // Not valid as it starts with 00
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeESImpl(postalCode));
    }
}
