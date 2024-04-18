package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorModelDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class SensorModelRepositoryJPAImplTest {

    /**
     * Test to check if the RepositorySensorModelTypeJPAImpl is instantiated when the SensorModelFactory and DataModelConverter are valid
     */
    @Test
    void shouldInstantiateRepositorySensorModelTypeJPAImpl() {
        //Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter = mock(IDataModelAssembler.class);

        //Act
        SensorModelRepositoryJPAImpl repositorySensorModelTypeJPA = new SensorModelRepositoryJPAImpl(dataModelConverter);

        //Assert
        assertNotNull(repositorySensorModelTypeJPA);
    }


    /**
     * Test to check if the RepositorySensorModelTypeJPAImpl throws an IllegalArgumentException when the DataModelConverter is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter = null;

        String expectedMessage = "Data Model Converter cannot be null.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorModelRepositoryJPAImpl(dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


}
