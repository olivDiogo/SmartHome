package SmartHomeDDD.valueObject;

public interface PostalCode {
    boolean validate(String postalCode);
    String getCode();
}
