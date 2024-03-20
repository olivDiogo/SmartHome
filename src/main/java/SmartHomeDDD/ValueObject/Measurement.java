package SmartHomeDDD.ValueObject;

public class Measurement {
    private String _measurementID;

    public Measurement(String unitID) {
        validateUnitID(unitID);
    }

    private void validateUnitID(String unitID) {
        if (unitID != null && !unitID.isBlank() && !unitID.isEmpty())
            this._measurementID = unitID;
        else
            throw new IllegalArgumentException("Invalid unit ID.");
    }


}
