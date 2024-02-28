package SmartHome.sensors;

import SmartHome.domain.Value;

public class WindSensorValue implements Value {

    public int _speed;
    public String _direction;

    public WindSensorValue(int speed, String direction)
    {
        this._speed = speed;
        this._direction = direction;
    }
}
