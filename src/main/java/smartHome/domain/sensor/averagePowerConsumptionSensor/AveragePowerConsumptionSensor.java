package smartHome.domain.sensor.averagePowerConsumptionSensor;

import smartHome.ddd.IValueObject;
import smartHome.domain.sensor.ISensor;
import smartHome.valueObject.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AveragePowerConsumptionSensor implements ISensor {
    /**
     * This class represents a PowerConsumptionSensor in the Smart Home System.
     * It includes details about the sensor's identification, type, and power consumption values.
     * It implements the ISensor interface with SensorID as its identifier.
     *
     */

    private final HashMap<LocalDateTime, Double> _powerConsumptions;
    private AveragePowerConsumptionSensorValue _averagePowerConsumptionSensorValue;
    private SensorTypeID _sensorTypeID;
    private SensorID _sensorID;

    private SensorName _sensorName;

    private DeviceID _deviceID;

    private ModelPath _modelPath;

    /**
     * @param deviceID     The device ID.
     * @param modelPath    The model path.
     * @param sensorTypeID The sensor type ID.
     * @param sensorName   The sensor name.
     */
    public AveragePowerConsumptionSensor(
            DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName)
            throws IllegalArgumentException {
        validateDeviceID(deviceID);
        validateSensorTypeID(sensorTypeID);
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        generateSensorID();
        _averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(0);
        _powerConsumptions = new HashMap<>();
    }

    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null.");
        } else {
            _deviceID = deviceID;
        }
    }

    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID cannot be null.");
        } else if (!sensorTypeID.getID().equals("AveragePowerConsumption")) {
            throw new IllegalArgumentException("SensorTypeID must be 'AveragePowerConsumption'.");
        } else {
            _sensorTypeID = sensorTypeID;
        }
    }

    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null.");
        } else {
            _modelPath = modelPath;
        }
    }

    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName cannot be null");
        } else {
            _sensorName = sensorName;
        }
    }

    private void generateSensorID() {
        _sensorID = new SensorID(UUID.randomUUID().toString());
    }

    /**
     * Sets the value of the PowerConsumptionSensor.
     *
     * @param readTime the time of the reading.
     * @param reading  the reading to be set.
     * @return a new HashMap with the power consumptions.
     * @throws IllegalArgumentException if there is already a reading for this time.
     */
    protected double addReading(LocalDateTime readTime, double reading) {
        if (_powerConsumptions.containsKey(readTime))
            throw new IllegalArgumentException("There is already a reading for this time");

        {
            _powerConsumptions.put(readTime, reading);
            return reading;
        }
    }

    /**
     * Gets the average value of the PowerConsumptionSensor.
     *
     * @param initialTime the initial time of the range.
     * @param finalTime   the final time of the range.
     * @return the average value of the PowerConsumptionSensor.
     * @throws IllegalArgumentException if the initial time is after the final time.
     * @throws IllegalArgumentException if the initial time is equal to the final time.
     */
    private double getAverageValue(LocalDateTime initialTime, LocalDateTime finalTime) {
        if (initialTime.isAfter(finalTime)) {
            throw new IllegalArgumentException("Initial time must be before final time");
        }
        // Calculate the average of the filtered power consumptions
        double average =
                filterPowerConsumptionsByTime(initialTime, finalTime).values().stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0);
        return average;
    }

    /**
     * Filters the power consumptions of the PowerConsumptionSensor by time.
     *
     * @param initialTime the initial time of the range.
     * @param finalTime   the final time of the range.
     * @return a new HashMap with the filtered power consumptions.
     */
    private Map<LocalDateTime, Double> filterPowerConsumptionsByTime(
            LocalDateTime initialTime, LocalDateTime finalTime) {
        // Filter the powerConsumptions map to only include entries within the specified time range
        Map<LocalDateTime, Double> filteredPowerConsumptions =
                _powerConsumptions.entrySet().stream()
                        .filter(
                                entry ->
                                        !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return filteredPowerConsumptions;
    }

    @Override
    public SensorID getID() {
        return _sensorID;
    }

    @Override
    public SensorName getName() {
        return _sensorName;
    }

    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Checks if this PowerConsumptionSensor instance is equal to another object.
     * Equality is based solely on the unique identifier of the sensor (_sensorID).
     * This method overrides the {@link Object#equals(Object)} method.
     *
     * @param o the object to be compared with this PowerConsumptionSensor instance for equality
     * @return true if the specified object is a PowerConsumptionSensor and has the same _sensorID as this sensor; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AveragePowerConsumptionSensor averagePowerConsumptionSensor){
            return _sensorID.equals(averagePowerConsumptionSensor._sensorID);
        }
        return false;
    }
    /**
     * Overrides the hashCode method to return the hash code of the _sensorID.
     * To keep the contract with the equals method, this method must be overridden.
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * Returns a string representation of this PowerConsumptionSensor instance.
     * The string includes the class name, along
     * * with the _sensorID, _sensorName, _deviceID, _modelPath, and _sensorTypeID properties.
     * This method overrides the {@link Object#toString()} method.
     *
     */
    public String toString() {
        return "PowerConsumptionSensor:"
                + " sensorID= "
                + _sensorID
                + ",  sensorName="
                + _sensorName
                + ",  deviceID="
                + _deviceID
                + ", modelPath="
                + _modelPath
                + ", sensorTypeID="
                + _sensorTypeID;
    }

    /**
     * Gets the value of the PowerConsumptionSensor with zero.
     *
     * @return the value of the PowerConsumptionSensor.
     */
    public IValueObject getValue() {
        return this._averagePowerConsumptionSensorValue;
    }

    /**
     * Gets the value of the PowerConsumptionSensor for a given period.
     *
     * @param initialTime the initial time of the range.
     * @param finalTime   the final time of the range.
     * @return the value of the PowerConsumptionSensor.
     */
    public IValueObject getValue(LocalDateTime initialTime, LocalDateTime finalTime) {
        double averageValue = getAverageValue(initialTime, finalTime);
        _averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(averageValue);
         return _averagePowerConsumptionSensorValue;
    }
}
