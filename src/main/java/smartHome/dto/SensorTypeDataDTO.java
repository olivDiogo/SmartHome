package smartHome.dto;

public class SensorTypeDataDTO {
    public String sensorTypeDescription;
    public String unitID;

    public SensorTypeDataDTO(String sensorTypeDescription, String unitID) {
        this.sensorTypeDescription = sensorTypeDescription;
        this.unitID = unitID;
    }
}
