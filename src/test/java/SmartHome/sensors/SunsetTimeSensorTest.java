package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Gps;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}