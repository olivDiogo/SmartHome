package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.IActuatorFactory;
import smart_home.domain.device.Device;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

/**
 * This class represents a service for managing actuators.
 */
public class ActuatorServiceImpl implements smart_home.domain.service.IActuatorService {

  private final IRepository<ActuatorID, IActuator> actuatorRepository;
  private final IActuatorFactory actuatorFactory;
  private final IDeviceRepository deviceRepository;

  /**
   * Constructs an ActuatorService with the specified repositories and factory.
   *
   * @param actuatorRepository The repository for storing actuators.
   * @param actuatorFactory    The factory for creating actuators.
   * @param deviceRepository   The repository for accessing devices.
   */
  public ActuatorServiceImpl(
      IRepository<ActuatorID, IActuator> actuatorRepository,
      IActuatorFactory actuatorFactory,
      IDeviceRepository deviceRepository) {

    Validator.validateNotNull(actuatorRepository, "Actuator repository");
    this.actuatorRepository = actuatorRepository;
    Validator.validateNotNull(actuatorFactory, "Actuator factory");
    this.actuatorFactory = actuatorFactory;
    Validator.validateNotNull(deviceRepository, "Device repository");
    this.deviceRepository = deviceRepository;
  }


  /**
   * Adds a new actuator to the repository and saves it.
   *
   * @param parameters The parameters needed to create the actuator.The first parameter should * be
   *                   of type DeviceID. The rest of the parameters should be the required
   *                   parameters to create * an actuator object.
   * @return The created and saved actuator object.
   */
  @Override
  public IActuator addActuator(Object... parameters) {
    DeviceID deviceID = (DeviceID) parameters[0];
    Optional<Device> deviceOptional = deviceRepository.ofIdentity(deviceID);
    if (deviceOptional.isEmpty()) {
      throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
    }
    IActuator actuator = actuatorFactory.createActuator(parameters);
    actuatorRepository.save(actuator);
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
    return actuatorRepository.ofIdentity(actuatorID);
  }

  /**
   * Retrieves all actuators stored in the system.
   *
   * @return A list of all actuators.
   */
  @Override
  public List<IActuator> getAllActuators() {
    return actuatorRepository.findAll();
  }
}
