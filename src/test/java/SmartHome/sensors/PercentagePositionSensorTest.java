package SmartHome.sensors;
import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * This class contains unit tests for the PercentagePositionSensor class.
 */
class PercentagePositionSensorTest {

    /**
     * Tests the creation of a valid PercentagePositionSensor.
     * @throws InstantiationException if an instantiation error occurs.
     */
    @Test
    void newValidPercentedPosicionSensor() throws InstantiationException {
        // Arrange
        String description = "Percented";

        // Mocks
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(catalogueDouble);

        // Assert
        assertNotNull(percentagePositionSensor);
    }

    /**
     * Tests the creation of an invalid PercentagePositionSensor.
     * @throws InstantiationException if an instantiation error occurs.
     */
    @Test
    void newInvalidPercentedPosicionSensor() throws InstantiationException {
        // Arrange
        String description = "Percented";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new PercentagePositionSensor(catalogueDouble));

        // Assert
        assertEquals("SensorType with description 'Percented' does not exist.", exception.getMessage());
    }

    /**
     * Tests the retrieval of the correct sensor type.
     * @throws InstantiationException if an instantiation error occurs.
     */
    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Percented";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(catalogueDouble);

        // Act
        SensorType result = percentagePositionSensor.getSensorType();

        // Assert
        assertEquals(sensorTypeDouble, result);
    }

    /**
     * Tests the retrieval of the correct sensor value.
     * @throws InstantiationException if an instantiation error occurs.
     */
    @Test
    void getValueReturnsCorrectValue() throws InstantiationException {
        // Arrange
        String description = "Percented";
        String value = "50";
        double expected = 50;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<PercentagePositionSensorValue> mocked = mockConstruction(PercentagePositionSensorValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(value);
        })) {
            PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(catalogueDouble);

            // Act
            double percentageValue = Double.parseDouble(percentagePositionSensor.getValue().toString());

            // Assert
            assertEquals(expected, percentageValue);
        }
    }
}
