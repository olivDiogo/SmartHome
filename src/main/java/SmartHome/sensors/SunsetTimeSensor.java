package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.shredzone.commons.suncalc.SunTimes;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
/**
 * This class represents a sensor that measures the sunset time.
 * It implements the Sensor interface and provides methods for setting the sensor type,
 * configuring GPS location, and getting the sunset time.
 */
public class SunsetTimeSensor implements Sensor {
    private SensorType _sensorType;
    private double _latitude;
    private double _longitude;
    private SunsetTimeValue _sunsetTimeValue;
    /**
     * Constructor for the SunsetTimeSensor class.
     * @param catalogueSensor CatalogueSensor object.
     * @throws InstantiationException if the sensor type with description 'SunsetTime' does not exist or if the GPS configuration file is not found.
     */
    public SunsetTimeSensor(CatalogueSensor catalogueSensor) throws InstantiationException {
    setSensorType(catalogueSensor);
    configureGpsLocation();
    }
    /**
     * Method to set the sensor type.
     * @param catalogueSensor CatalogueSensor object.
     * @throws InstantiationException if the sensor type with description 'SunsetTime' does not exist.
     */
    private void setSensorType(CatalogueSensor catalogueSensor) throws InstantiationException {
        SensorType sensorType = catalogueSensor.getSensorType("SunsetTime");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'SunsetTime' does not exist.");
        else {
            this._sensorType = sensorType;
        }
    }
    /**
     * Method to configure GPS location.
     * @throws InstantiationException if the GPS configuration file is not found.
     */
    private void configureGpsLocation() throws InstantiationException {
        try {
            Configurations configs = new Configurations();
            Configuration config = configs.properties(new File("gps.configuration"));
            if(Objects.isNull(config))
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
     * Method to get the sunset time for a specific date.
     * @param date LocalDate object representing the date.
     * @return LocalTime object representing the sunset time.
     * @throws IllegalArgumentException if the sunset time is null.
     */
    LocalTime getSunsetTime(LocalDate date) throws IllegalArgumentException {
        SunTimes time = SunTimes.compute().on(date).at(_latitude, _longitude).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime();
        return sunset;
    }
    /**
     * Method to get the sensor type.
     * @return SensorType object.
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }
    /**
     * Method to get the value of the sensor for the current day.
     * @return Value object representing the sunset time for the current day.
     */
    //Default behavior will return sunset for current day
    public Value getValue() {
        LocalTime sunset = getSunsetTime(LocalDate.now());
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue;
    }
    /**
     * Method to get the value of the sensor for a specific date.
     * @param date LocalDate object representing the date.
     * @return Value object representing the sunset time for the specific date.
     */
    public Value getValue(LocalDate date) {
        LocalTime sunset = getSunsetTime(date);
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue;
    }
}
