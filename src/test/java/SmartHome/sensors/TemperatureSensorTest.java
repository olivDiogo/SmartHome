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
     * Tests that the getValue method returns a positive temperature value within the expected range.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getValueReturnsPositiveValidValue() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        String temperature = "70";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(temperature);
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            int tempValue = Integer.parseInt(temperatureSensor.getValue().toString());

            // Assert
            assertTrue(tempValue >= -70 && tempValue <= 70, "The temperature value should be between -70 and 70.");
        }
    }

    /**
     * Tests that the getValue method returns zero as a valid temperature.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getValueReturnsZero() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        String temperature = "0";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(temperature);
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            int tempValue = Integer.parseInt(temperatureSensor.getValue().toString());

            // Assert
            assertTrue(tempValue >= -70 && tempValue <= 70, "The temperature value should be between -70 and 70.");
        }
    }

    /**
     * Tests that the getValue method returns a valid negative temperature value within the expected range.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getValueReturnsValidNegativeValue() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        String temperature = "-70";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(temperature);
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            int tempValue = Integer.parseInt(temperatureSensor.getValue().toString());

            // Assert
            assertTrue(tempValue >= -70 && tempValue <= 70, "The temperature value should be between -70 and 70.");
        }
    }

    /**
     * Tests that the getValue method returns an invalid value when the temperature is above the valid range.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getValueReturnsInvalidValueAboveRange() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        String temperature = "71";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(temperature);
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            int tempValue = Integer.parseInt(temperatureSensor.getValue().toString());

            // Assert
            assertFalse(tempValue >= -70 && tempValue <= 70, "The temperature value should be between -70 and 70.");
        }
    }

    /**
     * Tests that the getValue method returns an invalid value when the temperature is below the valid range.
     * @throws InstantiationException If the sensor type "Temperature" does not exist.
     */
    @Test
    void getValueReturnsInvalidValueUnderRange() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        String temperature = "-71";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<TemperatureSensorValue> temperatureSensorValue = mockConstruction(TemperatureSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(temperature);
        })) {
            TemperatureSensor temperatureSensor = new TemperatureSensor(catalogueDouble);

            // Act
            int tempValue = Integer.parseInt(temperatureSensor.getValue().toString());

            // Assert
            assertFalse(tempValue >= -70 && tempValue <= 70, "The temperature value should be between -70 and 70.");
        }
    }
}