package smartHome.domain.actuator;

import org.junit.jupiter.api.Test;
import smartHome.domain.actuator.blindRollerActuator.BlindRollerActuator;
import smartHome.domain.actuator.blindRollerActuator.BlindRollerValue;
import smartHome.valueObject.*;

import static org.junit.Assert.*;

class BlindRollerActuatorAggregateTest {

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

    /**
     * Test equals method should return true when instances are same object.
     */
    @Test
    void shouldReturnTrue_WhenInstancesAreSameObject() {
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
        boolean result = blindRollerActuator.equals(blindRollerActuator);

        //Assert
        assertTrue(result);
    }

    /**
     * Test equals method should return false when instances are different objects.
     */
    @Test
    void shouldReturnFalse_WhenInstancesAreDifferentObjects() {
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

        BlindRollerActuator blindRollerActuator2 = new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);

        //Act
        boolean result = blindRollerActuator.equals(blindRollerActuator2);

        //Assert
        assertFalse(result);
    }

    /**
     * Test equals method should return false when an object is not an instance of switch actuator.
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotInstanceOfBlindRollerActuator() {
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

        Object object = new Object();

        //Act
        boolean result = blindRollerActuator.equals(object);

        //Assert
        assertFalse(result);
    }

    /**
     * Test hashcode method
     */
    @Test
    void shouldReturnHashCode_WhenGetHashCodeIsCalled() {
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
        int result = blindRollerActuator.hashCode();

        //Assert
        assertNotNull(result);
    }

    /**
     * Test toString method
     */
    @Test
    void shouldReturnString_WhenToStringIsCalled() {
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

        String expected = blindRollerActuator.getID() + " " + blindRollerActuator.getDeviceID() + " " + blindRollerActuator.getModelPath() + " " + blindRollerActuator.getActuatorTypeID() + " " + blindRollerActuator.getName();

        //Act
        String result = blindRollerActuator.toString();

        //Assert
        assertEquals(expected, result);
    }
}
