package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class SolarIrradianceSensor implements Sensor {
    private SensorType _sensorType;

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
        return this._sensorType;
    }

    /**
     * Method to get the value of the sensor
     *
     * @return
     */
    public Value getValue() {
        return null;
    }
}
