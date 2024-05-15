package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.utils.dto.SensorModelDTO;

class SensorModelAssemblerTest {

  /**
   * Test that the method domainToDTO converts a SensorModel to a SensorModelDTO.
   */
  @Test
  void shouldConvertSensorModelToSensorModelDTO_whenSensorModelIsValid() {

    // Arrange
    String sensorModelID = "1";
    ModelPath sensorModelIDDouble = mock(ModelPath.class);
    when(sensorModelIDDouble.toString()).thenReturn(sensorModelID);

    String sensorModelName = "Temperature";
    SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
    when(sensorModelNameDouble.toString()).thenReturn(sensorModelName);

    String sensorModelPath = "path";
    ModelPath sensorModelPathDouble = mock(ModelPath.class);
    when(sensorModelPathDouble.toString()).thenReturn(sensorModelPath);

    SensorModel sensorModelDouble = mock(SensorModel.class);
    when(sensorModelDouble.getID()).thenReturn(sensorModelIDDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorModelNameDouble);
    when(sensorModelDouble.getModelPath()).thenReturn(sensorModelPathDouble);

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    String expected = sensorModelID + " " + sensorModelName + " " + sensorModelPath;

    // Act
    SensorModelDTO sensorModelDTO = sensorModelAssembler.domainToDTO(sensorModelDouble);

    // Assert
    assertEquals(expected, sensorModelDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an IllegalArgumentException when the SensorModel is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorModelIsNull() {

    // Arrange
    SensorModel sensorModel = null;
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    String expected = "Sensor Model is required";

    // Act and Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class, () -> sensorModelAssembler.domainToDTO(sensorModel));
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test that the method domainToDTO converts a list of SensorModel to a list of SensorModelDTO.
   */
  @Test
  void shouldConvertSensorModelListToSensorModelDTOList_whenSensorModelListIsValid()
      throws EmptyReturnException {

    // Arrange
    /* Sensor Model 1 */
    String sensorModelID = "1";
    ModelPath sensorModelIDDouble = mock(ModelPath.class);
    when(sensorModelIDDouble.toString()).thenReturn(sensorModelID);

    String sensorModelName = "Temperature";
    SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
    when(sensorModelNameDouble.toString()).thenReturn(sensorModelName);

    String sensorModelPath = "path";
    ModelPath sensorModelPathDouble = mock(ModelPath.class);
    when(sensorModelPathDouble.toString()).thenReturn(sensorModelPath);

    SensorModel sensorModelDouble = mock(SensorModel.class);
    when(sensorModelDouble.getID()).thenReturn(sensorModelIDDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorModelNameDouble);
    when(sensorModelDouble.getModelPath()).thenReturn(sensorModelPathDouble);

    /* Sensor Model 2 */
    String sensorModelID2 = "2";
    ModelPath sensorModelIDDouble2 = mock(ModelPath.class);
    when(sensorModelIDDouble2.toString()).thenReturn(sensorModelID2);

    String sensorModelName2 = "Temperature";
    SensorModelName sensorModelNameDouble2 = mock(SensorModelName.class);
    when(sensorModelNameDouble2.toString()).thenReturn(sensorModelName2);

    String sensorModelPath2 = "path";
    ModelPath sensorModelPathDouble2 = mock(ModelPath.class);
    when(sensorModelPathDouble2.toString()).thenReturn(sensorModelPath2);

    SensorModel sensorModelDouble2 = mock(SensorModel.class);
    when(sensorModelDouble2.getID()).thenReturn(sensorModelIDDouble2);
    when(sensorModelDouble2.getName()).thenReturn(sensorModelNameDouble2);
    when(sensorModelDouble2.getModelPath()).thenReturn(sensorModelPathDouble2);

    List sensorModels = List.of(sensorModelDouble, sensorModelDouble2);

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    SensorModelDTO sensorModelDTO =
        new SensorModelDTO(sensorModelID, sensorModelName, sensorModelPath);
    SensorModelDTO sensorModelDTO2 =
        new SensorModelDTO(sensorModelID2, sensorModelName2, sensorModelPath2);
    List<SensorModelDTO> expected = List.of(sensorModelDTO, sensorModelDTO2);

    // Act
    List<SensorModelDTO> sensorModelsDTO = sensorModelAssembler.domainToDTO(sensorModels);

    // Assert
    assertEquals(expected.toString(), sensorModelsDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an IllegalArgumentException when the list of
   * SensorModel is null
   */
  @Test
  void shouldThrowException_whenSensorModelListIsNull() {

    // Arrange
    List<SensorModel> sensorModels = null;
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    String expected = "The list of Sensor Models cannot be null.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      sensorModelAssembler.domainToDTO(sensorModels);
    });
    String result = exception.getMessage();
    assertEquals(expected, result);
  }
}
