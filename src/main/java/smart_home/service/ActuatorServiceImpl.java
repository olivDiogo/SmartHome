package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.IActuatorFactory;
import smart_home.domain.device.Device;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

/** This class represents a service for managing actuators. */
public class ActuatorServiceImpl implements smart_home.domain.service.IActuatorService {

  private final IRepository<ActuatorID, IActuator> _actuatorRepository;
  private final IActuatorFactory _actuatorFactory;
  private final IDeviceRepository _deviceRepository;

  /**
   * Constructs an ActuatorService with the specified repositories and factory.
   *
   * @param actuatorRepository The repository for storing actuators.
   * @param actuatorFactory The factory for creating actuators.
   * @param deviceRepository The repository for accessing devices.
   */
  public ActuatorServiceImpl(
      IRepository<ActuatorID, IActuator> actuatorRepository,
      IActuatorFactory actuatorFactory,
      IDeviceRepository deviceRepository) {
    validateActuatorRepository(actuatorRepository);
    _actuatorRepository = actuatorRepository;
    validateActuatorFactory(actuatorFactory);
    _actuatorFactory = actuatorFactory;
    validateDeviceRepository(deviceRepository);
    _deviceRepository = deviceRepository;
  }

  /**
   * Validates the actuator repository.
   *
   * @param actuatorRepository The actuator repository to validate.
   * @throws IllegalArgumentException If the actuator repository is null.
   */
  private void validateActuatorRepository(IRepository<ActuatorID, IActuator> actuatorRepository) {
    if (actuatorRepository == null) {
      throw new IllegalArgumentException("Actuator repository cannot be null.");
    }
  }

  /**
   * Validates the actuator factory.
   *
   * @param actuatorFactory The actuator factory to validate.
   * @throws IllegalArgumentException If the actuator factory is null.
   */
  private void validateActuatorFactory(IActuatorFactory actuatorFactory) {
    if (actuatorFactory == null) {
      throw new IllegalArgumentException("Actuator factory cannot be null.");
    }
  }

  /**
   * Validates the device repository.
   *
   * @param deviceRepository The device repository to validate.
   * @throws IllegalArgumentException If the device repository is null.
   */
  private void validateDeviceRepository(IDeviceRepository deviceRepository) {
    if (deviceRepository == null) {
      throw new IllegalArgumentException("Device repository cannot be null.");
    }
  }

  /**
   * Adds a new actuator to the repository and saves it.
   *
   * @param parameters The parameters needed to create the actuator.The first parameter should * be
   *     of type DeviceID. The rest of the parameters should be the required parameters to create *
   *     an actuator object.
   * @return The created and saved actuator object.
   */
  @Override
  public IActuator addActuator(Object... parameters) {
    DeviceID deviceID = (DeviceID) parameters[0];
    Optional<Device> deviceOptional = _deviceRepository.ofIdentity(deviceID);
    if (deviceOptional.isEmpty()) {
      throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
    }
    IActuator actuator = _actuatorFactory.createActuator(parameters);
    _actuatorRepository.save(actuator);
    return actuator;
  }

  /**
   * Retrieves an actuator by its ID.
   *
   * @param actuatorID The ID of the actuator to retrieve.
   * @return An Optional containing the retrieved actuator, or empty if not found.
   */
  @Override
  public Optional<IActuator> getActuatorByID(ActuatorID actuatorID) {
    return _actuatorRepository.ofIdentity(actuatorID);
  }

  /**
   * Retrieves all actuators stored in the system.
   *
   * @return A list of all actuators.
   */
  @Override
  public List<IActuator> getAllActuators() {
    return _actuatorRepository.findAll();
  }
}
