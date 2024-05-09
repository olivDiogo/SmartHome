package smarthome.utils.dto.data_dto.sensor_data_dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SensorDataGenericDTOImp.class, name = "sensorDataGenericDTOImp"),
    @JsonSubTypes.Type(value = SensorDataWithDateDTOImp.class, name = "sensorDataWithDateDTOImp"),
    @JsonSubTypes.Type(value = SensorDataWithGPSDTOImp.class, name = "sensorDataWithGPSDTOImp")
})
public interface ISensorDataDTO {

}
