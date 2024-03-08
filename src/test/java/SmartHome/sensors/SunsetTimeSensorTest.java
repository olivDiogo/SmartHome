package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Gps;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.shredzone.commons.suncalc.SunTimes;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SunsetTimeSensorTest {
    @Test
    void shouldInstantiateSunsetTimeSensor_WhenValidSensorTypeProvided() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);

        //Act
        new SunsetTimeSensor(catalogueSensorDouble);
    }
    @Test
    void shouldThrowException_WhenInvalidSensorTypeProvided() {
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
    void shouldReturnCorrectSensorType() throws InstantiationException {
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
void shouldThrowException_WhenGpsConfigurationFileIsMissing() {
    //Arrange
    CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
    SensorType sensorTypeDouble = mock(SensorType.class);
    when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);
    String expectedMessage = "gps.configuration file not found";

    try(MockedConstruction<Configurations> mocked = mockConstruction(Configurations.class, (mock, context) -> {
        when(mock.properties(any(File.class))).thenReturn(null);
    })) {
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SunsetTimeSensor(catalogueSensorDouble));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
    @Test
    void shouldReturnSunsetTimeValueForToday() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);

        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);


        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);

        try (MockedConstruction<SunsetTimeValue> mocked = mockConstruction(SunsetTimeValue.class)){
            //Act
            Value result = sunsetTimeSensor.getValue();
            //Assert
            assertTrue(mocked.constructed().contains(result));
        }
    }
    @Test
    void shouldReturnSunsetTimeValueForSpecificDate() throws InstantiationException {
        //Arrange
        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType("SunsetTime")).thenReturn(sensorTypeDouble);

        SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(catalogueSensorDouble);

        LocalDate date = LocalDate.now().plusDays(5);

        try (MockedConstruction<SunsetTimeValue> mocked = mockConstruction(SunsetTimeValue.class)){
            //Act
            Value result = sunsetTimeSensor.getValue(date);
            //Assert
            assertTrue(mocked.constructed().contains(result));
        }
    }
}