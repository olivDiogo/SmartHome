package SmartHomeDDD.domain.SensorType;

import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ValueObject.UnitDescription;

public class SensorType {
    private TypeDescription _sensorTypeName; //ID of SensorType
    private UnitDescription _unit;

    /**
     * Creates a new {@link SensorType} instance using the provided sensor type name and unit.
     *
     * @param sensorTypeName the sensor type name, must not be null
     * @param unit the unit of the sensor type, must not be null
     */
    public SensorType(TypeDescription sensorTypeName, UnitDescription unit) {
        validateSensorTypeName(sensorTypeName);
        this._sensorTypeName = sensorTypeName;

        validateUnit(unit);
        this._unit = unit;
    }

    /**
     * Validates the sensor type name and sets it.
     *
     * @param sensorTypeName the sensor type name, must not be null
     */
    private void validateSensorTypeName(TypeDescription sensorTypeName) {
        if (sensorTypeName == null)
            throw new IllegalArgumentException("Sensor type name must not be null.");
    }

    /**
     * Validates the unit and sets it.
     *
     * @param unit the unit of the sensor type, must not be null
     */
    private void validateUnit(UnitDescription unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit must not be null.");
    }

    /**
     * Gets the sensor type name.
     *
     * @return the sensor type name
     */
    public TypeDescription getSensorTypeName() {
        return _sensorTypeName;
    }

    /**
     * Gets the unit of the sensor type.
     *
     * @return the unit of the sensor type
     */
    public UnitDescription getUnit() {
        return _unit;
    }

}
