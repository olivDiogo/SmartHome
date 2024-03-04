package SmartHome.sensors;

import SmartHome.domain.Value;

public class WindSensorValue implements Value {

    public double _speed;
    public double _direction;

    public WindSensorValue(double speed, double direction)
    {
        this._speed = speed;
        this._direction = direction;
    }

    public double getSpeed () {
        return this._speed;
    }

    public double getDirection () {
        return this._direction;
    }

    @Override
    public WindSensorValue clone() {
        try {
            // Call the Object clone() method
            return (WindSensorValue) super.clone();   // (DewPointValue) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new AssertionError();
        }
    }
}
