package smartHome.persistence.assembler;

import org.junit.jupiter.api.Test;
import smartHome.domain.sensorModel.ISensorModelFactory;
import smartHome.domain.sensorModel.SensorModel;
import smartHome.persistence.jpa.dataModel.SensorModelDataModel;
import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorModelName;
import smartHome.valueObject.SensorTypeID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorModelDataModelAssemblerTest {

    /**
     * Test to check if the SensorModelDataModelAssembler is instantiated when the SensorModelFactory is valid
     */
    @Test
    void shouldThrowException_whenSensorModelFactoryIsNull() {
        // Arrange
        ISensorModelFactory sensorModelFactory = null;

        String expected = "Sensor Model Factory cannot be null";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelDataModelAssembler(sensorModelFactory));

        // Assert
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * Test to check if the SensorModelDataModelAssembler is instantiated when the SensorModelFactory is valid
     */
    @Test
    void shouldInstantiateSensorModelDataModelAssembler_whenSensorModelFactoryIsValid() {
        // Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);

        // Act
        SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(sensorModelFactory);

        // Assert
        assertNotNull(sensorModelDataModelAssembler);
    }

    /**
     * Test to check if the SensorModelDataModelAssembler is instantiated when the SensorModelFactory is valid
     */
    @Test
    void shouldConvertSensorModelDataModelToDomain_WhenSensorModelDataModelIsValid() {
        //Arrange
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        SensorModelDataModel sensorModelDataModelDouble = mock(SensorModelDataModel.class);
        when(sensorModelDataModelDouble.getModelPath()).thenReturn("modelPath");
        when(sensorModelDataModelDouble.getSensorModelName()).thenReturn("sensorModelName");
        when(sensorModelDataModelDouble.getSensorTypeID()).thenReturn("sensorTypeID");

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);

        SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(sensorModelFactory);

        SensorModel expected = sensorModelFactory.createSensorModel(sensorModelNameDouble, modelPathDouble, sensorTypeIDDouble);

        //Act
        SensorModel result = sensorModelDataModelAssembler.toDomain(sensorModelDataModelDouble);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to check if the SensorModelDataModelAssembler throws an exception when the SensorModelDataModel is null
     */
    @Test
    void shouldThrowException_WhenSensorDataModelisNull() {
        //Arrange
        SensorModelDataModel sensorModelDataModel = null;

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(sensorModelFactory);

        String expected = "Sensor Model Data Model cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensorModelDataModelAssembler.toDomain(sensorModelDataModel));

        //Assert
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void shouldConvertSensorModelDataModelListToDomainList_WhenSensorModelDataModelListIsValid() {
        //Arrange
        //SensorModel 1
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        SensorModelDataModel sensorModelDataModelDouble = mock(SensorModelDataModel.class);
        when(sensorModelDataModelDouble.getModelPath()).thenReturn("modelPath");
        when(sensorModelDataModelDouble.getSensorModelName()).thenReturn("sensorModelName");
        when(sensorModelDataModelDouble.getSensorTypeID()).thenReturn("sensorTypeID");

        //SensorModel 2
        ModelPath modelPathDouble2 = mock(ModelPath.class);
        SensorModelName sensorModelNameDouble2 = mock(SensorModelName.class);
        SensorTypeID sensorTypeIDDouble2 = mock(SensorTypeID.class);

        SensorModelDataModel sensorModelDataModelDouble2 = mock(SensorModelDataModel.class);
        when(sensorModelDataModelDouble2.getModelPath()).thenReturn("modelPath2");
        when(sensorModelDataModelDouble2.getSensorModelName()).thenReturn("sensorModelName2");
        when(sensorModelDataModelDouble2.getSensorTypeID()).thenReturn("sensorTypeID2");

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);

        SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(sensorModelFactory);

        List<SensorModelDataModel> sensorModelDataModelList = new ArrayList<>();
        sensorModelDataModelList.add(sensorModelDataModelDouble);
        sensorModelDataModelList.add(sensorModelDataModelDouble2);

        //Expected SensorModels
        SensorModel expected1 = mock(SensorModel.class);
        SensorModel expected2 = mock(SensorModel.class);

        when(sensorModelFactory.createSensorModel(any(SensorModelName.class), any(ModelPath.class), any(SensorTypeID.class))).thenReturn(expected1, expected2);

        List<SensorModel> expectedList = List.of(expected1, expected2);


        //Act
        List<SensorModel> resultList = sensorModelDataModelAssembler.toDomain(sensorModelDataModelList);

        //Assert
        assertEquals(expectedList, resultList);
    }

    /**
     * Test to check if the SensorModelDataModelAssembler throws an exception when the list of SensorModelDataModel is null
     */
    @Test
    void shouldThrowException_WhenListOfSensorModelDataModelsIsNull() {
        //Arrange
        List<SensorModelDataModel> sensorModelDataModelList = null;

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(sensorModelFactory);

        String expected = "The list of sensor models cannot be null or empty.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensorModelDataModelAssembler.toDomain(sensorModelDataModelList));

        //Assert
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    /**
     * Test to check if the SensorModelDataModelAssembler throws an exception when the list of SensorModelDataModel is empty
     */
    @Test
    void shouldThrowException_WhenListOfSensorModelDataModelsIsEmpty() {
        //Arrange
        List<SensorModelDataModel> sensorModelDataModelList = new ArrayList<>();

        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(sensorModelFactory);

        String expected = "The list of sensor models cannot be null or empty.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensorModelDataModelAssembler.toDomain(sensorModelDataModelList));

        //Assert
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }
}

