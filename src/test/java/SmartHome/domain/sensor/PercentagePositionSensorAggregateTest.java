package smartHome.domain.sensor;

import smartHome.domain.sensor.percentagePositionSensor.PercentagePositionSensor;
import smartHome.valueObject.DeviceID;
import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorName;
import smartHome.valueObject.SensorTypeID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test cases for the PercentagePositionSensor class.
 */
class PercentagePositionSensorAggregateTest {

    /**
     * Test to verify that PercentagePositionSensor is properly instantiated when constructor arguments are valid.
     */
    @Test
    void shouldInstantiatePercentagePositionSensor_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = new SensorName("sensorName");

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when ModelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = null;
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = new SensorName("sensorName");

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID));
        assertEquals("ModelPath is required", thrown.getMessage());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when SensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = null;

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID));
        assertEquals("SensorName is required", thrown.getMessage());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when SensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = null;
        SensorName sensorName = new SensorName("sensorName");

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID));
        assertEquals("SensorTypeID is required", thrown.getMessage());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when DeviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = new SensorName("sensorName");

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID));
        assertEquals("DeviceID is required", thrown.getMessage());
    }

    /**
     * Test to verify that the correct value is returned when getValue is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        String result = percentagePositionSensor.getValue().toString();

        // Assert
        assertEquals("14", result);
    }

    /**
     * Test to verify that the correct ID is returned when getID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        String result = percentagePositionSensor.getID().toString();

        // Assert

        assertNotNull(result);
    }

    /**
     * Test to verify that the correct SensorTypeID is returned when getSensorTypeID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        SensorTypeID result = percentagePositionSensor.getSensorTypeID();

        // Assert

        assertEquals(sensorTypeID, result);
    }

    /**
     * Test to verify that the correct DeviceID is returned when getDeviceID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetDeviceIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        DeviceID result = percentagePositionSensor.getDeviceID();

        // Assert

        assertEquals(deviceID, result);
    }

    /**
     * Test to verify that the correct ModelPath is returned when getModelPath is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetModelPathIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        ModelPath result = percentagePositionSensor.getModelPath();

        // Assert

        assertEquals(modelPath, result);
    }

    /**
     * Test to verify that the correct SensorName is returned when getName is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetNameIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        SensorName result = percentagePositionSensor.getName();

        // Assert

        assertEquals(sensorName, result);
    }

    @Test
    void shouldReturnTrue_WhenInstancesAreSameObject() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        boolean result = percentagePositionSensor.equals(percentagePositionSensor);

        // Assert

        assertTrue(result);
    }

    @Test
    void shouldReturnFalse_WhenInstancesAreNotEqual() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);

        // Act
        boolean result = percentagePositionSensor.equals(null);

        // Assert

        assertFalse(result);
    }

    @Test
    void shouldReturnPercentagePositionSensorHashCode_WhenHashCodeMethodIsCalled(){

        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);
        int expected = percentagePositionSensor.hashCode();
        // Act
        int result = percentagePositionSensor.hashCode();

        // Assert

        assertEquals(expected,result);
    }

    /** test for tostring method */

    @Test
    void shouldReturnPercentagePositionSensorString_WhenToStringMethodIsCalled(){

        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(modelPath, sensorName, sensorTypeID, deviceID);
        String expected = "SwitchSensor: DeviceID= " + deviceIDValue + " ModelPath= " + modelPathValue + " SensorTypeID= " + sensorTypeIDValue + " SensorName= " + sensorNameValue + " SensorID= " + percentagePositionSensor.getID().getID();
        // Act
        String result = percentagePositionSensor.toString();

        // Assert

        assertEquals(expected,result);
    }

}
