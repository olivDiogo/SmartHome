package SmartHomeDDD.ValueObject;

public class MeasurementID {
    private String _measurementID;

    public MeasurementID(String unitID) {
        validateUnitID(unitID);
    }

    private void validateUnitID(String unitID) {
        if (unitID != null && !unitID.isBlank() && !unitID.isEmpty())
            this._measurementID = unitID;
        else
            throw new IllegalArgumentException("Invalid unit ID.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof MeasurementID) {
            MeasurementID unit = (MeasurementID) object;

            if (this._measurementID.equals(unit._measurementID))
                return true;
        }
        return false;
    }

    public String getId() {
        return this._measurementID;
    }

    public int hashCode() {
        return this._measurementID.hashCode();
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "_measurementID='" + _measurementID + '\'' +
                '}';
    }
}
