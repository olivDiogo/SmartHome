package smarthome.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.IActuatorTypeFactory;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.Validator;

@Service
public class ActuatorTypeServiceImpl implements smarthome.domain.service.IActuatorTypeService {

  private final IActuatorTypeRepository actuatorTypeRepository;
  private final IActuatorTypeFactory actuatorTypeFactory;
  private final IUnitRepository unitRepository;

  /**
   * Constructor for ActuatorTypeService.
   *
   * @param actuatorTypeRepository is the repository for the actuator type.
   * @param actuatorTypeFactory    is the factory for the actuator type.
   * @param unitRepository         is the repository for the unit.
   */
  public ActuatorTypeServiceImpl(
      IActuatorTypeRepository actuatorTypeRepository,
      IActuatorTypeFactory actuatorTypeFactory,
      IUnitRepository unitRepository) {

    Validator.validateNotNull(actuatorTypeRepository, "Actuator type repository");
    this.actuatorTypeRepository = actuatorTypeRepository;
    Validator.validateNotNull(actuatorTypeFactory, "Actuator type factory");
    this.actuatorTypeFactory = actuatorTypeFactory;
    Validator.validateNotNull(unitRepository, "Unit repository");
    this.unitRepository = unitRepository;
  }


  /**
   * Add an ActuatorType. If the ActuatorType already exists, throw an IllegalArgumentException.
   *
   * @param name is the name of the ActuatorType.
   * @return the ActuatorType.
   */
  @Override
  public ActuatorType createActuatorType(TypeDescription name, UnitID unitID) {
    if (!unitRepository.containsOfIdentity(unitID)) {
      throw new IllegalArgumentException("Please enter a valid measurement type.");
    }
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(name, unitID);
    return actuatorType;
  }

  /**
   * Save an ActuatorType. If the ActuatorType is null, throw an IllegalArgumentException.
   */
  @Override
  public ActuatorType addActuatorType(ActuatorType type) {
    if (type == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type.");
    }
    return actuatorTypeRepository.save(type);
  }

  /**
   * Find all actuator types in the repository.
   */
  @Override
  public List<ActuatorType> getAllActuatorTypes() {
    return actuatorTypeRepository.findAll();
  }

  /**
   * Find actuator by typeId
   */
  @Override
  public Optional<ActuatorType> getActuatorTypeByID(ActuatorTypeID typeId) {
    if (typeId == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type ID.");
    }
    return actuatorTypeRepository.ofIdentity(typeId);
  }
}
