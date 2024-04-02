package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostalCodeFactoryTest {

    /**
     * Test to ensure that a PostalCode object is successfully created for a supported country code.
     */
    @Test
    public void shouldCreatePostalCode_WhenCountryCodeIsSupported() {
        // Arrange
        String postalCode = "1234-599";
        String countryCode = "PT";
        PostalCodeFactory factory = new PostalCodeFactory();

        // Act
        PostalCode result = factory.createPostalCode(postalCode, countryCode);

        // Assert
        assertNotNull(result);
        assertEquals(postalCode, result.getCode());
    }

    /**
     * Test to ensure that IllegalArgumentException is thrown for an unsupported country code.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenCountryCodeIsUnsupported() {
        // Arrange
        String postalCode = "1234-566";
        String countryCode = "XX";
        PostalCodeFactory factory = new PostalCodeFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createPostalCode(postalCode, countryCode));
        assertThrows(RuntimeException.class, () -> factory.createPostalCode(postalCode, "InvalidCountryCode"));
    }

    /**
     * Test to ensure that RuntimeException is thrown for an invalid postal code.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid() {
        // Arrange
        String postalCode = "1234-65";
        String countryCode = "PT";
        PostalCodeFactory factory = new PostalCodeFactory();
        PostalCode postalCodeMock = mock(PostalCode.class);
        when(postalCodeMock.validate(postalCode)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> factory.createPostalCode(postalCode, countryCode));
    }

}
