package smarthome.utils.dto;

import jakarta.validation.constraints.NotBlank;

public class SensorTypeDataDTO {

  @NotBlank(message = "SensorTypeDescription cannot be empty")
  public String sensorTypeDescription;

  @NotBlank(message = "UnitID cannot be empty")
  public String unitID;

  public SensorTypeDataDTO(String sensorTypeDescription, String unitID) {
    this.sensorTypeDescription = sensorTypeDescription;
    this.unitID = unitID;
  }
}
