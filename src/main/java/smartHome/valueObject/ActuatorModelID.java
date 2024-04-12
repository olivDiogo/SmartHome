package smartHome.valueObject;

import smartHome.ddd.IDomainID;

public class ActuatorModelID implements IDomainID {
    private final String _actuatorModelId;

    public ActuatorModelID(String actuatorModelId) throws IllegalArgumentException {
        validationActuatorModelID(actuatorModelId);
        this._actuatorModelId = actuatorModelId;
    }

    private void validationActuatorModelID(String actuatorModelID) {
        if (actuatorModelID == null || actuatorModelID.isBlank() || actuatorModelID.isEmpty())
            throw new IllegalArgumentException("The value of 'actuatorModelID' should not null, blank, or empty.");
    }

    @Override
    public String getID() {
        return _actuatorModelId;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ActuatorModelID actuatorModelID = (ActuatorModelID) object;
        return _actuatorModelId.equals(actuatorModelID._actuatorModelId);
    }

    @Override
    public int hashCode() {
        return _actuatorModelId.hashCode();
    }
}
