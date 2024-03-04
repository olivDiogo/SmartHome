package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Gps;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SunsetTimeSensorTest {
    @Test
    void shouldInstantiateSunsetTimeSensorIfSupported() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);

        //Act
        new SunsetTimeSensor(catalogueSensorDouble);
    }
    @Test
    void shouldThrowExceptionWhenSensorTypeIsNotSupported() {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(null);
        String expectedMessage = "SensorType with description 'SunsetTime' does not exist.";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SunsetTimeSensor(catalogueSensorDouble));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldConfigureGpsLocation() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);
        Gps gpsDouble = mock(Gps.class);

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);
        //Act
        Gps gps = sunsetTimeSensor.configureGpsLocation(gpsDouble);
        //Assert
        assertTrue(gps.equals(gpsDouble));
    }

    @Test
    void shouldThrowExceptionWhenGpsLocationIsNull() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);
        Gps gps = null;

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);
        String expectedMessage = "GPS location is required";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sunsetTimeSensor.configureGpsLocation(gps));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnSensorType() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);
        //Act
        SensorType sensorType = sunsetTimeSensor.getSensorType();
        //Assert
        assertTrue(sensorType.equals(sensorTypeDouble));
    }
    @Test
    void shouldSetSunTimesProvider() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);
        ISunTimesProviders sunTimesProviderDouble = mock(ISunTimesProviders.class);

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);
        //Act
        ISunTimesProviders sunTimesProvider = sunsetTimeSensor.configureSunTimeProvider(sunTimesProviderDouble);
        //Assert
        assertEquals(sunTimesProvider, sunTimesProviderDouble);
    }
    @Test
    void shouldThrowExceptionWhenSunTimesProviderIsNull() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(mock(SensorType.class));
        ISunTimesProviders sunTimesProvider = null;

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);
        String expectedMessage = "SunTimesProvider is required";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sunsetTimeSensor.configureSunTimeProvider(sunTimesProvider));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnSunsetTimeValueForCurrentDay() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);
        Gps gpsDouble = mock(Gps.class);
        when(gpsDouble.getLatitude()).thenReturn(41.1579); // Coordinates to oporto
        when(gpsDouble.getLongitude()).thenReturn(8.6291);

        ISunTimesProviders sunTimesProvidersDouble = mock(ISunTimesProviders.class);
        when(sunTimesProvidersDouble.getSunsetTime(41.1579, 8.6291)).thenReturn(LocalTime.of(20, 0));

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);

        sunsetTimeSensor.configureGpsLocation(gpsDouble);
        sunsetTimeSensor.configureSunTimeProvider(sunTimesProvidersDouble);

        try (MockedConstruction<SunsetTimeValue> mocked = mockConstruction(SunsetTimeValue.class)) {
            //Act
            Value result = sunsetTimeSensor.getValue();
            //Assert
            assertTrue(mocked.constructed().contains(result));
        }

    }

}