package SmartHome.domain;

public class LocationFactory {
    public Location createLocation(String street, String zipCode, int doorNumber, double latitude, double longitude) {
        return new Location(street, zipCode, doorNumber, latitude, longitude, new AddressFactory(), new GpsFactory());
    }
}
