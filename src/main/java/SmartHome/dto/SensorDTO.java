package SmartHome.dto;

import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;

import java.util.List;

public class SensorDTO {
    private final SensorType _sensorType;
    private List<String> _listStringClassesSensors;

    public SensorDTO(Sensor sensor) {

        this._sensorType= sensor.getSensorType();
        this._listStringClassesSensors = sensor.getSensorList();
    }
}
