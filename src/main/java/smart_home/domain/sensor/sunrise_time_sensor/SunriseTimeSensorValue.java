package smart_home.domain.sensor.sunrise_time_sensor;


import smart_home.ddd.IValueObject;

import java.time.LocalTime;

public class SunriseTimeSensorValue implements IValueObject {

    private LocalTime _value;

    /**
     * Creates a new SunriseTimeValue with a given value.
     *
     * @param value the value to be set.
     */

    public SunriseTimeSensorValue(LocalTime value) {
        setValue(value);
    }

    /**
     * Gets the value of the SunriseTimeValue.
     *
     * @return the value of the SunriseTimeValue.
     */
    private void setValue(LocalTime value) {
        if (value == null) {
            throw new IllegalArgumentException("Time is required");
        }
        else{ this._value = value;
        }
    }

    /**
     * Clones the SunriseTimeValue.
     *
     * @return a new SunriseTimeValue with the same value.
     */
    public String toString() {
        int hours = this._value.getHour();
        int minutes = this._value.getMinute();
        int seconds = this._value.getSecond();
        return String.format("Sunrise Time: %02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Equals method for SunriseTimeValue.
     */
    public boolean equals(Object obj) {
        if (obj instanceof SunriseTimeSensorValue sunriseTimeSensorValue) {
            return this._value.equals(sunriseTimeSensorValue._value);
        }
        return false;
    }

    /**
     * HashCode method for SunriseTimeValue.
     */
    public int hashCode() {
        return _value.hashCode();
    }

}
