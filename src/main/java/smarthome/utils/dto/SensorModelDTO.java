package smarthome.utils.dto;

import smarthome.ddd.IDTO;

/**
 * Represents a Data Transfer Object (DTO) for a sensor model.
 */
public class SensorModelDTO implements IDTO {



  /**
   * The name of the sensor model.
   */
  public final String sensorModelName;

  /**
   * The file path to the sensor model's data.
   */
  public final String modelPath;

  /**
   * Constructs a new {@code SensorModelDTO} with the specified ID, name, and model path.
   *
   * @param sensorModelName the name of the sensor model; should not be {@code null}.
   * @param modelPath       the file path to the sensor model's data; should not be {@code null}.
   */
  public SensorModelDTO(String sensorModelName, String modelPath) {
    this.sensorModelName = sensorModelName;
    this.modelPath = modelPath;
  }

  @Override
  public String toString() {
    return sensorModelName + " " + modelPath;
  }
}
