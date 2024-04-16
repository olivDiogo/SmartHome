package smart_home.dto.sensor_data_dto;

public class SensorDataGenericDTOImp implements ISensorDataDTO {
    /**
     * The data needed to create a sensor.
     */
    public final String deviceID;
    public final String sensorModelPath;
    public final String sensorName;
    public final String sensorTypeID;



    /**
     * Constructs a new SensorDataDTO object with the specified sensor details.
     *
     * @param deviceID        The unique identifier of the device.
     * @param sensorModelPath The file path to the sensor model's data.
     * @param sensorName      The name of the sensor.
     * @param sensorTypeID    The unique identifier of the sensor type.
     */
    public SensorDataGenericDTOImp(String deviceID, String sensorModelPath, String sensorName, String sensorTypeID) {
        this.deviceID = deviceID;
        this.sensorModelPath = sensorModelPath;
        this.sensorName = sensorName;
        this.sensorTypeID = sensorTypeID;

    }
}
