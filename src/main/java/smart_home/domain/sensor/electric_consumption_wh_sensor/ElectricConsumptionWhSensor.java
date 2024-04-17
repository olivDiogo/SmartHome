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
        this._deviceID = deviceID;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        validateModelPath(modelPath);
        this._modelPath = modelPath;
        validateSensorName(sensorName);
        this._sensorName = sensorName;
        validateDatePeriod(datePeriod);
        this._datePeriod = datePeriod;

        generateElectricConsumptionWhID();
    }

    public ElectricConsumptionWhSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, DatePeriod datePeriod, SensorID sensorID) {
        validateDeviceID(deviceID);
        this._deviceID = deviceID;
        validateSensorTypeID(sensorTypeID);
        this._sensorTypeID = sensorTypeID;
        validateModelPath(modelPath);
        this._modelPath = modelPath;
        validateSensorName(sensorName);
        this._sensorName = sensorName;
        validateDatePeriod(datePeriod);
        this._datePeriod = datePeriod;
        validateSensorID(sensorID);
        this._sensorID = sensorID;
    }

    /**
     * Generates a new ElectricConsumptionWhSensor ID.
     */

    private void generateElectricConsumptionWhID() {
        this._sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Validates the SensorID
     *
     * @param sensorID the ID of the sensor
     */
    private void validateSensorID(SensorID sensorID) {
        if (sensorID == null) {
            throw new IllegalArgumentException("SensorID is required");
        }
    }

    /**
     * Validates the given parameters.
     *
     * @param datePeriod the period during which the sensor measures consumption
     */
    private void validateDatePeriod(DatePeriod datePeriod) {
        if (datePeriod == null) {
            throw new IllegalArgumentException("DatePeriod is required");
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
