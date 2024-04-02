package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;

public class DewPointValue implements ValueObject {
    private int _DewPointValue;

    /**
     * Constructor of the class.
     *
     * @param dewPointValue The value of the dew point.
     */
    public DewPointValue(int dewPointValue) {
        if (dewPointValue < -100)
            throw new IllegalArgumentException("The value of the dew point cannot be lower than -100.");
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
}
