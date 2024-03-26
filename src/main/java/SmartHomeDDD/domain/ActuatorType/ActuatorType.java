package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.ActuatorTypeID;
import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

public class ActuatorType implements AggregateRoot<ActuatorTypeID> {

    /**
     * The actuator type ID.
     */
    private ActuatorTypeID _actuatorTypeID;
    /**
     * The actuator type name.
     */
    private TypeDescription _actuatorTypeName;

    /**
     * Creates a new {@link ActuatorType} instance using the provided actuator type name.
     *
     * @param actuatorTypeName the actuator type name, must not be null
     */
    public ActuatorType(TypeDescription actuatorTypeName) {
        generateID();
        validateActuatorTypeName(actuatorTypeName);
    }

    private void generateID() {
        _actuatorTypeID = new ActuatorTypeID(UUID.randomUUID().toString());
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
     * Gets the actuator type name.
     * @return the actuator type name
     */
    @Override
    public ActuatorTypeID getID() {
        return _actuatorTypeID;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActuatorType)) return false;
        ActuatorType that = (ActuatorType) o;
        return _actuatorTypeID.toString().equals(that._actuatorTypeID.toString());
    }

    @Override
    public String toString() {
        return "ActuatorType{" +
                "_actuatorTypeID=" + _actuatorTypeID +
                ", _actuatorTypeName=" + _actuatorTypeName +
                '}';
    }

}
