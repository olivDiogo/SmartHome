package smart_home.domain.actuator_type;

import smart_home.ddd.IAggregateRoot;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

public class ActuatorType implements IAggregateRoot<ActuatorTypeID> {

    private ActuatorTypeID _id;

    private TypeDescription _actuatorTypeName;

    private UnitID _unit;

    /**
     * Creates a new {@link ActuatorType} instance using the provided actuator type name and unit.
     *
     * @param name the actuator type name, must not be null
     * @param unit the unit of the actuator type, must not be null
     */
    public ActuatorType(TypeDescription name, UnitID unit) {
        validateActuatorTypeName(name);
        validateUnit(unit);
        generateID(name);
    }

    /**
     * Creates a new {@link ActuatorType} instance using the provided actuator type name, unit and actuator type ID.
     * @param name
     * @param unit
     * @param actuatorTypeID
     */
    public ActuatorType(TypeDescription name, UnitID unit, ActuatorTypeID actuatorTypeID) {
        validateActuatorTypeName(name);
        validateUnit(unit);
        validateID(actuatorTypeID);
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
     * Validates the actuator type ID and sets it.
     *
     * @param actuatorTypeID the actuator type ID, must not be null
     */
    private void validateID(ActuatorTypeID actuatorTypeID) {
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("Actuator type ID must not be null.");
        }
        this._id = actuatorTypeID;
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
     * @return unit
     */
    public UnitID getUnit() {
        return _unit;
    }

    /**
     * Method to compare two instances
     *
     * @param object
     * @return true if the instances are equal, false otherwise
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
     * @return hash code
     */
    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    /**
     * Method to get string representation
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return _id + " " + _actuatorTypeName + " " + _unit;
    }
}
