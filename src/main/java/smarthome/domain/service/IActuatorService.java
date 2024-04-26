package smarthome.domain.service;

import smarthome.ddd.IService;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.*;

import java.util.List;
import java.util.Optional;

public interface IActuatorService extends IService {
  /**
   * Adds a new actuator to the repository.
   *
   * @param parameters the parameters of the actuator.
   * @return the actuator that was added.
   */
  IActuator addActuator(Object... parameters);

  /**
   * Gets all actuators in the repository by the provided actuator ID.
   *
   * @param actuatorID
   * @return the actuator with the provided actuator ID.
   */
  Optional<IActuator> getActuatorByID(ActuatorID actuatorID);

  /**
   * Gets all actuators in the repository.
   *
   * @return a list of all actuators.
   */
  List<IActuator> getAllActuators();
}
