package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link TemperatureSensor} class.
 */
class TemperatureSensorTest {

    /**
     * Tests that a TemperatureSensor can be successfully instantiated with a valid sensor type.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void newValidTemperatureSensor() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new TemperatureSensor(catalogueDouble);
    }

    /**
     * Tests that an InstantiationException is thrown when attempting to create a TemperatureSensor
     * with an invalid sensor type.
     */
    @Test
    void newInvalidTemperatureSensor() {
        // Arrange
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new TemperatureSensor(catalogueDouble));
        assertEquals("SensorType with description 'Temperature' does not exist.", exception.getMessage());
    }

    /**
     * Verifies that the getSensorType method returns the correct SensorType.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

        // Act
        SensorType result = temperatureSensor.getSensorType();

        // Assert
        assertEquals(sensorTypeDouble, result);
    }

    /**
     * Tests that the getValue method returns a valid value.
     *
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getValueReturnsValidValue() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        String temperature = "10";
        double expected = 10;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(temperature);
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            double tempValue = Double.parseDouble(temperatureSensor.getValue().toString());

            // Assert
            assertEquals(expected, tempValue);
        }
    }

    /**
     * Tests if the {@link TemperatureSensorValue} constructor throws an {@link IllegalArgumentException} when the temperature value is below the lower boundary.
     */
    @Test
    void seeIfConstructorThrowsException() {
        // Arrange
        double nValue = -273.16;
        String expected = "Temperature value must be above or equal to -273.15";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensorValue(nValue));

        // Assert
        assertEquals(expected, exception.getMessage());
    }
}