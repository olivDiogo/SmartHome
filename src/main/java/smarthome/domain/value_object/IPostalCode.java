package smarthome.domain.value_object;

public interface IPostalCode {

  boolean validate(String postalCode);

  String getCode();
}
