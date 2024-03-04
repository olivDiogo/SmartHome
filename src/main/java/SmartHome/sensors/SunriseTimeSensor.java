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

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     * @param catalogue the catalogue to be set.
     * @throws InstantiationException if the SensorType with description 'SunriseTime' does not exist.
     */

    public SunriseTimeSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);

    }

    /**
     * Sets the SensorType of the SunriseTimeSensor.
     * @param catalogue the catalogue to be set.
     * @return the SensorType with description 'SunriseTime'.
     * @throws InstantiationException if the SensorType with description 'SunriseTime' does not exist.
     */

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {

        SensorType sensorType = catalogue.getSensorType("SunriseTime");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'SunriseTime' does not exist.");
        else {
            return sensorType;
        }
    }

    /**
     * Configures the GPS location of the SunriseTimeSensor.
     * @param gps the GPS location to be set.
     * @return the GPS location.
     * @throws IllegalArgumentException if the GPS location is null.
     */

    public Gps configureGpsLocation(Gps gps) {
        if (gps == null)
            throw new IllegalArgumentException("GPS location is required");
        else {
            this._gps = gps;
            return gps;
        }
    }

    /**
     * Gets the SensorType of the SunriseTimeSensor.
     * @return the SensorType with description 'SunriseTime'.
     */

    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the value of the SunriseTimeSensor for the current day.
     * @return the value of the SunriseTimeSensor.
     */

    @Override
    public Value getValue() {
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(this._gps.getLatitude(), this._gps.getLongitude()).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return new SunriseTimeValue(sunrise).clone();
    }

    /**
     * Gets the value of the SunriseTimeSensor for a given date.
     * @param date the date to be used.
     * @return the value of the SunriseTimeSensor for a given date.
     */

    public Value getValue(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(this._gps.getLatitude(), this._gps.getLongitude()).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return new SunriseTimeValue(sunrise).clone();
    }

}


