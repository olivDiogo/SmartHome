package smart_home.value_object;

import java.util.regex.Pattern;

public class PostalCodeUSImpl implements IPostalCode {

    private final String postalCode;

    public PostalCodeUSImpl(String postalCode) throws IllegalArgumentException {
        if (!validate(postalCode)) {
            throw new IllegalArgumentException("Invalid US postal code format");
        }
        this.postalCode = postalCode;
    }

    /**
     * Validates the postal code format.
     *
     * @param postalCode is the postal code.
     * @return true if the postal code is valid, false otherwise.
     */
    @Override
    public boolean validate(String postalCode) {
        // US postal code pattern: 5 digits, optional hyphen and 4 digits
        String pattern = "\\d{5}(-\\d{4})?";
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
        if (!(obj instanceof PostalCodeUSImpl postalCodeUS)) {
            return false;
        }
        return postalCode.equals(postalCodeUS.getCode());
    }

    @Override
    public int hashCode() {
        return postalCode.hashCode();
    }
}