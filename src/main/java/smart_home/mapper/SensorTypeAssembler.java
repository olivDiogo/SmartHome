package smart_home.mapper;

import smart_home.ddd.IAssembler;
import smart_home.domain.sensor_type.SensorType;
import smart_home.dto.SensorTypeDTO;

import java.util.List;

public class SensorTypeAssembler implements IAssembler<SensorType, SensorTypeDTO> {

    /**
     * Converts a {@link SensorType} domain entity to a {@link SensorTypeDTO} data transfer object.
     *
     * @param sensorType is the domain entity to be converted.
     * @return a {@link SensorTypeDTO} data transfer object.
     */
    public SensorTypeDTO domainToDTO(SensorType sensorType) {
        if (sensorType == null)
            throw new IllegalArgumentException("Sensor type cannot be null.");

        String sensorTypeID = sensorType.getID().toString();
        String sensorTypeDescription = sensorType.getName().toString();
        String unit = sensorType.getUnit().toString();

        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID, sensorTypeDescription, unit);

        return sensorTypeDTO;
    }

    /**
     * Converts a list of {@link SensorType} domain entities to a list of {@link SensorTypeDTO} data transfer objects.
     *
     * @param sensorTypes is the list of domain entities to be converted.
     * @return a list of {@link SensorTypeDTO} data transfer objects.
     */
    public List<SensorTypeDTO> domainToDTO(List<SensorType> sensorTypes) {
        if (sensorTypes == null || sensorTypes.isEmpty())
            throw new IllegalArgumentException("The list of sensor types cannot be null or empty.");

        List<SensorTypeDTO> sensorTypesDTO = sensorTypes.stream().map(this::domainToDTO).toList();

        return sensorTypesDTO;
    }

}
