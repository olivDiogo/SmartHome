package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link HumiditySensor}.
 */
class HumiditySensorTest {

    /**
     * Tests the creation of a valid HumiditySensor instance.
     *
     * @throws InstantiationException if the sensor type does not exist.
     */
    @Test
    void newValidHumiditySensor() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new HumiditySensor(catalogueDouble);
    }

    /**
     * Tests the creation of a HumiditySensor instance with an invalid sensor type,
     * expecting an InstantiationException.
     */
    @Test
    void newInvalidHumiditySensor() {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new HumiditySensor(catalogueDouble));

        // Assert
        assertEquals("SensorType with description 'Humidity' does not exist.", exception.getMessage());
    }

    /**
     * Tests that {@link HumiditySensor#getSensorType()} returns the correct {@link SensorType}.
     *
     * @throws InstantiationException if the sensor type does not exist.
     */
    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        HumiditySensor humiditySensor = new HumiditySensor(catalogueDouble);

        // Act
        SensorType result = humiditySensor.getSensorType();

        // Assert
        assertEquals(sensorTypeDouble, result);
    }

    /**
     * Tests that {@link HumiditySensor#getValue()} returns the correct humidity value.
     *
     * @throws InstantiationException if the sensor type does not exist.
     */
    @Test
    void getValueReturnCorrectValue() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        String humidity = "50";
        double expected = 50;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<HumiditySensorValue> humiditySensorValue = mockConstruction(HumiditySensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(humidity);
        })) {
            HumiditySensor humiditySensor = new HumiditySensor(catalogueDouble);

            // Act
            double result = Double.parseDouble(humiditySensor.getValue().toString());

            // Assert
            assertEquals(expected, result);
        }
    }
}
