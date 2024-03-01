package SmartHome.sensors;

import SmartHome.domain.*;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SunsetTimeSensor implements Sensor {
    private SensorType _sensorType;
    private Gps _gps;
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

    public SensorType getSensorType() {
        return this._sensorType;
    }

    //Default behavior will return sunset for current day
    public Value getValue() {
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(this._gps.getLatitude(), this._gps.getLongitude()).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime();
        return new SunsetTimeValue(sunset);
    }



}
