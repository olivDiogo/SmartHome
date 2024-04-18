package smart_home.domain.actuator.set_decimal_actuator;

import org.junit.jupiter.api.Test;
import smart_home.ddd.IValueObject;
import smart_home.domain.actuator.blind_roller_actuator.BlindRollerValue;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the SetDecimalActuator class.
 */
class SetDecimalActuatorAggregateTest {

    /**
     * Verifies that SetDecimalActuator is correctly instantiated when elements are valid.
     */
    @Test
    void shouldInstantiateSetDecimalActuator_WhenConstructorElementsAreValid() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        // Act
        SetDecimalActuator sensor = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Assert
        assertNotNull(sensor);
    }

    /**
     * Test the second constructor.
     */
    @Test
    void shouldInstantiateSetDecimalActuator_WhenSecondConstructorElementsAreValid() {
        // Arrange
        ActuatorID actuatorID = new ActuatorID("ActuatorID");
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        // Act
        SetDecimalActuator sensor = new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Assert
        assertNotNull(sensor);
    }


    /**
     * Test for invalid ActuatorID
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorIDIsNull() {
        // Arrange
        ActuatorID actuatorID = null;
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);


        String expectedMessage = "ActuatorID is required";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the DeviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNullForSecondActuator() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);
        ActuatorID actuatorID = new ActuatorID("ActuatorID");


        String expectedMessage = "DeviceID is required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());

    }


    /**
     * Verifies that an IllegalArgumentException is thrown when the DeviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "DeviceID is required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());

    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the ModelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "ModelPath is required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the ModelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNullForSecondConstructor() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);
        ActuatorID actuatorID = new ActuatorID("ActuatorID");

        String expectedMessage = "ModelPath is required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the ActuatorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNullForSecondConstructor() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = null;
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);
        ActuatorID actuatorID = new ActuatorID("ActuatorID");


        String expectedMessage = "ActuatorTypeID is required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the ActuatorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = null;
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "ActuatorTypeID is required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test for an invalid actuatorTypeID of another type
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNotSetDecimalForSecondConstructor() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);
        ActuatorID actuatorID = new ActuatorID("ActuatorID");


        String expectedMessage = "The value of 'actuatorTypeID' should be 'SetDecimal'.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test for an invalid actuatorTypeID of another type
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNotSetDecimal() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("Switch");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "The value of 'actuatorTypeID' should be 'SetDecimal'.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the ActuatorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNullForSecondConstructor() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = null;
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);
        ActuatorID actuatorID = new ActuatorID("123");

        String expectedMessage = "ActuatorName is required";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the ActuatorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = null;
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "ActuatorName is required";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the limits are null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenLimitsIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = null;

        String expectedMessage = "SetDecimalActuatorLimits are required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the limits are null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenLimitsIsNullForSecondConstructor() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = null;
        ActuatorID actuatorID = new ActuatorID("123");

        String expectedMessage = "SetDecimalActuatorLimits are required";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SetDecimalActuator(actuatorID, deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    /**
     * Generates an ActuatorID for the SetDecimalActuator.
     */
    @Test
    void shouldGenerateActuatorID() {
        // Arrange
        DeviceID deviceId = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceId, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorID result = setDecimalActuator.getActuatorID();

        // Assert
        assertNotNull(result);
    }


    /**
     * Test to verify if the correct actuator name is returned.
     */
    @Test
    void shouldReturnActuatorName() {
        // Arrange
        String name = "ActuatorName";

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName(name);
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorName result = setDecimalActuator.getActuatorName();

        // Assert
        assertEquals(result, actuatorName);
    }

    /**
     * Test to verify if the correct model path is returned.
     */
    @Test
    void shouldReturnModelPath() {
        // Arrange
        String path = "ModelPath";

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath(path);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ModelPath result = setDecimalActuator.getModelPath();

        // Assert
        assertEquals(result.toString(), path);
    }

    /**
     * Test to verify if the correct actuator type ID is returned.
     */
    @Test
    void shouldReturnActuatorTypeID() {
        // Arrange
        String id = "SetDecimal";

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(id);
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        ActuatorTypeID result = setDecimalActuator.getActuatorTypeID();

        // Assert
        assertEquals(result.getID(), id);
    }

    /**
     * Test to verify if the correct device ID is returned.
     */
    @Test
    void shouldGetDeviceID() {
        // Arrange
        String id = "DeviceID";

        DeviceID deviceID = new DeviceID(id);
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        DeviceID result = setDecimalActuator.getDeviceID();

        // Assert
        assertEquals(result.toString(), id);
    }

    /**
     * Test to verify if the correct limits are returned.
     */
    @Test
    void shouldGetLimits() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        SetDecimalActuatorLimits result = setDecimalActuator.getLimits();

        // Assert
        assertEquals(result.getLowerLimit(), lowerLimit);
        assertEquals(result.getUpperLimit(), upperLimit);
    }

    /**
     * Test to verify if the correct value is returned within the limits.
     */
    @Test
    void shouldSetValue_WhenValueIsWithinLimits() {
        // Arrange
        double value = 5.5;
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalValue valueDouble = new SetDecimalValue(value);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        IValueObject result = setDecimalActuator.setValue(valueDouble);

        // Assert
        assertEquals(result.toString(), value + "");


    }

    /**
     * Test to verify if an IllegalArgumentException is thrown when the value is less than the lower limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsLessThanLowerLimit() {
        // Arrange
        double value = 0.5;
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalValue valueDouble = new SetDecimalValue(value);

        String expectedMessage = "Value cannot be less than the lower limit.";

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act & Assert
        try {
            setDecimalActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Test to verify if an IllegalArgumentException is thrown when the value is greater than the upper limit.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsGreaterThanUpperLimit() {
        // Arrange
        double value = 10.5;
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalValue valueDouble = new SetDecimalValue(value);

        String expectedMessage = "Value cannot be greater than the upper limit.";

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            setDecimalActuator.setValue(valueDouble);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify if an IllegalArgumentException is thrown when the value is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNull() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalValue valueDouble = null;

        String expectedMessage = "Value cannot be null";

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            setDecimalActuator.setValue(valueDouble);
        });

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to set the value when it is not an instance of SetIntegerValue
     */
    @Test
    void shouldReturnNull_WhenValueIsNotSetDecimalValue() {
        // Arrange
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);


        IValueObject nonDecimalValue = new BlindRollerValue(2);
        IValueObject result = setDecimalActuator.setValue(nonDecimalValue);

        assertNull(result);
    }

    /**
     * Test method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenInstanceIsComparedToItself() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

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
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

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
    void shouldReturnFalse_whenInstanceIsComparedToAnObjectOfDifferentClass(){
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);

        // Act
        boolean result = setDecimalActuator.equals(modelPath);

        // Assert
        assertFalse(result);
    }

    /**
     * Test of method toString.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled(){
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        ActuatorID actuatorID = setDecimalActuator.getActuatorID();

        String expected = "ActuatorID: " + actuatorID + ", ActuatorName: " + actuatorName + ", ModelPath: " + modelPath + ", ActuatorTypeID: " + actuatorTypeID + ", DeviceID: " + deviceID + ", Limits: " + limits;

        // Act
        String result = setDecimalActuator.toString();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test of method hashcode.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled(){
        // Arrange
        String deviceID = "DeviceID";
        String modelPath = "ModelPath";
        String actuatorTypeID = "SetDecimal";
        String actuatorName = "ActuatorName";
        double lowerLimit = 1.5;
        double upperLimit = 9.5;

        DeviceID deviceId = new DeviceID(deviceID);
        ModelPath modelPath1 = new ModelPath(modelPath);
        ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
        ActuatorName actuatorName1 = new ActuatorName(actuatorName);
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(lowerLimit, upperLimit);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(deviceId, modelPath1, actuatorTypeID1, actuatorName1, limits);
        ActuatorID actuatorID = setDecimalActuator.getActuatorID();

        int expected = actuatorID.getID().hashCode();

        // Act
        int result = setDecimalActuator.hashCode();

        // Assert
        assertEquals(expected,result);
    }
}