package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class UnitSymbol implements IValueObject {

    private final String _unit;

    public UnitSymbol(String unit) {
        unitValidation(unit);
        this._unit = unit;
    }

    private void unitValidation(String unit) {
        if (unit == null || unit.trim().isEmpty() || unit.length() > 5) {
            throw new IllegalArgumentException("Invalid unit");
        }
    }

    public String getSymbol() {
        return _unit;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof UnitSymbol unit) {

            return this._unit.equals(unit._unit);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return _unit.hashCode();
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
