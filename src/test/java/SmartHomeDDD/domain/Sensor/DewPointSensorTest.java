package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DewPointSensorTest {

    /**
     * Test to check if the DewPointSensor is instantiated correctly.
     */
    @Test
    public void shouldInstantiateDewPointSensor_WhenParametersAreValid() {
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        // act
        new DewPointSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble,  sensorNameDouble);

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the DeviceID is null.
     */
    @Test
    public void shouldThrowException_WhenDeviceIDIsNull() {
        //Arrange
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = null;
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);


        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        String expectedMessage = "DeviceID is required";


        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble)
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
        String deviceID = "123A";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = null;
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        String expectedMessage = "ModelPath is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble)
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
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = null;
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        String expectedMessage = "SensorName is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble)
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
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = null;

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);

        String expectedMessage = "SensorTypeID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldGetDewPointID() {
        //Arrange
        String sensorID = "123C";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorIDDouble = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorID);
        })) {

            DewPointSensor dewPointSensor = new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //Act
            SensorID result = dewPointSensor.getID();

            //Assert
            assertEquals(sensorID, result.toString());
        }
    }

    @Test
    public void shouldGetDewPointName() {
        //Arrange
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);
        when(sensorNameDouble.toString()).thenReturn(sensorName);


        try (MockedConstruction<SensorName> mockedConstruction = mockConstruction(SensorName.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorName);
        })) {

            DewPointSensor dewPointSensor = new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //Act
            SensorName result = dewPointSensor.getName();

            //Assert
            assertEquals(sensorName, result.toString());
        }
    }

    @Test
    public void shouldGetDewPointModelPath() {
        //Arrange
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);
        when(sensorNameDouble.toString()).thenReturn(sensorName);

        try (MockedConstruction<ModelPath> mockedConstruction = mockConstruction(ModelPath.class, (mock, context) -> {
            when(mock.toString()).thenReturn(modelPath);
        })) {

            DewPointSensor dewPointSensor = new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //Act
            ModelPath result = dewPointSensor.getModelPath();

            //Assert
            assertEquals(modelPath, result.toString());
        }
    }

    @Test
    public void shouldGetDewPointSensorTypeID() {
        //Arrange
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try (MockedConstruction<SensorTypeID> mockedConstruction = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {

            DewPointSensor dewPointSensor = new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //Act
            SensorTypeID result = dewPointSensor.getSensorTypeID();

            //Assert
            assertEquals(sensorTypeID, result.toString());
        }
    }

    @Test
    public void shouldGetDeviceID() {
        //Arrange
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try (MockedConstruction<DeviceID> mockedConstruction = mockConstruction(DeviceID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(deviceID);
        })) {

            DewPointSensor dewPointSensor = new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //Act
            DeviceID result = dewPointSensor.getDeviceID();

            //Assert
            assertEquals(deviceID, result.toString());
        }
    }

    @Test
    public void shouldGetDewPointValue() {
        //Arrange
        String deviceID = "123A";
        String modelPath = "SmartHome.sensors.DewPointSensor";
        String sensorName = "DewPoint";
        String sensorTypeID = "123B";

        String expectedValue = "25";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try (MockedConstruction<DewPointValue> mockedConstruction = mockConstruction(DewPointValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(expectedValue);
        })) {

            DewPointSensor dewPointSensor = new DewPointSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //Act
            ValueObject result = dewPointSensor.getValue();

            //Assert
            assertEquals(expectedValue, result.toString());
        }
    }




}