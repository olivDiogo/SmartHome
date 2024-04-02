package SmartHomeDDD.valueObject;

import java.util.regex.Pattern;

public class PostalCodePTImpl implements PostalCode{

    private final String postalCode;

    public PostalCodePTImpl(String postalCode) {
        validate(postalCode);
        this.postalCode = postalCode;
    }

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
}
