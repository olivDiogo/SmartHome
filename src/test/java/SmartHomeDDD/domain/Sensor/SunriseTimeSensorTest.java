package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SunriseTimeSensorTest {
    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateSunriseTimeSensor() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {

            //Act
            new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
        }
    }

    /**
     * See if the constructor throws an exception when the {@link SensorTypeID} parameter is null.
     */
    @Test
    void shouldThrowExceptionWhenSensorTypeIsNull() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = null;
        GPS gpsDouble = mock(GPS.class);
        String expectedMessage = "SensorTypeID cannot be null.";

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));

            //Assert
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the constructor throws an exception when the GPS parameter is null.
     */
    @Test
    void shouldThrowExceptionWhenGpsAttributeIsNull() {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = null;
        String expectedMessage = "GPS cannot be null.";

        try (MockedConstruction<Configurations> mocked = mockConstruction(Configurations.class)) {
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));
            //Assert
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the constructor throws an exception when the DeviceID parameter is null.
     */

    @Test
    void shouldThrowExceptionWhenDeviceIDAttributeIsNull(){
        //Arrange
        DeviceID deviceIDDouble = null;
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);
        String expectedMessage = "DeviceID cannot be null.";

        try (MockedConstruction<Configurations> mocked = mockConstruction(Configurations.class)) {
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));
            //Assert
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the constructor throws an exception when the Model path parameter is null.
     */

    @Test
    void shouldThrowExceptionWhenModelPathAttributeIsNull(){
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = null;
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);
        String expectedMessage = "ModelPath cannot be null.";

        try (MockedConstruction<Configurations> mocked = mockConstruction(Configurations.class)) {
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));
            //Assert
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the constructor throws an exception when the SensorName parameter is null.
     */
    @Test
    void shouldThrowExceptionWhenSensorNameAttributeIsNull(){
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = null;
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);
        String expectedMessage = "SensorName cannot be null";

        try (MockedConstruction<Configurations> mocked = mockConstruction(Configurations.class)) {
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble));
            //Assert
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the getSensorName method works.
     */
    @Test
    void shouldReturnSensorName() throws InstantiationException {
        //Arrange
        String sensorName = "SunriseTime";
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);

        when(sensorNameDouble.toString()).thenReturn(sensorName);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
            SensorName result = sunriseTimeSensor.getName();

            //Assert
            assertEquals(result, sensorNameDouble);
        }
    }


    /**
     * See if the getSensorTypeID method works.
     */
    @Test
    void shouldReturnSensorTypeID() throws InstantiationException {
        //Arrange
        String sensorTypeID = "SunriseTimeID";
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);

        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {
            //Act
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
            SensorTypeID result = sunriseTimeSensor.getSensorTypeID();

            //Assert
            assertEquals(result, sensorTypeIDDouble);
        }
    }

    /**
     * See if the getDeviceID method works.
     */
    @Test
    void shouldReturnDeviceID() throws InstantiationException {
        //Arrange
        String deviceID = "DeviceID";
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
            DeviceID result = sunriseTimeSensor.getDeviceID();

            //Assert
            assertEquals(result, deviceIDDouble);
        }
    }

    /**
     * See if the getModelPath method works.
     */
    @Test
    void shouldReturnModelPath() throws InstantiationException {
        //Arrange
        String modelPath = "ModelPath";
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);

        when(modelPathDouble.toString()).thenReturn(modelPath);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
            ModelPath result = sunriseTimeSensor.getModelPath();

            //Assert
            assertEquals(result, modelPathDouble);
        }
    }

    /**
     * See if the getID method works.
     */

    @Test
    void shouldReturnSensorID() throws InstantiationException {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);

        try (MockedConstruction<SensorID> sensorIDMoked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble, gpsDouble);
            SensorID result = sunriseTimeSensor.getID();

            List<SensorID> sensorIDs = sensorIDMoked.constructed();
            //Assert
            assertEquals(result, sensorIDs.get(0));
        }
    }


    /**
     * See if the getValue method works for the current day.
     */
    @Test
    void shouldReturnSunriseTimeValueForCurrentDay() throws InstantiationException {
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);
        int expectedSize =1;

        when(gpsDouble.getLatitude()).thenReturn(41.1579); // Coordinates to oporto
        when(gpsDouble.getLongitude()).thenReturn(8.6291);

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble,modelPathDouble,sensorTypeIDDouble,sensorNameDouble,gpsDouble);

        try (MockedConstruction<SunriseTimeSensorValue> mocked = mockConstruction(SunriseTimeSensorValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            ValueObject result = sunriseTimeSensor.getValue();
            //Assert
            List<SunriseTimeSensorValue> sunriseTimeSensorValues = mocked.constructed();
            assertEquals(result.toString(), sunriseTimeSensorValues.get(0).toString());
            assertEquals(expectedSize, sunriseTimeSensorValues.size());
            System.out.println(result);
        }

    }

    /**
     * See if the getValue method works for a specific day.
     */
    @Test
    void shouldReturnSunriseTimeValueForSpecificDay() throws InstantiationException {
        //Arrange
        //Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
        GPS gpsDouble = mock(GPS.class);
        int expectedSize =1;
        LocalDate date = LocalDate.of(2021, 10, 10);

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(deviceIDDouble,modelPathDouble,sensorTypeIDDouble,sensorNameDouble,gpsDouble);

        try (MockedConstruction<SunriseTimeSensorValue> mocked = mockConstruction(SunriseTimeSensorValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            ValueObject result = sunriseTimeSensor.getValue(date);
            //Assert
            List<SunriseTimeSensorValue> sunriseTimeSensorValues = mocked.constructed();
            assertEquals(result.toString(), sunriseTimeSensorValues.get(0).toString());
            assertEquals(expectedSize, sunriseTimeSensorValues.size());
        }
    }
}
