package SmartHomeDDD.domain.Measurement;

import SmartHomeDDD.ValueObject.MeasurementTypeUnit;
import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import SmartHomeDDD.ddd.DomainEntity;

public class MeasurementType implements DomainEntity<MeasurementTypeDescription> {
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

    @Override
    public MeasurementTypeDescription getID() {
        return _unitDescription;
    }
    @Override
    public String toString() {
        return "MeasurementType{" +
                "_measurementUnit=" + _measurementUnit +
                ", _unitDescription=" + _unitDescription +
                '}';
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof MeasurementType) {
            MeasurementType measurementType = (MeasurementType) object;
            return this._measurementUnit.equals(measurementType._measurementUnit) && this._unitDescription.equals(measurementType._unitDescription);
        }
        return false;
    }
}
