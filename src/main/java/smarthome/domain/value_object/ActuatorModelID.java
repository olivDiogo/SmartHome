package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class ActuatorModelID implements IDomainID {

  private final String actuatorModelID;

  public ActuatorModelID(String actuatorModelID) throws IllegalArgumentException {
    validationActuatorModelID(actuatorModelID);
    this.actuatorModelID = actuatorModelID;
  }

  private void validationActuatorModelID(String actuatorModelID) {
    if (actuatorModelID == null || actuatorModelID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'actuatorModelID' should not null, blank, or empty.");
    }
  }

  @Override
  public String getID() {
    return actuatorModelID;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof ActuatorModelID actuatorModelID) {

      return this.actuatorModelID.equals(actuatorModelID.actuatorModelID);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return actuatorModelID.hashCode();
  }
}
