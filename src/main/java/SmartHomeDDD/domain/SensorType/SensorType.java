package SmartHomeDDD.domain.SensorType;

import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ValueObject.UnitDescription;
import SmartHomeDDD.ddd.AggregateRoot;

public class SensorType implements AggregateRoot<TypeDescription> {
    private TypeDescription _sensorTypeName; //ID of SensorType
    private UnitDescription _unit;

    /**
     * Creates a new {@link SensorType} instance using the provided sensor type name and unit.
     *
     * @param sensorTypeName the sensor type name, must not be null
     * @param unit the unit of the sensor type, must not be null
     */
    SensorType(TypeDescription sensorTypeName, UnitDescription unit) {
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

    /**
     * Compares the sensor type with another object.
     *
     * @param object is the object to compare with
     * @return true if the sensor type is the same as the object, false otherwise
     */
    @Override
    public boolean equals( Object object ) {

        if( this == object )
            return true;

        if( object instanceof SensorType ) {
            SensorType sensorTypeObject = (SensorType) object;

            if( this._sensorTypeName.equals(sensorTypeObject._sensorTypeName) )
                return true;
        }
        return false;
    }

    /**
     * Return the ID of the sensor type.
     *
     * @return the ID of the sensor type
     */
    public TypeDescription getID(){
        return _sensorTypeName;
    }

    /**
     * Returns the attributes of the sensor type as a string.
     *
     * @return the attributes of the sensor type as a string
     */
    @Override
    public String toString() {
        return  "TypeDescription: " + _sensorTypeName.toString() +
                "\nUnit: " + _unit.toString();
    }
}
