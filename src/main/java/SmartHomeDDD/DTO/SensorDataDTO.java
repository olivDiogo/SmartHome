package SmartHomeDDD.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SensorDataDTO {
    /**
     * The data needed to create a sensor.
     */
    public final String deviceID;
    public final String sensorModelPath;
    public final String sensorName;
    public final String sensorTypeID;
    public final ArrayList<Double> coordinates;
    public final ArrayList<LocalDateTime> dateTimes;


    /**
     * Constructs a new SensorDataDTO object with the specified sensor details.
     *
     * @param deviceID        The unique identifier of the device.
     * @param sensorModelPath The file path to the sensor model's data.
     * @param sensorName      The name of the sensor.
     * @param sensorTypeID    The unique identifier of the sensor type.
     */
    public SensorDataDTO(String deviceID, String sensorModelPath, String sensorName, String sensorTypeID, ArrayList<Double> coordinates, ArrayList<LocalDateTime> dateTimes) {
        this.deviceID = deviceID;
        this.sensorModelPath = sensorModelPath;
        this.sensorName = sensorName;
        this.sensorTypeID = sensorTypeID;
        this.coordinates = coordinates;
        this.dateTimes = dateTimes;
    }
}
