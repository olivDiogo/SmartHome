package smarthome.domain.value_object;

public interface IPostalCodeFactory {

  /**
   * Creates a PostalCode object based on the given postal code and country code.
   *
   * @param postalCode  The postal code value.
   * @param countryCode The country code to determine the implementation class.
   * @return A PostalCode object instantiated based on the provided postal code and country code.
   */
  IPostalCode createPostalCode(String postalCode, String countryCode);
}
