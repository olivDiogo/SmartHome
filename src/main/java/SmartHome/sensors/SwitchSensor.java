package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

/**
 * Represents a switch sensor that can be either in an on or off state.
 * This sensor is part of the Smart Home system and interacts with the system's sensor catalogue.
 */
public class SwitchSensor implements Sensor {

    /**
     * The type of sensor as defined in the sensor catalogue.
     */
    private final SensorType _sensorType;

    /**
     * The current value/state of the switch sensor.
     */
    private SwitchSensorValue _value;

    /**
     * Constructs a SwitchSensor with a specific SensorType from the given catalogue.
     *
     * @param catalogue The catalogue from which to retrieve the SensorType for "Switch".
     * @throws InstantiationException if the SensorType for "Switch" does not exist in the catalogue.
     */
    public SwitchSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        this._value = new SwitchSensorValue(false); // Initializes the sensor as 'off'
    }

    /**
     * Retrieves and sets the sensor type from the catalogue based on the sensor's description.
     *
     * @param catalogue The catalogue from which to retrieve the sensor type.
     * @return The sensor type for "Switch".
     * @throws InstantiationException if the sensor type with the specified description does not exist.
     */
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Switch");
        if (sensorType == null) {
            throw new InstantiationException("SensorType with description 'Switch' does not exist.");
        } else {
            return sensorType;
        }
    }

    /**
     * Returns the sensor type of this SwitchSensor.
     *
     * @return The SensorType for switch.
     */
    @Override
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Returns the current value of the switch sensor.
     * The value indicates whether the switch is in an on or off state.
     *
     * @return A cloned value of the current state of the switch sensor.
     */
    @Override
    public Value getValue() {
        return this._value.clone();
    }
}
