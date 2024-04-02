package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.valueObject.UnitID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MeasurementTypeRepository implements Repository<UnitID, Unit> {
    private final Map<UnitID, Unit> _measurementTypeData = new LinkedHashMap<>();

    /**
     * Save a MeasurementType. If the MeasurementType is null, throw an IllegalArgumentException.
     * If the MeasurementType already exists, throw an IllegalArgumentException.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Unit save(Unit entity) {
        if (entity == null) {
            throw new IllegalArgumentException("MeasurementType cannot be null.");
        } else if (containsOfIdentity(entity.getID())) {
            throw new IllegalArgumentException("MeasurementType already exists.");
        } else {
            _measurementTypeData.put(entity.getID(), entity);
        }
        return entity;
    }

    /**
     * Method to find all MeasurementTypes.
     *
     * @return a list of all MeasurementTypes.
     */
    @Override
    public List<Unit> findAll() {
        List<Unit> allUnits =  _measurementTypeData.values().stream().toList();
        return allUnits;
    }

    /**
     * Method to find a MeasurementType by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<Unit> ofIdentity(UnitID objectID) {
        Optional<Unit> measurementType = Optional.ofNullable(_measurementTypeData.get(objectID));
        return measurementType;
    }

    /**
     * Method to check if a MeasurementType exists by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(UnitID objectID) {
        return _measurementTypeData.containsKey(objectID);
    }
}
