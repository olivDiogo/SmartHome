package SmartHome.dto;

import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;

import java.util.List;

public class SensorDTO {
    private final SensorType _sensorType;

    public SensorDTO(Sensor sensor) {

        this._sensorType= sensor.getSensorType();
    }
}
