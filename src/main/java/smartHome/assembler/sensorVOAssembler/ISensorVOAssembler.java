package smartHome.assembler.sensorVOAssembler;
import smartHome.dto.sensorDataDto.ISensorDataDTO;

public interface ISensorVOAssembler {
    Object[] getSensorParameters(ISensorDataDTO sensorDataDTO);
}
