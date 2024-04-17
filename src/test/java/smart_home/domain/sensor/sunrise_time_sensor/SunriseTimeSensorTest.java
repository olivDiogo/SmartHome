package smart_home.domain.sensor.sunrise_time_sensor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.*;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SunriseTimeSensorTest {

    /**
     * Should throw IllegalArgumentException when modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = null;
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        String expectedMessage = "ModelPath cannot be null.";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should throw IllegalArgumentException when sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullSensorTypeID() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorTypeID sensorTypeIDDouble = null;
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);

        String expectedMessage = "SensorTypeID cannot be null.";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should throw IllegalArgumentException when DeviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullDeviceID() {
        //Arrange
        DeviceID deviceIDDouble = null;
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);

        String expectedMessage = "DeviceID cannot be null.";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should throw IllegalArgumentException when sensorName is null.
     */
     @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullSensorName() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = null;
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        String expectedMessage = "SensorName cannot be null";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should throw IllegalArgumentException when GPS is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullGPS() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = null;
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        String expectedMessage = "GPS cannot be null.";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test getID method.
     */
    @Test
    void shouldReturnSensorID_WhenGetID() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            SensorID result = sunriseTimeSensor.getID();

            //Assert
            assertTrue(sunriseTimeSensor.toString().contains(result.toString()));
        }
    }

    /**
     * Test getModelPath method.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPath() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            ModelPath result = sunriseTimeSensor.getModelPath();

            //Assert
            assertEquals(modelPathDouble, result);
        }
    }

    /**
     * Test getSensorTypeID method.
     */
    @Test
    void shouldReturnSensorTypeID_WhenGetSensorTypeIDIsCalled() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            SensorTypeID result = sunriseTimeSensor.getSensorTypeID();

            //Assert
            assertEquals(sensorTypeIDDouble, result);
        }
    }

    /**
     * Test getSensorName method.
     */
    @Test
    void shouldReturnSensorName_WhenGetSensorNameIsCalled() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            SensorName result = sunriseTimeSensor.getName();

            //Assert
            assertEquals(sensorNameDouble, result);
        }
    }

    /**
     * Test getDeviceID method.
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {

            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            DeviceID result = sunriseTimeSensor.getDeviceID();

            //Assert
            assertEquals(deviceIDDouble, result);
        }
    }

    /**
     * Test getValue method.
     */
    @Test
    void shouldReturnSunriseTimeSensorValue_WhenGetValueIsCalled() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            SunriseTimeSensorValue result = sunriseTimeSensor.getValue();

            //Assert
            assertNotNull(result);
        }

    }

    /**
     * Test getValue method whit LocalDate.
     */
    @Test
    void shouldReturnSunriseTimeSensorValue_WhenGetValueIsCalledWithLocalDate() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            SunriseTimeSensorValue result = sunriseTimeSensor.getValue(LocalDate.now());

            //Assert
            assertNotNull(result);
        }

    }

    /**
     * Test method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenInstanceIsComparedToItself() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            boolean result = sunriseTimeSensor.equals(sunriseTimeSensor);

            //Assert
            assertTrue(result);
        }

    }

    /**
     * Test of method equals when comparing different objects.
     */
    @Test
    void shouldReturnFalse_WhenInstancesAreNotEqual(){
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
            SunriseTimeSensor sunriseTimeSensor2 = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            boolean result = sunriseTimeSensor.equals(sunriseTimeSensor2);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Tests equal method with null.
     */
    @Test
    void shouldReturnFalse_WhenComparingObjectWithNull() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            //Act
            boolean result = sunriseTimeSensor.equals(null);

            //Assert
            assertFalse(result);
        }

    }

    /**
     * Test of method hashCode
     **/
    @Test
    void shouldReturnHashCode_WhenHashCodeIsCalled() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            int expected = sunriseTimeSensor.hashCode();

            //Act
            int result = sunriseTimeSensor.hashCode();

            //Assert
            assertEquals(result, expected);
        }
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnString_WhenToStringIsCalled() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        GPS gpsDouble = mock(GPS.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.getID()).thenReturn("SunriseTime");

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);

            String expected = "SunriseTimeSensor:" +
                    ", sensorID=" + sunriseTimeSensor.getID() +
                    ", sensorName=" + sensorNameDouble +
                    ", modelPath=" + modelPathDouble +
                    ", sensorTypeID=" + sensorTypeIDDouble +
                    ", deviceID=" + deviceIDDouble +
                    ", gps=" + gpsDouble;

            //Act
            String result = sunriseTimeSensor.toString();

            //Assert
            assertEquals(expected, result);
        }

    }

    @Test
    void shouldInstantiateSunriseTimeSensor_WhenSensorIDIsValid () {
        // Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorName sensorName= mock(SensorName.class);
        GPS gps = mock(GPS.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        SensorID sensorID = mock(SensorID.class);

        when(sensorTypeID.getID()).thenReturn("SunriseTime");

        // Act
        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID);

        // Assert
        assertNotNull(sunriseTimeSensor);
    }



}
