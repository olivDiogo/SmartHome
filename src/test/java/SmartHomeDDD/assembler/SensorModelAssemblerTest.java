package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.SensorModelDTO;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorModelAssemblerTest {

    @Test
    void shouldConvertSensorModelToSensorModelDTO_whenSensorModelIsValid() {

        //Arrange
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

        //Act
        SensorModelDTO sensorModelDTO = sensorModelAssembler.domainToDTO(sensorModelDouble);

        //Assert
        assertEquals(sensorModelID, sensorModelDTO.sensorModelID);
    }

    @Test
    void shouldThrowException_whenSensorModelIsNull() {

        //Arrange
        SensorModel sensorModel = null;
        SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> sensorModelAssembler.domainToDTO(sensorModel));
    }

    @Test
    void shouldConvertSensorModelListToSensorModelDTOList_whenSensorModelListIsValid() {

        //Arrange

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

        //Act
        List<SensorModelDTO> sensorModelsDTO = sensorModelAssembler.domainToDTO(sensorModels);

        //Assert
        assertEquals(sensorModelID, sensorModelsDTO.get(0).sensorModelID);
        assertEquals(sensorModelID2, sensorModelsDTO.get(1).sensorModelID);
    }

    @Test
    void shouldThrowException_whenSensorModelListIsNull() {

        //Arrange
        List<SensorModel> sensorModels = null;
        SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> sensorModelAssembler.domainToDTO(sensorModels));
    }

    @Test
    void shouldThrowException_whenSensorModelListIsEmpty() {

        //Arrange
        List<SensorModel> sensorModels = List.of();
        SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> sensorModelAssembler.domainToDTO(sensorModels));
    }

}