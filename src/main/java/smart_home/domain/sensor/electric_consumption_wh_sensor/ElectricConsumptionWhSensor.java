package smart_home.domain.sensor.electric_consumption_wh_sensor;

import smart_home.domain.sensor.ISensor;
import smart_home.value_object.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a sensor that measures electric consumption in watt-hours.
 */
public class ElectricConsumptionWhSensor implements ISensor {
    /**
     * Constructs a new ElectricConsumptionWhSensor with the given parameters.
     *
     * @param deviceID the ID of the device
     * @param modelPath the model path of the sensor
     * @param sensorTypeID the type ID of the sensor
     * @param sensorName the name of the sensor
     * @param datePeriod the period during which the sensor measures consumption
     */
    private DeviceID _deviceID;
    private ModelPath _modelPath;
    private SensorTypeID _sensorTypeID;
    private ElectricConsumptionWhValue _electricConsumptionWhValue;
    private SensorName _sensorName;
    private SensorID _sensorID;
    private DatePeriod _datePeriod;

    /**
     * Constructs a new ElectricConsumptionWhSensor with the given parameters.
     *
     * @param deviceID     the ID of the device
     * @param modelPath    the model path of the sensor
     * @param sensorTypeID the type ID of the sensor
     * @param sensorName   the name of the sensor
     * @param datePeriod   the period during which the sensor measures consumption
     */

    public ElectricConsumptionWhSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, DatePeriod datePeriod) {
        validateDeviceID(deviceID);
        validateSensorTypeID(sensorTypeID);
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateDatePeriod(datePeriod);
        generateElectricConsumptionWhID();
    }

    /**
     * Generates a new ElectricConsumptionWhSensor ID.
     */

    private void generateElectricConsumptionWhID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the given parameters.
     *
     * @param datePeriod the period during which the sensor measures consumption
     */
    private void validateDatePeriod(DatePeriod datePeriod) {
        if (datePeriod == null) {
            throw new IllegalArgumentException("DatePeriod is required");
        } else {
            this._datePeriod = datePeriod;
        }
    }

    /**
     * Validates the given parameters.
     *
     * @param modelPath the model path of the sensor
     */
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath is required");
        } else {
            this._modelPath = modelPath;
        }
    }

    /**
     * Validates the given parameters.
     *
     * @param sensorName the name of the sensor
     */
    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName is required");
        } else {
            this._sensorName = sensorName;
        }
    }

    /**
     * Validates the given parameters.
     *
     * @param sensorTypeID the type ID of the sensor
     */
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID is required");
        } else if (!Objects.equals("ElectricConsumptionWh", sensorTypeID.getID())) {
            throw new IllegalArgumentException("SensorTypeID must be of type 'ElectricConsumptionWh'");
        } else {
            this._sensorTypeID = sensorTypeID;
        }
    }

    /**
     * Validates the given parameters.
     *
     * @param deviceID the ID of the device
     */
    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID is required");
        } else {
            this._deviceID = deviceID;
        }
    }

    /**
     * Returns the ID of the sensor.
     *
     * @return the ID of the sensor
     */
    @Override
    public SensorID getID() {
        return _sensorID;
    }

    /**
     * Returns the name of the sensor.
     *
     * @return the name of the sensor
     */
    @Override
    public SensorName getName() {
        return _sensorName;
    }

    /**
     * Returns the model path of the sensor.
     *
     * @return the model path of the sensor
     */
    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    /**
     * Returns the type ID of the sensor.
     *
     * @return the type ID of the sensor
     */
    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    /**
     * Returns the period during which the sensor measures consumption.
     *
     * @return the period during which the sensor measures consumption
     */
    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Returns the period during which the sensor measures consumption.
     *
     * @return the period during which the sensor measures consumption
     */
    @Override
    public ElectricConsumptionWhValue getValue() {
        int consumptionInWh = getConsumptionInWhForGivenPeriod();
        _electricConsumptionWhValue = new ElectricConsumptionWhValue(consumptionInWh);
        return _electricConsumptionWhValue;
    }

    /**
     * Returns the consumption in watt-hours for the given period.
     *
     * @return the consumption in watt-hours for the given period
     */
    private int getConsumptionInWhForGivenPeriod() {
        return _datePeriod.getDurationInMinutes() * 5;
    }

    /**
     * Method to compare two instances
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof ElectricConsumptionWhSensor electricConsumptionWhSensor) {
            return this._sensorID.equals(electricConsumptionWhSensor._sensorID);
        }
        return false;
    }

    /**
     * Method to get hash code
     *
     * @return
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * Returns the period during which the sensor measures consumption.
     *
     * @return the period during which the sensor measures consumption
     */
    @Override
    public String toString() {
        return _deviceID + " " + _modelPath + " " + _sensorTypeID + " " + _electricConsumptionWhValue + " " + _sensorName + " " + _sensorID + " " + _datePeriod;
    }

}
