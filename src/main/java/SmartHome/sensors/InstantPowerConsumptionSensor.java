package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class InstantPowerConsumptionSensor implements Sensor {
    private SensorType _sensorType;
    private InstantPowerConsumptionValue _instantPowerConsumptionValue;

    /**
     * Constructor of the class. Creates a new instant power consumption sensor.
     *
     * @param catalogueSensor The catalogue of sensors.
     * @throws InstantiationException If the sensor type does not exist.
     */
    public InstantPowerConsumptionSensor(CatalogueSensor catalogueSensor) throws InstantiationException {
        setSensorType(catalogueSensor);
    }

    /**
     * Validates the sensor type. In addition, it sets the sensor type.
     *
     * @param catalogueSensor The catalogue of sensors.
     * @throws InstantiationException If the sensor type does not exist.
     */
    private void setSensorType(CatalogueSensor catalogueSensor) throws InstantiationException {
        SensorType sensorType = catalogueSensor.getSensorType("InstantPowerConsumption");

        if (sensorType == null) {
            throw new InstantiationException("SensorType with description 'Instant Power Consumption' does not exist.");
        }

        this._sensorType = sensorType;

    }


    /**
     * Gets the sensor type.
     *
     * @return The sensor type.
     */
    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the value of the sensor. In this case is returning a fixed value, until the service with real data is implemented.
     *
     * @return The value measured by the sensor.
     */
    @Override
    public Value getValue() {
        double instantPowerConsumptionValue = 25.0;

        this._instantPowerConsumptionValue = new InstantPowerConsumptionValue(instantPowerConsumptionValue);
        return this._instantPowerConsumptionValue.clone();
    }
}
