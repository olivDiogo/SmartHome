package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator.IActuator;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.visitor_pattern.IActuatorVisitorForDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorRepositoryJPAImpTest {
    /**
     * Test to verify if the RepositoryActuatorJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryActuatorJPAImpl_WHenGivenDataModelAssembler() {
        //Arrange
        IDataModelAssembler<ActuatorDataModel, IActuator> dataModelConverter = mock(IDataModelAssembler.class);
        IActuatorVisitorForDataModel actuatorVisitorForDataModel = mock(IActuatorVisitorForDataModel.class);

        //Act
        ActuatorRepositoryJPAImp repositoryActuatorJPA = new ActuatorRepositoryJPAImp(dataModelConverter, actuatorVisitorForDataModel);

        //Assert
        assertNotNull(repositoryActuatorJPA);
    }

    /**
     * Test to verify if the RepositoryActuatorJPAImpl throws an IllegalArgumentException when given a null data model assembler
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        IDataModelAssembler<ActuatorDataModel, IActuator> dataModelConverter = null;
        IActuatorVisitorForDataModel actuatorVisitorForDataModel = mock(IActuatorVisitorForDataModel.class);

        String expectedMessage = "Data model converter cannot be null";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorRepositoryJPAImp(dataModelConverter, actuatorVisitorForDataModel));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}