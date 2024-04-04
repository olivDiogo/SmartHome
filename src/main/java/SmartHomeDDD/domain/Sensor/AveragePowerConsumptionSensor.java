package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AveragePowerConsumptionSensor implements Sensor {

  private HashMap<LocalDateTime, Double> _powerConsumptions;
  private AveragePowerConsumptionSensorValue _averagePowerConsumptionSensorValue;
  private final double _dValue = 0;
  private SensorTypeID _sensorTypeID;
  private SensorID _sensorID;

  private SensorName _sensorName;

  private DeviceID _deviceID;

  private ModelPath _modelPath;

  /** Creates a new PowerConsumptionSensor. */
  public AveragePowerConsumptionSensor(
      DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName)
      throws InstantiationException {
    validateDeviceID(deviceID);
    validateSensorTypeID(sensorTypeID);
    validateModelPath(modelPath);
    validateSensorName(sensorName);
    generateSensorID();
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
    } else if (!sensorTypeID.getId().equals("AveragePowerConsumption")) {
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
   * @param reading the reading to be set.
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
   * @param finalTime the final time of the range.
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
   * @param finalTime the final time of the range.
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

  public String toString() {
    return "PowerConsumptionSensor{"
        + "_sensorID="
        + _sensorID
        + ", _sensorName="
        + _sensorName
        + ", _deviceID="
        + _deviceID
        + ", _modelPath="
        + _modelPath
        + ", _sensorTypeID="
        + _sensorTypeID
        + '}';
  }

  /**
   * Gets the value of the PowerConsumptionSensor with zero.
   *
   * @return the value of the PowerConsumptionSensor.
   */
  public ValueObject getValue() {
    return this._averagePowerConsumptionSensorValue =
        new AveragePowerConsumptionSensorValue(_dValue);
  }

  /**
   * Gets the value of the PowerConsumptionSensor for a given period.
   *
   * @param initialTime the initial time of the range.
   * @param finalTime the final time of the range.
   * @return the value of the PowerConsumptionSensor.
   */
  public ValueObject getValue(LocalDateTime initialTime, LocalDateTime finalTime) {

    return this._averagePowerConsumptionSensorValue =
        new AveragePowerConsumptionSensorValue(getAverageValue(initialTime, finalTime));
  }
}
