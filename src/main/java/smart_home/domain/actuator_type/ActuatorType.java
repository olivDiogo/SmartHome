package smart_home.domain.actuator_type;

import org.apache.commons.lang3.Validate;
import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
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
      Validator.validateNotNull(name, "Type Description");
      Validator.validateNotNull(unit, "Unit ID");
        generateID(name);

        this._actuatorTypeName = name;
        this._unit = unit;
    }

    /**
     * Creates a new {@link ActuatorType} instance using the provided actuator type name, unit and actuator type ID.
     * @param name the actuator type name, must not be null
     * @param unit the unit of the actuator type, must not be null
     * @param actuatorTypeID the actuator type ID, must not be null
     */
    public ActuatorType(TypeDescription name, UnitID unit, ActuatorTypeID actuatorTypeID) {
       Validator.validateNotNull(name, "Type Description");
        Validator.validateNotNull(unit, "Unit ID");
        Validator.validateNotNull(actuatorTypeID, "Actuator Type ID");

        this._actuatorTypeName = name;
        this._unit = unit;
        this._id = actuatorTypeID;
    }

    private void generateID(TypeDescription name) {
        _id = new ActuatorTypeID(name.toString());
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
     * @param object the object to compare
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
