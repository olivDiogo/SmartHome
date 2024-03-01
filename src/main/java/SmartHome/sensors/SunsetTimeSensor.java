package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;

public class SunsetTimeSensor {
    private SensorType _sensorType;
    public SunsetTimeSensor(CatalogueSensor catalogueSensor) throws InstantiationException {
    setSensorType(catalogueSensor);
    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("SunsetTime");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'SunsetTime' does not exist.");
        else {
            this._sensorType = sensorType;
            return sensorType;
        }
    }
}
