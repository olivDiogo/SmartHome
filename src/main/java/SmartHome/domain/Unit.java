package SmartHome.domain;

import java.util.Arrays;
import java.util.List;

public enum Unit
{
    Switch("Switch", "On/Off"),
    Temperature("Temperature",  "C"),
    Humidity("Humidity", "%"),
    PowerConsumption("Power Consumption", "W"),
    WindSpeedAndDirection("WindSpeedAndDirection", "km/h & Cardinal Points"),
    SolarIrradiance("SolarIrradiance", "W/m^2"),
    Percented("Percented", "%"),
    Time("Time", "s");


    private final String type;
    private final String unit;

    /**
     * Constructor for the Unit enum.
     *
     * @param type The type of the unit.
     * @param unit The unit.
     */
    Unit(String type, String unit) {
        this.type = type;
        this.unit = unit;
    }

    /**
     * Gets the type of the unit.
     *
     * @return The type of the unit.
     */
    public String getType() {
        return type;
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
        List<String> result = Arrays.stream(Unit.values()).map(Unit::getType).toList();
        return result;
    }
}
