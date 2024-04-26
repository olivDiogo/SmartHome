package smarthome.mapper;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor_type.SensorType;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorTypeDTO;

public class SensorTypeAssembler implements IAssembler<SensorType, SensorTypeDTO> {

  /**
   * Converts a {@link SensorType} domain entity to a {@link SensorTypeDTO} data transfer object.
   *
   * @param sensorType is the domain entity to be converted.
   * @return a {@link SensorTypeDTO} data transfer object.
   */
  public SensorTypeDTO domainToDTO(SensorType sensorType) {
    Validator.validateNotNull(sensorType, "Sensor Type");

    String sensorTypeID = sensorType.getID().toString();
    String sensorTypeDescription = sensorType.getName().toString();
    String unit = sensorType.getUnit().toString();

    SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID, sensorTypeDescription, unit);

    return sensorTypeDTO;
  }

  /**
   * Converts a list of {@link SensorType} domain entities to a list of {@link SensorTypeDTO} data
   * transfer objects.
   *
   * @param sensorTypes is the list of domain entities to be converted.
   * @return a list of {@link SensorTypeDTO} data transfer objects.
   */
  public List<SensorTypeDTO> domainToDTO(List<SensorType> sensorTypes) {
    if (sensorTypes == null || sensorTypes.isEmpty()) {
      throw new IllegalArgumentException("The list of sensor types cannot be null or empty.");
    }

    List<SensorTypeDTO> sensorTypesDTO = sensorTypes.stream().map(this::domainToDTO).toList();

    return sensorTypesDTO;
  }

}
