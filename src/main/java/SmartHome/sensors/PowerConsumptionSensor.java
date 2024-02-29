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
    private final Map<LocalDateTime, Double> powerConsumptions = new HashMap<>();


    public PowerConsumptionSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue);

    }

    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Power Consumption");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Power Consumption' does not exist.");
        else {
            return sensorType;
        }
    }

    public SensorType getSensorType() {
        return this._sensorType;
    }


    public Value getValue() {
        Random rand = new Random();
        double dValue = rand.nextDouble(2000); // simulate power consumption between 0 and 2000W

        return new PowerConsumptionSensorValue(dValue);
    }

    public Map<LocalDateTime, Double> getValue(LocalDateTime readTime) {
        Random rand = new Random();
        double dValue = rand.nextDouble(2000); // simulate power consumption between 0 and 2000W
        powerConsumptions.put(readTime, dValue);
        return powerConsumptions;
    }

    public double getAverageValue(LocalDateTime initialTime, LocalDateTime finalTime) {
        if (initialTime.isAfter(finalTime)) {
            throw new IllegalArgumentException("Initial time must be before final time");
        }
                double sum = powerConsumptions.entrySet().stream() // get the sum of all power consumptions
                        .filter(entry -> !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime)) // filter by time
                        .mapToDouble(Map.Entry::getValue)// get the value
                        .sum();// sum all values

                return sum / powerConsumptions.size();
    }

//    public double getAverageValue(LocalDateTime initialTime, LocalDateTime finalTime) {
//    if (initialTime.isAfter(finalTime)) {
//        throw new IllegalArgumentException("Initial time must be before final time");
//    }
//
//    // Filter the powerConsumptions map to only include entries within the specified time range
//    Map<LocalDateTime, Double> filteredPowerConsumptions = powerConsumptions.entrySet().stream()
//            .filter(entry -> !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
//            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//    // Calculate the average of the filtered power consumptions
//    double average = filteredPowerConsumptions.values().stream()
//            .mapToDouble(Double::doubleValue)
//            .average()
//            .orElse(0);
//
//    return average;
//}
}
