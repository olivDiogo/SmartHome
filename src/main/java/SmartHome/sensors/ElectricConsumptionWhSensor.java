package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.time.Duration;
import java.time.LocalDateTime;

public class ElectricConsumptionWhSensor {
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
        if (startTimestamp.isAfter(endTimestamp))
            throw new IllegalArgumentException("Start timestamp must be before end timestamp.");
        _startTimestamp = startTimestamp;
        _endTimestamp = endTimestamp;
        return Duration.between(_startTimestamp, _endTimestamp).toHours();
    }
    public SensorType getSensorType() {
        return _type;
    }

}
