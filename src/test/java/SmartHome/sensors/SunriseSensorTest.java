package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Gps;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SunriseSensorTest {
    @Test
    void shouldInstantiateSunriseTimeSensor() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunriseTime")).thenReturn(sensorTypeDouble);

        //Act
        new SunriseTimeSensor(catalogueSensorDouble);
    }

    @Test
    void shouldThrowExceptionWhenSensorTypeIsNotSupported() {
        //Arrange
        String description = "SunriseTime";
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(null);
        String expectedMessage = "SensorType with description 'SunriseTime' does not exist.";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SunriseTimeSensor(catalogueSensorDouble));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldConfigureGpsLocation() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        Gps gpsDouble = mock(Gps.class);

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);

        //Act
        Gps gps = sunriseTimeSensor.configureGpsLocation(gpsDouble);

        //Assert
        assertEquals(gps, gpsDouble);
    }

    @Test
    void shouldThrowExceptionWhenGpsLocationIsNull() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        Gps gps = null;

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);
        String expectedMessage = "GPS location is required";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sunriseTimeSensor.configureGpsLocation(gps));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnSensorType() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);

        //Act
        SensorType sensorType = sunriseTimeSensor.getSensorType();

        //Assert
        assertEquals(sensorType, sensorTypeDouble);
    }

    @Test
    void shouldReturnSunriseTimeValueForCurrentDay() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        int expectedSize = 1;
        String value = "Sunrise Time: 06:00:00";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SunriseTimeValue sunriseTimeValueDouble = mock(SunriseTimeValue.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        Gps gpsDouble = mock(Gps.class);
        when(gpsDouble.getLatitude()).thenReturn(41.1579); // Coordinates to oporto
        when(gpsDouble.getLongitude()).thenReturn(8.6291);

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);
        sunriseTimeSensor.configureGpsLocation(gpsDouble);

        try (MockedConstruction<SunriseTimeValue> mocked = mockConstruction(SunriseTimeValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(sunriseTimeValueDouble);
            when(mock.toString()).thenReturn(value);
            when(sunriseTimeValueDouble.toString()).thenReturn(value);
        })) {

            //Act
            Value result = sunriseTimeSensor.getValue();
            System.out.println(result.toString());
            //Assert
            List<SunriseTimeValue> sunriseTimeSensorValues = mocked.constructed();
            // assertTrue(sunriseTimeSensorValues.contains(result));
            assertEquals(result.toString(), sunriseTimeSensorValues.get(0).toString());
            assertEquals(expectedSize, sunriseTimeSensorValues.size());
        }

    }

    @Test
    void shouldReturnSunriseTimeValueForSpecificDay() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        int expectedSize = 1;
        String value = "Sunrise Time: 06:00:00";
        LocalDate date = LocalDate.of(2021, 10, 10);

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SunriseTimeValue sunriseTimeValueDouble = mock(SunriseTimeValue.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        Gps gpsDouble = mock(Gps.class);
        when(gpsDouble.getLatitude()).thenReturn(41.1579); // Coordinates to oporto
        when(gpsDouble.getLongitude()).thenReturn(8.6291);

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);
        sunriseTimeSensor.configureGpsLocation(gpsDouble);

        try (MockedConstruction<SunriseTimeValue> mocked = mockConstruction(SunriseTimeValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(sunriseTimeValueDouble);
            when(mock.toString()).thenReturn(value);
            when(sunriseTimeValueDouble.toString()).thenReturn(value);
        })) {

            //Act
            Value result = sunriseTimeSensor.getValue(date);
            System.out.println(result.toString());
            //Assert
            List<SunriseTimeValue> sunriseTimeSensorValues = mocked.constructed();
            // assertTrue(sunriseTimeSensorValues.contains(result));
            assertEquals(result.toString(), sunriseTimeSensorValues.get(0).toString());
            assertEquals(expectedSize, sunriseTimeSensorValues.size());
        }

    }
}
