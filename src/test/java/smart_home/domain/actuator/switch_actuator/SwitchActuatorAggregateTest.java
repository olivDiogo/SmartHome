package smart_home.domain.actuator.switch_actuator;


import org.junit.jupiter.api.Test;
import smart_home.ddd.IValueObject;
import smart_home.domain.actuator.blind_roller_actuator.BlindRollerValue;
import smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.value_object.*;
import smart_home.visitor_pattern.ActuatorVisitorForDataModelImpl;
import smart_home.visitor_pattern.IActuatorVisitor;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

class SwitchActuatorAggregateTest {

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
     * Test the second constructor.
     */
    @Test
    void shouldInstantiateSwitchActuator_WhenSecondConstructorArgumentsAreValid() {
        // Arrange
        ActuatorID actuatorID = new ActuatorID(UUID.randomUUID().toString());
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        // Act
        SwitchActuator switchActuator = new SwitchActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName);

        // Assert
        assertNotNull(switchActuator);
    }

    /**
     * Test for invalid actuatorID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorIDIsNull() {
        // Arrange
        ActuatorID actuatorID = null;
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        String expectedMessage = "ActuatorID is required";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName);
        });

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
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

        String expectedMessage = "DeviceID is required";


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

        String expectedMessage = "ActuatorName is required";

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

        String expectedMessage = "ModelPath is required";

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

        String expectedMessage = "ActuatorTypeID is required";

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
    void shouldSetValue_whenValueIsOff() {
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

    /**
     * Test of accept method, of class SwitchActuator.
     */
    @Test
    void shouldReturnString_WhenAcceptIsCalled() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator");
        ActuatorName actuatorName = new ActuatorName("SwitchActuator");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        SwitchActuator switchActuator = new SwitchActuator (deviceID, modelPath, actuatorTypeID, actuatorName);



        IActuatorVisitor visitor = new ActuatorVisitorForDataModelImpl(new ActuatorDataModel());

        String expected = switchActuator.toString();

        //Act
        String result = switchActuator.accept(visitor);

        //Assert
       assertEquals(expected, result);
    }
}
