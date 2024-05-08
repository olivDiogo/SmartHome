package smarthome.utils.dto;

import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing a sensor type.
 */
public class SensorTypeDTO implements IDTO {

  public final String sensorTypeID;
  public final String description;
  public final String unitID;

  /**
   * Constructs a new SensorTypeDTO object.
   *
   * @param description The description of the sensor type.
   * @param unitID                  The unit of measurement for the sensor type.
   */
  public SensorTypeDTO(String sensorTypeID, String description, String unitID) {
    this.sensorTypeID = sensorTypeID;
    this.description = description;
    this.unitID = unitID;
  }

  @Override
  public String toString() {
    return sensorTypeID + " " + description + " " + unitID;
  }
}
