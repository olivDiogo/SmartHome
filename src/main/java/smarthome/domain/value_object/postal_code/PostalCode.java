package smarthome.domain.value_object.postal_code;

public class PostalCode implements IPostalCode {
  private String postalCode;

  public PostalCode(String postalCode, PostalCodePattern pattern) {
    if (!pattern.validate(postalCode)) {
      throw new IllegalArgumentException("Invalid postal code format");
    }
    this.postalCode = postalCode;
  }

  @Override
  public String getCode() {
    return postalCode;
  }
}

