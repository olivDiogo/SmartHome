package SmartHome.sensors;

import SmartHome.domain.Value;

public class SolarIrradianceValue implements Value, Cloneable {
    private int _iValue;

    /**
     * Constructor for SolarIrradianceValue
     *
     * @param nValue is the value of the solar irradiance
     */
    public SolarIrradianceValue(int nValue) {
        this._iValue = nValue;
    }


    /**
     * Method to get the value of the solar irradiance
     *
     * @return double
     */
    public String toString() {
        return this._iValue + "";
    }


}


