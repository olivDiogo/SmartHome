package smart_home.domain.sensor.solar_irradiance_sensor;

import smart_home.ddd.IValueObject;

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
