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

public class SunsetTimeSensor implements Sensor {
    private SensorType _sensorType;
    private double _latitude;
    private double _longitude;
    private SunsetTimeValue _sunsetTimeValue;
    public SunsetTimeSensor(CatalogueSensor catalogueSensor) throws InstantiationException {
    setSensorType(catalogueSensor);
    configureGpsLocation();
    }

    private void setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("SunsetTime");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'SunsetTime' does not exist.");
        else {
            this._sensorType = sensorType;
        }
    }
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
    LocalTime getSunsetTime() throws IllegalArgumentException{
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(_latitude, _longitude).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime();
        return sunset;
    }
    LocalTime getSunsetTime(LocalDate date) throws IllegalArgumentException {
        SunTimes time = SunTimes.compute().on(date).at(_latitude, _longitude).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime();
        return sunset;
    }

    public SensorType getSensorType() {
        return this._sensorType;
    }

    //Default behavior will return sunset for current day
    public Value getValue() {
        LocalTime sunset = getSunsetTime();
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue;
    }
    public Value getValue(LocalDate date) {
        LocalTime sunset = getSunsetTime(date);
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue;
    }
}
