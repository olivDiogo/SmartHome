package smarthome.domain.actuator.set_integer_actuator;

import smarthome.ddd.IActuatorValue;

public class SetIntegerValue implements IActuatorValue {

  private final int value;

  /**
   * Constructor for SetIntegerValue
   *
   * @param value the value of the integer actuator
   */
  public SetIntegerValue(int value) {
    this.value = value;
  }

  /**
   * Method to check if the value object is equal to another object.
   *
   * @return true if the value object is equal to the other object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SetIntegerValue objectSetIntegerValue) {
      return value == objectSetIntegerValue.value;
    }
    return false;
  }

  /**
   * Returns the hash code of the integer actuator.
   *
   * @return the hash code of the integer actuator
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(value);
  }

  /**
   * Returns the value of the integer actuator as a string.
   *
   * @return the value of the integer actuator as a string
   */
  @Override
  public String toString() {
    return value + "";
  }
}
