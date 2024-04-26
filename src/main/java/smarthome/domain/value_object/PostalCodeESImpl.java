package smarthome.domain.value_object;

import java.util.regex.Pattern;

public class PostalCodeESImpl implements IPostalCode {

  private final String postalCode;

  public PostalCodeESImpl(String postalCode) {
    if (!validate(postalCode)) {
      throw new IllegalArgumentException("Invalid Spanish postal code format");
    }
    this.postalCode = postalCode;
  }

  @Override
  public boolean validate(String postalCode) {
    // Spanish postal code pattern: 5 digits, starting from 01 to 52
    String pattern = "^(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}$";
    return Pattern.matches(pattern, postalCode);
  }

  public String getCode() {
    return postalCode;
  }

  @Override
  public String toString() {
    return postalCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof PostalCodeESImpl postalCodeES)) {
      return false;
    }
    return postalCode.equals(postalCodeES.getCode());
  }

  @Override
  public int hashCode() {
    return postalCode.hashCode();
  }
}
