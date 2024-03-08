package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

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
    void shouldReturnNewTemperatureSensor_WhenSensorTypeIsValid() throws InstantiationException {
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
    void shouldThrowInstantiationException_WhenSensorTypeIsInvalid() {
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
    void shouldReturnCorrectSensorType_WhenGetSensorTypeIsCalled() throws InstantiationException {
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
    void shouldReturnValue_WhenGetValueIsCalled() throws InstantiationException {
        // Arrange
        String description = "Temperature";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            Value value = temperatureSensor.getValue();

            // Assert
            List<TemperatureSensorValue> constructed = temperatureSensorValue.constructed();
            assertEquals(constructed.get(0).toString(), value.toString());
            assertEquals("70.5", value.toString());
        }
    }
}