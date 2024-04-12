package smartHome.assembler;

import smartHome.dto.SensorModelDTO;
import smartHome.domain.sensorModel.SensorModel;
import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorModelName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    when(sensorModelDouble.getSensorModelName()).thenReturn(sensorModelNameDouble);
    when(sensorModelDouble.getModelPath()).thenReturn(sensorModelPathDouble);

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    String expected = sensorModelID + " " + sensorModelName + " " + sensorModelPath;

    // Act
    SensorModelDTO sensorModelDTO = sensorModelAssembler.domainToDTO(sensorModelDouble);

    // Assert
    assertEquals(expected, sensorModelDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an IllegalArgumentException when the SensorModel is null.
   */
  @Test
  void shouldThrowException_whenSensorModelIsNull() {

    // Arrange
    SensorModel sensorModel = null;
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> sensorModelAssembler.domainToDTO(sensorModel));
  }

  /**
   * Test that the method domainToDTO converts a list of SensorModel to a list of SensorModelDTO.
   */
  @Test
  void shouldConvertSensorModelListToSensorModelDTOList_whenSensorModelListIsValid() {

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
    when(sensorModelDouble.getSensorModelName()).thenReturn(sensorModelNameDouble);
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
    when(sensorModelDouble2.getSensorModelName()).thenReturn(sensorModelNameDouble2);
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
   * Test that the method domainToDTO throws an IllegalArgumentException when the list of SensorModel is null
   */
  @Test
  void shouldThrowException_whenSensorModelListIsNull() {

    // Arrange
    List<SensorModel> sensorModels = null;
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      sensorModelAssembler.domainToDTO(sensorModels);
    });
    //Assert
    String expected = "The list of Sensor Models cannot be null or empty.";
    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /**
   * Test that the method domainToDTO throws an IllegalArgumentException when the list of SensorModel is empty
   */
  @Test
  void shouldThrowException_whenSensorModelListIsEmpty() {

    // Arrange
    List<SensorModel> sensorModels = List.of();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorModelAssembler.domainToDTO(sensorModels);
            });
    // Assert
    String expected = "The list of Sensor Models cannot be null or empty.";
    String result = exception.getMessage();
    assertEquals(expected, result);
    }
}
