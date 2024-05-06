package smarthome.domain.unit;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

/**
 * Factory implementation for creating {@link Unit} instances.
 */
@Component
public class UnitFactoryImpl implements IUnitFactory {

  /**
   * Creates and returns a new {@link Unit} instance with the provided measurement value,
   * measurement type, and measurement time.
   */

  @Override
  public Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol)
      throws IllegalArgumentException {
    return new Unit(unitDescription, unitSymbol);
  }

  /**
   * Creates and returns a new {@link Unit} instance with the provided measurement value,
   * measurement type, measurement time, and unit ID.
   */
  @Override
  public Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol, UnitID unitID)
      throws IllegalArgumentException {
    return new Unit(unitDescription, unitSymbol, unitID);
  }
}
