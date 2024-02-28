package SmartHome.sensors;

import SmartHome.domain.Value;

import java.time.LocalTime;

public class SunsetTimeValue implements Value {
    private int _value;

    public SunsetTimeValue(int value) {
        setValue(value);
    }

    private void setValue(int value) {
        if (value < 0 || value > 86400) {
            throw new IllegalArgumentException("Invalid time range");
        }
        else this._value = value;
    }

    public SunsetTimeValue clone() {
        return new SunsetTimeValue(this._value);
    }
    public LocalTime toLocalTime() {
        int hours = this._value / 3600;
        int minutes = (this._value % 3600) / 60;
        int seconds = this._value % 60;
        return LocalTime.of(hours, minutes, seconds);
    }

    public String toString() {
        int hours = this._value / 3600;
        int minutes = (this._value % 3600) / 60;
        int seconds = this._value % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
