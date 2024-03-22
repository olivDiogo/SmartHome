package SmartHomeDDD.ValueObject;
import SmartHomeDDD.ddd.ValueObject;

public class ActuatorName implements ValueObject {

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
}
