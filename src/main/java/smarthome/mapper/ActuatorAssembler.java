package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorDTO;

@Component
public class ActuatorAssembler implements IAssembler<IActuator, ActuatorDTO> {

  /**
   * Converts an Actuator domain entity to an ActuatorDTO data transfer object.
   *
   * @param actuator is the domain entity to be converted.
   * @return The ActuatorDTO data transfer object.
   */
  @Override
  public ActuatorDTO domainToDTO(IActuator actuator) {
    Validator.validateNotNull(actuator, "Actuator");

    String actuatorID = actuator.getID().getID();
    String actuatorName = actuator.getName().getName();
    String modelPath = actuator.getModelPath().toString();
    String actuatorTypeID = actuator.getActuatorTypeID().getID();
    String deviceID = actuator.getDeviceID().getID();

    return new ActuatorDTO(actuatorID, actuatorTypeID, actuatorName, modelPath,
        deviceID);
  }

  /**
   * Converts a list of Actuator domain entities to a list of ActuatorDTO data transfer objects.
   *
   * @param actuators is the list of domain entities to be converted.
   * @return The list of ActuatorDTO data transfer objects.
   */
  @Override
  public List<ActuatorDTO> domainToDTO(List<IActuator> actuators) throws EmptyReturnException {
    if (actuators == null) {
      throw new IllegalArgumentException("The list of Actuators cannot be null.");
    }
    if (actuators.isEmpty()) {
      throw new EmptyReturnException("The list of Actuators is empty.");
    }
    return actuators.stream().map(this::domainToDTO).toList();
  }
}
