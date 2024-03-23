package SmartHomeDDD.repository;

import SmartHomeDDD.ValueObject.MeasurementID;
import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.MeasurementType.MeasurementType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import java.util.Optional;

public class MeasurementTypeRepository implements Repository<MeasurementID, MeasurementType> {
    private final Map<MeasurementID, MeasurementType> _measurementTypeData = new LinkedHashMap<>();

    /**
     * Save a MeasurementType. If the MeasurementType is null, throw an IllegalArgumentException.
     * If the MeasurementType already exists, throw an IllegalArgumentException.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public MeasurementType save(MeasurementType entity) {
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
    public List<MeasurementType> findAll() {
        List<MeasurementType> allMeasurementTypes =  _measurementTypeData.values().stream().toList();
        return allMeasurementTypes;
    }

    /**
     * Method to find a MeasurementType by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<MeasurementType> ofIdentity(MeasurementID objectID) {
        Optional<MeasurementType> measurementType = Optional.ofNullable(_measurementTypeData.get(objectID));
        return measurementType;
    }

    /**
     * Method to check if a MeasurementType exists by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(MeasurementID objectID) {
        return _measurementTypeData.containsKey(objectID);
    }
}
