package smarthome.mapper.sensor_vo_assembler;

import smarthome.utils.dto.sensor_data_dto.ISensorDataDTO;

public interface ISensorVOAssembler {
    Object[] getSensorParameters(ISensorDataDTO sensorDataDTO);
}
