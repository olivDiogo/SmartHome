package SmartHomeDDD.ValueObject;

public class ActuatorName {

    private String _actuatorName;

    public ActuatorName(String actuatorName) {
        setActuatorName(actuatorName);
    }

    private void setActuatorName(String actuatorName) {
        if (actuatorName == null || actuatorName.isEmpty() || actuatorName.isBlank()) {
            throw new IllegalArgumentException("The actuator name cannot be null, blank, or empty.");
        } else {
            _actuatorName = actuatorName.trim();
        }
    }

}
