package smartHome.domain.actuator;


import smartHome.ddd.IValueObject;
import smartHome.domain.actuator.blindRollerActuator.BlindRollerValue;
import smartHome.domain.actuator.setIntegerActuator.SetIntegerActuator;
import smartHome.domain.actuator.switchActuator.SwitchActuator;
import smartHome.domain.actuator.switchActuator.SwitchActuatorValue;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SwitchActuatorAggregateTest {

    /**
     * Should instantiate SwitchActuator when constructor arguments are valid.
     */
    @Test
    void shouldInstantiateSwitchActuator_WhenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        // Act
        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Assert
        assertNotNull(switchActuator);
    }

    /**
     * Should throw IllegalArgumentException when deviceID is null.
     */

    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        // Arrange
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        String expectedMessage = "deviceID should not be null.";


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Should throw IllegalArgumentException when actuatorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        String expectedMessage = "The value of 'actuatorName' should not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should throw IllegalArgumentException when modelPath is null.
     */

    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        String deviceIDString = "deviceID";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = null;
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        String expectedMessage = "The value of 'modelPath' should not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Should throw IllegalArgumentException when actuatorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = null;

        String expectedMessage = "The value of 'actuatorTypeID' should not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenActuatorTypeIDIsNotSwitch() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Swwitch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        String expectedMessage = "The value of 'actuatorTypeID' should be 'Switch'.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should generate actuatorID when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorID() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ActuatorID result = switchActuator.getID();

        // Assert
        assertTrue(switchActuator.toString().contains(result.toString()));

    }

    /**
     * Should get actuator name when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorName() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);


        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ActuatorName result = switchActuator.getName();

        // Assert
        assertEquals(actuatorNameString, result.getActuatorName());
    }

    /**
     * Should get model path when constructor arguments are valid.
     */
    @Test
    void shouldGetModelPath() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);


        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ModelPath result = switchActuator.getModelPath();

        // Assert
        assertEquals(modelPathString, result.toString());

    }

    /**
     * Should get actuator type id when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorTypeID() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ActuatorTypeID result = switchActuator.getActuatorTypeID();

        // Assert
        assertEquals(actuatorTypeIDString, result.toString());

    }


    /**
     * Should get device type id when constructor arguments are valid.
     */
    @Test
    void shouldGetDeviceID() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        DeviceID result = switchActuator.getDeviceID();

        // Assert
        assertEquals(deviceIDString, result.toString());
    }

    /**
     * Should set value when value is valid.
     */
    @Test
    void shouldSetValue() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        // Act
        IValueObject result = switchActuator.setValue(switchActuatorValueDouble);

        // Assert
        assertNotNull(result);
    }

    /**
     * Should set value when string is "ON".
     */
    @Test
    public void shouldSetValue_whenValueIsOn() {
        //Arrange
        String value = "On";

        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);
        SwitchActuatorValue valueDouble = new SwitchActuatorValue(true);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        //Act
        IValueObject result = switchActuator.setValue(valueDouble);

        //Assert
        assertEquals(result.toString(), value);

    }

    /**
     * Should set value when string is "OFF".
     */
    @Test
    public void shouldSetValue_whenValueIsOff() {
        //Arrange
        String value = "Off";

        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);
        SwitchActuatorValue valueDouble = new SwitchActuatorValue(false);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        //Act
        IValueObject result = switchActuator.setValue(valueDouble);

        //Assert
        assertNotNull(result);
    }

    /**
     * Should throw IllegalArgumentException when value is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNull() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);
        SwitchActuatorValue valueDouble = null;

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        String expectedMessage = "The value of 'value' should not be null.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            switchActuator.setValue(valueDouble);
        });

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should return null when value is not instance of SwitchActuatorValue.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNotInstanceOfSwitchActuatorValue() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        int value = 1;
        IValueObject valueDouble = new BlindRollerValue(value);


        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        IValueObject result = switchActuator.setValue(valueDouble);

        // Assert
        assertNull(result);
    }

    /**
     * Should return string representation of SwitchActuator.
     */
    @Test
    void shouldReturnStringRepresentationOfSwitchActuator() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        String expectedString = "SwitchActuator: DeviceID=" + deviceID + ", ActuatorName=" + actuatorName + ", ModelPath=" + modelPath + ", ActuatorTypeID=" + actuatorTypeID + ", ActuatorID=" + switchActuator.getID() + ", Value=" + null;

        // Act
        String result = switchActuator.toString();

        // Assert
        assertEquals(expectedString, result);

    }

    /**
     * Should return true when instances are same object.
     */
    @Test
    void shouldReturnTrue_WhenInstancesAreSameObject() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        switchActuator.setValue(new SwitchActuatorValue(true));

        // Act
        boolean result = switchActuator.equals(switchActuator);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if the method returns false when the object in not the same
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotTheSame() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        SwitchActuator switchActuator2 = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        boolean result = switchActuator.equals(switchActuator2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the method returns false when the object is not an instance of switch actuator
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotInstanceOfSwitchActuator() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        SetIntegerActuator setIntegerActuator = mock(SetIntegerActuator.class);

        // Act
        boolean result = switchActuator.equals(setIntegerActuator);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the method return the hashcode of the switch actuator.
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeMethodIsCalled() {
        // Arrange
        String deviceIDString = "deviceID";
        String modelPathString = "modelPath";
        String actuatorNameString = "actuatorName";
        String actuatorTypeIDString = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDString);
        ModelPath modelPath = new ModelPath(modelPathString);
        ActuatorName actuatorName = new ActuatorName(actuatorNameString);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDString);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        int expected = switchActuator.getID().hashCode();

        // Act
        int result = switchActuator.hashCode();

        // Assert
        assertEquals(expected, result);
    }


}
