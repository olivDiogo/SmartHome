package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstantPowerConsumptionSensorTest {

    /**
     * Test to verify that the InstantPowerConsumptionSensor is instantiated when all parameters are valid.
     */
    @Test
    public void shouldInstantiateInstantPowerConsumptionSensor_WhenAllParametersAreValid() {
        // Arrange
        String deviceID = "deviceID";
        String modelPath = "modelPath";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        //act
        new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path is null.
     */
    @Test
    public void shouldThrowException_WhenDeviceIDIsNull () {
        // Arrange
        String modelPath = "modelPath";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = null;
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        String expectedMessage = "DeviceID is required";

       //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path is null.
     */
    @Test
    public void shouldThrowException_WhenModelPathIsNull () {
        // Arrange
        String deviceID = "deviceID";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = null;
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        String expectedMessage = "ModelPath is required";

       //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor name is null.
     */
    @Test
    public void shouldThrowException_WhenSensorNameIsNull () {
        // Arrange
        String deviceID = "deviceID";
        String modelPath = "modelPath";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = null;
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        String expectedMessage = "SensorName is required";

       //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor type ID is null.
     */
    @Test
    public void shouldThrowException_WhenSensorTypeIDIsNull () {
        // Arrange
        String deviceID = "deviceID";
        String modelPath = "modelPath";
        String sensorName = "sensorName";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = null;

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);

        String expectedMessage = "SensorTypeID is required";

       //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);
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
      String sensorID = "sensorID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorID);
        })) {
            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);
            //act
            SensorID result = instantPowerConsumptionSensor.getID();

            //assert
            assertEquals(sensorID, result.toString());
        }
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor returns the sensor name.
     */
    @Test
    public void shouldReturnSensorName_WhenGetSensorNameIsCalled() {
        // Arrange
        String deviceID = "deviceID";
        String modelPath = "modelPath";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try (MockedConstruction<SensorName> mocked = mockConstruction(SensorName.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorName);
        })) {

            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //act
            SensorName result = instantPowerConsumptionSensor.getName();

            //assert
            assertEquals(sensorName, result.toString());
        }

    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor returns the model path.
     */
    @Test
    public void shouldGetInstantPowerConsumptionModelPath () {
        //Arrange
        String deviceID = "deviceID";
        String modelPath =  "SmartHome.sensors.InstantPowerConsumptionSensor";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try(MockedConstruction<ModelPath> mocked = mockConstruction(ModelPath.class, (mock, context) -> {
            when(mock.toString()).thenReturn(modelPath);
        })) {
            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //act
            ModelPath result = instantPowerConsumptionSensor.getModelPath();

            //assert
            assertEquals(modelPath, result.toString());
        }
    }

    /**
     * Test to verify that the InstantPowerConsumptionSensor returns the sensor type ID.
     */
    @Test
    public void shouldGetInstantPowerConsumptionSensorTypeID () {
        //Arrange
        String deviceID = "deviceID";
        String modelPath =  "SmartHome.sensors.InstantPowerConsumptionSensor";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try(MockedConstruction<SensorTypeID> mocked = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //act
            SensorTypeID result = instantPowerConsumptionSensor.getSensorTypeID();

            //assert
            assertEquals(sensorTypeID, result.toString());
        }
    }

    @Test
    public void shouldGetDeviceID () {
        //Arrange
        String deviceID = "deviceID";
        String modelPath =  "SmartHome.sensors.InstantPowerConsumptionSensor";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try(MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(deviceID);
        })) {
            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //act
            DeviceID result = instantPowerConsumptionSensor.getDeviceID();

            //assert
            assertEquals(deviceID, result.toString());
        }
    }

    @Test
    public void shouldGetInstantPowerConsumptionValue (){
        //Arrange
        String deviceID = "deviceID";
        String modelPath =  "SmartHome.sensors.InstantPowerConsumptionSensor";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";

        String expectedValue = "100";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);
        when(modelPathDouble.toString()).thenReturn(modelPath);
        when(sensorNameDouble.toString()).thenReturn(sensorName);
        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try(MockedConstruction<InstantPowerConsumptionValue> mocked = mockConstruction(InstantPowerConsumptionValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(expectedValue);
        })) {
            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(deviceIDDouble, modelPathDouble,  sensorTypeIDDouble, sensorNameDouble);

            //act
            InstantPowerConsumptionValue result = (InstantPowerConsumptionValue) instantPowerConsumptionSensor.getValue();

            //assert
            assertEquals(expectedValue, result.toString());
        }
    }



}