package smart_home.persistence.mem;

import smart_home.ddd.IRepository;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.unit.Unit;
import smart_home.utils.Validator;
import smart_home.value_object.UnitID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UnitRepository implements IUnitRepository {
    private final Map<UnitID, Unit> DATA = new LinkedHashMap<>();

    /**
     * Save a MeasurementType. If the MeasurementType is null, throw an IllegalArgumentException.
     * If the MeasurementType already exists, throw an IllegalArgumentException.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Unit save(Unit entity) {
        Validator.validateNotNull(entity, "MeasurementType");

        if (containsOfIdentity(entity.getID())) {
            throw new IllegalArgumentException("MeasurementType already exists.");
        } else {
            DATA.put(entity.getID(), entity);
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
        List<Unit> allUnits = DATA.values().stream().toList();
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
        Optional<Unit> measurementType = Optional.ofNullable(DATA.get(objectID));
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
        return DATA.containsKey(objectID);
    }
}
