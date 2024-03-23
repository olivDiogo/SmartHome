package SmartHomeDDD.domain.MeasurementType;

import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import SmartHomeDDD.ValueObject.MeasurementTypeUnit;

public interface MeasurementTypeFactory {

    /**
     * Creates and returns a new {@link MeasurementType} instance.
     * @param measurementTypeDescription The description of the measurement type, defining its nature or category (e.g., temperature, pressure).
     * @param measurementTypeUnit The unit of measurement (e.g., Celsius, Pascal) associated with this measurement type.
     * @return A new {@link MeasurementType} instance configured with the specified description and unit.
     */
    MeasurementType createMeasurement(MeasurementTypeDescription measurementTypeDescription, MeasurementTypeUnit measurementTypeUnit);
}
