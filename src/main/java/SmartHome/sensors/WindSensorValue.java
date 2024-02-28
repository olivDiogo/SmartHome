package SmartHome.sensors;

import SmartHome.domain.Value;

public class WindSensorValue implements Value {

    public int _nValue;
    public String _dValue;

    public WindSensorValue(int nValue, String direction)
    {
        this._nValue = nValue;
        this._dValue = direction;
    }
    public int getSpeed() {
        return _nValue;
    }
    public String getDirection() {
        return _dValue;
    }
}
