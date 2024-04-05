package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator;
import SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

class BlindRollerActuatorTest {

    /**
     * Test of constructor, of class BlindRollerActuator.
     */
    @Test
    void shouldCreateBlindRollerActuator_WhenConstructorArgumentsAreValid() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        //Act
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Assert
        assertNotNull(blindRollerActuator);
    }

    /**
     * Test for invalid deviceID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        //Arrange
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = null;
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        String expectedMessage = "DeviceID cannot be null";

        //Act+Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);
        });

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for invalid actuatorTypeID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = null;

        String expectedMessage = "ActuatorTypeID cannot be null";

        //Act+Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);
        });

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Test for invalid actuatorName
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = null;
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);


        String expectedMessage = "ActuatorName cannot be null";

        //Act+Assert
       Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);
            });

         String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Test for invalid modelPath
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        //Arrange
        String deviceID = "1";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = null;
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);


        String expectedMessage = "ModelPath cannot be null";

        //Act+Assert
       Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);
            });

         String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Test of getID method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorID_WhenGetIDIsCalled() {
        // Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);


        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

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
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Act
        ActuatorName result = blindRollerActuator.getName();

        //Assert
        assertEquals(actuatorName, result.getActuatorName());
    }

    /**
     * Test of getModelPath method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        String expected = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Act
        ModelPath result = blindRollerActuator.getModelPath();

        //Assert
        assertEquals(expected, result.toString());
    }

    /**
     * Test of getActuatorTypeID method, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        String expected = "1";

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Act
        ActuatorTypeID result = blindRollerActuator.getActuatorTypeID();

        //Assert
        assertEquals(expected, result.toString());

    }
    /**
     * Test of getDeviceID method, of class BlindRoller
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        String expected = "1";

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Act
        DeviceID result = blindRollerActuator.getDeviceID();

        //Assert
        assertEquals(expected, result.toString());
    }

    /**
     * Test of setValue, of class BlindRollerActuator.
     */
    @Test
    void shouldReturnActuatorValue_WhenSetValueIsCalled() {
        //Arrange
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        int value = 50;

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        BlindRollerValue blindRollerValue = new BlindRollerValue(value);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

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
        String deviceID = "1";
        String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
        String actuatorName = "BlindRoller";
        String actuatorTypeID = "1";

        DeviceID deviceIDObject = new DeviceID(deviceID);
        ModelPath modelPathObject = new ModelPath(modelPath);
        ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
        ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Act
        BlindRollerValue result = blindRollerActuator.setValue(null);

        //Assert
        assertNull(result);
    }
}
