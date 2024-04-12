package smartHome.domain.actuator;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smartHome.ddd.IValueObject;
import smartHome.domain.actuator.setIntegerActuator.SetIntegerActuator;
import smartHome.domain.actuator.switchActuator.SwitchActuator;
import smartHome.domain.actuator.switchActuator.SwitchActuatorValue;
import smartHome.valueObject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SwitchActuatorTest {

    /**
     * Should create instance of SwitchActuator when parameters are valid.
     */
    @Test
    void shouldCreateInstanceOfSwitchActuator_WhenParametersAreValid() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            //Assert
            assertNotNull(switchActuator);
        }
    }

    /**
     * Should return SwitchActuatorID when getActuatorID is called.
     */
    @Test
    void shouldReturnSwitchActuatorID_WhenGetActuatorIDIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("Switch");

        String expectedActuatorID = "123";

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            ActuatorID actuatorID = switchActuator.getID();

            //Assert
            assertEquals(expectedActuatorID, actuatorID.getID());
        }
    }

    /**
     * Should return the actuator name when getActuatorName is called.
     */
    @Test
    void shouldReturnActuatorName_WhenGetActuatorNameIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorName.toString()).thenReturn("Switch Actuator");

        String expectedActuatorName = "Switch Actuator";

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            ActuatorName actuatorNameResult = switchActuator.getName();

            //Assert
            assertEquals(expectedActuatorName, actuatorNameResult.toString());
        }
    }

    /**
     * Should return the model path when getModelPath is called.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathMethodIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(modelPath.toString()).thenReturn("modelPath");

        String expectedModelPath = "modelPath";

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            ModelPath modelPathResult = switchActuator.getModelPath();

            //Assert
            assertEquals(expectedModelPath, modelPathResult.toString());
        }
    }

    /**
     * Should return the actuator type ID when getActuatorTypeID is called.
     */
    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            ActuatorTypeID actuatorTypeIDResult = switchActuator.getActuatorTypeID();

            //Assert
            assertEquals(actuatorTypeID, actuatorTypeIDResult);
        }
    }

    /**
     * Should set value to true when setValue is called with true.
     */
    @Test
    void shouldSetValueToTrue_WhenSetValueIsCalledWithTrue() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuatorValue value = mock(SwitchActuatorValue.class);
            when(value.toString()).thenReturn("true");

            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            //Assert
            assertNotNull(switchActuator.setValue(value));
        }
    }

    /**
     * Should set value to false when setValue is called with false.
     */
    @Test
    void shouldSetValueToFalse_WhenSetValueIsCalledWithFalse() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuatorValue value = mock(SwitchActuatorValue.class);
            when(value.toString()).thenReturn("false");

            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            //Assert
            assertNotNull(switchActuator.setValue(value));
        }
    }

    /**
     * Should return deviceID when getDeviceID is called.
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDMethodIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(deviceID.toString()).thenReturn("123");

        String expectedDeviceIDString = "123";

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            //Act
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            DeviceID deviceIDResult = switchActuator.getDeviceID();

            //Assert
            assertEquals(expectedDeviceIDString, deviceIDResult.toString());
        }
    }

    /**
     * Should return true when instances are same object.
     */
    @Test
    void shouldReturnTrue_WhenInstancesAreSameObject() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);
            when(switchActuatorValueDouble.toString()).thenReturn("true");

            switchActuator.setValue(switchActuatorValueDouble);

            //Act
            boolean result = switchActuator.equals(switchActuator);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * should return false when instances are different objects.
     */
    @Test
    void shouldReturnFalse_WhenInstancesAreNotEqual() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            SwitchActuator switchActuator1 = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);
            when(switchActuatorValueDouble.toString()).thenReturn("true");

            switchActuator.setValue(switchActuatorValueDouble);

            //Act
            boolean result = switchActuator.equals(switchActuator1);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * should return false when an object is not an instance of switch actuator.
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotTheInstanceOfSwitchActuator() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            IValueObject value = mock(IValueObject.class);
            when(value.toString()).thenReturn("true");

            switchActuator.setValue(value);

            SetIntegerActuator setIntegerActuator = mock(SetIntegerActuator.class);

            //Act
            boolean result = switchActuator.equals(setIntegerActuator);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * should return actuator hashcode.
     */
    @Test
    void shouldReturnSwitchActuatorHashCode() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

            int expectedHashCode = switchActuator.hashCode();

            //Act
            int hashCode = switchActuator.hashCode();

            //Assert
            assertEquals(expectedHashCode, hashCode);
        }
    }

    /**
     * should return switch actuator in string format.
     */
    @Test
    void shouldReturnSwitchActuatorInStringFormat() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        SwitchActuatorValue switchActuatorValue = mock(SwitchActuatorValue.class);

        when(actuatorTypeID.getID()).thenReturn("Switch");
        when(actuatorTypeID.toString()).thenReturn("Switch");
        when(deviceID.toString()).thenReturn("123");
        when(actuatorName.toString()).thenReturn("Switch Actuator");
        when(modelPath.toString()).thenReturn("modelPath");
        when(switchActuatorValue.toString()).thenReturn("true");

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123");
            when(mock.toString()).thenReturn("123");
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);
            switchActuator.setValue(switchActuatorValue);

            String expectedString = "SwitchActuator: DeviceID=123, ActuatorName=Switch Actuator, ModelPath=modelPath, ActuatorTypeID=Switch, ActuatorID=123, Value=true";

            //Act
            String result = switchActuator.toString();

            //Assert
            assertEquals(expectedString, result);
        }
    }

}