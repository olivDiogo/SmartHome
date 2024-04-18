package smart_home.visitor_pattern;

import org.junit.jupiter.api.Test;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.persistence.jpa.data_model.ActuatorTypeDataModel;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActuatorVisitorForDataModelImplTest {


    /**
     * Method to test the constructor of the ActuatorVisitorForDataModelImpl class.
     */
    @Test
    void shouldCreateActuatorVisitorForDataModel_whenConstructorIsCalled() {
        //Arrange
        ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

        //Act
        ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(actuatorDataModel);

        //Assert
        assertNotNull(actuatorVisitor);
    }


    /**
     * Test if throws exception when ActuatorDataModel is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenActuatorDataModelIsNull() {
        //Arrange
        ActuatorDataModel actuatorDataModel = null;

        String expectedMessage = "ActuatorDataModel cannot be null";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorVisitorForDataModelImpl(actuatorDataModel));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    /**
     * Method to test the getActuatorDataModel method.
     */
    @Test
    void shouldReturnActuatorModel_whenGetActuatorDataModelIsCalled() {
        //Arrange
        ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

        ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(actuatorDataModel);

        //Act
        ActuatorDataModel result = actuatorVisitor.getActuatorDataModel();

        //Assert
        assertNotNull(result);
    }


}
