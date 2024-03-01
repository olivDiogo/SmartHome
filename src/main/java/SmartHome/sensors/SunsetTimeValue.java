package SmartHome.sensors;

import SmartHome.domain.Value;

import java.time.LocalTime;

public class SunsetTimeValue implements Value {
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
}
