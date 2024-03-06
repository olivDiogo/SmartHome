package SmartHome.sensors;

import SmartHome.domain.Value;

/**
 * Represents a value of temperature measured by a {@link TemperatureSensor}.
 * This class encapsulates a temperature reading and supports cloning to ensure
 * that temperature values can be safely copied without altering the original measurement.
 */
public class TemperatureSensorValue implements Value {

    /**
     * The numeric value of the temperature measurement in degrees Celsius.
     */
    private double _nValue;

    /**
     * Constructs a new TemperatureSensorValue with the specified temperature measurement.
     *
     * @param nValue The numeric value of the temperature, in degrees Celsius.
     */
    public TemperatureSensorValue(double nValue) {
        validateValue(nValue);
    }

    /**
     * Validates the temperature value by ensuring it falls above or equal to -273.15 degrees Celsius.
     *
     * @param nValue The temperature value to validate.
     * @throws IllegalArgumentException if the value is not above -273.15.
     */

    private void validateValue(double nValue) throws IllegalArgumentException {
        if (nValue < -273.15) {
            throw new IllegalArgumentException("Temperature value must be above or equal to -273.15");
        } else {
            this._nValue = nValue;
        }
    }

    /**
     * Returns a string representation of the temperature measurement.
     * This method allows the temperature value to be easily printed or logged.
     *
     * @return A string representation of the temperature value, in degrees Celsius.
     */
    @Override
    public String toString()
    {
        return this._nValue + "";
    }
}

