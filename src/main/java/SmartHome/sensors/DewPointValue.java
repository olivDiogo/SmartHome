package SmartHome.sensors;

import SmartHome.domain.Value;

public class DewPointValue implements Value, Cloneable{
    private int _dewPointValue;


    /**
     * Constructor of the class.
     *
     * @param dewPointValue The value of the dew point.
     */
    public DewPointValue(int dewPointValue) {
        setValue(dewPointValue);
    }

    /**
     * Method to set the value of the dew point.
     *
     * @param dewPointValue The value of the dew point.
     */
    private void setValue(int dewPointValue) {
        this._dewPointValue = dewPointValue;
    }

    /**
     * Gets the value of the dew point.
     *
     * @return The value of the dew point.
     */
    public String toString() {
        return this._dewPointValue + "";
    }

    @Override
    public DewPointValue clone() {
        try {
            // Call the Object clone() method
            return (DewPointValue) super.clone();   // (DewPointValue) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new AssertionError();
        }
    }

}
