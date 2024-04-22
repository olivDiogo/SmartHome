package smart_home.mapper.actuator_vo_assembler;

import smart_home.dto.actuator_data_dto.IActuatorDataDTO;

public interface IActuatorVOAssembler {
    Object[] getActuatorParameters(IActuatorDataDTO actuatorDataDTO);
}
