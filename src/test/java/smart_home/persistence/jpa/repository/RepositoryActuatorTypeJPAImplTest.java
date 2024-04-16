package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorTypeDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RepositoryActuatorTypeJPAImplTest {

    /**
     * Test to verify if the RepositorySensorTypeJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryActuatorTypeJPAImpl_WHenGivenDataModelConverter() {
        //Arrange
        IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelConverter = mock(IDataModelAssembler.class);

        //Act
        RepositoryActuatorTypeJPAImpl repositoryActuatorTypeJPA = new RepositoryActuatorTypeJPAImpl(dataModelConverter);

        //Assert
        assertNotNull(repositoryActuatorTypeJPA);
    }

    /**
     * Test to verify if the RepositorySensorTypeJPAImpl throws an IllegalArgumentException when given a null data model converter
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelConverter = null;

        String expectedMessage = "The data model converter must not be null.";

        //Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RepositoryActuatorTypeJPAImpl(dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}