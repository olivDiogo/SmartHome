package SmartHome.sensors;

import SmartHome.domain.*;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SunsetTimeSensor implements Sensor {
    private SensorType _sensorType;
    private double _latitude;
    private double _longitude;
    private ISunTimesProviders _sunTimesProvider;
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
        LocalTime sunset = _sunTimesProvider.getSunsetTime(_latitude, _longitude);
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue.clone();
    }
    public Value getValue(LocalDate date) {
        LocalTime sunset = _sunTimesProvider.getSunsetTime(_latitude, _longitude, date);
        this._sunsetTimeValue = new SunsetTimeValue(sunset);
        return this._sunsetTimeValue.clone();
    }
}
