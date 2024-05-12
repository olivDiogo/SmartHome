package smarthome.utils.dto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing an actuator type.
 */
@Getter
public class ActuatorTypeDTO extends RepresentationModel<ActuatorTypeDTO> implements IDTO {

  /**
   * Description of the actuator type.
   */
  private final String actuatorTypeID;
  private final String actuatorTypeDescription;
  private final String unit;

  /**
   * Constructs a new ActuatorTypeDTO object.
   *
   * @param actuatorTypeID The description of the actuator type.
   */
  public ActuatorTypeDTO(String actuatorTypeID, String actuatorTypeDescription, String unit) {
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorTypeDescription = actuatorTypeDescription;
    this.unit = unit;
  }

  /**
   * Returns a string representation of the ActuatorTypeDTO object.
   *
   * @return A string representation of the ActuatorTypeDTO object.
   */
  @Override
  public String toString() {
    return actuatorTypeID + " " + actuatorTypeDescription + " " + unit;
  }
}

