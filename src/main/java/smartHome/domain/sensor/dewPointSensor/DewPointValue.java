package smartHome.domain.sensor.dewPointSensor;

import smartHome.ddd.IValueObject;
import smartHome.domain.sensor.sunriseTimeSensor.SunriseTimeSensorValue;

public class DewPointValue implements IValueObject {
    private final int _DewPointValue;

    /**
     * Constructor of the class.
     *
     * @param dewPointValue The value of the dew point.
     */
    public DewPointValue(int dewPointValue) {
        if (dewPointValue < -70)
            throw new IllegalArgumentException("The value of the dew point cannot be lower than -70.");
        this._DewPointValue = dewPointValue;
    }

    /**
     * Gets the value of the dew point.
     *
     * @return The value of the dew point.
     */
    public String toString() {
        return this._DewPointValue + "";
    }


    /**
     * Equals method for DewPointValue.
     * @param obj The object to compare.
     */
    public boolean equals(Object obj) {
        if (obj instanceof DewPointValue dewPointValue) {
            return this._DewPointValue == dewPointValue._DewPointValue;
        }
        return false;

    }

    /**
     * HashCode method for DewPointValue.
     * @return The hash code.
     */

    public int hashCode() {
        return Integer.hashCode(_DewPointValue);
    }

}
