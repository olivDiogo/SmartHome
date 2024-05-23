package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

public interface IUnitService extends IService {

  /**
   * Adds a new unit type to the repository.
   *
   * @param description The description of the unit type.
   * @param unit        The unit of the unit type.
   * @return The created and saved unitType object.
   */
  Unit addunitType(UnitDescription description, UnitSymbol unit);

  /**
   * Retrieves a unit type by its ID.
   *
   * @param unitID The ID of the unit type to retrieve.
   * @return The unit type with the given ID, if it exists.
   */
  Optional<Unit> getunitTypeById(UnitID unitID);

  /**
   * Retrieves all unit types from the repository.
   *
   * @return A list of all unit types.
   */
  List<Unit> getAllunitTypes();
}
