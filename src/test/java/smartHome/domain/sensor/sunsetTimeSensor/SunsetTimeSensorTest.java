package smartHome.domain.sensor.sunsetTimeSensor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.shredzone.commons.suncalc.SunTimes;
import smartHome.valueObject.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SunsetTimeSensorTest {

    /**
     * Test to check if SunsetTimeSensor is instantiated when all attributes are valid
     */
    @Test
    void shouldInstantiateSunsetTimeSensor_whenAttributesAreValid() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Assert
            assertNotNull(sunsetTimeSensor);
        }
    }

    /**
     * Test to check if IllegalArgumentException is thrown when DeviceID is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        String expectedMessage = "DeviceID cannot be null.";

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

            //Assert
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    /**
     * Test to check if IllegalArgumentException is thrown when SensorTypeID is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = null;

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        String expectedMessage = "SensorTypeID cannot be null.";

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

            //Assert
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    /**
     * Test to check if IllegalArgumentException is thrown when SensorTypeID is not SunsetTime
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSunsetTime() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("WrongSensorTypeID");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        String expectedMessage = "SensorTypeID must be 'SunsetTime'.";

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

            //Assert
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    /**
     * Test to check if IllegalArgumentException is thrown when ModelPath is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = null;

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        String expectedMessage = "ModelPath cannot be null.";

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

            //Assert
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    /**
     * Test to check if IllegalArgumentException is thrown when SensorName is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = null;
        GPS gps = mock(GPS.class);

        String expectedMessage = "SensorName cannot be null";

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

            //Assert
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    /**
     * Test to check if IllegalArgumentException is thrown when GPS is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGPSIsNull() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = null;

        String expectedMessage = "GPS cannot be null.";

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            //Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

            //Assert
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    /**
     * Test to check if SunsetTimeSensorValue is returned for the current day
     */
    @Test
    void shouldReturnSunsetTimeForCurrentDay_whenGetValueIsCalledWithNoArguments() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            LocalTime expectedSunsetTime = Objects.requireNonNull(SunTimes.compute().on(LocalDate.now()).at(gps.getLatitude(), gps.getLongitude()).execute().getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);

            SunsetTimeSensorValue expected = new SunsetTimeSensorValue(expectedSunsetTime);

            //Act
            SunsetTimeSensorValue sunsetTime = sunsetTimeSensor.getValue();

            //Assert
            assertEquals(expected.toString(), sunsetTime.toString());
        }
    }

    /**
     * Test to check if SunsetTimeSensorValue is returned for a given day
     */
    @Test
    void shouldReturnSunsetTimeForGivenDay_whenGetValueIsCalledWithADate()  {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            LocalDate date = LocalDate.now().plusDays(5);
            LocalTime expectedSunsetTime = Objects.requireNonNull(SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute().getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);

            SunsetTimeSensorValue expected = new SunsetTimeSensorValue(expectedSunsetTime);

            //Act
            SunsetTimeSensorValue sunsetTime = sunsetTimeSensor.getValue(date);

            //Assert
            assertEquals(expected.toString(), sunsetTime.toString());
        }
    }

    /**
     * Test to check if device ID is returned when getDeviceID is called
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            DeviceID actualDeviceID = sunsetTimeSensor.getDeviceID();

            //Assert
            assertEquals(deviceID, actualDeviceID);
        }
    }

    /**
     * Test to check if SensorTypeID is returned when getSensorTypeID is called
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            SensorTypeID actualSensorTypeID = sunsetTimeSensor.getSensorTypeID();

            //Assert
            assertEquals(sensorTypeID, actualSensorTypeID);
        }
    }

    /**
     * Test to check if SensorName is returned when getSensorName is called
     */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            SensorName actualSensorName = sunsetTimeSensor.getName();

            //Assert
            assertEquals(sensorName, actualSensorName);
        }
    }

    /**
     * Test to check if ModelPath is returned when getModelPath is called
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {
            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            ModelPath actualModelPath = sunsetTimeSensor.getModelPath();

            //Assert
            assertEquals(modelPath, actualModelPath);
        }
    }

    /**
     * Test to check if SensorID is returned when getSensorID is called
     */
    @Test
    void shouldReturnSensorID_whenGetSensorIDIsCalled() throws InstantiationException {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            SensorID actualSensorID = sunsetTimeSensor.getID();

            //Assert
            assertTrue(sunsetTimeSensor.toString().contains(actualSensorID.toString()));
        }
    }

    /**
     * Test to check if two SunsetTimeSensor objects with different IDs are equal
     */
    @Test
    void shouldReturnFalse_whenTwoSunsetTimeSensorsHaveDifferentIDs() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName1 = mock(SensorName.class);
        SensorName sensorName2 = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor1 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName1, gps);
            SunsetTimeSensor sunsetTimeSensor2 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName2, gps);

            //Act
            boolean result = sunsetTimeSensor1.equals(sunsetTimeSensor2);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Test to check if two SunsetTimeSensor objects with same ID are equal
     */
    @Test
    void shouldReturnTrue_whenTwoSunsetTimeSensorsHaveSameIDs() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor1 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            boolean result = sunsetTimeSensor1.equals(sunsetTimeSensor1);

            //Assert
            assertTrue(result);
        }
    }

    /**
     * Test to check if an object of another class is not equal to SunsetTimeSensor
     */
    @Test
    void shouldReturnFalse_whenObjectIsNotSunsetTimeSensor() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            //Act
            boolean result = sunsetTimeSensor.equals(new Object());

            //Assert
            assertFalse(result);
        }
    }


    /**
     * Test to check if hashCode is returned
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        //Arrange
        DeviceID deviceID = mock(DeviceID.class);
        ModelPath modelPath = mock(ModelPath.class);

        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        when(sensorTypeID.getID()).thenReturn("SunsetTime");

        SensorName sensorName = mock(SensorName.class);
        GPS gps = mock(GPS.class);

        try(MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class)) {

            SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);

            int expected = sunsetTimeSensor.getID().hashCode();

            //Act
            int hashCode = sunsetTimeSensor.hashCode();

            //Assert
            assertEquals(expected, hashCode);
        }
    }

}