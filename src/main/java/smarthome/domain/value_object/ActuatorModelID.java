package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class ActuatorModelID implements IDomainID {
    private final String actuatorModelId;

    public ActuatorModelID(String actuatorModelId) throws IllegalArgumentException {
        validationActuatorModelID(actuatorModelId);
        this.actuatorModelId = actuatorModelId;
    }

    private void validationActuatorModelID(String actuatorModelID) {
        if (actuatorModelID == null || actuatorModelID.isBlank()) {
            throw new IllegalArgumentException("The value of 'actuatorModelID' should not null, blank, or empty.");
        }
    }

    @Override
    public String getID() {
        return actuatorModelId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof ActuatorModelID actuatorModelID) {

            return this.actuatorModelId.equals(actuatorModelID.actuatorModelId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return actuatorModelId.hashCode();
    }
}
