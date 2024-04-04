package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.ActuatorType.ImpActuatorTypeFactory;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;

import java.util.List;
import java.util.Optional;

public class ActuatorTypeService {
  private ActuatorTypeRepository _actuatorTypeRepository;
  private ImpActuatorTypeFactory _actuatorTypeFactory;
  private UnitRepository _unitRepository;

  /**
   * Constructor for ActuatorTypeService.
   *
   * @param actuatorTypeRepository
   * @param actuatorTypeFactory
   * @param unitRepository
   */
  public ActuatorTypeService(
      ActuatorTypeRepository actuatorTypeRepository,
      ImpActuatorTypeFactory actuatorTypeFactory,
      UnitRepository unitRepository) {

    _actuatorTypeRepository = actuatorTypeRepository;
    _actuatorTypeFactory = actuatorTypeFactory;
    this._unitRepository = unitRepository;
  }

  /**
   * Add an ActuatorType. If the ActuatorType already exists, throw an IllegalArgumentException.
   *
   * @param actuatorTypeName is the name of the ActuatorType.
   * @return the ActuatorType.
   */
  public ActuatorType addActuatorType(TypeDescription actuatorTypeName) {
    if (actuatorTypeName == null) {
      throw new IllegalArgumentException("ActuatorType name cannot be null.");
    } else if (_actuatorTypeRepository.existsOfName(actuatorTypeName)) {
      throw new IllegalArgumentException("Actuator type already exists.");
    } else {
      ActuatorType actuatorType = _actuatorTypeFactory.createActuatorType(actuatorTypeName);

      _actuatorTypeRepository.save(actuatorType);

      return actuatorType;
    }
  }

  /** Find all actuator types in the repository. */
  public List<ActuatorType> findAllActuatorTypes() {
    return _actuatorTypeRepository.findAll();
  }

  /** Find actuator by typeId */
  public Optional<ActuatorType> findActuatorTypeByID(ActuatorTypeID typeId) {
    if (typeId == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type ID.");
    }
    return _actuatorTypeRepository.ofIdentity(typeId);
  }
}
