package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.ActuatorType.ImpActuatorTypeFactory;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;

import java.util.List;
import java.util.Optional;

public class ActuatorTypeService {
    private final ActuatorTypeRepository _actuatorTypeRepository;
    private final ImpActuatorTypeFactory _actuatorTypeFactory;
    private final UnitRepository _unitRepository;

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

        this._actuatorTypeRepository = actuatorTypeRepository;
        this._actuatorTypeFactory = actuatorTypeFactory;
        this._unitRepository = unitRepository;
    }

    /**
     * Add an ActuatorType. If the ActuatorType already exists, throw an IllegalArgumentException.
     *
     * @param name is the name of the ActuatorType.
     * @return the ActuatorType.
     */
    public ActuatorType createActuatorType(TypeDescription name, UnitID unitID) {
        if (!_unitRepository.containsOfIdentity(unitID)) {
            throw new IllegalArgumentException("Please enter a valid measurement type.");
        }
        ActuatorType actuatorType = _actuatorTypeFactory.createActuatorType(name, unitID);
        return actuatorType;
    }

    /**
     * Save an ActuatorType. If the ActuatorType is null, throw an IllegalArgumentException.
     */
    public ActuatorType saveActuatorType(ActuatorType type) {
        if (type == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type.");
        }
        return _actuatorTypeRepository.save(type);
    }

    /**
     * Find all actuator types in the repository.
     */
    public List<ActuatorType> findAllActuatorTypes() {
        return _actuatorTypeRepository.findAll();
    }

    /**
     * Find actuator by typeId
     */
    public Optional<ActuatorType> findActuatorTypeByID(ActuatorTypeID typeId) {
        if (typeId == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return _actuatorTypeRepository.ofIdentity(typeId);
    }
}
