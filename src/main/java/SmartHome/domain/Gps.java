package SmartHome.domain;

public class Gps {
    private double _latitude;
    private double _longitude;
    public Gps(double latitude, double longitude) {
        this._latitude = setLatitude(latitude);
        this._longitude = setLongitude(longitude);
    }
    private double setLatitude(double latitude) throws IllegalArgumentException {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Please enter a valid latitude.");
        } else {
            this._latitude = latitude;
        }
        return this._latitude;
    }
    private double setLongitude(double longitude) throws IllegalArgumentException {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Please enter a valid longitude.");
        } else {
            this._longitude = longitude;
        }
        return this._longitude;
    }

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    @Override
    public String toString() {
        return "Gps{" +
                "latitude=" + _latitude +
                ", longitude=" + _longitude +
                '}';
    }
}
