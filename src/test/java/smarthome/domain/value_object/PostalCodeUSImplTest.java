package smarthome.domain.value_object;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.PostalCodeUSImpl;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeUSImplTest {

    /**
     * Validate constructor with valid postal code in 5-digit format.
     */
    @Test
    void shouldCreatePostalCode_WhenPostalCodeIs5Digits() {
        // Arrange
        String postalCode = "12345";
        // Act & Assert
        assertDoesNotThrow(() -> new PostalCodeUSImpl(postalCode));
    }

    /**
     * Validate constructor with valid postal code in ZIP+4 format.
     */
    @Test
    void shouldCreatePostalCode_WhenPostalCodeIsZIPPlus4() {
        // Arrange
        String postalCode = "12345-6789";
        // Act & Assert
        assertDoesNotThrow(() -> new PostalCodeUSImpl(postalCode));
    }

    /**
     * Test getCode method.
     */
    @Test
    void shouldReturnPostalCode_WhenGetCodeIsCalled() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        // Act
        String result = postalCodeUS.getCode();
        // Assert
        assertEquals(postalCode, result);
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnPostalCode_WhenToStringIsCalled() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        // Act
        String result = postalCodeUS.toString();
        // Assert
        assertEquals(postalCode, result);
    }

    /**
     * Test validate method with valid postal code in 5-digit format.
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIs5Digits() {
        // Arrange
        String postalCode = "12345";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        // Act
        boolean result = postalCodeUS.validate(postalCode);
        // Assert
        assertTrue(result);
    }

    /**
     * Test validate method with valid postal code in ZIP+4 format.
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIsZIPPlus4() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        // Act
        boolean result = postalCodeUS.validate(postalCode);
        // Assert
        assertTrue(result);
    }

    /**
     * Test validate method with invalid postal code.
     */
    @Test
    void shouldThrowException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "1234";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeUSImpl(postalCode));
    }

    /**
     * Test validate method with another invalid postal code format.
     */
    @Test
    void shouldThrowException_WhenPostalCodeFormatIsInvalid() {
        // Arrange
        String postalCode = "abcd-1234";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeUSImpl(postalCode));
    }

    /**
     * Test equals method with same postal code.
     */
    @Test
    void shouldReturnTrue_WhenPostalCodeIsEqual() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        PostalCodeUSImpl postalCodeUS2 = new PostalCodeUSImpl(postalCode);
        // Act
        boolean result = postalCodeUS.equals(postalCodeUS2);
        // Assert
        assertTrue(result);
    }

    /**
     * Test equals method with different postal code.
     */
    @Test
    void shouldReturnFalse_WhenPostalCodeIsDifferent() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        PostalCodeUSImpl postalCodeUS2 = new PostalCodeUSImpl("98765-4321");
        // Act
        boolean result = postalCodeUS.equals(postalCodeUS2);
        // Assert
        assertFalse(result);
    }

    /**
     * Test equals method with same object.
     */
    @Test
    void shouldReturnTrue_WhenObjectIsTheSame() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        // Act
        boolean result = postalCodeUS.equals(postalCodeUS);
        // Assert
        assertTrue(result);
    }

    /**
     * Test equals method with different object.
     */
    @Test
    void shouldReturnFalse_WhenObjectIsDifferent() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        Object object = new Object();
        // Act
        boolean result = postalCodeUS.equals(object);
        // Assert
        assertFalse(result);
    }

    /**
     * Test hashCode method.
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeIsCalled() {
        // Arrange
        String postalCode = "12345-6789";
        PostalCodeUSImpl postalCodeUS = new PostalCodeUSImpl(postalCode);
        // Act
        int result = postalCodeUS.hashCode();
        // Assert
        assertEquals(postalCode.hashCode(), result);
    }
}
