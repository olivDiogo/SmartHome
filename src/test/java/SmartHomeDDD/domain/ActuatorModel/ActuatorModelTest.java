package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.valueObject.ActuatorModelID;
import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class ActuatorModelTest {

    /**
     * Test of class ActuatorModel constructor with valid parameters
     */
    @Test
    void shouldReturnValidActuatorModel_WhenGivenValidParameters() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

        //Act
        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        //Assert
        assertNotNull(actuatorModel);
    }

    /**
     * Test of class ActuatorModel constructor with null ActuatorModelName
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullActuatorModelName() {
        //Arrange
        ActuatorModelName actuatorModelName = null;
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

        String expectedMessage = "Please enter a valid actuator model name.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of class ActuatorModel constructor with null ModelPath
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
        String expectedMessage = "Please enter a valid model path.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of class ActuatorModel equals method with same object
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        //Act
        boolean result = actuatorModel.equals(actuatorModel);

        //Assert
        assertTrue(result);
    }


    /**
     * Test of class ActuatorModel equals method with different object
     */
    @Test
    void shouldReturnFalse_WhenGivenDifferentObject() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID("BlindRoller");

        ActuatorModelName actuatorModelName2 = new ActuatorModelName("SwitchActuator");
        ModelPath modelPath2 = new ModelPath("SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator;SwitchActuator");
        ActuatorTypeID actuatorTypeID2 = new ActuatorTypeID("BlindRoller");

        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID1);
        ActuatorModel actuatorModel2 = new ActuatorModel(actuatorModelName2, modelPath2, actuatorTypeID2);

        //Act
        boolean result = actuatorModel.equals(actuatorModel2);

        //Assert
        assertFalse(result);
    }


    /**
     * Test of class ActuatorModel equals method with null object
     */
    @Test
    void shouldReturnFalse_WhenComparedWithNull() {
        // Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        // Act
        boolean result = actuatorModel.equals(null);

        // Assert
        assertFalse(result);
    }


    /**
     * Test of class ActuatorModel getID method
     */
    @Test
    void shouldReturnActuatorModelID_WhenGetIDIsCalled() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        //Act
        ModelPath result = actuatorModel.getID();

        //Assert
        assertNotNull(result);
    }


    /**
     * Test of class ActuatorModel toString method
     */
    @Test
    void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        //Act
        String result = actuatorModel.toString();

        //Assert
        assertTrue(result.contains(actuatorModel.getID().toString()));
        assertTrue(result.contains(actuatorModelName.toString()));
        assertTrue(result.contains(modelPath.toString()));
    }


    /**
     * Test of class ActuatorModel getActuatorModelName method
     */
    @Test
    void shouldReturnActuatorModelName_WhenGetActuatorModelNameIsCalled() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        //Act
        ActuatorModelName result = actuatorModel.getActuatorModelName();

        //Assert
        assertEquals(actuatorModelName, result);
    }


    /**
     * Test of class ActuatorModel getModelPath method
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        //Arrange
        ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
        ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

        //Act
        ModelPath result = actuatorModel.getID();

        //Assert
        assertEquals(modelPath, result);
    }
}


