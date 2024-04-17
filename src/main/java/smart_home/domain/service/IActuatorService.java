package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.actuator.IActuator;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

public interface IActuatorService extends IService {
    /**
     * Adds a new actuator to the repository.
     *
     * @param deviceID
     * @param modelPath
     * @param actuatorTypeID
     * @param actuatorName
     * @return the actuator that was added.
     */
    IActuator addActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName);

    /**
     * Gets all actuators in the repository by the provided actuator ID.
     * @param actuatorID
     * @return the actuator with the provided actuator ID.
     */
    Optional<IActuator> getActuatorByID(ActuatorID actuatorID);

    /**
     * Gets all actuators in the repository.
     * @return a list of all actuators.
     */
    List<IActuator> getAllActuators();
}
