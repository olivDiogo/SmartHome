package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;
import SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class BlindRollerActuatorTest {

    /**
     * Test of constructor, of class BlindRollerActuator.
     */
    @Test
    void shouldCreateBlindRollerActuator_WhenConstructorArgumentsAreValid() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        //Act
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Assert
        assertNotNull(blindRollerActuator);
    }

    /**
     * Test for invalid deviceID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");
        DeviceID deviceID = null;

        String expectedMessage = "DeviceID cannot be null";

       //Act+Assert
        try {
            new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Test for invalid actuatorTypeID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = null;

        String expectedMessage = "ActuatorTypeID cannot be null";

        //Act+Assert
        try {
            new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Test for invalid actuatorName
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = null;

        String expectedMessage = "ActuatorName cannot be null";

        //Act+Assert
        try {
            new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Test for invalid modelPath
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");
        ModelPath modelPath = null;

        String expectedMessage = "ModelPath cannot be null";

        //Act+Assert
       try {
            new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }

    }

    /**
     * Test of getID method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorID_WhenGetIDIsCalled() {
        // Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        // Act
        ActuatorID result = blindRollerActuator.getID();

        // Assert
        assertNotNull(result);

    }


    /**
     * Test of getName method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorName_WhenGetNameIsCalled() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Act
        ActuatorName result = blindRollerActuator.getName();

        //Assert
        assertNotNull(result);
    }

    /**
     * Test of getModelPath method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Act
        ModelPath result = blindRollerActuator.getModelPath();

        //Assert
        assertNotNull(result);

    }

    /**
     * Test of getActuatorTypeID method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Act
        ActuatorTypeID result = blindRollerActuator.getActuatorTypeID();

        //Assert
        assertNotNull(result);
    }
    /**
     * Test of getDeviceID method, of class BlindRoller
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Act
        DeviceID result = blindRollerActuator.getDeviceID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Test of setValue, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorValue_WhenSetValueIsCalled() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");
        BlindRollerValue blindRollerValue = new BlindRollerValue(1);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Act
        BlindRollerValue result = blindRollerActuator.setValue(blindRollerValue);

        //Assert
        assertNotNull(result);
    }

    /**
     * Test of setValue when is null, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnNull_WhenSetValueIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("BlindRoller");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, actuatorTypeID, actuatorName, modelPath);

        //Act
        BlindRollerValue result = blindRollerActuator.setValue(null);

        //Assert
        assertEquals(null, result);
    }
}
