package SmartHome.sensors;

import SmartHome.domain.*;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.shredzone.commons.suncalc.SunTimes;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class SunriseTimeSensor implements Sensor {

    private final SensorType _sensorType;

    private double _latitude;
    private double _longitude;
    private SunriseTimeValue _sunriseTimeValue;

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     *
     * @param catalogue the catalogue to be set.
     * @throws InstantiationException if the SensorType with description 'SunriseTime' does not exist.
     */
    public SunriseTimeSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        configureGpsLocation();
    }

    /**
     * Sets the SensorType of the SunriseTimeSensor.
     *
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
     *
     * @throws InstantiationException if the gps.configuration file is not found or if something went wrong in reading the gps configuration.
     */
    private void configureGpsLocation() throws InstantiationException {
        try {
            Configurations configs = new Configurations();
            Configuration config = configs.properties(new File("gps.configuration"));
            if (Objects.isNull(config))
                throw new InstantiationException("gps.configuration file not found");
            else {
                _latitude = Double.parseDouble(config.getString("latitude"));
                _longitude = Double.parseDouble(config.getString("longitude"));
            }
        } catch (ConfigurationException exception) {
            throw new InstantiationException("something went wrong in reading the gps configuration: " + exception.getMessage());
        }
    }

    /**
     * Gets the SensorType of the SunriseTimeSensor.
     *
     * @return the SensorType with description 'SunriseTime'.
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the Sunrise Time of the GPS location for the current day.
     *
     * @return the Sunrise Time of the GPS location.
     */
    LocalTime getSunriseTime() {
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(_latitude, _longitude).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return sunrise;
    }

    /**
     * Gets the Sunrise Time of the GPS location for a given date.
     *
     * @param date the date to be used.
     * @return the Sunrise Time of the GPS location for a given date.
     */
    LocalTime getSunriseTime(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(_latitude, _longitude).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return sunrise;
    }

    /**
     * Gets the value of the SunriseTimeSensor for the current day.
     *
     * @return the value of the SunriseTimeSensor.
     */
    @Override
    public Value getValue() {
        LocalTime sunrise = getSunriseTime();
        this._sunriseTimeValue = new SunriseTimeValue(sunrise);
        return this._sunriseTimeValue;
    }

    /**
     * Gets the value of the SunriseTimeSensor for a given date.
     *
     * @param date the date to be used.
     * @return the value of the SunriseTimeSensor for a given date.
     */
    public Value getValue(LocalDate date) {
        LocalTime sunset = getSunriseTime(date);
        this._sunriseTimeValue = new SunriseTimeValue(sunset);
        return this._sunriseTimeValue;
    }

}


