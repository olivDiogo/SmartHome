package smarthome.domain.service;

import smarthome.ddd.IService;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

import java.util.List;
import java.util.Optional;

public interface IUnitService extends IService {

    /**
     * Adds a new measurement type to the repository.
     *
     * @param description The description of the measurement type.
     * @param unit        The unit of the measurement type.
     * @return The created and saved MeasurementType object.
     */
    Unit addMeasurementType(UnitDescription description, UnitSymbol unit);

    /**
     * Retrieves a measurement type by its ID.
     *
     * @param unitID The ID of the measurement type to retrieve.
     * @return The measurement type with the given ID, if it exists.
     */
    Optional<Unit> getMeasurementTypeById(UnitID unitID);

    /**
     * Retrieves all measurement types from the repository.
     *
     * @return A list of all measurement types.
     */
    List<Unit> getAllMeasurementTypes();
}
