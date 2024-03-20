package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class GPS implements ValueObject {
    private double _latitude;
    private double _longitude;

    public GPS(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    private void setLatitude(double latitude) {
        if (!validLatitudeForFiveDecimalNumber(latitude) || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Please enter a valid latitude.");
        }
        this._latitude = latitude;
    }

    private void setLongitude(double longitude) {
        if (!validLongitudeForFiveDecimalNumber(longitude) || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Please enter a valid longitude.");
        }
        this._longitude = longitude;
    }

    private boolean validLatitudeForFiveDecimalNumber(double latitude) {
        String latitudeStr = Double.toString(latitude);
        int decimalPointIndex = latitudeStr.indexOf('.');
        // Count the digits after the decimal point
        int digitsAfterDecimal = latitudeStr.length() - decimalPointIndex - 1;
        // Check if the number of digits after the decimal is 5 or fewer.
        return digitsAfterDecimal <= 5;

    }

    private boolean validLongitudeForFiveDecimalNumber(double longitude) {
        String longitudeStr = Double.toString(longitude);
        int decimalPointIndex = longitudeStr.indexOf('.');
        // Count the digits after the decimal point
        int digitsAfterDecimal = longitudeStr.length() - decimalPointIndex - 1;
        // Check if the number of digits after the decimal is 5 or fewer.
        return digitsAfterDecimal <= 5;
    }

    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof GPS) {
            GPS gps = (GPS) object;

            if (this._latitude == gps._latitude && this._longitude == gps._longitude) return true;
        }
        return false;
    }
    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    @Override
    public String toString() {
        return "GPS{" +
                "latitude=" + _latitude +
                ", longitude=" + _longitude +
                '}';
    }


}
