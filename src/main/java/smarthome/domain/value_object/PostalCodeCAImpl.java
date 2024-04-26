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
        if (!(obj instanceof PostalCodeCAImpl postalCodeCA)) {
            return false;
        }
        return postalCode.equals(postalCodeCA.getCode());
    }

    @Override
    public int hashCode() {
        return postalCode.hashCode();
    }
}
