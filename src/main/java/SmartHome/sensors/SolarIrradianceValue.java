package SmartHome.sensors;

import SmartHome.domain.Value;

public class SolarIrradianceValue implements Value {
    private int _value;

    /**
     * Constructor for SolarIrradianceValue
     *
     * @param nValue is the value of the solar irradiance
     */
    public SolarIrradianceValue(int nValue) {
        this._value = nValue;
    }


    /**
     * Method to get the value of the solar irradiance
     *
     * @return double
     */
    public String toString() {
        return this._value + "";
    }


}


