package SmartHome.sensors;
import SmartHome.domain.Value;

public class TemperatureSensorValue implements Value, Cloneable
{
    private int _nValue;

    public TemperatureSensorValue(int nValue )
    {
        this._nValue = nValue;
    }

    public TemperatureSensorValue clone()
    {
        try
        {
            // Call the Object clone() method
            return (TemperatureSensorValue) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // This should never happen since we are Cloneable
            throw new AssertionError();
        }
    }

    @Override
    public String toString()
    {
        return this._nValue + "";
    }
}
