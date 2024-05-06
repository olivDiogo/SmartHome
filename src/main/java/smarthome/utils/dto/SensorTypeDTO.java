package smarthome.utils.dto;

import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing a sensor type.
 */
public class SensorTypeDTO implements IDTO {

  public final String sensorTypeID;
  public final String sensorTypeDescription;
  public final String unitID;

  /**
   * Constructs a new SensorTypeDTO object.
   *
   * @param sensorTypeDescription The description of the sensor type.
   * @param unitID                  The unit of measurement for the sensor type.
   */
  public SensorTypeDTO(String sensorTypeID, String sensorTypeDescription, String unitID) {
    this.sensorTypeID = sensorTypeID;
    this.sensorTypeDescription = sensorTypeDescription;
    this.unitID = unitID;
  }

  @Override
  public String toString() {
    return sensorTypeID + " " + sensorTypeDescription + " " + unitID;
  }
}
