package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerValue;
import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuator;
import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuatorLimits;
import SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class SetIntegerActuatorTest {
    /**
     * Test to verify that the SetIntegerActuator class can be instantiated when the constructor arguments are valid.
     */
    @Test
    public void shouldInstantiateSetIntegerActuator_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        //Act
        SetIntegerActuator sensor = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Assert
        assertNotNull(sensor);
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the deviceID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceId = null;
        ModelPath modelPathDouble = new ModelPath("modelPath");
        ActuatorName actuatorNameDouble = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeIDDouble = new ActuatorTypeID("SetIntegerActuator");
        SetIntegerActuatorLimits limitsDouble = new SetIntegerActuatorLimits(0, 100);

        String expectedMessage = "DeviceID cannot be null";

        //Act & Assert
        try {
            new SetIntegerActuator(deviceId, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble, limitsDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the modelPath is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = null;
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);
        String expectedMessage = "ModelPath cannot be null";

        //Act & Assert
        try {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorName is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorNameIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        String expectedMessage = "ActuatorName cannot be null";

        //Act & Assert
        try {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorTypeID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = null;
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        String expectedMessage = "ActuatorTypeID cannot be null";

        //Act & Assert
        try {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorTypeID is not SetInteger.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeIDIsNotSetInteger() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("NotSetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        String expectedMessage = "ActuatorTypeID must be SetInteger";

        //Act & Assert
        try {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the limits is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenLimitsIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = null;

        String expectedMessage = "SetIntegerActuatorLimits cannot be null";

        //Act & Assert
        try {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to get the actuator ID.
     */
    @Test
    public void shouldGetActuatorID() {
        //Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        ActuatorID result = setIntegerActuator.getID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Test to get the actuator name.
     */
    @Test
    public void shouldGetActuatorName() {
        //Arrange
        String name = "actuatorName";

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName(name);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        ActuatorName result = setIntegerActuator.getName();

        //Assert
        assertEquals(result.getActuatorName(), name);
    }

    /**
     * Test to get the model path.
     */
    @Test
    public void shouldGetModelPath() {
        //Arrange
        String path = "modelPath";

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath(path);
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        ModelPath result = setIntegerActuator.getModelPath();

        //Assert
        assertEquals(result.getId(),path);

    }

    /**
     * Test to get the actuator type ID.
     */
    @Test
    public void shouldGetActuatorTypeID() {
        //Arrange
        String id = "SetInteger";

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(id);
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        ActuatorTypeID result = setIntegerActuator.getActuatorTypeID();

        //Assert
        assertEquals(result.getId(), id);

    }

    /**
     * Test to get the device ID.
     */
    @Test
    public void shouldGetDeviceID() {
        //Arrange
        String id = "deviceID";

        DeviceID deviceID = new DeviceID(id);
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(0, 100);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        DeviceID result = setIntegerActuator.getDeviceID();

        //Assert
        assertEquals(result.getId(), id);
    }

    /**
     * Test to get the limits.
     */
    @Test
    public void shouldGetLimits() {
        //Arrange
        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        SetIntegerActuatorLimits result = setIntegerActuator.getLimits();

        //Assert
        assertEquals(result.getLowerLimit(), lowerLimit);
        assertEquals(result.getUpperLimit(), upperLimit);
    }

    /**
     * Test to set the value when it is valid and within range.
     */
    @Test
    public void shouldSetValue_whenValueIsWithinRange() {
        //Arrange
        int value = 50;

        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        SetIntegerValue valueDouble = new SetIntegerValue(value);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        SetIntegerValue result = setIntegerActuator.setValue(valueDouble);

        //Assert
        assertEquals(result.toString(),value + "");
    }

    /**
     * Test to set the value when it is valid but below the lower limit.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsBelowLowerLimit() {
        //Arrange
        int value = -1;

        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        SetIntegerValue valueDouble = new SetIntegerValue(value);

        String expectedMessage = "Value cannot be less than the lower limit.";

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act & Assert
        try {
            setIntegerActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to set the value when it is valid but above the upper limit.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsAboveUpperLimit() {
        //Arrange
        int value = 101;

        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        SetIntegerValue valueDouble = new SetIntegerValue(value);

        String expectedMessage = "Value cannot be greater than the upper limit.";

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act & Assert
        try {
            setIntegerActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to set the value when it is null
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsNull() {
        //Arrange
        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        SetIntegerValue value = null;

        String expectedMessage = "Value cannot be null";

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act & Assert
        try {
            setIntegerActuator.setValue(value);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    /**
     * Test to set the value when it is not an instance of SetIntegerValue
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsNotInstanceOfSetIntegerValue() {
        //Arrange
        int value = 1;
        ValueObject valueDouble = new BlindRollerValue(value);

        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
        SetIntegerActuatorLimits limits = new SetIntegerActuatorLimits(lowerLimit, upperLimit);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        ValueObject result = setIntegerActuator.setValue(valueDouble);

        //Assert
        assertNull(result);
    }
}

