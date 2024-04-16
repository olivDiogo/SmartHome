package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RepositoryActuatorModelJPImplTest {

    /**
     * Test to verify if the RepositoryActuatorModelJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryActuatorModelJPAImpl() {
        //Arrange
        IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter = mock(IDataModelAssembler.class);

        //Act
        RepositoryActuatorModelJPAImpl repositoryActuatorModelJPA = new RepositoryActuatorModelJPAImpl(dataModelConverter);

        //Assert
        assertNotNull(repositoryActuatorModelJPA);
    }

    /**
     * Test to verify if the RepositoryActuatorModelJPAImpl throws an IllegalArgumentException when given a null data model converter
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter = null;

        String expectedMessage = "Data model converter cannot be null";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RepositoryActuatorModelJPAImpl(dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
