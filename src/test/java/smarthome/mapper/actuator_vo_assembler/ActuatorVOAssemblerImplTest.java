package smarthome.mapper.actuator_vo_assembler;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataWithDecimalLimitsDTOImp;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataWithIntegerLimitsDTOImp;
import smarthome.utils.dto.actuator_data_dto.IActuatorDataDTO;
import smarthome.utils.dto.actuator_data_dto.ActuatorDataGenericDTOImp;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActuatorVOAssemblerImplTest {
  /** Tests if the object is instantiated when the attributes are null. */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenUnsupportedActuatorDataDTO() {
    // Arrange
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();
    String message = "Unsupported actuator data DTO";
    IActuatorDataDTO actuatorDataDTO = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> actuatorVOAssembler.getActuatorParameters(actuatorDataDTO));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /** Tests if the object is instantiated when the ActuatorDataDTO is with decimal limits attribute. */
  @Test
  void shouldReturnArrayOfObjectsWhenActuatorDataDTOIsActuatorWithDecimalLimitsDataDTO() {
    // Arrange
    String deviceID = "deviceID";
    String actuatorModelPath = "actuatorModelPath";
    String actuatorName = "actuatorName";
    String actuatorTypeID = "actuatorTypeID";
    String minLimit = "10";
    String maxLimit = "50";
    IActuatorDataDTO actuatorDataDTO =
        new ActuatorDataWithDecimalLimitsDTOImp(
            deviceID, actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    ModelPath modelPath = new ModelPath(actuatorModelPath);
    ActuatorName actuatorName1 = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
    DecimalLimits decimalLimits =
        new DecimalLimits(Double.parseDouble(minLimit), Double.parseDouble(maxLimit));
    Object[] expected = {deviceID1, modelPath, actuatorTypeID1, actuatorName1, decimalLimits};

    // Act
    Object[] result = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }

  /** Tests if the object is instantiated when the ActuatorDataDTO is with generic data attribute. */
  @Test
  void shouldReturnArrayOfObjectsWhenActuatorDataDTOIsActuatorGenericDataDTOImp() {
    // Arrange
    String deviceID = "deviceID";
    String actuatorModelPath = "actuatorModelPath";
    String actuatorName = "actuatorName";
    String actuatorTypeID = "actuatorTypeID";
    IActuatorDataDTO actuatorDataDTO =
        new ActuatorDataGenericDTOImp(deviceID, actuatorModelPath, actuatorName, actuatorTypeID);
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    ModelPath modelPath = new ModelPath(actuatorModelPath);
    ActuatorName actuatorName1 = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
    Object[] expected = {deviceID1, modelPath, actuatorTypeID1, actuatorName1};

    // Act
    Object[] result = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }

  /** Tests if the object is instantiated when the ActuatorDataDTO is with integer limits attribute. */
  @Test
  void shouldReturnArrayOfObjectsWhenActuatorDataDTOIsActuatorDataWithIntegerLimitsDTOImp() {
    // Arrange
    String deviceID = "deviceID";
    String actuatorModelPath = "actuatorModelPath";
    String actuatorName = "actuatorName";
    String actuatorTypeID = "actuatorTypeID";
    String minLimit = "1";
    String maxLimit = "10";
    IActuatorDataDTO actuatorDataDTO =
        new ActuatorDataWithIntegerLimitsDTOImp(
            deviceID, actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    ModelPath modelPath = new ModelPath(actuatorModelPath);
    ActuatorName actuatorName1 = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
    IntegerLimits integerLimits =
        new IntegerLimits(Integer.parseInt(minLimit), Integer.parseInt(maxLimit));

    Object[] expected = {deviceID1, modelPath, actuatorTypeID1, actuatorName1, integerLimits};

    // Act
    Object[] result = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }
}
