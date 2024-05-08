package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorTypeDTO;

@Component
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

    return new ActuatorTypeDTO(actuatorTypeID, actuatorTypeDescription,
        unit);
  }


  /**
   * Converts a list of ActuatorType domain entities to a list of ActuatorTypeDTO data transfer
   * objects.
   *
   * @param actuatorTypes is the list of domain entities to be converted.
   * @return The list of ActuatorTypeDTO data transfer objects.
   */
  @Override
  public List<ActuatorTypeDTO> domainToDTO(List<ActuatorType> actuatorTypes)
      throws EmptyReturnException {
    if (actuatorTypes == null) {
      throw new IllegalArgumentException("The list of ActuatorTypes cannot be null.");
    }
    if (actuatorTypes.isEmpty()) {
      throw new EmptyReturnException("The list of ActuatorTypes is empty.");
    }
    return actuatorTypes.stream().map(this::domainToDTO).toList();
  }
}
