package smart_home.mapper.sensor_vo_assembler;

import smart_home.dto.sensor_data_dto.ISensorDataDTO;

public interface ISensorVOAssembler {
    Object[] getSensorParameters(ISensorDataDTO sensorDataDTO);
}
