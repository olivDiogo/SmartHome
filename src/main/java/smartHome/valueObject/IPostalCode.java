package smartHome.valueObject;

public interface IPostalCode {
    boolean validate(String postalCode);

    String getCode();
}
