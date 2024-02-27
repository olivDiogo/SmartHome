package SmartHome.domain;

public class AddressFactory {
    public Address createAddress(String street, String zipCode, int doorNumber) {
        return new Address(street, zipCode, doorNumber);
    }
}
