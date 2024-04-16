package smartHome.domain.sensor;


import org.junit.jupiter.api.Test;
import smartHome.domain.sensor.percentagePositionSensor.PercentagePositionSensor;
import smartHome.domain.sensor.percentagePositionSensor.PercentagePositionSensorValue;
import smartHome.valueObject.DeviceID;
import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorName;
import smartHome.valueObject.SensorTypeID;


import static org.junit.jupiter.api.Assertions.*;


/**
 * Test cases for the PercentagePositionSensor class.
 */
class PercentagePositionSensorTest {

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
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
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

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = percentagePositionSensor.getName();

        // Assert

        assertEquals(sensorName, result);
    }

    @Test
    void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        PercentagePositionSensorValue result = percentagePositionSensor.getValue();

        // Assert
        assertNotNull(result);
    }

}