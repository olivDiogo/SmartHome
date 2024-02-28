package SmartHome.sensors;

import SmartHome.domain.Value;

public class PercentagePositionSensorValue implements Value {
    private double _percented;

    public PercentagePositionSensorValue(double percented) {
        this._percented = percented;
    }
    public double getPercented() {
        return _percented;
    }
}
