package SmartHome.sensors;

import SmartHome.domain.Value;

import java.time.LocalTime;

public class SunsetTimeValue implements Value, Cloneable{
    private LocalTime _value;

    public SunsetTimeValue(LocalTime value) {
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
        return String.format("Sunset Time: %02d:%02d:%02d", hours, minutes, seconds);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        SunsetTimeValue sunsetTimeValue = (SunsetTimeValue) obj;
        return sunsetTimeValue._value.equals(this._value);
    }
    @Override
    public SunsetTimeValue clone() {
        try {
            return (SunsetTimeValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("SunsetTimeValue instance could not be cloned.");
        }
    }

}
