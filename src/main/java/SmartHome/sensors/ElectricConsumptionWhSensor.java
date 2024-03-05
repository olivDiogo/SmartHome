package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class ElectricConsumptionWhSensor implements Sensor {
    private double _value;
    private SensorType _type;
    private LocalDateTime _startTimestamp;
    private LocalDateTime _endTimestamp;

    public ElectricConsumptionWhSensor(CatalogueSensor catalogue) throws InstantiationException {
        _type = setSensorType(catalogue);
    }

    public SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("ElectricConsumptionWh");
        if (sensorType == null) {
            throw new InstantiationException("SensorType with description 'ElectricConsumptionWh' does not exist.");
        } else {
            return sensorType;
        }
    }
    public Long setTimePeriodAndReturnDeltaInHours(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        if (startTimestamp.withNano(0).isEqual(endTimestamp.withNano(0)) || startTimestamp.isAfter(endTimestamp))
            throw new IllegalArgumentException("Start timestamp must be before end timestamp.");
        _startTimestamp = startTimestamp;
        _endTimestamp = endTimestamp;
        return Duration.between(_startTimestamp, _endTimestamp).toHours();
    }
    public double calculateConsumptionInWh (Map<LocalDateTime, Double> mapAllConsumption){
        //Calculate average within time stamps
        Map<LocalDateTime, Double> mapChosenTime = filterPowerConsumptionsByTime(_startTimestamp, _endTimestamp, mapAllConsumption);
        double consumptionWh = mapChosenTime.values().stream().mapToDouble(Double::doubleValue).sum();
        long timeDelta = Duration.between(_startTimestamp, _endTimestamp).toHours();
        consumptionWh = consumptionWh / timeDelta;
        _value = consumptionWh;
        return consumptionWh;
    }
    protected Map<LocalDateTime, Double> filterPowerConsumptionsByTime(LocalDateTime initialTime, LocalDateTime finalTime, Map<LocalDateTime, Double> mapAllConsumption) {
        // Filter the powerConsumptions map to only include entries within the specified time range
        Map<LocalDateTime, Double> filteredPowerConsumptions = mapAllConsumption.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return filteredPowerConsumptions;
    }
    public Value getValue(){
        return new ElectricConsumptionWhValue(_value);
    }
    public SensorType getSensorType() {
        return _type;
    }

}
