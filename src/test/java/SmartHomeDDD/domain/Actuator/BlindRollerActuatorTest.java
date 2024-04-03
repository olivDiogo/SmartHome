package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        //Act
        new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);
    }

    /**
     * Test for invalid deviceID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        //Arrange
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        String expectedMessage = "DeviceID cannot be null";

        //Act+Assert
        try {
            new BlindRollerActuator(null, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);
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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        String expectedMessage = "ActuatorTypeID cannot be null";

        //Act+Assert
        try {
            new BlindRollerActuator(deviceIDDouble, null, actuatorNameDouble, modelPathDouble);
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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        String expectedMessage = "ActuatorName cannot be null";

        //Act+Assert
        try {
            new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, null, modelPathDouble);
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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        String expectedMessage = "ModelPath cannot be null";

        //Act+Assert
        try {
            new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, null);
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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        BlindRollerValue blindRollerValue = mock(BlindRollerValue.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

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
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble, actuatorTypeIDDouble, actuatorNameDouble, modelPathDouble);

        //Act
        BlindRollerValue result = blindRollerActuator.setValue(null);

        //Assert
        assertEquals(null, result);
    }
}
