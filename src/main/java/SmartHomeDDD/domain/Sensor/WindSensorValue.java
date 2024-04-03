package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;

public class WindSensorValue implements ValueObject {
    private double _speed;
    private double _direction;

    /**
     * Constructor of the class.
     * @param speed
     * @param direction
     */
    public WindSensorValue(double speed, double direction)
    {
        this._speed = speed;
        this._direction = direction;
    }

    /**
     * Method to get the value of the speed.
     * @return
     */
    public double getSpeed () {
        return this._speed;
    }

    /**
     * Method to get the value of the direction.
     * @return
     */
    public double getDirection () {
        return this._direction;
    }
}
