package SmartHome.domain;

public class AddressFactory {
    /**
     * Create an address with the given street, zip code and door number.
     *
     * @param street     the street of the house.
     * @param zipCode    the zip code of the house.
     * @param doorNumber the door number of the house.
     * @return
     */
    public Address createAddress(String street, String zipCode, int doorNumber) {
        return new Address(street, zipCode, doorNumber);
    }
}
