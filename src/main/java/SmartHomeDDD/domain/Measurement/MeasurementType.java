package SmartHomeDDD.domain.Measurement;

import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;

public class MeasurementType {
    private MeasurementTypeUnit _measurementUnit;
    private MeasurementTypeDescription _unitDescription;

    MeasurementType(MeasurementTypeDescription unitDescription, MeasurementTypeUnit measurementUnit) {
        validateMeasurementUnit(measurementUnit);
        validateUnitDescription(unitDescription);
        this._measurementUnit = measurementUnit;
        this._unitDescription = unitDescription;
    }
    private void validateMeasurementUnit(MeasurementTypeUnit measurementUnit) {
        if (measurementUnit == null) {
            throw new IllegalArgumentException("Measurement unit is required");
        }
    }
    private void validateUnitDescription(MeasurementTypeDescription unitDescription) {
        if (unitDescription == null) {
            throw new IllegalArgumentException("Unit description is required");
        }
    }


}
