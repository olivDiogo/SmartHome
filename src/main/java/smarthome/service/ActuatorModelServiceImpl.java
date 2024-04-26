package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.service.IActuatorModelService;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;

/**
 * Service class for managing actuator models within the smart home domain. This includes loading
 * default actuator models from a configuration file, as well as providing access to these models.
 */
public class ActuatorModelServiceImpl implements IActuatorModelService {

  private final IActuatorModelRepository actuatorModelRepository;

  /**
   * Constructs an ActuatorModelService with a specified repository and factory. Also attempts to
   * load default actuator models from the configuration file upon instantiation.
   *
   * @param actuatorModelRepository Repository for storing and retrieving actuator models.
   * @param factoryActuatorModel    Factory for creating new actuator model instances.
   */
  public ActuatorModelServiceImpl(IActuatorModelRepository actuatorModelRepository,
      IActuatorModelFactory factoryActuatorModel) {

    Validator.validateNotNull(factoryActuatorModel, "Actuator model factory");
    this.actuatorModelRepository = actuatorModelRepository;
    Validator.validateNotNull(actuatorModelRepository, "Actuator model repository");
  }


  /**
   * Retrieves all actuator models from the repository.
   *
   * @return A list of all actuator models.
   */
  @Override
  public List<ActuatorModel> getAllActuatorModels() {
    return actuatorModelRepository.findAll();
  }

  /**
   * Retrieves an actuator model by its unique ID.
   *
   * @param modelPath The unique ID of the actuator model.
   * @return An Optional containing the actuator model if found, or an empty Optional otherwise.
   */
  @Override
  public Optional<ActuatorModel> getActuatorModel(ModelPath modelPath) {
    return actuatorModelRepository.ofIdentity(modelPath);
  }

  /**
   * Retrieves all actuator models of a specific actuator type.
   *
   * @param actuatorTypeID The unique ID of the actuator type.
   * @return A list of all actuator models of the specified type.
   */
  @Override
  public List<ActuatorModel> getActuatorModelsByActuatorTypeId(ActuatorTypeID actuatorTypeID) {
    return actuatorModelRepository.findBy_actuatorTypeID(actuatorTypeID);
  }

}
