package smarthome.utils.dto;

import smarthome.ddd.IDTO;

/**
 * ActuatorModelDTO is a DTO class that represents the actuator model.
 */
public class ActuatorModelDTO implements IDTO {

  /**
   * Attributes of class ActuatorModelDTO.
   */
  public String actuatorModelPath;

  /**
   * Attributes of class ActuatorModelDTO.
   */
  public String actuatorModelName;



  /**
   * Constructor of class ActuatorModelDTO.
   */
  public ActuatorModelDTO(String actuatorModelPath, String actuatorModelName) {
    this.actuatorModelPath = actuatorModelPath;
    this.actuatorModelName = actuatorModelName;
  }

  @Override
  public String toString() {
    return actuatorModelPath + " " + actuatorModelName;
  }
}
