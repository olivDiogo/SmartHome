package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.InstantPowerConsumptionSensor.InstantPowerConsumptionSensor;
import SmartHomeDDD.domain.Sensor.InstantPowerConsumptionSensor.InstantPowerConsumptionValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstantPowerConsumptionSensorTest {

    /**
     * Test to verify that the InstantPowerConsumptionSensor is instantiated when all parameters are valid.
     */
    @Test
    public void shouldInstantiateInstantPowerConsumptionSensor_WhenAllParametersAreValid() {
        // Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        //act
        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //assert
        assertNotNull(instantPowerConsumptionSensor);

    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path is null.
     */
    @Test
    public void shouldThrowException_WhenDeviceIDIsNull() {
        // Arrange
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "DeviceID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path is null.
     */
    @Test
    public void shouldThrowException_WhenModelPathIsNull() {
        // Arrange
        String deviceIDName = "123B";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "ModelPath is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor name is null.
     */
    @Test
    public void shouldThrowException_WhenSensorNameIsNull() {
        // Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorName is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor type ID is null.
     */
    @Test
    public void shouldThrowException_WhenSensorTypeIDIsNull() {
        // Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = null;

        String expectedMessage = "SensorTypeID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowException_WhenSensorTypeIsNotCorrect () {
        // Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Temperature";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorTypeID must be of type 'Watt'";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor generates an ID when the sensor is instantiated.
     */
    @Test
    public void shouldGenerateInstantPowerConsumptionID_WhenInstantPowerConsumptionSensorIsInstantiated() {
        // Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorID result = instantPowerConsumptionSensor.getID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor returns the sensor name.
     */
    @Test
    public void shouldReturnSensorName_WhenGetSensorNameIsCalled() {
        // Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        String expectedName = sensorName.toString();

        //Act
        SensorName result = instantPowerConsumptionSensor.getName();

        //Assert
        assertEquals(expectedName, result.toString());


    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor returns the model path.
     */
    @Test
    public void shouldGetInstantPowerConsumptionModelPath() {
        //Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedModelPath = modelPath.toString();

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        ModelPath result = instantPowerConsumptionSensor.getModelPath();

        //Assert
        assertEquals(expectedModelPath, result.toString());
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor returns the sensor type ID.
     */
    @Test
    public void shouldGetInstantPowerConsumptionSensorTypeID() {
        //Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedSensorTypeID = sensorTypeID.toString();

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorTypeID result = instantPowerConsumptionSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedSensorTypeID, result.toString());
    }

    @Test
    public void shouldGetDeviceID() {
        //Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedDeviceID = deviceID.toString();

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        DeviceID result = instantPowerConsumptionSensor.getDeviceID();

        //Assert
        assertEquals(expectedDeviceID, result.toString());
    }

    @Test
    public void shouldGetInstantPowerConsumptionValue() {
        //Arrange
        String deviceIDName = "123B";
        String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
        String name = "InstantPowerConsumptionSensor";
        String typeID = "Watt";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        InstantPowerConsumptionValue result = instantPowerConsumptionSensor.getValue();

        //Assert
        double value = Double.parseDouble(result.toString());
        assertTrue(value >= 0 && value <= 100);
        assertTrue(value >1);

    }


}