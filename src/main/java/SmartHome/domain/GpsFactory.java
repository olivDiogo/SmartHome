package SmartHome.domain;

public class GpsFactory {
    public Gps createGps(double latitude, double longitude) {
        return new Gps(latitude, longitude);
    }
}
