package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AveragePowerConsumptionSensor implements Sensor {
    private final SensorType _sensorType;
    private final Map<LocalDateTime, Double> powerConsumptions;
    private double averageResult;

    private PowerConsumptionSensorValue powerConsumptionSensorValue;

    /**
     * Creates a new PowerConsumptionSensor with a given catalogue.
     * @param catalogue the catalogue to be set.
     * @throws InstantiationException if the SensorType with description 'Power Consumption' does not exist.
     */

    public AveragePowerConsumptionSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        this.powerConsumptions = setPowerConsumptions();
        this.averageResult = setResult();

    }

    /**
     * Sets the SensorType of the PowerConsumptionSensor.
     * @param catalogue the catalogue to be set.
     * @return the SensorType with description 'Power Consumption'.
     * @throws InstantiationException if the SensorType with description 'Power Consumption' does not exist.
     */

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Power Consumption");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Power Consumption' does not exist.");
        else {
            return sensorType;
        }
    }

    /**
     * Sets the power consumptions of the PowerConsumptionSensor.
     * @return a new HashMap with the power consumptions.
     */

    private HashMap<LocalDateTime, Double> setPowerConsumptions() {
        return new HashMap<>();
    }

    /**
     * Sets the average result of the PowerConsumptionSensor.
     * @return the average result.
     */

    private double setResult() {
        return averageResult = 0;
    }

    /**
     * Gets the SensorType of the PowerConsumptionSensor.
     * @return the SensorType with description 'Power Consumption'.
     */

    public SensorType getSensorType() {
        return this._sensorType;
    }

    /**
     * Gets the value of the PowerConsumptionSensor.
     * @return the value of the PowerConsumptionSensor.
     */

    public Value getValue() {
        return this.powerConsumptionSensorValue = new PowerConsumptionSensorValue(averageResult).clone();
    }

    /**
     * Sets the value of the PowerConsumptionSensor.
     * @param readTime the time of the reading.
     * @param reading the reading to be set.
     * @return a new HashMap with the power consumptions.
     * @throws IllegalArgumentException if there is already a reading for this time.
     */

    public Map<LocalDateTime, Double> setValue(LocalDateTime readTime, double reading) {
        if (powerConsumptions.containsKey(readTime))
            throw new IllegalArgumentException("There is already a reading for this time");

        else {
            powerConsumptions.put(readTime, reading);
            return powerConsumptions;
        }
    }

    /**
     * Gets the average value of the PowerConsumptionSensor.
     * @param initialTime the initial time of the range.
     * @param finalTime the final time of the range.
     * @return the average value of the PowerConsumptionSensor.
     * @throws IllegalArgumentException if the initial time is after the final time.
     * @throws IllegalArgumentException if the initial time is equal to the final time.
     */

    public double getAverageValue(LocalDateTime initialTime, LocalDateTime finalTime) {
        if (initialTime.isAfter(finalTime)) {
            throw new IllegalArgumentException("Initial time must be before final time");
        }
        if (initialTime.equals(finalTime)) {
            throw new IllegalArgumentException("Initial time cannot be equal to final time");
        }

        // Calculate the average of the filtered power consumptions
        double average = filterPowerConsumptionsByTime(initialTime, finalTime).values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        this.averageResult = average;

        return average;
    }

    /**
     * Filters the power consumptions of the PowerConsumptionSensor by time.
     * @param initialTime the initial time of the range.
     * @param finalTime the final time of the range.
     * @return a new HashMap with the filtered power consumptions.
     */

    public Map<LocalDateTime, Double> filterPowerConsumptionsByTime(LocalDateTime initialTime, LocalDateTime finalTime) {
        // Filter the powerConsumptions map to only include entries within the specified time range
        Map<LocalDateTime, Double> filteredPowerConsumptions = powerConsumptions.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return filteredPowerConsumptions;

    }


}

