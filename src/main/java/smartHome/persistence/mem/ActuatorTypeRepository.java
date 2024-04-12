package smartHome.persistence.mem;

import smartHome.ddd.IRepository;
import smartHome.domain.actuatorType.ActuatorType;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.TypeDescription;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ActuatorTypeRepository implements IRepository<ActuatorTypeID, ActuatorType> {

    private final Map<ActuatorTypeID, ActuatorType> _actuatorTypeData = new LinkedHashMap<>();

    /**
     * Save an ActuatorType. If the ActuatorType is null, throw an IllegalArgumentException.
     *
     * @param actuatorType is the domain entity to be saved.
     * @return the saved ActuatorType.
     */
    @Override
    public ActuatorType save(ActuatorType actuatorType) {
        if (actuatorType == null) {
            throw new IllegalArgumentException("ActuatorType cannot be null.");
        } else if (containsOfIdentity(actuatorType.getID())) {
            throw new IllegalArgumentException("ActuatorType already exists.");
        } else {
            _actuatorTypeData.put(actuatorType.getID(), actuatorType);
        }
        return actuatorType;
    }

    /**
     * Find all ActuatorTypes.
     *
     * @return a list of all ActuatorTypes.
     */
    @Override
    public List<ActuatorType> findAll() {
        List<ActuatorType> allActuatorTypes = _actuatorTypeData.values().stream().toList();
        return allActuatorTypes;
    }

    /**
     * Find an ActuatorType by its identity.
     *
     * @param actuatorTypeID is the unique identifier of the domain entity.
     * @return the ActuatorType if found, otherwise Optional.empty().
     */
    @Override
    public Optional<ActuatorType> ofIdentity(ActuatorTypeID actuatorTypeID) {
        Optional<ActuatorType> actuatorType = Optional.ofNullable(_actuatorTypeData.get(actuatorTypeID));

        return actuatorType;
    }

    /**
     * Checks if an ActuatorType exists by its name.
     *
     * @param actuatorTypeName is the name of the ActuatorType.
     * @return true if the ActuatorType exists, otherwise false.
     */
    public boolean existsOfName(TypeDescription actuatorTypeName) {
        return _actuatorTypeData.values().stream().anyMatch(actuatorType -> actuatorType.getActuatorTypeName().equals(actuatorTypeName));
    }

    /**
     * Checks if the repository contains an ActuatorType with the given identity.
     *
     * @param actuatorTypeID is the unique identifier of the domain entity.
     * @return true if the repository contains the ActuatorType, otherwise false.
     */
    @Override
    public boolean containsOfIdentity(ActuatorTypeID actuatorTypeID) {
        return _actuatorTypeData.containsKey(actuatorTypeID);
    }
}