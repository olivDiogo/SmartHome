package SmartHome.sensors;

import SmartHome.domain.Value;

import java.time.LocalTime;

public class SunriseTimeValue implements Value, Cloneable {

    private LocalTime _value;

    public SunriseTimeValue(LocalTime value) {
        setValue(value);
    }
    private void setValue(LocalTime value) {
        if (value == null)
            throw new IllegalArgumentException("Time is required");
        else this._value = value;
    }

    public String toString() {
        int hours = this._value.getHour();
        int minutes = this._value.getMinute();
        int seconds = this._value.getSecond();
        return String.format("Sunrise Time: %02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public SunriseTimeValue clone(){
        try {
            return (SunriseTimeValue) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
