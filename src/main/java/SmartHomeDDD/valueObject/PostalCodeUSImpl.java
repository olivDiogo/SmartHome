package SmartHomeDDD.valueObject;

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
     * @param postalCode
     * @return
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
}