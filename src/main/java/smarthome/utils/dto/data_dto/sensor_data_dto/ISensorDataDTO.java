package smarthome.utils.dto.data_dto.sensor_data_dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SensorDataGenericDTOImp.class, name = "genericSensor"),
    @JsonSubTypes.Type(value = SensorDataWithDateDTOImp.class, name = "dateSensor"),
    @JsonSubTypes.Type(value = SensorDataWithGPSDTOImp.class, name = "gpsSensor")
})
public interface ISensorDataDTO {

}
