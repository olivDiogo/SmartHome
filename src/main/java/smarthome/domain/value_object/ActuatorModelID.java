package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class ActuatorModelID implements IDomainID {

  private final String id;

  public ActuatorModelID(String id) throws IllegalArgumentException {
    validationActuatorModelID(id);
    this.id = id;
  }

  private void validationActuatorModelID(String actuatorModelID) {
    if (actuatorModelID == null || actuatorModelID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'actuatorModelID' should not null, blank, or empty.");
    }
  }

  @Override
  public String getID() {
    return id;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof ActuatorModelID actuatorModelID) {

      return this.id.equals(actuatorModelID.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
