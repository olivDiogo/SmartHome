package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.ValueObject;

public class Address implements ValueObject {

    private final String _street;
    private final String _doorNumber;
    private final String _countryCode; // ISO 3166-1 alpha-2 country code
    private final int COUNTRY_CODE_LENGTH = 2;
    private final IPostalCode _postalCode;

    public Address(String street, String doorNumber, String postalCode, String countryCode, PostalCodeFactory factory) {
        validateStreet(street);
        validateDoorNumber(doorNumber);
        validateCountryCode(countryCode);
        this._street = street;
        this._doorNumber = doorNumber;
        this._countryCode = countryCode;
        this._postalCode = factory.createPostalCode(postalCode, countryCode);
    }

    /**
     * Street validation method.
     *
     * @param street is the street of the address.
     */
    private void validateStreet(String street) {
        if (street == null ||
                street.trim().isEmpty() ||
                street.length() > 70 ||
                !street.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid street");
        }
    }

    /**
     * Door number validation method.
     *
     * @param doorNumber is the door number of the address.
     */

    private void validateDoorNumber(String doorNumber) {
        if (doorNumber == null ||
                doorNumber.trim().isEmpty() ||
                doorNumber.length() > 10 ||
                !doorNumber.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid door number");
        }
    }

    private void validateCountryCode(String countryCode) {
        if (countryCode == null ||
                countryCode.trim().isEmpty() ||
                countryCode.length() != COUNTRY_CODE_LENGTH ||
                !countryCode.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid country code");
        }
    }

    /**
     * Equals method for Address.
     *
     * @param object Object.
     * @return boolean.
     */
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof Address address) {

            return this._street.equals(address._street) &&
                    this._doorNumber.equals(address._doorNumber) &&
                    this._countryCode.equals(address._countryCode) &&
                    this._postalCode.equals(address._postalCode);
        }
        return false;
    }

    /**
     * Getter for street.
     *
     * @return _street.
     */
    public String getStreet() {
        return this._street;
    }

    /**
     * Getter for door number.
     *
     * @return _doorNumber.
     */
    public String getDoorNumber() {
        return this._doorNumber;
    }

    /**
     * Address object to string
     *
     * @return String
     */
    public String toString() {
        return this._street + ", " + this._doorNumber;
    }


}
