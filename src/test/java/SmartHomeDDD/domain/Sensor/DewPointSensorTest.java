package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.DewPointSensor.DewPointSensor;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DewPointSensorTest {

    /**
     * Test to check if the DewPointSensor is instantiated correctly.
     */
    @Test
    public void shouldInstantiateDewPointSensor_WhenParametersAreValid() {
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        // act
        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //assert
        assertNotNull(dewPointSensor);

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the DeviceID is null.
     */
    @Test
    public void shouldThrowException_WhenDeviceIDIsNull() {
        //Arrange
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "DeviceID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the ModelPath is null.
     */
    @Test
    public void shouldThrowException_WhenModelPathIsNull() {
        //Arrange
        String deviceIDName = "123A";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "ModelPath is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test to check if the DewPointSensor throws an exception when the SensorName is null.
     */
    @Test
    public void shouldThrowException_WhenSensorNameIsNull() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorName is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test to check if the DewPointSensor throws an exception when the SensorTypeID is null.
     */
    @Test
    public void shouldThrowException_WhenSensorTypeIDIsNull() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = null;

        String expectedMessage = "SensorTypeID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_WhenSensorTypeIDIsInvalid () {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "Fahrenheit";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorTypeID must be 'DewPoint'";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Should return Sensor ID.
     */
    @Test
    public void shouldGetDewPointID() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorID result = dewPointSensor.getID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Should get sensor name.
     */
    @Test
    public void shouldGetDewPointName() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorName result = dewPointSensor.getName();

        //Assert
        assertEquals(sensorName.toString(), result.toString());

    }

    /**
     * Should get model Path.
     */
    @Test
    public void shouldGetDewPointModelPath() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        ModelPath result = dewPointSensor.getModelPath();

        //Assert
        assertEquals(modelPath.toString(), result.toString());
    }

    /**
     * Should get sensorType ID.
     */
    @Test
    public void shouldGetDewPointSensorTypeID() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorTypeID result = dewPointSensor.getSensorTypeID();

        //Assert
        assertEquals(sensorTypeID.toString(), result.toString());
    }

    /**
     * Should return device ID.
     */
    @Test
    public void shouldGetDeviceID() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        DeviceID result = dewPointSensor.getDeviceID();

        //Assert
        assertEquals(deviceID.toString(), result.toString());
    }

    /**
     * Should return dew point value.
     */
    @Test
    public void shouldGetDewPointValue() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        //Act
        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Assert
        int value = Integer.parseInt(dewPointSensor.getValue().toString());
        assertTrue(value >= -70 && value <= 70);
    }


}