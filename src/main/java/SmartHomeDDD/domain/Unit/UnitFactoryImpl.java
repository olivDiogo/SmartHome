package SmartHomeDDD.domain.Unit;

import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitSymbol;

/**
 * Factory implementation for creating {@link Unit} instances.
 */
public class UnitFactoryImpl implements IUnitFactory {
    /**
     * Creates and returns a new {@link Unit} instance with the provided measurement value, measurement type, and measurement time.
     */

    public Unit createMeasurement(UnitDescription unitDescription, UnitSymbol unitSymbol) {
        return new Unit(unitDescription, unitSymbol);
    }
}
