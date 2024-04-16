package smartHome.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smartHome.domain.sensorModel.ISensorModelFactory;
import smartHome.domain.sensorModel.SensorModel;
import smartHome.persistence.assembler.IDataModelAssembler;
import smartHome.persistence.jpa.dataModel.SensorModelDataModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class RepositorySensorModelTypeJPAImplTest {

    /**
     * Test to check if the RepositorySensorModelTypeJPAImpl is instantiated when the SensorModelFactory and DataModelConverter are valid
     */
    @Test
    void shouldInstantiateRepositorySensorModelTypeJPAImpl() {
        //Arrange
        ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
        IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter = mock(IDataModelAssembler.class);

        //Act
        RepositorySensorModelJPAImpl repositorySensorModelTypeJPA = new RepositorySensorModelJPAImpl(dataModelConverter);

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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RepositorySensorModelJPAImpl(dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


}
