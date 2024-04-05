package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.IActuatorModelFactory;
import SmartHomeDDD.domain.ActuatorModel.IActuatorModelRepo;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing actuator models within the smart home domain.
 * This includes loading default actuator models from a configuration file, as well as providing access to these models.
 */
public class ActuatorModelService {
    private IActuatorModelRepo _actuatorModelRepository;
    private IActuatorModelFactory _factoryActuatorModel;

    /**
     * Constructs an ActuatorModelService with a specified repository and factory.
     * Also attempts to load default actuator models from the configuration file upon instantiation.
     *
     * @param actuatorModelRepository Repository for storing and retrieving actuator models.
     * @param factoryActuatorModel    Factory for creating new actuator model instances.
     */
    public ActuatorModelService(IActuatorModelRepo actuatorModelRepository, IActuatorModelFactory factoryActuatorModel) {
        validateActuatorModelRepository(actuatorModelRepository);
        validateFactoryActuatorModel(factoryActuatorModel);
    }

    /**
     * Validates the provided actuator model repository and sets it if valid.
     * Throws IllegalArgumentException if the repository is null.
     *
     * @param actuatorModelRepository The repository to be validated.
     */
    private void validateActuatorModelRepository(IActuatorModelRepo actuatorModelRepository) {
        if (actuatorModelRepository == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model repository.");
        } else {
            this._actuatorModelRepository = actuatorModelRepository;
        }
    }

    /**
     * Validates the provided actuator model factory and sets it if valid.
     * Throws IllegalArgumentException if the factory is null.
     *
     * @param factoryActuatorModel The factory to be validated.
     */
    private void validateFactoryActuatorModel(IActuatorModelFactory factoryActuatorModel) {
        if (factoryActuatorModel == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model factory.");
        } else {
            this._factoryActuatorModel = factoryActuatorModel;
        }
    }

    /**
     * Retrieves all actuator models from the repository.
     *
     * @return A list of all actuator models.
     */
    public List<ActuatorModel> getAllActuatorModels() {
        return _actuatorModelRepository.findAll();
    }

    /**
     * Retrieves an actuator model by its unique ID.
     *
     * @param modelPath The unique ID of the actuator model.
     * @return An Optional containing the actuator model if found, or an empty Optional otherwise.
     */
    public Optional<ActuatorModel> getActuatorModel(ModelPath modelPath) {
        return _actuatorModelRepository.ofIdentity(modelPath);
    }

    /**
     * Retrieves all actuator models of a specific actuator type.
     *
     * @param actuatorTypeID
     * @return
     */
    public List<ActuatorModel> getActuatorModelsByActuatorTypeId(ActuatorTypeID actuatorTypeID) {
        return _actuatorModelRepository.findByActuatorTypeId(actuatorTypeID);
    }

}
