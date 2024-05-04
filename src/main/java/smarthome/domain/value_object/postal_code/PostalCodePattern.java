package smarthome.domain.value_object.postal_code;

import java.util.regex.Pattern;

public enum PostalCodePattern {
  US("\\d{5}(-\\d{4})?"),
  CA("^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$"),
  PT("\\d{4}-?\\d{3}"),
  ES("^(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}$");

  private final String pattern;

  PostalCodePattern(String pattern) {
    this.pattern = pattern;
  }

  public boolean validate(String postalCode) {
    return Pattern.matches(this.pattern, postalCode);
  }
}
