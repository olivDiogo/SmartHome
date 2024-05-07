package smarthome.utils.dto.actuator_data_dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ActuatorDataGenericDTOImp.class, name = "actuatorDataGenericDTOImp"),
    @JsonSubTypes.Type(value = ActuatorDataWithIntegerLimitsDTOImp.class, name = "actuatorDataWithIntegerLimitsDTOImp")
})
public interface IActuatorDataDTO {

}
