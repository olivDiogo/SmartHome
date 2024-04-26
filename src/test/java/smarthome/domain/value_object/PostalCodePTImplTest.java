package smarthome.domain.value_object;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.PostalCodePTImpl;

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
        PostalCodePTImpl result = new PostalCodePTImpl(postalCode);

        // Assert
        assertNotNull(result);
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
     * Test validate method with invalid postal code should throw exception
     */
    @Test
    void shouldThrowException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "1234-65";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodePTImpl(postalCode));
    }

    /**
     * Test equals method with same postal code
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIsTheSame() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        PostalCodePTImpl postalCodePT2 = new PostalCodePTImpl(postalCode);
        // Act
        boolean result = postalCodePT.equals(postalCodePT2);
        // Assert
        assertTrue(result);
    }

    /**
     * Test equals method with different postal code
     */
    @Test
    void shouldReturnFalse_WhenPostalCodeIsDifferent() {
        // Arrange
        String postalCode = "1234-599";
        String postalCode2 = "1234-600";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        PostalCodePTImpl postalCodePT2 = new PostalCodePTImpl(postalCode2);
        // Act
        boolean result = postalCodePT.equals(postalCodePT2);
        // Assert
        assertFalse(result);
    }

    /**
     * Test equals method with same object
     */
    @Test
    void shouldReturnTrue_WhenObjectIsTheSame() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        // Act
        boolean result = postalCodePT.equals(postalCodePT);
        // Assert
        assertTrue(result);
    }

    /**
     * Test equals method with different object
     */
    @Test
    void shouldReturnFalse_WhenObjectIsDifferent() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        Object obj = new Object();
        // Act
        boolean result = postalCodePT.equals(obj);
        // Assert
        assertFalse(result);
    }

    /**
     * Test hashCode method
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeIsCalled() {
        // Arrange
        String postalCode = "1234-599";
        PostalCodePTImpl postalCodePT = new PostalCodePTImpl(postalCode);
        // Act
        int result = postalCodePT.hashCode();
        // Assert
        assertEquals(postalCode.hashCode(), result);
    }
}