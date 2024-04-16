package smartHome.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smartHome.domain.actuatorType.ActuatorType;
import smartHome.persistence.assembler.IDataModelConverter;
import smartHome.persistence.jpa.dataModel.ActuatorTypeDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RepositoryActuatorTypeJPAImplTest {

    /**
     * Test to verify if the RepositorySensorTypeJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryActuatorTypeJPAImpl_WHenGivenDataModelConverter() {
        //Arrange
        IDataModelConverter<ActuatorTypeDataModel, ActuatorType> dataModelConverter = mock(IDataModelConverter.class);

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
        IDataModelConverter<ActuatorTypeDataModel, ActuatorType> dataModelConverter = null;

        String expectedMessage = "The data model converter must not be null.";

        //Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RepositoryActuatorTypeJPAImpl(dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}