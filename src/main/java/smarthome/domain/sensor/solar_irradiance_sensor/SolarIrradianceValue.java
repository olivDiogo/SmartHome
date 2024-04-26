package smarthome.domain.sensor.solar_irradiance_sensor;

import smarthome.ddd.IValueObject;

public class SolarIrradianceValue implements IValueObject {

  private final int value;

  /**
   * Constructor for SolarIrradianceValue
   *
   * @param value is the value of the solar irradiance sensor
   */
  public SolarIrradianceValue(int value) {
    this.value = value;
  }

  /**
   * Returns the value of the solar irradiance sensor as a string.
   *
   * @return The value of the solar irradiance sensor as a string.
   */
  @Override
  public String toString() {
    return value + "";
  }
}
