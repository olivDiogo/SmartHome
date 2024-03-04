package SmartHome.sensors;

import SmartHome.domain.Value;

/**
 * Represents a value of humidity measured by a HumiditySensor.
 * This class implements the Value interface and is Cloneable.
 */
public class HumiditySensorValue implements Value, Cloneable {
    /**
     * The humidity value, expressed as a percentage from 0 to 100.
     */
    private double _dValue;

    /**
     * Constructs a HumiditySensorValue with a specified humidity percentage.
     *
     * @param dValue The humidity value as a percentage.
     * @throws IllegalArgumentException if the provided value is outside the range of 0 to 100.
     */
    public HumiditySensorValue(double dValue) {
        validateValue(dValue);
    }

    /**
     * Validates the humidity value by ensuring it falls within the acceptable range of 0 to 100.
     *
     * @param dValue The humidity value to validate.
     * @throws IllegalArgumentException if the value is not between 0 and 100.
     */
    private void validateValue(double dValue) throws IllegalArgumentException {
        if (dValue < 0 || dValue > 100) {
            throw new IllegalArgumentException("Humidity value must be between 0 and 100");
        } else {
            this._dValue = dValue;
        }
    }

    /**
     * Creates and returns a copy of this HumiditySensorValue object.
     *
     * @return A clone of this HumiditySensorValue object.
     */
    @Override
    public HumiditySensorValue clone() {
        try {
            // Call the Object clone() method
            return (HumiditySensorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    /**
     * Returns a string representation of the humidity value.
     *
     * @return The humidity value as a string.
     */
    public String toString()
    {
        return this._dValue + "";
    }
}
