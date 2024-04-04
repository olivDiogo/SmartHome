package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeCAImplTest {

    /**
     * Validate constructor with valid postal code in uppercase.
     */
    @Test
    void shouldCreatePostalCode_WhenPostalCodeIsValidAndUppercase() {
        // Arrange
        String postalCode = "K1A 0B1";
        // Act & Assert
        assertDoesNotThrow(() -> new PostalCodeCAImpl(postalCode));
    }

    /**
     * Validate constructor with valid postal code in lowercase.
     */
    @Test
    void shouldCreatePostalCode_WhenPostalCodeIsValidAndLowercase() {
        // Arrange
        String postalCode = "k1a 0b1";
        // Act & Assert
        assertDoesNotThrow(() -> new PostalCodeCAImpl(postalCode));
    }

    /**
     * Test getCode method returns uppercase.
     */
    @Test
    void shouldReturnPostalCodeInUppercase_WhenGetCodeIsCalled() {
        // Arrange
        String postalCode = "k1a 0b1";
        PostalCodeCAImpl postalCodeCA = new PostalCodeCAImpl(postalCode);
        // Act
        String result = postalCodeCA.getCode();
        // Assert
        assertEquals(postalCode.toUpperCase(), result);
    }

    /**
     * Test toString method returns uppercase.
     */
    @Test
    void shouldReturnPostalCodeInUppercase_WhenToStringIsCalled() {
        // Arrange
        String postalCode = "k1a 0b1";
        PostalCodeCAImpl postalCodeCA = new PostalCodeCAImpl(postalCode);
        // Act
        String result = postalCodeCA.toString();
        // Assert
        assertEquals(postalCode.toUpperCase(), result);
    }

    /**
     * Test validate method with valid postal code.
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIsValid() {
        // Arrange
        String postalCode = "K1A 0B1";
        PostalCodeCAImpl postalCodeCA = new PostalCodeCAImpl(postalCode);
        // Act
        boolean result = postalCodeCA.validate(postalCode);
        // Assert
        assertTrue(result);
    }

    /**
     * Test validate method with invalid postal code.
     */
    @Test
    void shouldThrowException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "123 ABC";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeCAImpl(postalCode));
    }

    /**
     * Test constructor throws IllegalArgumentException for invalid postal code.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "ABC 123";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeCAImpl(postalCode));
    }
}
