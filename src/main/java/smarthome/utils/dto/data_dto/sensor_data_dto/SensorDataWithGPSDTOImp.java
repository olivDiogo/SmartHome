/**
 * This class represents the data transfer object for the Sensor data for a sensor with gps info
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto.sensor_data_dto;

import jakarta.validation.constraints.NotBlank;


public class SensorDataWithGPSDTOImp implements ISensorDataDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "SensorModelPath cannot be empty")
  public final String sensorModelPath;

  @NotBlank(message = "SensorName cannot be empty")
  public final String sensorName;

  @NotBlank(message = "SensorTypeID cannot be empty")
  public final String sensorTypeID;

  @NotBlank(message = "Latitude cannot be empty")
  public final String latitude;

  @NotBlank(message = "Longitude cannot be empty")
  public final String longitude;

  public SensorDataWithGPSDTOImp(String deviceID, String sensorModelPath, String sensorName,
      String sensorTypeID, String latitude, String longitude) {
    this.deviceID = deviceID;
    this.sensorModelPath = sensorModelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
