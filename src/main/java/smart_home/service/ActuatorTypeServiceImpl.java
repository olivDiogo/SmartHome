package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.actuator_type.IActuatorTypeFactory;
import smart_home.domain.unit.Unit;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public class ActuatorTypeServiceImpl implements smart_home.domain.service.IActuatorTypeService {

  private final IRepository<ActuatorTypeID, ActuatorType> actuatorTypeRepository;
  private final IActuatorTypeFactory actuatorTypeFactory;
  private final IRepository<UnitID, Unit> unitRepository;

  /**
   * Constructor for ActuatorTypeService.
   *
   * @param actuatorTypeRepository is the repository for the actuator type.
   * @param actuatorTypeFactory    is the factory for the actuator type.
   * @param unitRepository         is the repository for the unit.
   */
  public ActuatorTypeServiceImpl(
      IRepository<ActuatorTypeID, ActuatorType> actuatorTypeRepository,
      IActuatorTypeFactory actuatorTypeFactory,
      IRepository<UnitID, Unit> unitRepository) {

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
