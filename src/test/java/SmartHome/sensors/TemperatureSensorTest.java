package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TemperatureSensorTest {
    @Test
    void newValidTemperatureSensor() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new TemperatureSensor( catalogueDouble);
    }

    @Test
    void newInvalidTemperatureSensor() {
        // Arrange
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType("Temperature")).thenReturn(null);

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new TemperatureSensor( catalogueDouble));

        // Assert
        assertEquals( "SensorType with description 'Temperature' does not exist.", exception.getMessage() );
    }

    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        TemperatureSensor temperatureSensor = new TemperatureSensor( catalogueDouble);

        // Act
        SensorType result = temperatureSensor.getSensorType();

        // Assert
        assertEquals( sensorTypeDouble, result );
    }

    @Test
    void getValueReturnsCorrectValue() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

        // Act
        Value value = temperatureSensor.getValue();
        int tempValue = Integer.parseInt(value.toString());

        // Assert
        assertTrue(tempValue >= -70 && tempValue <= 70, "The temperature value should be between -70 and 70.");
    }
}