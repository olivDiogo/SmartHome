package smart_home.domain.sensor.dew_point_sensor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DewPointSensorTest {

    /**
     * Test to check if the DewPointSensor is instantiated correctly.
     */
    @Test
    void shouldInstantiateDewPointSensor_WhenParametersAreValid() {

        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Assert
            assertNotNull(dewPointSensor);
        }

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the DeviceID is null.
     */
    @Test
    void shouldThrowException_WhenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        String expectedMessage = "DeviceID is required";

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
            );

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);
        }
    }

    /**
     * Test to check if the DewPointSensor throws an exception when the ModelPath is null.
     */
    @Test
    void shouldThrowException_WhenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        String expectedMessage = "ModelPath is required";

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
            );

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);
        }

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the SensorName is null.
     */
    @Test
    void shouldThrowException_WhenSensorNameIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        String expectedMessage = "SensorName is required";

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
            );

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);
        }
    }

    /**
     * Test to check if the DewPointSensor throws an exception when the SensorTypeID is null.
     */
    @Test
    void shouldThrowException_WhenSensorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = null;

        String expectedMessage = "SensorTypeID is required";

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
            );

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);
        }
    }

    @Test
    void shouldThrowException_WhenSensorTypeIDIsInvalid() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("WrongSensorTypeID");

        String expectedMessage = "SensorTypeID must be 'DewPoint'";

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
            );

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);

        }
    }

    /**
     * Should return Sensor ID.
     */
    @Test
    void shouldGetDewPointID() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            SensorID result = dewPointSensor.getID();

            //Assert
            assertNotNull(result);
        }
    }

    /**
     * Should get sensor name.
     */
    @Test
    void shouldGetDewPointName() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            SensorName result = dewPointSensor.getName();

            //Assert
            assertEquals(sensorName, result);

        }
    }

    /**
     * Should get model Path.
     */
    @Test
    void shouldGetDewPointModelPath() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            ModelPath result = dewPointSensor.getModelPath();

            //Assert
            assertEquals(modelPath, result);

        }
    }

    /**
     * Should get sensorType ID.
     */
    @Test
    void shouldGetDewPointSensorTypeID() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            SensorTypeID result = dewPointSensor.getSensorTypeID();

            //Assert
            assertEquals(sensorTypeID, result);
        }

    }

    /**
     * Should return device ID.
     */
    @Test
    void shouldGetDeviceID() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            DeviceID result = dewPointSensor.getDeviceID();

            //Assert
            assertEquals(deviceID, result);
        }
    }

    /**
     * Should return dew point value.
     */
    @Test
    void shouldGetDewPointValue() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);


            //Act + Assert
            int value = Integer.parseInt(dewPointSensor.getValue().toString());
            assertTrue(value >= -70 && value <= 70);
        }
    }

    /**
     * Test to check if the DewPointSensor is equal to another DewPointSensor.
     */
    @Test
    void shouldReturnFalse_whenTwoDewPointSensorsHaveDifferentIDs() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor1 = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);
            DewPointSensor dewPointSensor2 = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            boolean result = dewPointSensor1.equals(dewPointSensor2);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Test to check if the DewPointSensor is equal to another object.
     */
    @Test
    void shouldReturnTrue_whenTwoDewPointSensorsHaveSameID() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor1 = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);
            //DewPointSensor dewPointSensor2 = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            boolean result = dewPointSensor1.equals(dewPointSensor1);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * Test to check if the DewPointSensor is equal to another object.
     */
    @Test
    void shouldReturnFalse_whenObjectIsNotDewPointSensor() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor1 = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            //Act
            boolean result = dewPointSensor1.equals(new Object());

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Test to check if hashCode is returned.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");

        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

            int expected = dewPointSensor.getID().hashCode();

            //Act
            int result = dewPointSensor.hashCode();

            //Assert
            assertEquals(expected, result);
        }
    }

    /**
     * Test to check if the DewPointSensor returns a string value.
     */
    @Test
    void shouldReturnStringValue_whenToStringIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName = mock(SensorName.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("DewPoint");


        try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("MockedSensorID");
        })) {
            DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);
            String expectedValue = dewPointSensor.getValue().toString();

            DewPointValue dewPointValue = mock(DewPointValue.class);
            when(dewPointValue.toString()).thenReturn(expectedValue);


            String expected = "DewPointSensor:" +
                    " modelPath=" + modelPath +
                    ", sensorName=" + sensorName +
                    ", sensorID=MockedSensorID" +
                    ", sensorTypeID=" + sensorTypeID +
                    ", dewPointValue=" + expectedValue +
                    ", deviceID=" + deviceID;

            //Act
            String result = dewPointSensor.toString();

            //Assert
            assertEquals(expected, result);
        }
    }



}



