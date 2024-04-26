package smarthome.mapper;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorModelDTO;

public class SensorModelAssembler implements IAssembler<SensorModel, SensorModelDTO> {

  @Override
  public SensorModelDTO domainToDTO(SensorModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Sensor Model");

    String sensorModelID = domainEntity.getID().toString();
    String sensorModelName = domainEntity.getSensorModelName().toString();
    String sensorModelPath = domainEntity.getModelPath().toString();

    SensorModelDTO sensorModelDTO = new SensorModelDTO(sensorModelID, sensorModelName,
        sensorModelPath);
    return sensorModelDTO;
  }

  @Override
  public List<SensorModelDTO> domainToDTO(List<SensorModel> domainEntities) {
    if (domainEntities == null || domainEntities.isEmpty()) {
      throw new IllegalArgumentException("The list of Sensor Models cannot be null or empty.");
    }
    List<SensorModelDTO> sensorModelsDTO = domainEntities.stream().map(this::domainToDTO).toList();
    return sensorModelsDTO;
  }


}


