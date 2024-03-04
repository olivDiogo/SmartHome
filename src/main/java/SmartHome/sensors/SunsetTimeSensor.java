package SmartHome.sensors;

import SmartHome.domain.*;

import java.time.LocalTime;

public class SunsetTimeSensor implements Sensor {
    private SensorType _sensorType;
    private Gps _gps;
    private ISunTimesProviders _sunTimesProvider;
    private SunsetTimeValue _sunsetTimeValue;
    public SunsetTimeSensor(CatalogueSensor catalogueSensor) throws InstantiationException {
    setSensorType(catalogueSensor);
    }

    private void setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("SunsetTime");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'SunsetTime' does not exist.");
        else {
            this._sensorType = sensorType;
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
    public ISunTimesProviders configureSunTimeProvider(ISunTimesProviders sunTimesProvider) {
        if (sunTimesProvider == null)
            throw new IllegalArgumentException("SunTimesProvider is required");
        else {
            this._sunTimesProvider = sunTimesProvider;
            return sunTimesProvider;
        }
    }

    public SensorType getSensorType() {
        return this._sensorType;
    }

    //Default behavior will return sunset for current day
    public Value getValue() {
        LocalTime sunset = _sunTimesProvider.getSunsetTime(_gps.getLatitude(), _gps.getLongitude());
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue;
    }



}
