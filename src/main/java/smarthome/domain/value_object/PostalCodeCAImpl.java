package smarthome.domain.value_object;

import java.util.regex.Pattern;

public class PostalCodeCAImpl implements IPostalCode {

  private final String postalCode;

  public PostalCodeCAImpl(String postalCode) {
    if (!validate(postalCode)) {
      throw new IllegalArgumentException("Invalid Canadian postal code format");
    }
    this.postalCode = postalCode.toUpperCase(); // Standardize to upper case
  }

  /**
   * Validate Canadian postal code format
   *
   * @param postalCode is the postal code.
   * @return true if the postal code is valid, false otherwise.
   */
  @Override
  public boolean validate(String postalCode) {
    // Canadian postal code pattern: Letter, digit, letter, optional space, digit, letter, digit
    String pattern = "^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$";
    return Pattern.matches(pattern, postalCode);
  }

  /**
   * Get the postal code.
   *
   * @return the postal code.
   */
  public String getCode() {
    return postalCode;
  }

  /**
   * Return the postal Code as a string.
   *
   * @return the postal code in string.
   */
  @Override
  public String toString() {
    return postalCode;
  }

  /**
   * Compare two postal codes.
   *
   * @param obj is the object to compare.
   * @return true if the postal codes are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PostalCodeCAImpl postalCodeCAImpl) {
      return postalCode.equals(postalCodeCAImpl.postalCode);
    }
    return false;
  }

  /**
   * Get the hash code of the postal code.
   *
   * @return the hash code.
   */
  @Override
  public int hashCode() {
    return postalCode.hashCode();
  }
}
