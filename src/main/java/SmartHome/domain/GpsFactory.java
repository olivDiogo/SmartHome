package SmartHome.domain;

public class GpsFactory {
    /**
     * Creates a new Gps object with the given latitude and longitude.
     *
     * @param latitude  The latitude of the GPS coordinates.
     * @param longitude The longitude of the GPS coordinates.
     * @return A new Gps object with the specified latitude and longitude.
     */
    public Gps createGps(double latitude, double longitude) {
        return new Gps(latitude, longitude);
    }
}
