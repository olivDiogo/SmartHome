package SmartHomeDDD.valueObject;

public interface IPostalCode {
    boolean validate(String postalCode);

    String getCode();
}
