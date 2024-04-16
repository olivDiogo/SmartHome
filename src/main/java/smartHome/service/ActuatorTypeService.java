package smartHome.service;

import smartHome.ddd.IRepository;
import smartHome.domain.actuatorType.ActuatorType;
import smartHome.domain.actuatorType.ActuatorTypeFactoryImpl;
import smartHome.domain.actuatorType.IActuatorTypeFactory;
import smartHome.domain.unit.Unit;
import smartHome.persistence.mem.ActuatorTypeRepository;
import smartHome.persistence.mem.UnitRepository;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import java.util.List;
import java.util.Optional;

public class ActuatorTypeService {
    private final IRepository<ActuatorTypeID, ActuatorType> _actuatorTypeRepository;
    private final IActuatorTypeFactory _actuatorTypeFactory;
    private final IRepository<UnitID, Unit> _unitRepository;

    /**
     * Constructor for ActuatorTypeService.
     *
     * @param actuatorTypeRepository
     * @param actuatorTypeFactory
     * @param unitRepository
     */
    public ActuatorTypeService(
            IRepository<ActuatorTypeID, ActuatorType> actuatorTypeRepository,
            IActuatorTypeFactory actuatorTypeFactory,
            IRepository<UnitID, Unit> unitRepository) {
        validateActuatorTypeRepository(actuatorTypeRepository);
        this._actuatorTypeRepository = actuatorTypeRepository;
        validateActuatorTypeFactory(actuatorTypeFactory);
        this._actuatorTypeFactory = actuatorTypeFactory;
        validateUnitRepository(unitRepository);
        this._unitRepository = unitRepository;
    }

    /**
     * Validate the actuatorType repository.
     */
    private void validateActuatorTypeRepository(IRepository<ActuatorTypeID, ActuatorType> actuatorTypeRepository) {
        if (actuatorTypeRepository == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type repository.");
        }
    }

    /**
     * Validate the actuatorType factory.
     */
    private void validateActuatorTypeFactory(IActuatorTypeFactory actuatorTypeFactory) {
        if (actuatorTypeFactory == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type factory.");
        }
    }

    /**
     * Validate the unit repository.
     */
    private void validateUnitRepository(IRepository<UnitID, Unit> unitRepository) {
        if (unitRepository == null) {
            throw new IllegalArgumentException("Please enter a valid unit repository.");
        }
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
