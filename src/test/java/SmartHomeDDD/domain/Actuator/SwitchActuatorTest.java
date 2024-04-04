package SmartHomeDDD.domain.Actuator;


import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator;
import SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuatorValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SwitchActuatorTest {

    /**
     * Should instantiate SwitchActuator when constructor arguments are valid.
     */
    @Test
    void shouldInstantiateSwitchActuator_WhenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        // Act
        SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

        // Assert
        assertNotNull(switchActuator);
    }

    /**
     * Should throw IllegalArgumentException when deviceID is null.
     */

    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceIDDouble = null;
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);


        // Act & Assert
        try {
            new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("deviceID should not be null.");
        }
    }

    /**
     * Should throw IllegalArgumentException when actuatorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = null;
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        // Act & Assert
        try {
            new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("The value of 'actuatorName' should not be null.");
        }
    }

    /**
     * Should throw IllegalArgumentException when modelPath is null.
     */

    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = null;
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        // Act & Assert
        try {
            new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("The value of 'modelPath' should not be null.");
        }
    }

    /**
     * Should throw IllegalArgumentException when actuatorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = null;

        // Act & Assert
        try {
            new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("The value of 'actuatorTypeID' should not be null.");
        }
    }

    /**
     * Should generate actuatorID when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorID() {
        // Arrange
        String actuatorID = "actuatorID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        try (MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(actuatorID);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ActuatorID result = switchActuator.getID();

            // Assert
            assertEquals(actuatorID, result.toString());
            //result.toString().equals(actuatorID);
        }
    }

    /**
     * Should get actuator name when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorName() {
        // Arrange
        String actuatorName = "actuatorName";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);

        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        when(actuatorNameDouble.toString()).thenReturn(actuatorName);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        try (MockedConstruction<ActuatorName> mockedConstruction = mockConstruction(ActuatorName.class, (mock, context) -> {
            when(mock.toString()).thenReturn(actuatorName);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ActuatorName result = switchActuator.getName();

            // Assert
            assertEquals(actuatorNameDouble, result);
        }
    }

    /**
     * Should get model path when constructor arguments are valid.
     */
    @Test
    void shouldGetModelPath() {
        // Arrange
        String modelPath = "modelPath";

        DeviceID deviceIDDouble = mock(DeviceID.class);

        ModelPath modelPathDouble = mock(ModelPath.class);
        when(modelPathDouble.toString()).thenReturn(modelPath);

        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        try (MockedConstruction<ModelPath> mockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
            when(mock.toString()).thenReturn(modelPath);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ModelPath result = switchActuator.getModelPath();

            // Assert
            assertNotNull(result);
        }
    }

    /**
     * Should get actuator type id when constructor arguments are valid.
     */
    @Test
    void shouldGetActuatorTypeID() {
        // Arrange
        String actuatorTypeID = "SwitchActuator";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn(actuatorTypeID);

        try (MockedConstruction<ActuatorTypeID> mockedConstruction = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(actuatorTypeID);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ActuatorTypeID result = switchActuator.getActuatorTypeID();

            // Assert
            assertEquals(actuatorTypeIDDouble, result);
        }
    }

    @Test
    void shouldThrowException_whenGetActuatorTypeID() {
        // Arrange
        String actuatorTypeID = "BlindRollerActuator";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn(actuatorTypeID);

        try {
            new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("The value of 'actuatorTypeID' should be 'SwitchActuator'.");
        }


//        try (MockedConstruction<ActuatorTypeID> mockedConstruction = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
//            when(mock.toString()).thenReturn(actuatorTypeID);
//        })) {
//            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
//
//            // Act
//            ActuatorTypeID result = switchActuator.getActuatorTypeID();
//
//            // Assert
//            assertNotEquals(actuatorTypeIDDouble, result);
//        }
    }

    /**
     * Should get device type id when constructor arguments are valid.
     */
    @Test
    void shouldGetDeviceID() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

        // Act
        DeviceID result = switchActuator.getDeviceID();

        // Assert
        assertEquals(deviceIDDouble, result);
    }

    /**
     * Should set value when value is valid.
     */
    @Test
    void shouldSetValue() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

        // Act
        ValueObject result = switchActuator.setValue(switchActuatorValueDouble);

        // Assert
        assertEquals(switchActuatorValueDouble, result);
    }

    /**
     * Should set value when string is "ON".
     */
    @Test
    public void shouldSetValue_whenValueIsOn() {
        //Arrange
        String value = "ON";

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        SwitchActuatorValue valueDouble = mock(SwitchActuatorValue.class);
        when(valueDouble.toString()).thenReturn(value);

        try (MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class, (mock, context) -> {
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIdDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            //Act
            ValueObject result = switchActuator.setValue(valueDouble);

            //Assert
            assertEquals(result.toString(), value);
        }
    }

    /**
     * Should set value when string is "OFF".
     */
    @Test
    public void shouldSetValue_whenValueIsOff() {
        //Arrange
        String value = "OFF";

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        SwitchActuatorValue valueDouble = mock(SwitchActuatorValue.class);
        when(valueDouble.toString()).thenReturn(value);

        try (MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class, (mock, context) -> {
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIdDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            //Act
            ValueObject result = switchActuator.setValue(valueDouble);

            //Assert
            assertEquals(result.toString(), value);
        }
    }

    /**
     * Should throw IllegalArgumentException when value is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNull() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

        // Act & Assert
        try {
            switchActuator.setValue(null);
        } catch (IllegalArgumentException e) {
            // Assert
            assert e.getMessage().equals("The value of 'value' should not be null.");
        }
    }

    /**
     * Should return null when value is not instance of SwitchActuatorValue.
     */
    @Test
    void shouldThrowIlegalArgumentException_WhenValueIsNotInstanceOfSwitchActuatorValue() {
        // Arrange
        String value = "On";
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.getId()).thenReturn("SwitchActuator");

        ValueObject valueDouble = mock(ValueObject.class);
        when(valueDouble.toString()).thenReturn(value);

        // Act & Assert
        try (MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class, (mock, context) -> {
        })) {

            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            //Act
            ValueObject result = switchActuator.setValue(valueDouble);

            // Assert
            assertNull(result);
        }
    }

}
