package smarthome.domain.actuator.blind_roller_actuator;

import smarthome.ddd.IValueObject;

public class BlindRollerValue implements IValueObject {

  private final int value;

  /**
   * Constructor for BlindRollerValue
   *
   * @param value It must be between 0 and 100.
   */
  public BlindRollerValue(int value) {
    validateValue(value);
    this.value = value;
  }

  /**
   * Validates the value of the blind roller.
   *
   * @param value The value to be validated.
   */
  private void validateValue(int value) {
    if (value < 0 || value > 100) {
      throw new IllegalArgumentException("The value must be between 0 and 100.");
    }
  }

  /**
   * Gets the value of the blind roller.
   *
   * @return The value of the blind roller.
   */
  public String toString() {
    return this.value + "";
  }

}
