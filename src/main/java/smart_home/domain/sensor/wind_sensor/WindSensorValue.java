package smart_home.domain.sensor.wind_sensor;

import smart_home.ddd.IValueObject;

public class WindSensorValue implements IValueObject {
    private final double speed;
    private final double direction;

    /**
     * Constructor of the class.
     *
     * @param speed The speed of the wind.
     * @param direction The direction of the wind.
     */
    public WindSensorValue(double speed, double direction) {
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Method to get the value of the speed.
     *
     * @return The speed of the wind.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Method to get the value of the direction.
     *
     * @return The direction of the wind.
     */
    public double getDirection() {
        return this.direction;
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
        if (obj instanceof WindSensorValue other) {
            return this.speed == other.speed && this.direction == other.direction;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(speed) + Double.hashCode(direction);
    }

}
