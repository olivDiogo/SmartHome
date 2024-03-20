package SmartHomeDDD.ValueObject;

public class ActuatorName {

    private String _actuatorName;

    public ActuatorName(String actuatorName) {
        setActuatorName(actuatorName);
    }

    private void setActuatorName(String actuatorName) {
        if (actuatorName == null || actuatorName.isEmpty() || actuatorName.isBlank()) {
            throw new IllegalArgumentException("The actuator name cannot be null, blank, or empty.");
        }
        if (!actuatorName.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The actuator name can only contain letters and numbers.");
        }
        else {
            _actuatorName = actuatorName.trim();
        }
    }

}
