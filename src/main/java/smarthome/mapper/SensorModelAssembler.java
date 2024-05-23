package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.utils.PathEncoder;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorModelDTO;

@Component
public class SensorModelAssembler implements IAssembler<SensorModel, SensorModelDTO> {

  @Override
  public SensorModelDTO domainToDTO(SensorModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Sensor Model");

    String sensorModelName = domainEntity.getName().toString();
    String sensorModelPath = domainEntity.getID().toString();
    sensorModelPath = PathEncoder.encode(sensorModelPath);

    return new SensorModelDTO(sensorModelName,
        sensorModelPath);
  }

  @Override
  public List<SensorModelDTO> domainToDTO(List<SensorModel> domainEntities) {
    if (domainEntities == null) {
      throw new IllegalArgumentException("The list of Sensor Models cannot be null.");
    }

    return domainEntities.stream().map(this::domainToDTO).toList();
  }


}


