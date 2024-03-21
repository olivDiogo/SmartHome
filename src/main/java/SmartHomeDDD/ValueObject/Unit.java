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

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Unit) {
            Unit unit = (Unit) object;

            if (this._unit.equals(unit._unit)) {
                return true;
            }
        }
        return false;
    }

    public String getUnit() {
        return _unit;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "_unit='" + _unit +
                '}';
    }

}
