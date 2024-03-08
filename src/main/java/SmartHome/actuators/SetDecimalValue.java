package SmartHome.actuators;

import SmartHome.domain.Value;


public class SetDecimalValue implements Value, Cloneable {
    private double _nValue; // The decimal value
    private double _lowerLimit; // The lower limit of the value range
    private double _upperLimit; // The upper limit of the value range


    public SetDecimalValue(double nValue) {
        setValue(nValue);
    }

    public double setLowerLimit(double lowerLimit) {
        if (_nValue < setLowerLimit(lowerLimit)) {
            throw new IllegalArgumentException("Value cannot be less than the lower limit.");
        }
            this._lowerLimit = lowerLimit;
            return lowerLimit;

    }


    public double setUpperLimit(double upperLimit) {
        if (_nValue > setUpperLimit(upperLimit)) {
            throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
        }
        this._upperLimit = upperLimit;
        return upperLimit;
    }

    private void setValue(double nValue) {
            this._nValue = nValue;
    }


    @Override
    public SetDecimalValue clone() {
        try {
            return (SetDecimalValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return _nValue + "";
    }
}

