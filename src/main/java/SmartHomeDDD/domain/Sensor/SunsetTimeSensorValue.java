package SmartHomeDDD.domain.Sensor;


import SmartHomeDDD.ddd.ValueObject;

import java.time.LocalTime;

public class SunsetTimeSensorValue implements ValueObject {

    private LocalTime _value;

    /**
     * Creates a new SunsetTimeValue with a given value.
     *
     * @param value the value to be set.
     */

    public SunsetTimeSensorValue(LocalTime value) {
        setValue(value);
    }

    /**
     * Gets the value of the SunsetTimeValue.
     *
     * @return the value of the SunsetTimeValue.
     */
    private void setValue(LocalTime value) {
        if (value == null)
            throw new IllegalArgumentException("Time is required");
        else this._value = value;
    }

    /**
     * Clones the SunsetTimeValue.
     *
     * @return a new SunsetTimeValue with the same value.
     */
    public String toString() {
        int hours = this._value.getHour();
        int minutes = this._value.getMinute();
        int seconds = this._value.getSecond();
        return String.format("Sunset Time: %02d:%02d:%02d", hours, minutes, seconds);
    }


}