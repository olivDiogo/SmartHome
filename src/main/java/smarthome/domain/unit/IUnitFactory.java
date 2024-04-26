package smarthome.domain.unit;

import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

public interface IUnitFactory {

    /**
     * Creates and returns a new {@link Unit} instance.
     *
     * @param unitDescription The description of the measurement type, defining its nature or category (e.g., temperature, pressure).
     * @param unitSymbol      The unit of measurement (e.g., Celsius, Pascal) associated with this measurement type.
     * @return A new {@link Unit} instance configured with the specified description and unit.
     */
    Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol);


    /**
     * Creates and returns a new {@link Unit} instance.
     *
     * @param unitDescription The description of the measurement type, defining its nature or category (e.g., temperature, pressure).
     * @param unitSymbol      The unit of measurement (e.g., Celsius, Pascal) associated with this measurement type.
     * @param unitID          The unique identifier for the measurement type.
     * @return A new {@link Unit} instance configured with the specified description, unit, and ID.
     */
    Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol, UnitID unitID);
}
