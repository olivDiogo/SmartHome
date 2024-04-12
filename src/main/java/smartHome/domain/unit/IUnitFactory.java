package smartHome.domain.unit;

import smartHome.valueObject.UnitDescription;
import smartHome.valueObject.UnitSymbol;

public interface IUnitFactory {

    /**
     * Creates and returns a new {@link Unit} instance.
     *
     * @param unitDescription The description of the measurement type, defining its nature or category (e.g., temperature, pressure).
     * @param unitSymbol      The unit of measurement (e.g., Celsius, Pascal) associated with this measurement type.
     * @return A new {@link Unit} instance configured with the specified description and unit.
     */
    Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol);
}
