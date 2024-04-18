package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.actuator_type.IActuatorTypeFactory;
import smart_home.domain.unit.Unit;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public class ActuatorTypeServiceImpl implements smart_home.domain.service.IActuatorTypeService {
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
    public ActuatorTypeServiceImpl(
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
    @Override
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
    @Override
    public ActuatorType addActuatorType(ActuatorType type) {
        if (type == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type.");
        }
        return _actuatorTypeRepository.save(type);
    }

    /**
     * Find all actuator types in the repository.
     */
    @Override
    public List<ActuatorType> getAllActuatorTypes() {
        return _actuatorTypeRepository.findAll();
    }

    /**
     * Find actuator by typeId
     */
    @Override
    public Optional<ActuatorType> getActuatorTypeByID(ActuatorTypeID typeId) {
        if (typeId == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        }
        return _actuatorTypeRepository.ofIdentity(typeId);
    }
}
