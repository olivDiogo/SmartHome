package smart_home.mapper.actuator_vo_assembler;

import org.junit.jupiter.api.Test;
import smart_home.dto.actuator_data_dto.ActuatorDataWithDecimalLimitsDTOImp;
import smart_home.dto.actuator_data_dto.ActuatorDataWithIntegerLimitsDTOImp;
import smart_home.dto.actuator_data_dto.IActuatorDataDTO;
import smart_home.dto.actuator_data_dto.ActuatorDataGenericDTOImp;
import smart_home.value_object.*;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActuatorVOAssemblerImplTest {
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
  ;

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
