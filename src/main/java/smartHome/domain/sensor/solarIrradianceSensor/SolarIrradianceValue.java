package smartHome.domain.sensor.solarIrradianceSensor;

import smartHome.ddd.IValueObject;

public class SolarIrradianceValue implements IValueObject {
    private final int _value;

    /**
     * Constructor for SolarIrradianceValue
     *
     * @param value
     */
    public SolarIrradianceValue(int value) {
        this._value = value;
    }

    /**
     * Returns the value of the solar irradiance sensor as a string.
     *
     * @return
     */
    @Override
    public String toString() {
        return _value + "";
    }
}
