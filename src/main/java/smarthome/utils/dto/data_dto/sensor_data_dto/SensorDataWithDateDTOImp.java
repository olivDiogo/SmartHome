/**
 * This class represents the data transfer object for the Sensor data for a sensor with date info
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto.sensor_data_dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


public class SensorDataWithDateDTOImp implements ISensorDataDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "SensorModelPath cannot be empty")
  public final String sensorModelPath;

  @NotBlank(message = "SensorName cannot be empty")
  public final String sensorName;

  @NotBlank(message = "SensorTypeID cannot be empty")
  public final String sensorTypeID;

  @NotBlank(message = "Start Date cannot be empty")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  public final String startDate;

  @NotBlank(message = "End Date cannot be empty")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  public final String endDate;

  public SensorDataWithDateDTOImp(String deviceID, String sensorModelPath, String sensorName,
      String sensorTypeID, String startDate, String endDate) {
    this.deviceID = deviceID;
    this.sensorModelPath = sensorModelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
