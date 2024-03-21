package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.TypeDescription;

public class ActuatorType {

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
        validateActuatorTypeName(actuatorTypeName);
        this._actuatorTypeName = actuatorTypeName;
    }

    /**
     * Validates the actuator type name and sets it.
     *
     * @param actuatorTypeName the actuator type name, must not be null
     */
    private void validateActuatorTypeName(TypeDescription actuatorTypeName) {
        if (actuatorTypeName == null)
            throw new IllegalArgumentException("Actuator type name must not be null.");
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
        return _actuatorTypeName.equals(that._actuatorTypeName);
    }

}
