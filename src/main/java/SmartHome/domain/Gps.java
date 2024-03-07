package SmartHome.domain;

public class Gps {
    private double _latitude;
    private double _longitude;

    /**
     * Constructor for the Gps class.
     *
     * @param latitude  The latitude of the GPS coordinates.
     * @param longitude The longitude of the GPS coordinates.
     * @throws IllegalArgumentException if the latitude is not between -90 and 90 or if the longitude is not between -180 and 180.
     */
    public Gps(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
     * Sets the latitude of the GPS coordinates.
     *
     * @param latitude The latitude to set.
     * @throws IllegalArgumentException if the latitude is not between -90 and 90.
     */
    private void setLatitude(double latitude) throws IllegalArgumentException {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Please enter a valid latitude.");
        } else {
            this._latitude = latitude;
        }
    }

    /**
     * Sets the longitude of the GPS coordinates.
     *
     * @param longitude The longitude to set.
     * @throws IllegalArgumentException if the longitude is not between -180 and 180.
     */
    private void setLongitude(double longitude) throws IllegalArgumentException {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Please enter a valid longitude.");
        } else {
            this._longitude = longitude;
        }
    }

    /**
     * Gets the latitude of the GPS coordinates.
     *
     * @return The latitude of the GPS coordinates.
     */
    public double getLatitude() {
        return _latitude;
    }

    /**
     * Gets the longitude of the GPS coordinates.
     *
     * @return The longitude of the GPS coordinates.
     */
    public double getLongitude() {
        return _longitude;
    }

    /**
     * Returns a string representation of the Gps object.
     * The string includes the latitude and longitude of the GPS coordinates.
     *
     * @return A string representation of the Gps object.
     */
    @Override
    public String toString() {
        return "Gps{" +
                "latitude=" + _latitude +
                ", longitude=" + _longitude +
                '}';
    }
}
