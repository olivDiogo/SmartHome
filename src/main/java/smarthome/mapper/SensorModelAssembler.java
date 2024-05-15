package smarthome.mapper;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorModelDTO;

public class SensorModelAssembler implements IAssembler<SensorModel, SensorModelDTO> {

  @Override
  public SensorModelDTO domainToDTO(SensorModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Sensor Model");

    String sensorModelID = domainEntity.getID().toString();
    String sensorModelName = domainEntity.getName().toString();
    String sensorModelPath = domainEntity.getModelPath().toString();

    return new SensorModelDTO(sensorModelID, sensorModelName,
        sensorModelPath);
  }

  @Override
  public List<SensorModelDTO> domainToDTO(List<SensorModel> domainEntities)
      throws EmptyReturnException {
    if (domainEntities == null) {
      throw new IllegalArgumentException("The list of Sensor Models cannot be null.");
    }

    return domainEntities.stream().map(this::domainToDTO).toList();
  }


}


