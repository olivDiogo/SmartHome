package smart_home.domain.sensor.sunset_time_sensor;


import smart_home.ddd.IValueObject;

import java.time.LocalTime;

public class SunsetTimeSensorValue implements IValueObject {

    private LocalTime _value;

    /**
     * Creates a new SunsetTimeValue with a given value.
     *
     * @param value the value to be set.
     */

    public SunsetTimeSensorValue(LocalTime value) {
        validateValue(value);
        this._value = value;
    }

    /**
     * Validates the value being passed in the constructor.
     */
    private void validateValue(LocalTime value) {
        if (value == null)
            throw new IllegalArgumentException("Time is required");
    }

    /**
     * Returns the value of the SunsetTimeValue as a String.
     */
    @Override
    public String toString() {
        int hours = this._value.getHour();
        int minutes = this._value.getMinute();
        int seconds = this._value.getSecond();
        return String.format("Sunset Time: %02d:%02d:%02d", hours, minutes, seconds);
    }


}