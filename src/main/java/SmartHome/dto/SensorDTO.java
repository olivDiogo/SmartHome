package SmartHome.dto;

import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;

import java.util.List;

public class SensorDTO {
    public final SensorType _sensorType;

    /**
     * Constructor for the SensorDTO class.
     *
     * @param sensor The Sensor object from which to create the DTO.
     */
    public SensorDTO(Sensor sensor) {

        this._sensorType= sensor.getSensorType();
    }
}
