package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

  /**
   * Test equals method with same postal code.
   */
  @Test
  void shouldReturnTrue_WhenPostalCodesAreEqual() {
    // Arrange
    String postalCode = "28039";
    PostalCodeESImpl postalCodeES1 = new PostalCodeESImpl(postalCode);
    PostalCodeESImpl postalCodeES2 = new PostalCodeESImpl(postalCode);
    // Act
    boolean result = postalCodeES1.equals(postalCodeES2);
    // Assert
    assertTrue(result);
  }

  /**
   * Test equals method with different postal code.
   */
  @Test
  void shouldReturnFalse_WhenPostalCodesAreDifferent() {
    // Arrange
    String postalCode1 = "28039";
    String postalCode2 = "28040";
    PostalCodeESImpl postalCodeES1 = new PostalCodeESImpl(postalCode1);
    PostalCodeESImpl postalCodeES2 = new PostalCodeESImpl(postalCode2);
    // Act
    boolean result = postalCodeES1.equals(postalCodeES2);
    // Assert
    assertFalse(result);
  }

  /**
   * Test equals method with same object.
   */
  @Test
  void shouldReturnTrue_WhenEqualsIsCalledWithSameObject() {
    // Arrange
    String postalCode = "28039";
    PostalCodeESImpl postalCodeES = new PostalCodeESImpl(postalCode);
    // Act
    boolean result = postalCodeES.equals(postalCodeES);
    // Assert
    assertTrue(result);
  }

  /**
   * Test equals method with different object.
   */
  @Test
  void shouldReturnFalse_WhenEqualsIsCalledWithDifferentObject() {
    // Arrange
    String postalCode1 = "28039";
    String postalCode2 = "28040";
    PostalCodeESImpl postalCodeES1 = new PostalCodeESImpl(postalCode1);
    PostalCodeESImpl postalCodeES2 = new PostalCodeESImpl(postalCode2);
    // Act
    boolean result = postalCodeES1.equals(postalCodeES2);
    // Assert
    assertFalse(result);
  }

  /**
   * Test equals method with different class.
   */
  @Test
  void shouldReturnFalse_WhenEqualsIsCalledWithDifferentClass() {
    // Arrange
    String postalCode = "28039";
    PostalCodeESImpl postalCodeES = new PostalCodeESImpl(postalCode);
    // Act
    boolean result = postalCodeES.equals(new Object());
    // Assert
    assertFalse(result);
  }

  /**
   * Test hashCode method.
   */
  @Test
  void shouldReturnHashCode_WhenHashCodeIsCalled() {
    // Arrange
    String postalCode = "28039";
    PostalCodeESImpl postalCodeES = new PostalCodeESImpl(postalCode);
    // Act
    int result = postalCodeES.hashCode();
    // Assert
    assertEquals(postalCode.hashCode(), result);
  }

}
