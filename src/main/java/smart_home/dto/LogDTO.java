package smart_home.dto;

public class LogDTO {
    private String logID;
    private String deviceID;
    private String sensorID;
    private String sensorTypeID;
    private String reading;
    private String timestamp;
    private String unitID;

    /**
     * Constructs a new LogDTO object with the specified log details.
     */
    public LogDTO(String logID, String deviceID, String sensorID, String sensorTypeID, String reading, String timestamp, String unitID) {
        this.logID = logID;
        this.deviceID = deviceID;
        this.sensorID = sensorID;
        this.sensorTypeID = sensorTypeID;
        this.reading = reading;
        this.timestamp = timestamp;
        this.unitID = unitID;
    }

}
