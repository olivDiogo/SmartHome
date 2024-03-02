package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        String description = "Temperature";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

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