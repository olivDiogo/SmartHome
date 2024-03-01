package SmartHome.sensors;


import SmartHome.domain.Value;

public class HumiditySensorValue implements Value, Cloneable
{
    private double _dValue;

    public HumiditySensorValue(double dValue )
    {
        this._dValue = dValue;
    }

    public HumiditySensorValue clone()
    {
        try
        {
            // Call the Object clone() method
            return (HumiditySensorValue) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    public String toString()
    {
        return this._dValue + "";
    }
}
