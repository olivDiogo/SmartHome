package smartHome.domain.sensor;

import smartHome.domain.actuator.setIntegerActuator.SetIntegerActuator;
import smartHome.domain.actuator.setIntegerActuator.SetIntegerActuatorLimits;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetIntegerActuatorTest {
    /**
     * Test to verify that the SetIntegerActuator class can be instantiated when the constructor arguments are valid.
     */
    @Test
    void testSetIntegerActuatorCanBeInstantiated_WhenConstructorArgumentsAreValid() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);

        // Act
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Assert
        assertNotNull(actuator);
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the deviceID is null.
     */
    @Test
    void testSetIntegerActuatorThrowsIllegalArgumentException_WhenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        String expectedMessage ="DeviceID cannot be null";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the modelPath is null.
     */
    @Test
    void testSetIntegerActuatorThrowsIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        String expectedMessage ="ModelPath cannot be null";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorName is null.
     */
    @Test
    void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = null;
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        String expectedMessage ="ActuatorName cannot be null";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorTypeID is null.
     */
    @Test
    void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = null;
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        String expectedMessage ="ActuatorTypeID cannot be null";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the actuatorTypeID is not SetInteger.
     */
    @Test
    void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorTypeIDIsNotSetInteger() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("NotSetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        String expectedMessage ="ActuatorTypeID must be SetInteger";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the limits is null.
     */
    @Test
    void testSetIntegerActuatorThrowsIllegalArgumentException_WhenLimitsIsNull() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = null;
        String expectedMessage ="SetIntegerActuatorLimits cannot be null";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to get the actuator ID.
     */
    @Test
    void shouldReturnActuatorID_WhenGetIDisCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorID actuatorID = actuator.getID();

        // Assert
        assertTrue(actuator.toString().contains(actuatorID.toString()));
    }

    /**
     * Test to get the actuator name.
     */
    @Test
    void shouldReturnActuatorName_WhenGetNameIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorName name = actuator.getName();

        // Assert
        assertEquals(actuatorName, name);
    }

    /**
     * Test to get the model path.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ModelPath path = actuator.getModelPath();

        // Assert
        assertEquals(modelPath, path);
    }

    /**
     * Test to get the actuator type ID.
     */
    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorTypeID typeID = actuator.getActuatorTypeID();

        // Assert
        assertEquals(actuatorTypeID, typeID);
    }

    /**
     * Test to get the device ID.
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        DeviceID id = actuator.getDeviceID();

        // Assert
        assertEquals(deviceID, id);
    }

    /**
     * Test to get the limits.
     */
    @Test
    void shouldReturnLimits_WhenGetLimitsIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        SetIntegerActuatorLimits actuatorLimits = actuator.getLimits();

        // Assert
        assertEquals(limits, actuatorLimits);
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
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = actuator.equals(actuator);

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
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator1 = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        SetIntegerActuator actuator2 = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = actuator1.equals(actuator2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test of method equals when the instance is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_whenInstanceIsComparedToAnObjectOfDifferentClass(){
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);
        SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = actuator.equals(modelPath);

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
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);

        try(MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("ActuatorID");
        })) {
            SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

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
    void shouldReturnHashCode_WhenHashCodeIsCalled() {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.getID()).thenReturn("SetInteger");
        SetIntegerActuatorLimits limits = mock(SetIntegerActuatorLimits.class);

        try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

            int expected = actuator.getID().hashCode();

            //Act
            int result = actuator.hashCode();

            //Assert
            assertEquals(expected, result);
        }
    }
}
