package SmartHome.domain;

import java.util.Arrays;
import java.util.List;

public enum Unit
{
    SWITCH( "On/Off"),
    TEMPERATURE("C"),
    HUMIDITY("%"),
    POWERCONSUMPTION("W"),
    WINDSPEEDANDDIRECTION("km/h & Cardinal Points"),
    SOLARIRRADIANCE("W/m^2"),
    PERCENTED("%"),
    TIME("s"),
    WATTSHOUR("Wh"),
    NOUNIT("");


    private final String unit;

    /**
     * Constructor for the Unit enum.
     *
     * @param unit The unit.
     */
    Unit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets the unit.
     *
     * @return The unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Gets all supported units.
     *
     * @return A list of all supported units.
     */
    public static List<String> getAllSupportedUnits() {
        List<String> result = Arrays.stream(Unit.values()).map(Unit::name).toList();
        return result;
    }
}
