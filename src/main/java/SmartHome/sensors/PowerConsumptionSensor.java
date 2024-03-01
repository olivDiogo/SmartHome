package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PowerConsumptionSensor implements Sensor {
    private final SensorType _sensorType;
    private final Map<LocalDateTime, Double> powerConsumptions;
    private double result;


    public PowerConsumptionSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);
        this.powerConsumptions = setPowerConsumptions();
        this.result = setResult();

    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Power Consumption");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Power Consumption' does not exist.");
        else {
            return sensorType;
        }
    }

    private HashMap<LocalDateTime, Double> setPowerConsumptions() {
        return new HashMap<>();
    }

    private double setResult() {
        return result = 0;
    }

    public SensorType getSensorType() {
        return this._sensorType;
    }


    public Value getValue() {
        return new PowerConsumptionSensorValue(result).clone();
    }

    public Map<LocalDateTime, Double> setValue(LocalDateTime readTime, double reading) {
        if (powerConsumptions.containsKey(readTime))
            throw new IllegalArgumentException("There is already a reading for this time");
        if (reading < 0)
            throw new IllegalArgumentException("Value must be positive");
        else {
            powerConsumptions.put(readTime, reading);
            return powerConsumptions;
        }
    }


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

        this.result = average;

        return average;
    }

    public Map<LocalDateTime, Double> filterPowerConsumptionsByTime(LocalDateTime initialTime, LocalDateTime finalTime) {
        // Filter the powerConsumptions map to only include entries within the specified time range
        Map<LocalDateTime, Double> filteredPowerConsumptions = powerConsumptions.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return filteredPowerConsumptions;

    }


}

