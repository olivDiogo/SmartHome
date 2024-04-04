package SmartHomeDDD.service;


import SmartHomeDDD.domain.Actuator.Actuator;
import SmartHomeDDD.domain.Actuator.ActuatorFactory;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.valueObject.*;

import java.util.List;
import java.util.Optional;


/**
 * This class represents a service for managing actuators.
 */
public class ActuatorService {

    private final ActuatorRepository _actuatorRepository;
    private final ActuatorFactory _actuatorFactory;
    private final DeviceRepository _deviceRepository;

    /**
     * Constructs an ActuatorService with the specified repositories and factory.
     *
     * @param actuatorRepository The repository for storing actuators.
     * @param actuatorFactory    The factory for creating actuators.
     * @param deviceRepository   The repository for accessing devices.
     */
    public ActuatorService(ActuatorRepository actuatorRepository, ActuatorFactory actuatorFactory, DeviceRepository deviceRepository) {
        _actuatorRepository = actuatorRepository;
        _actuatorFactory = actuatorFactory;
        _deviceRepository = deviceRepository;
    }

    /**
     * Adds an actuator to the system.
     *
     * @param actuatorTypeID The type ID of the actuator.
     * @param actuatorName   The name of the actuator.
     * @param modelPath      The path of the model associated with the actuator.
     * @param deviceID       The ID of the device the actuator belongs to.
     * @return The added actuator.
     * @throws IllegalArgumentException If the device with the specified ID does not exist.
     */
    public Actuator addActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName) {
        Optional<Device> deviceOptional = _deviceRepository.ofIdentity(deviceID);
        if (deviceOptional.isEmpty()) {
            throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
        }
        Actuator actuator = _actuatorFactory.createActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        _actuatorRepository.save(actuator);
        return actuator;
    }

    /**
     * Retrieves an actuator by its ID.
     *
     * @param actuatorID The ID of the actuator to retrieve.
     * @return An Optional containing the retrieved actuator, or empty if not found.
     */
    public Optional<Actuator> getActuatorByID(ActuatorID actuatorID) {
        return _actuatorRepository.ofIdentity(actuatorID);
    }

    /**
     * Retrieves all actuators stored in the system.
     *
     * @return A list of all actuators.
     */
    public List<Actuator> getAllActuators() {
        return _actuatorRepository.findAll();
    }
}

