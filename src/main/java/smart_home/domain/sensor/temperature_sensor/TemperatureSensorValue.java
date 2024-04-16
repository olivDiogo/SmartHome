package smart_home.domain.sensor.temperature_sensor;

import smart_home.ddd.IValueObject;


public class TemperatureSensorValue implements IValueObject {
    /**
     * Represents the value of the temperature measured by the sensor.
     * This class is used to encapsulate the numeric value of the temperature measurement.
     */
    private double _TemperatureValue;

    /**
     * Constructs a new TemperatureSensorValue with the specified temperature measurement.
     *
     * @param dValue The numeric value of the temperature, in degrees Celsius.
     */
    public TemperatureSensorValue(double dValue) {
        validateValue(dValue);
    }

    /**
     * Validates the temperature value by ensuring it falls above or equal to -273.15 degrees Celsius.
     *
     * @param dValue The temperature value to validate.
     * @throws IllegalArgumentException if the value is not above -273.15.
     */

    private void validateValue(double dValue) throws IllegalArgumentException {
        if (dValue < -273.15) {
            throw new IllegalArgumentException("Temperature value must be above or equal to -273.15");
        } else {
            this._TemperatureValue = dValue;
        }
    }

    /**
     * Returns a string representation of the temperature measurement.
     * This method allows the temperature value to be easily printed or logged.
     *
     * @return A string representation of the temperature value, in degrees Celsius.
     */
    @Override
    public String toString() {
        return this._TemperatureValue + "";
    }

    /**
     * Compares this TemperatureSensorValue to another object.
     * This method returns true if and only if the other object is a TemperatureSensorValue with the same value.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TemperatureSensorValue) {
            TemperatureSensorValue other = (TemperatureSensorValue) obj;
            return this._TemperatureValue == other._TemperatureValue;
        }
        return false;
    }

    /**
     * Returns the hash code of the temperature value.
     *
     * @return The hash code of the temperature value.
     */
    @Override
    public int hashCode() {
        return Double.hashCode(_TemperatureValue);
    }

}
