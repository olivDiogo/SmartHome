package SmartHome.sensors;

import SmartHome.domain.*;

public class SunsetTimeSensor {
    private SensorType _sensorType;
    private Gps _gps;
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
    public Gps configureGpsLocation(Gps gps) {
        if (gps == null)
            throw new IllegalArgumentException("GPS location is required");
        else {
            this._gps = gps;
            return gps;
        }
    }


}
