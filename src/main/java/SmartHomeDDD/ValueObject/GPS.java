package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class GPS implements ValueObject {
    double _latitude;
    double _longitude;

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
        // Check if the decimal point exists in the string.
        // The indexOf method returns -1 if the decimal point is not found,
        // which can occur if the number is a whole number (no fractional part) or it's rounded to an integer.
        if (decimalPointIndex != -1) {
            int digitsAfterDecimal = latitudeStr.length() - decimalPointIndex - 1;
            return digitsAfterDecimal <= 5;
        }
        return true; // True if there's no decimal point

    }
    private boolean validLongitudeForFiveDecimalNumber(double longitude) {
        String longitudeStr = Double.toString(longitude);
        int decimalPointIndex = longitudeStr.indexOf('.');
        // Check if the decimal point exists in the string.
        // The indexOf method returns -1 if the decimal point is not found,
        // which can occur if the number is a whole number (no fractional part) or it's rounded to an integer.
        if (decimalPointIndex != -1) {
            int digitsAfterDecimal = longitudeStr.length() - decimalPointIndex - 1;
            return digitsAfterDecimal <= 5;
        }
        return true; // True if there's no decimal point
    }



}
