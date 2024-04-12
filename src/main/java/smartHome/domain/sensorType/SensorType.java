package smartHome.domain.sensorType;

import smartHome.ddd.IAggregateRoot;
import smartHome.valueObject.SensorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

public class SensorType implements IAggregateRoot<SensorTypeID> {
    private final TypeDescription _name;
    private final UnitID _unit;
    private SensorTypeID _id;

    /**
     * Creates a new {@link SensorType} instance using the provided sensor type name and unit.
     *
     * @param name the sensor type name, must not be null
     * @param unit the unit of the sensor type, must not be null
     */
    SensorType(TypeDescription name, UnitID unit) {
        validateSensorTypeName(name);
        this._name = name;

        validateUnit(unit);
        this._unit = unit;

        generateID(name);
    }

    /**
     * Creates a new {@link SensorTypeID} instance.
     */
    private void generateID(TypeDescription name) {
        _id = new SensorTypeID(name.toString());
    }

    /**
     * Validates the sensor type name and sets it.
     *
     * @param name the sensor type name, must not be null
     */
    private void validateSensorTypeName(TypeDescription name) {
        if (name == null)
            throw new IllegalArgumentException("Sensor type name must not be null.");
    }

    /**
     * Validates the unit and sets it.
     *
     * @param unit the unit of the sensor type, must not be null
     */
    private void validateUnit(UnitID unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit must not be null.");
    }

    /**
     * Return the ID of the sensor type.
     *
     * @return the ID of the sensor type
     */
    @Override
    public SensorTypeID getID() {
        return _id;
    }

    /**
     * Gets the name of the sensor type.
     *
     * @return the name of the sensor type
     */
    public TypeDescription getName() {
        return _name;
    }

    /**
     * Gets the unit of the sensor type.
     *
     * @return the unit of the sensor type
     */
    public UnitID getUnit() {
        return _unit;
    }

    /**
     * Compares the sensor type with another object.
     *
     * @param object is the object to compare with
     * @return true if the sensor type is the same as the object, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof SensorType sensorTypeObject) {
            return _id.equals(sensorTypeObject._id);
        }
        return false;

    }

    /**
     * Returns the hash code of the sensor type.
     */
    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    /**
     * Returns the attributes of the sensor type as a string.
     *
     * @return the attributes of the sensor type as a string
     */
    @Override
    public String toString() {
        return "SensorType:" +
                "id=" + _id +
                ", name=" + _name +
                ", unit=" + _unit;

    }
}
