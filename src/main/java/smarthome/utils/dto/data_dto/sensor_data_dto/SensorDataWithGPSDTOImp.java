/**
 * This class represents the data transfer object for the Sensor data for a sensor with gps info
 * to be received from the client.
 */

package smarthome.utils.dto.data_dto.sensor_data_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SensorDataWithGPSDTOImp implements ISensorDataDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "SensorModelPath cannot be empty")
  public final String sensorModelPath;

  @NotBlank(message = "SensorTypeID cannot be empty")
  public final String sensorTypeID;

  @NotBlank(message = "SensorName cannot be empty")
  public final String sensorName;

  @NotBlank(message = "Latitude cannot be empty")
  public final String latitude;

  @NotBlank(message = "Longitude cannot be empty")
  public final String longitude;

}
