package smarthome.utils.dto.sensor_data_dto;

public class SensorDataWithGPSDTOImp implements ISensorDataDTO {

  /**
   * The data needed to create a sensor.
   */
  public final String deviceID;
  public final String sensorModelPath;
  public final String sensorName;
  public final String sensorTypeID;
  public final String latitude;
  public final String longitude;

  /**
   * Constructs a new SensorDataDTO object with the specified sensor details.
   *
   * @param deviceID        The unique identifier of the device.
   * @param sensorModelPath The file path to the sensor model's data.
   * @param sensorName      The name of the sensor.
   * @param sensorTypeID    The unique identifier of the sensor type.
   * @param latitude        The latitude of the sensor.
   * @param longitude       The longitude of the sensor.
   */
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
