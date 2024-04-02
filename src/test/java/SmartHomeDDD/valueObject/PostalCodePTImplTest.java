package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodePTImplTest {

    /**
     * Validate constructor with valid postal code.
     */
    @Test
    void shouldCreatePostalCode_WhenPostalCodeIsValid() {
        // Arrange
        String postalCode = "1234-599";
        // Act
        new PostalCodePTImpl(postalCode);
    }

    /**
     * Test getCode method
     */
    @Test
    void shouldReturnPostalCode_WhenGetCodeIsCalled() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        // Act
        String result = postalCodePT.getCode();
        // Assert
        assertEquals(postalCode, result);
    }

    /**
     * Test toString method
     */
    @Test
    void shouldReturnPostalCode_WhenToStringIsCalled() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        // Act
        String result = postalCodePT.toString();
        // Assert
        assertEquals(postalCode, result);
    }

    /**
     * Test validate method with valid postal code.
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIsValid() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        // Act
        boolean result = postalCodePT.validate(postalCode);
        // Assert
        assertTrue(result);
    }

    /**
     * Test validate method with invalid postal code.
     */
    @Test
    void shouldReturnFalse_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "1234-59";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        // Act
        boolean result = postalCodePT.validate(postalCode);
        // Assert
        assertFalse(result);
    }
}