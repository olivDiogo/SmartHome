package smart_home.mapper;

import smart_home.ddd.IAssembler;
import smart_home.domain.sensor.ISensor;
import smart_home.dto.SensorDTO;

import java.util.List;

public class SensorAssembler implements IAssembler<ISensor, SensorDTO> {


    /**
     * Converts a domain entity to a DTO.
     *
     * @param sensor is the domain entity to be converted.
     * @return the DTO that was created.
     */
    @Override
    public SensorDTO domainToDTO(ISensor sensor) {
        if (sensor == null)
            throw new IllegalArgumentException("Sensor cannot be null.");

        String deviceID = sensor.getDeviceID().toString();
        String modelPath = sensor.getModelPath().toString();
        String sensorTypeID = sensor.getSensorTypeID().toString();
        String sensorID = sensor.getID().toString();
        String sensorName = sensor.getName().toString();

        SensorDTO sensorDTO = new SensorDTO(deviceID, modelPath, sensorTypeID, sensorID, sensorName);

        return sensorDTO;
    }

    /**
     * Converts a list of domain entities to a list of DTOs.
     *
     * @param sensors is the list of domain entities to be converted.
     * @return the list of DTOs that was created.
     */
    @Override
    public List<SensorDTO> domainToDTO(List<ISensor> sensors) {
        if (sensors == null || sensors.isEmpty())
            throw new IllegalArgumentException("The list of sensors cannot be null or empty");

        List<SensorDTO> sensorDTOList = sensors.stream().map(this::domainToDTO).toList();

        return sensorDTOList;
    }
}
