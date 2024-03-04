package SmartHome.sensors;

import SmartHome.domain.Value;

/**
 * Represents a value of temperature measured by a {@link TemperatureSensor}.
 * This class encapsulates a temperature reading and supports cloning to ensure
 * that temperature values can be safely copied without altering the original measurement.
 */
public class TemperatureSensorValue implements Value, Cloneable {

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
        this._nValue = nValue;
    }

    /**
     * Creates and returns a copy of this TemperatureSensorValue object.
     * The clone method supports the cloning of TemperatureSensorValue instances,
     * allowing for the duplication of temperature values.
     *
     * @return A clone of this TemperatureSensorValue instance.
     * @throws AssertionError if the object cannot be cloned, which should never happen.
     */
    @Override
    public TemperatureSensorValue clone() {
        try {
            // Call the Object clone() method
            return (TemperatureSensorValue) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since we are Cloneable
            throw new AssertionError("TemperatureSensorValue instance could not be cloned.");
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
