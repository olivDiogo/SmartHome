package smartHome.valueObject;

import java.util.regex.Pattern;

public class PostalCodePTImpl implements IPostalCode {

    private final String postalCode;

    public PostalCodePTImpl(String postalCode) {
        if (!validate(postalCode)) {
            throw new IllegalArgumentException("Invalid PT postal code format");
        }
        this.postalCode = postalCode;
    }

    /**
     * Validates the postal code format.
     *
     * @param postalCode
     * @return
     */
    @Override
    public boolean validate(String postalCode) {
        // Portuguese postal code pattern: Four digits, optional hyphen, three digits
        String pattern = "\\d{4}-?\\d{3}";
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
        if (!(obj instanceof PostalCodePTImpl postalCodePT)) {
            return false;
        }
        return postalCode.equals(postalCodePT.getCode());
    }

    @Override
    public int hashCode() {
        return postalCode.hashCode();
    }
}
