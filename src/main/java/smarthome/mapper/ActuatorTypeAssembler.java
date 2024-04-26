package smarthome.mapper;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorTypeDTO;

public class ActuatorTypeAssembler implements IAssembler<ActuatorType, ActuatorTypeDTO> {


  /**
   * Converts an ActuatorType domain entity to an ActuatorTypeDTO data transfer object.
   *
   * @param actuatorType is the domain entity to be converted.
   * @return The ActuatorTypeDTO data transfer object.
   */
  @Override
  public ActuatorTypeDTO domainToDTO(ActuatorType actuatorType) {
    Validator.validateNotNull(actuatorType, "Actuator Type");

    String actuatorTypeID = actuatorType.getID().getID();
    String actuatorTypeDescription = actuatorType.getActuatorTypeName().toString();
    String unit = actuatorType.getUnit().toString();

    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID, actuatorTypeDescription,
        unit);
    return actuatorTypeDTO;
  }


  /**
   * Converts a list of ActuatorType domain entities to a list of ActuatorTypeDTO data transfer
   * objects.
   *
   * @param actuatorTypes is the list of domain entities to be converted.
   * @return The list of ActuatorTypeDTO data transfer objects.
   */
  @Override
  public List<ActuatorTypeDTO> domainToDTO(List<ActuatorType> actuatorTypes) {
    if (actuatorTypes == null || actuatorTypes.isEmpty()) {
      throw new IllegalArgumentException("The list of ActuatorTypes cannot be null or empty.");
    }
    List<ActuatorTypeDTO> actuatorTypeDTOS = actuatorTypes.stream().map(this::domainToDTO).toList();
    return actuatorTypeDTOS;
  }
}
