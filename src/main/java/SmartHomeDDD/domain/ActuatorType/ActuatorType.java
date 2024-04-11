package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;

public class ActuatorType implements AggregateRoot<ActuatorTypeID> {

    /**
     * The actuator type ID.
     */
    private ActuatorTypeID _id;

    /**
     * The actuator type name.
     */
    private TypeDescription _actuatorTypeName;

    /**
     * The unit of the actuator type.
     */
    private UnitID _unit;

    /**
     * Creates a new {@link ActuatorType} instance using the provided actuator type name.
     *
     * @param name the actuator type name, must not be null
     */
    public ActuatorType(TypeDescription name, UnitID unit) {
        validateActuatorTypeName(name);
        validateUnit(unit);
        generateID(name);
    }

    private void generateID(TypeDescription name) {
        _id = new ActuatorTypeID(name.toString());
    }

    /**
     * Validates the actuator type name and sets it.
     *
     * @param actuatorTypeName the actuator type name, must not be null
     */
    private void validateActuatorTypeName(TypeDescription actuatorTypeName) {
        if (actuatorTypeName == null) {
            throw new IllegalArgumentException("Actuator type name must not be null.");
        }
        this._actuatorTypeName = actuatorTypeName;
    }

    /**
     * Validates the unit and sets it.
     *
     * @param unit the unit of the sensor type, must not be null
     */
    private void validateUnit(UnitID unit) {
        if (unit == null) throw new IllegalArgumentException("Unit must not be null.");
        this._unit = unit;
    }

    /**
     * Gets the actuator type name.
     *
     * @return the actuator type name
     */
    @Override
    public ActuatorTypeID getID() {
        return _id;
    }

    /**
     * Gets the actuator type name.
     *
     * @return the actuator type name
     */
    public TypeDescription getActuatorTypeName() {
        return _actuatorTypeName;
    }

    /**
     * Compares this instance with another instance.
     *
     * @param o the other instance to compare with
     * @return true if the instances are equal, false otherwise
     */

    /**
     * Method to get unit
     *
     * @return
     */
    public UnitID getUnit() {
        return _unit;
    }

    /**
     * Method to compare two instances
     *
     * @param object
     * @return
     */

    @Override
    public boolean equals(Object object) {
        if (object instanceof ActuatorType actuatorType) {
            return this._id.equals(actuatorType._id);
        }
        return false;
    }

    /**
     * Method to get hash code
     *
     * @return
     */
    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    /**
     * Method to get string representation
     *
     * @return
     */
    @Override
    public String toString() {
        return _id + " " + _actuatorTypeName + " " + _unit;
    }
}
