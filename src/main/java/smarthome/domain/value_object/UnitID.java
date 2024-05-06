package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

/**
 * This class ensures that the measurement ID adheres to specific validation rules before it is
 * assigned.
 */
public class UnitID implements IDomainID {

  private final String UnitID;

  /**
   * Constructs a new MeasurementID instance after validating the provided ID.
   *
   * @param UnitID The string representation of the measurement ID. It must not be null, empty, or
   *               blank.
   * @throws IllegalArgumentException if the UnitID is null, empty, or blank.
   */
  public UnitID(String UnitID) {
    validateID(UnitID);
    this.UnitID = UnitID.trim();
  }

  /**
   * Validates the given measurement ID.
   *
   * @param UnitID The measurement ID to validate.
   * @throws IllegalArgumentException if the UnitID is null, empty, or blank.
   */
  private void validateID(String UnitID) {
    if (UnitID == null || UnitID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'UnitID' should not null, blank, or empty.");
    }
  }

  /**
   * Retrieves the measurement ID.
   *
   * @return The measurement ID as a string.
   */
  public String getID() {
    return UnitID;
  }

  /**
   * Checks if this MeasurementID is equal to another object. Two MeasurementID instances are
   * considered equal if their IDs are equal.
   *
   * @param o The object to compare this instance against.
   * @return true if the given object is an instance of MeasurementID and has an equal ID; false
   * otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof UnitID objectUnitId) {
      return this.UnitID.equals(objectUnitId.UnitID);
    }
    return false;
  }

  /**
   * Generates a hash code for this MeasurementID.
   *
   * @return The hash code of the measurement ID.
   */
  @Override
  public int hashCode() {
    return UnitID.hashCode();
  }

  /**
   * Returns the string representation of the measurement ID.
   *
   * @return The measurement ID as a string.
   */
  @Override
  public String toString() {
    return UnitID;
  }
}
