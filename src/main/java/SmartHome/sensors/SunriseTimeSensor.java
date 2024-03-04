package SmartHome.sensors;

import SmartHome.domain.*;
import SmartHome.sensors.SunriseTimeValue;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class SunriseTimeSensor implements Sensor {

    private final SensorType _sensorType;
    private Gps _gps;

    public SunriseTimeSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);

    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {

        SensorType sensorType = catalogue.getSensorType("SunriseTime");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'SunriseTime' does not exist.");
        else {
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

    public SensorType getSensorType() {
        return this._sensorType;
    }

    @Override
    public Value getValue() {
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(this._gps.getLatitude(), this._gps.getLongitude()).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return new SunriseTimeValue(sunrise).clone();
    }

    public Value getValue(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(this._gps.getLatitude(), this._gps.getLongitude()).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return new SunriseTimeValue(sunrise).clone();
    }

}


