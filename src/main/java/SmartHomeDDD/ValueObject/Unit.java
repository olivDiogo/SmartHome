package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class Unit implements ValueObject {

    private final String _unit;

    public Unit(String unit) {
        unitValidation(unit);
        this._unit = unit;
    }

    private void unitValidation(String unit){
        if (unit == null || unit.trim().isEmpty() || unit.length() > 5) {
            throw new IllegalArgumentException("Invalid unit");
        }
    }

}
