package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Gps;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.shredzone.commons.suncalc.SunTimes;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SunriseSensorTest {

    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateSunriseTimeSensor() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunriseTime")).thenReturn(sensorTypeDouble);

        //Act
        new SunriseTimeSensor(catalogueSensorDouble);
    }

    /**
     * See if the constructor throws an exception when the SensorType does not exist.
     */
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

    /**
     * See if the configureGpsLocation method throws an exception when the gps.configuration file is not found.
     */
    @Test
    void shouldThrowExceptionWhenGpsConfigurationFileIsNotFound() {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);
        String expectedMessage = "gps.configuration file not found";

        try (MockedConstruction<Configurations> mocked = mockConstruction(Configurations.class, (mock, context) -> {
            when(mock.properties(any(File.class))).thenReturn(null);
        })) {
            //Act
            Exception exception = assertThrows(InstantiationException.class, () -> new SunsetTimeSensor(catalogueSensorDouble));
            //Assert
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the getSensorType method works.
     */
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

    /**
     * See if the getValue method works for the current day.
     */
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

        try (MockedConstruction<SunriseTimeValue> mocked = mockConstruction(SunriseTimeValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(value);
            when(sunriseTimeValueDouble.toString()).thenReturn(value);
        })) {

            //Act
            Value result = sunriseTimeSensor.getValue();
            //Assert
            List<SunriseTimeValue> sunriseTimeSensorValues = mocked.constructed();
            assertEquals(result.toString(), sunriseTimeSensorValues.get(0).toString());
            assertEquals(expectedSize, sunriseTimeSensorValues.size());
        }

    }

    /**
     * See if the getValue method works for a specific day.
     */
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

        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);

        try (MockedConstruction<SunriseTimeValue> mocked = mockConstruction(SunriseTimeValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(value);
            when(sunriseTimeValueDouble.toString()).thenReturn(value);
        })) {

            //Act
            Value result = sunriseTimeSensor.getValue(date);
            //Assert
            List<SunriseTimeValue> sunriseTimeSensorValues = mocked.constructed();
            assertEquals(result.toString(), sunriseTimeSensorValues.get(0).toString());
            assertEquals(expectedSize, sunriseTimeSensorValues.size());
        }

    }

    /**
     * See if the getValue method works for the current day.
     */

    @Test
    void seeIfSunriseTimeMethodWorks() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        LocalTime expected = SunTimes.compute().on(LocalDate.now()).at(41.1579, 8.6291).execute().getRise().toLocalTime();
        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);

        //Act
        LocalTime result = sunriseTimeSensor.getSunriseTime();
        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * See if the getValue method works for a specific day.
     */

    @Test
    void seeIfSunriseTimeMethodWorksForSpecificDate() throws InstantiationException {
        //Arrange
        String description = "SunriseTime";
        LocalDate date = LocalDate.of(2024, 10, 10);
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);


        SunTimes time = SunTimes.compute().on(date).at(41.1579, 8.6291).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(catalogueSensorDouble);

        //Act
        LocalTime result = sunriseTimeSensor.getSunriseTime(date);

        //Assert
        assertNotNull(result);
        assertEquals(sunrise, result);
    }
}
