package SmartHomeDDD.valueObject;

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
     * @param postalCode
     * @return
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
}
