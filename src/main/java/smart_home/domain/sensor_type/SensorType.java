package smart_home.domain.sensor_type;

import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

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
        Validator.validateNotNull(name, "Name");
        this._name = name;

        Validator.validateNotNull(unit, "Unit");
        this._unit = unit;

        generateID(name);
    }

    /**
     * Creates a new {@link SensorType} instance using the provided sensor type ID, name and unit.
     *
     * @param sensorTypeID the sensor type ID, must not be null
     * @param name         the sensor type name, must not be null
     * @param unitID       the unit of the sensor type, must not be null
     */
    SensorType(SensorTypeID sensorTypeID, TypeDescription name, UnitID unitID) {
        Validator.validateNotNull(sensorTypeID, "SensorTypeID");
        Validator.validateNotNull(name, "Name");
        Validator.validateNotNull(unitID, "Unit");

        this._name = name;
        this._unit = unitID;

    }



    /**
     * Creates a new {@link SensorTypeID} instance.
     */
    private void generateID(TypeDescription name) {
        _id = new SensorTypeID(name.toString());
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
