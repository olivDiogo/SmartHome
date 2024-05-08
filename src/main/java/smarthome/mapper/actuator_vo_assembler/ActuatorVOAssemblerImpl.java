package smarthome.mapper.actuator_vo_assembler;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataGenericDTOImp;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataWithDecimalLimitsDTOImp;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataWithIntegerLimitsDTOImp;
import smarthome.utils.dto.actuator_data_dto.IActuatorDataDTO;

@Component
public class ActuatorVOAssemblerImpl implements IActuatorVOAssembler {

  /**
   * Returns an array of objects that are needed to create an actuator. The generic objects.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  private static Object[] getActuatorParameters(ActuatorDataGenericDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    return new Object[]{deviceID, modelPath, actuatorTypeID, actuatorName};
  }

  /**
   * Returns an array of objects that are needed to create an actuator. The generic objects plus the
   * decimal limits.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  private static Object[] getActuatorParameters(
      ActuatorDataWithDecimalLimitsDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    double minLimit = Double.parseDouble(actuatorDataDTO.minLimit);
    double maxLimit = Double.parseDouble(actuatorDataDTO.maxLimit);
    DecimalLimits limits = new DecimalLimits(minLimit, maxLimit);
    return new Object[]{deviceID, modelPath, actuatorTypeID, actuatorName, limits};
  }

  /**
   * Returns an array of objects that are needed to create an actuator. The generic objects plus the
   * integer limits.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  private static Object[] getActuatorParameters(
      ActuatorDataWithIntegerLimitsDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    int minLimit = Integer.parseInt(actuatorDataDTO.minLimit);
    int maxLimit = Integer.parseInt(actuatorDataDTO.maxLimit);
    IntegerLimits limits = new IntegerLimits(minLimit, maxLimit);
    return new Object[]{deviceID, modelPath, actuatorTypeID, actuatorName, limits};
  }

  /**
   * Returns an array of objects that are needed to create an actuator.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  @Override
  public Object[] getActuatorParameters(IActuatorDataDTO actuatorDataDTO) {
    if (actuatorDataDTO instanceof ActuatorDataGenericDTOImp actuatorDataGenericDTOImp) {
      return getActuatorParameters(actuatorDataGenericDTOImp);
    } else if (actuatorDataDTO
        instanceof ActuatorDataWithDecimalLimitsDTOImp actuatorDataWithDecimalLimitsDTOImp) {
      return getActuatorParameters(actuatorDataWithDecimalLimitsDTOImp);
    } else if (actuatorDataDTO
        instanceof ActuatorDataWithIntegerLimitsDTOImp actuatorDataWithIntegerLimitsDTOImp) {
      return getActuatorParameters(actuatorDataWithIntegerLimitsDTOImp);
    } else {
      throw new IllegalArgumentException("Unsupported actuator data DTO");
    }
  }
}
