package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class ActuatorName implements IValueObject {

    private final String _actuatorName;

    public ActuatorName(String actuatorName) {
        validateActuatorName(actuatorName);
        this._actuatorName = actuatorName;
    }

    private void validateActuatorName(String actuatorName) {
        if (actuatorName == null || actuatorName.isEmpty() || actuatorName.isBlank()) {
            throw new IllegalArgumentException("The actuator name cannot be null, blank, or empty.");
        }
        if (!actuatorName.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The actuator name can only contain letters and numbers.");
        }
    }

    public String getActuatorName() {
        return _actuatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActuatorName that = (ActuatorName) o;

        return _actuatorName.equals(that._actuatorName);
    }

    /**
     * @return the hash code of the object.
     */
    @Override
    public int hashCode() {
        return _actuatorName.hashCode();
    }
}
