package smartHome.assembler;

import smartHome.ddd.IAssembler;
import smartHome.domain.actuator.IActuator;
import smartHome.dto.ActuatorDTO;

import java.util.List;

public class ActuatorAssembler implements IAssembler<IActuator, ActuatorDTO> {

    /**
     * Converts an Actuator domain entity to an ActuatorDTO data transfer object.
     *
     * @param actuator is the domain entity to be converted.
     * @return The ActuatorDTO data transfer object.
     */
    @Override
    public ActuatorDTO domainToDTO(IActuator actuator) {
        if (actuator == null) {
            throw new IllegalArgumentException("The Actuator cannot be null.");
        }

        String actuatorID = actuator.getID().getID();
        String actuatorName = actuator.getName().getActuatorName();
        String modelPath = actuator.getModelPath().toString();
        String actuatorTypeID = actuator.getActuatorTypeID().getID();
        String deviceID = actuator.getDeviceID().getID();

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorID, actuatorTypeID, actuatorName, modelPath, deviceID);
        return actuatorDTO;
    }

    /**
     * Converts a list of Actuator domain entities to a list of ActuatorDTO data transfer objects.
     *
     * @param actuators is the list of domain entities to be converted.
     * @return The list of ActuatorDTO data transfer objects.
     */
    @Override
    public List<ActuatorDTO> domainToDTO(List<IActuator> actuators) {
        if (actuators == null || actuators.isEmpty()) {
            throw new IllegalArgumentException("The list of Actuators cannot be null or empty.");
        }

        List<ActuatorDTO> actuatorDTOs = actuators.stream().map(this::domainToDTO).toList();
        return actuatorDTOs;
    }
}
