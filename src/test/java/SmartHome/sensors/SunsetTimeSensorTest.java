package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
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

}