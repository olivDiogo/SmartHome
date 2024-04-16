package smart_home.domain.unit;

import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitSymbol;

/**
 * Factory implementation for creating {@link Unit} instances.
 */
public class UnitFactoryImpl implements IUnitFactory {
    /**
     * Creates and returns a new {@link Unit} instance with the provided measurement value, measurement type, and measurement time.
     */

    public Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol) {
        return new Unit(unitDescription, unitSymbol);
    }
}
