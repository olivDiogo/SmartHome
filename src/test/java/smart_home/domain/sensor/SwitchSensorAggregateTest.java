package smart_home.domain.sensor;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor.dew_point_sensor.DewPointSensor;
import smart_home.domain.sensor.switch_sensor.SwitchSensor;
import smart_home.domain.sensor.switch_sensor.SwitchSensorValue;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SwitchSensorAggregateTest {
    /**
     * Tests the instantiation of SwitchSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateSwitchSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(switchSensor);
    }

    /**
     * Tests the instantiation of SwitchSensor when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        assertEquals("DeviceID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of SwitchSensor when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        String deviceIDValue = "deviceID";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        assertEquals("ModelPath is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of SwitchSensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        assertEquals("SensorTypeID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of SwitchSensor when the sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        assertEquals("SensorName is required", exception.getMessage());
    }

    /**
     * Tests the getID method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID result = switchSensor.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the getSensorName method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = switchSensor.getName();

        // Assert
        assertEquals(sensorName, result);
    }

    /**
     * Tests the getModelPath method of SwitchSensor.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = switchSensor.getModelPath();

        // Assert
        assertEquals(modelPath, result);
    }

    /**
     * Tests the getSensorTypeID method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = switchSensor.getSensorTypeID();

        // Assert
        assertEquals(sensorTypeID, result);
    }

    /**
     * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Switch'.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotOfSwitchType() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Humidity";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        // Assert
        assertEquals("SensorTypeID must be of type 'Switch'", exception.getMessage());
    }

    /**
     * Tests the getDeviceID method of SwitchSensor.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = switchSensor.getDeviceID();

        // Assert
        assertEquals(deviceID, result);
    }

    /**
     * Tests the getValue method of SwitchSensor.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SwitchSensorValue result = switchSensor.getValue();

        // Assert
        String state = result.toString();
        assertTrue("On".equals(state) || "Off".equals(state), "State should be 'On' or 'Off'.");
    }

    /**
     * Tests if the equals method returns true when the instances are the same object.
     */
    @Test
    void shouldReturnTrue_WhenInstancesAreSameObject () {
        //Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = switchSensor.equals(switchSensor);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests if the equals method returns false when the objects are not the same.
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotTheSame () {
        //Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
        SwitchSensor switchSensor1 = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = switchSensor.equals(switchSensor1);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if the equals method returns false when the object is not an instance of SwitchSensor.
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotInstanceOfSwitchSensor () {
        //Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        DewPointSensor dewPointSensor = mock(DewPointSensor.class);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = switchSensor.equals(dewPointSensor);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if the toString method returns the SwitchSensor in string format.
     */
    @Test
    void shouldReturnSwitchSensorInString_WhenToStringMethodIsCalled () {
        //Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        String expected = "SwitchSensor: DeviceID= " + deviceIDValue + " ModelPath= " + modelPathValue + " SensorTypeID= " + sensorTypeIDValue + " SensorName= " + sensorNameValue + " SensorID= " + switchSensor.getID();
        //Act
        String result = switchSensor.toString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if the hashCode method returns the hash code of the SwitchSensor.
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeMethodIsCalled () {
        //Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Switch";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

        int expected = switchSensor.getID().hashCode();

        //Act
        int result = switchSensor.hashCode();

        //Assert
        assertEquals(expected, result);
    }
}
