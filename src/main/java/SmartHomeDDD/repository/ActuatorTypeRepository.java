package SmartHomeDDD.repository;

import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;

import java.util.*;

public class ActuatorTypeRepository implements Repository<ActuatorTypeID, ActuatorType> {

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