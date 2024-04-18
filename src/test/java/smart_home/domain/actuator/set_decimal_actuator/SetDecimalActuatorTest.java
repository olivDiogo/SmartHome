package smart_home.domain.actuator.set_decimal_actuator;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetDecimalActuatorTest {

    /**
     * Test if the SetDecimalActuator object is instantiated when the constructor elements are valid.
     */
    @Test
    void shouldInstantiateSetDecimalActuator_WhenConstructorElementsAreValid() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        ActuatorName actuatorName = mock(ActuatorName.class);
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);

        // Act
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Assert
        assertNotNull(setDecimalActuator);

    }

    /**
     * Test the second constructor.
     */
    @Test
    void shouldCreateSetDecimalActuator_WhenSecondConstructorArgumentsAreValid() {
        // Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);

        // Act
        SetDecimalActuator actuator = new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Assert
        assertNotNull(actuator);
    }

    /**
     * Test for invalid actuatorID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorIDIsNull() {
        // Arrange
        ActuatorID actuatorID = null;
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        String expectedMessage = "ActuatorID is required";

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetDecimalActuator class throws an IllegalArgumentException when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        String expectedMessage = "DeviceID is required";

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetDecimalActuator class throws an IllegalArgumentException when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        String expectedMessage = "ModelPath is required";

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetDecimalActuator class throws an IllegalArgumentException when the actuatorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = null;
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        String expectedMessage = "ActuatorName is required";

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetDecimalActuator class throws an IllegalArgumentException when the actuatorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = null;
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        String expectedMessage = "ActuatorTypeID is required";

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetDecimalActuator class throws an IllegalArgumentException when the actuatorTypeID is not SetDecimal.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNotSetDecimal() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        String expectedMessage = "The value of 'actuatorTypeID' should be 'SetDecimal'.";

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetDecimalActuator class throws an IllegalArgumentException when the limits are null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenLimitsAreNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = null;
        String expectedMessage = "SetDecimalActuatorLimits is required";

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to get the actuator ID.
     */
    @Test
    void shouldGetActuatorID_WhenGetIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorID result = setDecimalActuator.getActuatorID();

        // Assert
        assertTrue(setDecimalActuator.toString().contains(result.toString()));    }

    /**
     * Test to get the actuator name.
     */
    @Test
    void shouldGetActuatorName_WhenCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorName result = setDecimalActuator.getActuatorName();

        // Assert
        assertEquals(actuatorName, result);
    }


    /**
     * Test to get the model path.
     */
    @Test
    void shouldGetModelPath_WhenCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ModelPath result = setDecimalActuator.getModelPath();

        // Assert
        assertEquals(modelPath, result);
    }


    /**
     * Test to get the actuator type ID.
     */
    @Test
    void shouldGetActuatorTypeID_WhenCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorTypeID result = setDecimalActuator.getActuatorTypeID();

        // Assert
        assertEquals(actuatorTypeID, result);
    }

    /**
     * Test to get the device ID.
     */
    @Test
    void shouldGetDeviceID_WhenCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        DeviceID result = setDecimalActuator.getDeviceID();

        // Assert
        assertEquals(deviceID, result);
    }


    /**
     * Test to get the limits.
     */
    @Test
    void shouldGetLimits_WhenCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        SetDecimalActuatorLimits result = setDecimalActuator.getLimits();

        // Assert
        assertEquals(limits, result);
    }


    /**
     * Test method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenTheInstanceIsComparedToItself() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);
        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = setDecimalActuator.equals(setDecimalActuator);

        // Assert
        assertTrue(result);

    }

    /**
     * Test of method equals when the instances are not equal.
     */
    @Test
    void shouldReturnFalse_whenInstancesAreNotEqual(){
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        SetDecimalActuator setDecimalActuator2 = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = setDecimalActuator.equals(setDecimalActuator2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test of method equals when the instance is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_WhenInstanceIsComparedToAnObjectOfDifferentClass(){
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = setDecimalActuator.equals(new Object());

        // Assert
        assertFalse(result);
    }

    /**
     * Test of method toString.
     */
    @Test
    void shouldReturnString_WhenToStringIsCalled(){
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);

        try(MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("ActuatorID");
        })) {
            SetDecimalActuator actuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

            //Act
            String result = actuator.toString();

            //Assert
            assertTrue(result.contains("ActuatorID"));
        }
    }


    /**
     * Test of method hashcode.
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeIsCalled(){
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetDecimal");
        SetDecimalActuatorLimits limits = mock(SetDecimalActuatorLimits.class);

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            SetDecimalActuator actuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

            int expected = actuator.getActuatorID().hashCode();

            //Act
            int result = actuator.hashCode();

            //Assert
            assertEquals(expected, result);
        }
    }
}
