package SmartHomeDDD.domain.Actuator;


import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SwitchActuatorTest {

    @Test
    void shouldInstantiateSwitchActuator_WhenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        // Act
        new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
    }

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

    @Test
    void shouldGetActuatorID() {
        // Arrange
        String actuatorID = "actuatorID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        try (MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(actuatorID);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ActuatorID result = switchActuator.getID();

            // Assert
            assert result.toString().equals(actuatorID);
        }
    }

    @Test
    void shouldGetActuatorName() {
        // Arrange
        String actuatorName = "actuatorName";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);

        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        when(actuatorNameDouble.toString()).thenReturn(actuatorName);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        try (MockedConstruction<ActuatorName> mockedConstruction = mockConstruction(ActuatorName.class, (mock, context) -> {
            when(mock.toString()).thenReturn(actuatorName);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ActuatorName result = switchActuator.getName();

            // Assert
            assert result.toString().equals(actuatorName);
        }
    }

    @Test
    void shouldGetModelPath() {
        // Arrange
        String modelPath = "modelPath";

        DeviceID deviceIDDouble = mock(DeviceID.class);

        ModelPath modelPathDouble = mock(ModelPath.class);
        when(modelPathDouble.toString()).thenReturn(modelPath);

        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        try (MockedConstruction<ModelPath> mockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
            when(mock.toString()).thenReturn(modelPath);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ModelPath result = switchActuator.getModelPath();

            // Assert
            assert result.toString().equals(modelPath);
        }
    }

    @Test
    void shouldGetActuatorTypeID() {
        // Arrange
        String actuatorTypeID = "actuatorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        when(actuatorTypeIDDouble.toString()).thenReturn(actuatorTypeID);

        try (MockedConstruction<ActuatorTypeID> mockedConstruction = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(actuatorTypeID);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            ActuatorTypeID result = switchActuator.getActuatorTypeID();

            // Assert
            assert result.toString().equals(actuatorTypeID);
        }
    }

    @Test
    void shouldGetDeviceID() {
        // Arrange
        String deviceID = "deviceID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        when(deviceIDDouble.toString()).thenReturn(deviceID);

        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

        try (MockedConstruction<DeviceID> mockedConstruction = mockConstruction(DeviceID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(deviceID);
        })) {
            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            // Act
            DeviceID result = switchActuator.getDeviceID();

            // Assert
            assert result.toString().equals(deviceID);
        }
    }

    @Test
    void shouldSetValue() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

        // Act
        ValueObject result = switchActuator.setValue(switchActuatorValueDouble);

        // Assert
        assertEquals(switchActuatorValueDouble, result);
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNull() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        ActuatorName actuatorNameDouble = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

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

        ValueObject valueDouble = mock(ValueObject.class);
        when(valueDouble.toString()).thenReturn(value);

        // Act & Assert
        try (MockedConstruction<ActuatorID> mockedConstruction = mockConstruction(ActuatorID.class, (mock, context) -> {
        })) {

            SwitchActuator switchActuator = new SwitchActuator(deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

            //Act
            ValueObject result = switchActuator.setValue(valueDouble);

            // Assert
            assert result == null;
        }
    }

}
