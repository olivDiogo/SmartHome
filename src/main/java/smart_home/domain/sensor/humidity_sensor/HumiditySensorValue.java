package smart_home.domain.sensor.humidity_sensor;

import smart_home.ddd.IValueObject;

public class HumiditySensorValue implements IValueObject {
    private int _iValue;

    /**
     * Constructs a HumiditySensorValue with a specified humidity percentage.
     *
     * @param iValue The humidity value as a percentage.
     * @throws IllegalArgumentException if the provided value is outside the range of 0 to 100.
     */
    public HumiditySensorValue(int iValue) {
        validateValue(iValue);
    }

    /**
     * Validates the humidity value by ensuring it falls within the acceptable range of 0 to 100.
     *
     * @param iValue The humidity value to validate.
     * @throws IllegalArgumentException if the value is not between 0 and 100.
     */
    private void validateValue(int iValue) throws IllegalArgumentException {
        if (iValue < 0 || iValue > 100) {
            throw new IllegalArgumentException("Humidity value must be between 0 and 100");
        } else {
            this._iValue = iValue;
        }
    }

    /**
     * Returns a string representation of the humidity value.
     *
     * @return The humidity value as a string.
     */
    public String toString() {
        return this._iValue + "";
    }
}
