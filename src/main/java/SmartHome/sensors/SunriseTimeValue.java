package SmartHome.sensors;

import SmartHome.domain.Value;

import java.time.LocalTime;

public class SunriseTimeValue implements Value, Cloneable {

    private LocalTime _value;

    /**
     * Creates a new SunriseTimeValue with a given value.
     * @param value the value to be set.
     */

    public SunriseTimeValue(LocalTime value) {
        setValue(value);
    }

    /**
     * Gets the value of the SunriseTimeValue.
     * @return the value of the SunriseTimeValue.
     */
    private void setValue(LocalTime value) {
        if (value == null)
            throw new IllegalArgumentException("Time is required");
        else this._value = value;
    }

    /**
     * Clones the SunriseTimeValue.
     * @return a new SunriseTimeValue with the same value.
     */
    public String toString() {
        int hours = this._value.getHour();
        int minutes = this._value.getMinute();
        int seconds = this._value.getSecond();
        return String.format("Sunrise Time: %02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Clones the SunriseTimeValue.
     * @return a new SunriseTimeValue with the same value.
     */
    @Override
    public SunriseTimeValue clone(){
        try {
            return (SunriseTimeValue) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
