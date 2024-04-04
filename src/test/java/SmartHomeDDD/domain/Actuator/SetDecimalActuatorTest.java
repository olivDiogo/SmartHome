package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.domain.Actuator.SetDecimalActuator.SetDecimalActuator;
import SmartHomeDDD.domain.Actuator.SetDecimalActuator.SetDecimalActuatorLimits;
import SmartHomeDDD.domain.Actuator.SetDecimalActuator.SetDecimalValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetDecimalActuatorTest {

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

    @Test
    void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
        // Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "DeviceID cannot be null";

        // Act
        try {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }

    }

    @Test
    void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = null;
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "ModelPath cannot be null";

        // Act
        try {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = null;
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "ActuatorTypeID cannot be null";

        // Act
        try {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = null;
        SetDecimalActuatorLimits limits = new SetDecimalActuatorLimits(1.5, 9.5);

        String expectedMessage = "ActuatorName cannot be null";

        // Act
        try {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenLimitsIsNull() {
        // Arrange
        DeviceID deviceID = new DeviceID("DeviceID");
        ModelPath modelPath = new ModelPath("ModelPath");
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetDecimal");
        ActuatorName actuatorName = new ActuatorName("ActuatorName");
        SetDecimalActuatorLimits limits = null;

        String expectedMessage = "Limits cannot be null";

        // Act
        try {
            new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals(expectedMessage, e.getMessage());
        }
    }


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
        assertEquals(result.getId(), id);
    }

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
        ValueObject result = setDecimalActuator.setValue(valueDouble);

        // Assert
        assertEquals(result.toString(), value + "");


    }

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
        try {
            setDecimalActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

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

        // Act & Assert
        try {
            setDecimalActuator.setValue(valueDouble);
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }



}