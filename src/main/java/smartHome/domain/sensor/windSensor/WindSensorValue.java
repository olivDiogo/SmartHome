package smartHome.domain.sensor.windSensor;

import smartHome.ddd.IValueObject;

public class WindSensorValue implements IValueObject {
    private final double _speed;
    private final double _direction;

    /**
     * Constructor of the class.
     *
     * @param speed
     * @param direction
     */
    public WindSensorValue(double speed, double direction) {
        this._speed = speed;
        this._direction = direction;
    }

    /**
     * Method to get the value of the speed.
     *
     * @return
     */
    public double getSpeed() {
        return this._speed;
    }

    /**
     * Method to get the value of the direction.
     *
     * @return
     */
    public double getDirection() {
        return this._direction;
    }

    /**
     * Compares this WindSensorValue to another object.
     *
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WindSensorValue) {
            WindSensorValue other = (WindSensorValue) obj;
            return this._speed == other._speed && this._direction == other._direction;
        }
        return false;
    }

}
