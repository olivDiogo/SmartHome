package SmartHomeDDD.service;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModelFactory;
import SmartHomeDDD.valueObject.ActuatorModelID;
import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ModelPath;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing actuator models within the smart home domain.
 * This includes loading default actuator models from a configuration file, as well as providing access to these models.
 */
public class ActuatorModelService {
    private Repository<ActuatorModelID, ActuatorModel> _actuatorModelRepository;
    private ActuatorModelFactory _factoryActuatorModel;

    /**
     * Constructs an ActuatorModelService with a specified repository and factory.
     * Also attempts to load default actuator models from the configuration file upon instantiation.
     *
     * @param actuatorModelRepository Repository for storing and retrieving actuator models.
     * @param factoryActuatorModel Factory for creating new actuator model instances.
     */
public ActuatorModelService(Repository<ActuatorModelID, ActuatorModel> actuatorModelRepository, ActuatorModelFactory factoryActuatorModel) {
        validateActuatorModelRepository(actuatorModelRepository);
        validateFactoryActuatorModel(factoryActuatorModel);
        try {
            loadDefaultActuatorModels();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates the provided actuator model repository and sets it if valid.
     * Throws IllegalArgumentException if the repository is null.
     *
     * @param actuatorModelRepository The repository to be validated.
     */
    private void validateActuatorModelRepository(Repository<ActuatorModelID, ActuatorModel> actuatorModelRepository) {
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
    private void validateFactoryActuatorModel(ActuatorModelFactory factoryActuatorModel) {
        if (factoryActuatorModel == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model factory.");
        } else {
            this._factoryActuatorModel = factoryActuatorModel;
        }
    }

    /**
     * Loads default actuator models from a configuration file and adds them to the repository.
     * Throws InstantiationException if there's an issue loading the configurations.
     */
    private void loadDefaultActuatorModels() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("config.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringClassesActuators = config.getStringArray("actuator");
            for (String actuator : arrayStringClassesActuators) {
                ModelPath actuatorPath = new ModelPath(actuator);

                String actuatorModelName = actuator.substring(actuator.lastIndexOf('.') + 1);
                ActuatorModelName actuatorName = new ActuatorModelName(actuatorModelName);

                ActuatorModel actuatorModel = _factoryActuatorModel.createActuatorModel(actuatorName, actuatorPath);
                _actuatorModelRepository.save(actuatorModel);
            }
        } catch (ConfigurationException exception) {
            throw new InstantiationException("Error instantiating actuator model");
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
     * @param actuatorModelID The unique ID of the actuator model.
     * @return An Optional containing the actuator model if found, or an empty Optional otherwise.
     */
    public Optional<ActuatorModel> getActuatorModel(ActuatorModelID actuatorModelID) {
        return _actuatorModelRepository.ofIdentity(actuatorModelID);
    }



}
