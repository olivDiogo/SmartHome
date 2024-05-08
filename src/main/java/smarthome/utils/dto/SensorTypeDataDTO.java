package smarthome.utils.dto;

import jakarta.validation.constraints.NotBlank;

public class SensorTypeDataDTO {

  @NotBlank(message = "SensorTypeDescription cannot be empty")
  public String description;

  @NotBlank(message = "UnitID cannot be empty")
  public String unitID;

  /**
   * Constructor for the SensorTypeDataDTO.
   *
   * @param description the description of the sensor type.
   * @param unitID      the unit of measurement for the sensor type.
   */
  public SensorTypeDataDTO(String description, String unitID) {
    this.description = description;
    this.unitID = unitID;
  }
}
