package SmartHome.dto;

import SmartHome.domain.Sensor;

public class SensorAssembler {

    /**
     * Converts a Sensor domain object to a SensorDTO.
     *
     * @param sensor The Sensor domain object to be converted.
     * @return A SensorDTO object that represents the given Sensor.
     */
    public static SensorDTO domain2DTO(Sensor sensor) {
        return new SensorDTO(sensor);
    }
}
