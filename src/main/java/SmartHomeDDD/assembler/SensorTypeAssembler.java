package SmartHomeDDD.assembler;

import java.util.List;

import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.DTO.SensorTypeDTO;
import SmartHomeDDD.domain.SensorType.SensorType;

public class SensorTypeAssembler implements Assembler<SensorType, SensorTypeDTO>{

    /**
     * Converts a {@link SensorType} domain entity to a {@link SensorTypeDTO} data transfer object.
     *
     * @param sensorType is the domain entity to be converted.
     * @return a {@link SensorTypeDTO} data transfer object.
     */
    public SensorTypeDTO domainToDTO(SensorType sensorType){
        if (sensorType == null)
            throw new IllegalArgumentException("Sensor type cannot be null.");

        String sensorTypeDescription = sensorType.getID().toString();
        String unit = sensorType.getUnit().toString();

        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeDescription, unit);

        return sensorTypeDTO;
    }

    /**
     * Converts a list of {@link SensorType} domain entities to a list of {@link SensorTypeDTO} data transfer objects.
     *
     * @param sensorTypes is the list of domain entities to be converted.
     * @return a list of {@link SensorTypeDTO} data transfer objects.
     */
    public List<SensorTypeDTO> domainToDTO(List<SensorType> sensorTypes){
        if (sensorTypes == null || sensorTypes.isEmpty())
            throw new IllegalArgumentException("The list of sensor types cannot be null or empty.");

        List<SensorTypeDTO> sensorTypesDTO = sensorTypes.stream().map(this::domainToDTO).toList();

        return sensorTypesDTO;
    }

}
