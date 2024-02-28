package SmartHome.sensors;

import SmartHome.domain.Value;

public class DewPointValue implements Value {
    public int _nValue;

    /**
     * Constructor of the class.
     * @param nValue The value of the dew point.
     */
    public DewPointValue(int nValue )
    {
        this._nValue = nValue;
    }

    /**
     * Gets the value of the dew point.
      * @return The value of the dew point.
     */
    public String toString()
    {
        return this._nValue + "";
    }

}
