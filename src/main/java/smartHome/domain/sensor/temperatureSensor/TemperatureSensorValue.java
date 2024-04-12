package smartHome.domain.sensor.temperatureSensor;

import smartHome.ddd.IValueObject;


public class TemperatureSensorValue implements IValueObject {
    /**
     * Represents the value of the temperature measured by the sensor.
     * This class is used to encapsulate the numeric value of the temperature measurement.
     */
    private double _dValue;

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
            this._dValue = dValue;
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
        return this._dValue + "";
    }
}
