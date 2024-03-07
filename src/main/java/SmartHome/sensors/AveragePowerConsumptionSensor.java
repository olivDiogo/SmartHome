package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class AveragePowerConsumptionSensor implements Sensor {
    private SensorType _sensorType;
    private HashMap<LocalDateTime, Double> _powerConsumptions;
    private AveragePowerConsumptionSensorValue _averagePowerConsumptionSensorValue;
    private final double _dValue = 0;

    /**
     * Creates a new PowerConsumptionSensor with a given catalogue.
     *
     * @param catalogue the catalogue to be set.
     * @throws InstantiationException if the SensorType with description 'Power Consumption' does not exist.
     */
    public AveragePowerConsumptionSensor(CatalogueSensor catalogue) throws InstantiationException {
        setSensorType(catalogue);
        _powerConsumptions = new HashMap<>();
    }

    /**
     * Sets the SensorType of the PowerConsumptionSensor.
     *
     * @param catalogue the catalogue to be set.
     * @return the SensorType with description 'Power Consumption'.
     * @throws InstantiationException if the SensorType with description 'Power Consumption' does not exist.
     */
    private void setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Power Consumption");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Power Consumption' does not exist.");
        else {
            _sensorType = sensorType;
        }
    }

    /**
     * Sets the power consumptions of the PowerConsumptionSensor.
     *
     * @return a new HashMap with the power consumptions.
     */
//    private HashMap<LocalDateTime, Double> setPowerConsumptions() {
//        return this._powerConsumptions = new HashMap<>();
//    }

    /**
     * Gets the SensorType of the PowerConsumptionSensor.
     *
     * @return the SensorType with description 'Power Consumption'.
     */
    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Sets the value of the PowerConsumptionSensor.
     *
     * @param readTime the time of the reading.
     * @param reading  the reading to be set.
     * @return a new HashMap with the power consumptions.
     * @throws IllegalArgumentException if there is already a reading for this time.
     */
    public double addReading(LocalDateTime readTime, double reading) {
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
    protected double getAverageValue(LocalDateTime initialTime, LocalDateTime finalTime) {
        if (initialTime.isAfter(finalTime)) {
            throw new IllegalArgumentException("Initial time must be before final time");
        }

        // Calculate the average of the filtered power consumptions
        double average = filterPowerConsumptionsByTime(initialTime, finalTime).values().stream()
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
    private Map<LocalDateTime, Double> filterPowerConsumptionsByTime(LocalDateTime initialTime, LocalDateTime finalTime) {
        // Filter the powerConsumptions map to only include entries within the specified time range
        Map<LocalDateTime, Double> filteredPowerConsumptions = _powerConsumptions.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return filteredPowerConsumptions;
    }

    /**
     * Gets the value of the PowerConsumptionSensor.
     *
     * @return the value of the PowerConsumptionSensor.
     */
    public Value getValue() {
        return this._averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(_dValue);
    }

    public Value getValue(LocalDateTime initialTime, LocalDateTime finalTime) {

        return this._averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(getAverageValue(initialTime, finalTime));
    }
}

