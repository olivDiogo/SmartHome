package SmartHome.domain;

import java.util.Arrays;
import java.util.List;

public enum Unit
{
    Temperature("Temperature",  "C"),
    Humidity("Humidity", "%");


    private final String type;
    private final String unit;

    Unit(String type, String unit) {
        this.type = type;
        this.unit = unit;
    }

    public String getType() {
        return type;
    }
    public String getUnit() {
        return unit;
    }
    public static List<String> getAllSupportedUnits() {
        List<String> result = Arrays.stream(Unit.values()).map(Unit::getType).toList();
        return result;
    }
}
