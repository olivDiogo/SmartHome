/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

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
