package smart_home.value_object;

public interface IPostalCode {
    boolean validate(String postalCode);

    String getCode();
}
