package SmartHomeDDD.domain.Actuator;

import SmartHome.domain.Value;
import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.mockito.Mockito.*;

public class SetIntegerActuatorTest {
    /**
     * Test to verify that the SetIntegerActuator class can be instantiated when the constructor arguments are valid.
     */
    @Test
    public void shouldInstantiateSetIntegerActuator_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        //Act
        new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the deviceID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        //Arrange
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        //Act & Assert
        try {
            new SetIntegerActuator(null, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("DeviceID cannot be null");
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the modelPath is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        //Arrange
        DeviceID deviceIdDouble = mock(DeviceID.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        //Act & Assert
        try {
            new SetIntegerActuator(deviceIdDouble, null, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("ModelPath cannot be null");
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorName is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorNameIsNull() {
        //Arrange
        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        //Act & Assert
        try {
            new SetIntegerActuator(deviceIdDouble, modelPathDouble, null, actuatorTypeIDDouble, lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("ActuatorName cannot be null");
        }
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorTypeID is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenActuatorTypeIDIsNull() {
        //Arrange
        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        //Act & Assert
        try {
            new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, null, lowerLimit, upperLimit);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("ActuatorTypeID cannot be null");
        }
    }

    /**
     * Test to get the actuator ID.
     */
    @Test
    public void shouldGetActuatorID() {
        //Arrange
        String actuatorID = "actuatorID";

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
            when(mock.toString()).thenReturn(actuatorID);
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        ActuatorID result = setIntegerActuator.getID();

        //Assert
        assert result.toString().equals(actuatorID);
        }
    }

    /**
     * Test to get the actuator name.
     */
    @Test
    public void shouldGetActuatorName() {
        //Arrange
        String actuatorName = "actuatorName";

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);

        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        when(actuatorNameDouble.toString()).thenReturn(actuatorName);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        ActuatorName result = setIntegerActuator.getName();

        //Assert
        assert result.toString().equals(actuatorName);
        }
    }

    /**
     * Test to get the model path.
     */
    @Test
    public void shouldGetModelPath() {
        //Arrange
        String modelPath = "modelPath";

        DeviceID deviceIdDouble = mock(DeviceID.class);

        ModelPath modelPathDouble = mock(ModelPath.class);
        when(modelPathDouble.toString()).thenReturn(modelPath);

        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        ModelPath result = setIntegerActuator.getModelPath();

        //Assert
        assert result.toString().equals(modelPath);
        }
    }

    /**
     * Test to get the actuator type ID.
     */
    @Test
    public void shouldGetActuatorTypeID() {
        //Arrange
        String actuatorTypeID = "actuatorTypeID";

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.toString()).thenReturn(actuatorTypeID);

        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        ActuatorTypeID result = setIntegerActuator.getActuatorTypeID();

        //Assert
        assert result.toString().equals(actuatorTypeID);
        }
    }

    /**
     * Test to get the device ID.
     */
    @Test
    public void shouldGetDeviceID() {
        //Arrange
        String deviceID = "deviceID";

        DeviceID deviceIdDouble = mock(DeviceID.class);
        when(deviceIdDouble.toString()).thenReturn(deviceID);

        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        DeviceID result = setIntegerActuator.getDeviceID();

        //Assert
        assert result.toString().equals(deviceID);
        }
    }

    /**
     * Test to set the value when it is valid and within range.
     */
    @Test
    public void shouldSetValue() {
        //Arrange
        int value = 50;

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        SetIntegerValue valueDouble = mock(SetIntegerValue.class);
        when(valueDouble.toString()).thenReturn(value + "");

        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        ValueObject result = setIntegerActuator.setValue(valueDouble);

        //Assert
        assert result.toString().equals(value + "");
        }
    }

    /**
     * Test to set the value when it is valid but below the lower limit.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsBelowLowerLimit() {
        //Arrange
        int value = -1;

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        SetIntegerValue valueDouble = mock(SetIntegerValue.class);
        when(valueDouble.toString()).thenReturn(value + "");

        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act & Assert
        try {
            setIntegerActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Value cannot be less than the lower limit.");
        }
        }
    }

    /**
     * Test to set the value when it is valid but above the upper limit.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsAboveUpperLimit() {
        //Arrange
        int value = 101;

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        SetIntegerValue valueDouble = mock(SetIntegerValue.class);
        when(valueDouble.toString()).thenReturn(value + "");

        int lowerLimit = 0;
        int upperLimit = 100;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act & Assert
        try {
            setIntegerActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Value cannot be greater than the upper limit.");
        }
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

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        SetIntegerValue valueDouble = null;

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act & Assert
        try {
            setIntegerActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Value cannot be null");
        }
        }
    }

    /**
     * Test to set the value when it is not an instance of SetIntegerValue
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenValueIsNotInstanceOfSetIntegerValue() {
        //Arrange
        String value = "1";
        int lowerLimit = 0;
        int upperLimit = 100;

        DeviceID deviceIdDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        ValueObject valueDouble = mock(ValueObject.class);
        when(valueDouble.toString()).thenReturn(value);

        try(MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class,(mock, context) -> {
        })){
        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceIdDouble, modelPathDouble, actuatorNameDouble, actuatorTypeIDDouble, lowerLimit, upperLimit);

        //Act
        ValueObject result = setIntegerActuator.setValue(valueDouble);

        //Assert
        assert result == null;
        }
    }

}

