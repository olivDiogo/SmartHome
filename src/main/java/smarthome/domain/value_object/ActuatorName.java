package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class ActuatorName implements IValueObject {

    private final String actuatorName;

    public ActuatorName(String actuatorName) {
        validateActuatorName(actuatorName);
        this.actuatorName = actuatorName;
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
        return actuatorName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }

        if (object instanceof ActuatorName actuatorName) {

            return this.actuatorName.equals(actuatorName.actuatorName);
        }
        return false;
    }

    /**
     * @return the hash code of the object.
     */
    @Override
    public int hashCode() {
        return actuatorName.hashCode();
    }
}
