package SmartHome.domain;

public class LocationFactory {

    /**
     * Creates a new Location object with the given street, zip code, door number, latitude, and longitude.
     *
     * @param street     The street of the location.
     * @param zipCode    The zip code of the location.
     * @param doorNumber The door number of the location.
     * @param latitude   The latitude of the location.
     * @param longitude  The longitude of the location.
     * @return A new Location object with the specified street, zip code, door number, latitude, and longitude.
     */
    public Location createLocation(String street, String zipCode, int doorNumber, double latitude, double longitude) {
        return new Location(street, zipCode, doorNumber, latitude, longitude, new AddressFactory(), new GpsFactory());
    }
}
