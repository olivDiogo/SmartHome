package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class SolarIrradianceSensor implements Sensor, Cloneable {
    private SensorType _sensorType;
    private SolarIrradianceValue _value;

    /**
     * Constructor for SolarIrradianceSensor
     *
     * @param catalogue is the catalogue of sensors
     */
    public SolarIrradianceSensor(CatalogueSensor catalogue) {
        setSensorType(catalogue);
    }

    /**
     * Sets the sensor type
     *
     * @param catalogue is the catalogue of sensors
     */
    private void setSensorType(CatalogueSensor catalogue){
        SensorType sensorType = catalogue.getSensorType("SolarIrradiance");

        if (sensorType == null) {
            throw new IllegalArgumentException("SensorType with description 'SolarIrradiance' does not exist.");
        }

        this._sensorType = sensorType;
    }

    /**
     * Method to get the sensor type
     *
     * @return SensorType
     */
    public SensorType getSensorType() {
        return _sensorType;
    }

    /**
     * Method to get the value of the sensor
     *
     * @return Value
     */
    public SolarIrradianceValue getValue() {
        Random rand = new Random();
        double solarIrradiance = rand.nextDouble() * 1500;

        this._value = new SolarIrradianceValue(solarIrradiance);

        return this._value.clone();
    }


}
