package smarthome.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.ActuatorModelDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorModelRepositoryJPImplTest {

    /**
     * Test to verify if the RepositoryActuatorModelJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryActuatorModelJPAImpl() {
        //Arrange
        IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter = mock(IDataModelAssembler.class);

        //Act
        ActuatorModelRepositoryJPAImpl repositoryActuatorModelJPA = new ActuatorModelRepositoryJPAImpl(dataModelConverter);

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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelRepositoryJPAImpl(dataModelConverter));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
