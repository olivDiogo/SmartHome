package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class ActuatorName implements IValueObject {

    private final String _actuatorName;

    public ActuatorName(String actuatorName) {
        validateActuatorName(actuatorName);
        this._actuatorName = actuatorName;
    }

    private void validateActuatorName(String actuatorName) {
        if (actuatorName == null || actuatorName.isBlank()) {
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
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }

        if (object instanceof ActuatorName actuatorName) {

            return this._actuatorName.equals(actuatorName._actuatorName);
        }
        return false;
    }

    /**
     * @return the hash code of the object.
     */
    @Override
    public int hashCode() {
        return _actuatorName.hashCode();
    }
}
