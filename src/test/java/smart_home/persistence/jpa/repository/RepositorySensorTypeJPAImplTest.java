package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor_type.SensorType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RepositorySensorTypeJPAImplTest {

    /**
     * Test to verify if the RepositorySensorTypeJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositorySensorTypeJPAImpl() {
        //Arrange
        IDataModelAssembler<SensorTypeDataModel, SensorType> dataModelConverter = mock(IDataModelAssembler.class);

        //Act
        RepositorySensorTypeJPAImpl repositorySensorTypeJPA = new RepositorySensorTypeJPAImpl(dataModelConverter);

        //Assert
        assertNotNull(repositorySensorTypeJPA);
    }

    /**
     * Test to verify if the RepositorySensorTypeJPAImpl throws an IllegalArgumentException when given a null data model converter
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        IDataModelAssembler<SensorTypeDataModel, SensorType> dataModelConverter = null;

        String expectedMessage = "Data model converter cannot be null.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RepositorySensorTypeJPAImpl( dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

//    @Test
//    void shouldSaveSensorType_whenGivenValidSensorType() {
//        //Arrange
//        String strSensorTypeID = "123";
//        String strTypeDescription = "Temperature";
//        String strUnitID = "C";
//
//        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
//        when(sensorTypeIDDouble.toString()).thenReturn(strSensorTypeID);
//
//        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
//        when(typeDescriptionDouble.toString()).thenReturn(strTypeDescription);
//
//        UnitID unitIDDouble = mock(UnitID.class);
//        when(unitIDDouble.toString()).thenReturn(strUnitID);
//
//        ISensorTypeFactory sensorTypeFactory = mock(ISensorTypeFactory.class);
//        IDataModelConverter<SensorTypeDataModel, SensorType>  dataModelConverter = mock(IDataModelConverter.class);
//
//        RepositorySensorTypeJPAImpl repositorySensorTypeJPA = new RepositorySensorTypeJPAImpl(sensorTypeFactory, dataModelConverter);
//
//        SensorType sensorType = mock(SensorType.class);
//        when(sensorType.getID()).thenReturn(sensorTypeIDDouble);
//        when(sensorType.getName()).thenReturn(typeDescriptionDouble);
//        when(sensorType.getUnit()).thenReturn(unitIDDouble);
//
//        //Act
//        SensorType savedSensorType = repositorySensorTypeJPA.save(sensorType);
//
//        //Assert
//        assertEquals(sensorType, savedSensorType);
//    }

}