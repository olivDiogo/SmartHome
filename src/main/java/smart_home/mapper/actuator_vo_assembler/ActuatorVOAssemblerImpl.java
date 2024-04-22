package smart_home.mapper.actuator_vo_assembler;

import smart_home.dto.actuator_data_dto.*;
import smart_home.dto.actuator_data_dto.IActuatorDataDTO;
import smart_home.value_object.*;

public class ActuatorVOAssemblerImpl implements IActuatorVOAssembler {

  @Override
  public Object[] getActuatorParameters(IActuatorDataDTO actuatorDataDTO) {
    if (actuatorDataDTO instanceof ActuatorDataGenericDTOImp actuatorDataGenericDTOImp) {
      return getActuatorParameteres(actuatorDataGenericDTOImp);
    } else if (actuatorDataDTO
        instanceof ActuatorDataWithDecimalLimitsDTOImp actuatorDataWithDecimalLimitsDTOImp) {
      return getActuatorParameteres(actuatorDataWithDecimalLimitsDTOImp);
    } else if (actuatorDataDTO
        instanceof ActuatorDataWithIntegerLimitsDTOImp actuatorDataWithIntegerLimitsDTOImp) {
      return getActuatorParameteres(actuatorDataWithIntegerLimitsDTOImp);
    } else {
      throw new IllegalArgumentException("Unsupported actuator data DTO");
    }
  }

  private static Object[] getActuatorParameteres(ActuatorDataGenericDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    return new Object[] {deviceID, modelPath, actuatorTypeID, actuatorName};
  }

  private static Object[] getActuatorParameteres(
      ActuatorDataWithDecimalLimitsDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    double minLimit = Double.parseDouble(actuatorDataDTO.minLimit);
    double maxLimit = Double.parseDouble(actuatorDataDTO.maxLimit);
    DecimalLimits limits = new DecimalLimits(minLimit, maxLimit);
    return new Object[] {deviceID, modelPath, actuatorTypeID, actuatorName, limits};
  }

  private static Object[] getActuatorParameteres(
      ActuatorDataWithIntegerLimitsDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    int minLimite = Integer.parseInt(actuatorDataDTO.minLimit);
    int maxLimite = Integer.parseInt(actuatorDataDTO.maxLimit);
    IntegerLimits limits = new IntegerLimits(minLimite, maxLimite);
    return new Object[] {deviceID, modelPath, actuatorTypeID, actuatorName, limits};
  }
}
