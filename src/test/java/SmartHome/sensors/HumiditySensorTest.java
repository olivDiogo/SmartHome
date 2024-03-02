package SmartHome.sensors;

import SmartHome.domain.*;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HumiditySensorTest {
    @Test
    void newValidHumiditySensor() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new HumiditySensor( catalogueDouble );
    }

    @Test
    void newInvalidHumiditySensor() {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new HumiditySensor( catalogueDouble));

        // Assert
        assertEquals( "SensorType with description 'Humidity' does not exist.", exception.getMessage() );
    }

    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        HumiditySensor humiditySensor = new HumiditySensor( catalogueDouble);

        // Act
        SensorType result = humiditySensor.getSensorType();

        // Assert
        assertEquals( sensorTypeDouble, result );
    }

    @Test
    void getValueReturnsPositiveValue() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        String humidity = "100";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<HumiditySensorValue> humiditySensorValue = mockConstruction(HumiditySensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(humidity);
        })) {
            HumiditySensor humiditySensor = new HumiditySensor( catalogueDouble);

            // Act
            double result =Double.parseDouble(humiditySensor.getValue().toString());

            // Assert
            assertTrue(result >= 0 && result <= 100, "The humidity value should be between 0 and 100.");
        }
    }

    @Test
    void getValueReturnsZero() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        String humidity = "0";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<HumiditySensorValue> humiditySensorValue = mockConstruction(HumiditySensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(humidity);
        })) {
            HumiditySensor humiditySensor = new HumiditySensor( catalogueDouble);

            // Act
            double result =Double.parseDouble(humiditySensor.getValue().toString());

            // Assert
            assertTrue(result >= 0 && result <= 100,"The humidity value should be between 0 and 100.");
        }
    }

    @Test
    void getValueReturnsInvalidValue() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        String humidity = "-1";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try(MockedConstruction<HumiditySensorValue> humiditySensorValue = mockConstruction(HumiditySensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(humidity);
        })) {
            HumiditySensor humiditySensor = new HumiditySensor( catalogueDouble);

            // Act
            double result =Double.parseDouble(humiditySensor.getValue().toString());

            // Assert
            assertFalse(result >= 0 && result <= 100,"The humidity value should be between 0 and 100.");
        }
    }
}