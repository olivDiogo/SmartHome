package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing a sensor.
 */
public class SensorDTO implements DTO {

    public final String deviceID;
    public final String modelPath;
    public final String sensorTypeID;
    public final String sensorID;
    public final String sensorName;


    /**
     * Constructor of SensorDTO.
     *
     * @param deviceID
     * @param modelPath
     * @param sensorTypeID
     * @param sensorID
     * @param sensorName
     */
    public SensorDTO(String deviceID, String modelPath, String sensorTypeID, String sensorID, String sensorName) {
        this.deviceID = deviceID;
        this.modelPath = modelPath;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = sensorID;
        this.sensorName = sensorName;
    }

}

