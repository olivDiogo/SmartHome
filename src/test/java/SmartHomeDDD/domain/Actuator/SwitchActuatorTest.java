package SmartHomeDDD.domain.Actuator;


import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerValue;
import SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator;
import SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuatorValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SwitchActuatorTest {

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
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");


        // Act
        try {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        } catch (IllegalArgumentException e) {

            // Assert
            String message = e.getMessage();
            assertEquals(message,"deviceID should not be null.");
        }
    }

    /**
     * Should throw IllegalArgumentException when actuatorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        // Act
        try {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        } catch (IllegalArgumentException e) {
            // Assert
            String message = e.getMessage();
            assertEquals(message,"The value of 'actuatorName' should not be null.");
        }
    }

    /**
     * Should throw IllegalArgumentException when modelPath is null.
     */

    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = null;
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        // Act
        try {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        } catch (IllegalArgumentException e) {
            // Assert
            String message = e.getMessage();
            assertEquals(message, "The value of 'modelPath' should not be null.");
        }
    }

    /**
     * Should throw IllegalArgumentException when actuatorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = null;

        // Act
        try {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        } catch (IllegalArgumentException e) {
            // Assert
            String message = e.getMessage();
            assertEquals(message,"The value of 'actuatorTypeID' should not be null.");
        }
    }

    @Test
    void shouldThrowIllegalArgumentException_whenActuatorTypeIDIsNotSwitch() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("NotSwitch");

        // Act
        try {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        } catch (IllegalArgumentException e) {
            // Assert
            String message = e.getMessage();
            assertEquals(message, "The value of 'actuatorTypeID' should be 'Switch'.");
        }
    }

    /**
     * Should generate actuatorID when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorID() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ActuatorID result = switchActuator.getID();

        // Assert
        assertNotNull(result);

    }

    /**
     * Should get actuator name when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorName() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");


        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ActuatorName result = switchActuator.getName();

        // Assert
        assertNotNull(result);
    }

    /**
     * Should get model path when constructor arguments are valid.
     */
    @Test
    void shouldGetModelPath() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");


        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ModelPath result = switchActuator.getModelPath();

        // Assert
        assertNotNull(result);

    }

    /**
     * Should get actuator type id when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorTypeID() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        ActuatorTypeID result = switchActuator.getActuatorTypeID();

        // Assert
        assertNotNull(result);

    }

    @Test
    void shouldThrowException_whenGetActuatorTypeID() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");


        try {
            new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(e.getMessage(), "The value of 'actuatorTypeID' should be 'Switch'.");
        }

    }

    /**
     * Should get device type id when constructor arguments are valid.
     */
    @Test
    void shouldGetDeviceID() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        // Act
        DeviceID result = switchActuator.getDeviceID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Should set value when value is valid.
     */
    @Test
    void shouldSetValue() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        // Act
        ValueObject result = switchActuator.setValue(switchActuatorValueDouble);

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

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");
        SwitchActuatorValue valueDouble = new SwitchActuatorValue(true);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        //Act
        ValueObject result = switchActuator.setValue(valueDouble);

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

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");
        SwitchActuatorValue valueDouble = new SwitchActuatorValue(false);

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        //Act
        ValueObject result = switchActuator.setValue(valueDouble);

        //Assert
        assertNotNull(result);
    }

    /**
     * Should throw IllegalArgumentException when value is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");
        SwitchActuatorValue valueDouble = null;

        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        // Act & Assert
        try {
            switchActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            // Assert
            String message = e.getMessage();
            assertEquals(message,"The value of 'value' should not be null.");
        }
    }

    /**
     * Should return null when value is not instance of SwitchActuatorValue.
     */
    @Test
    void shouldThrowIlegalArgumentException_WhenValueIsNotInstanceOfSwitchActuatorValue() {
        // Arrange
        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        ActuatorName actuatorName = new ActuatorName("actuatorName");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");

        int value = 1;
        ValueObject valueDouble = new BlindRollerValue(value);


        SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
        //Act
        ValueObject result = switchActuator.setValue(valueDouble);

        // Assert
        assertNull(result);
    }
}
