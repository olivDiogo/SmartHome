package SmartHome.sensors;

import SmartHome.domain.*;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void getValueReturnsCorrectValue() throws InstantiationException {
        // Arrange
        String description = "Humidity";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        HumiditySensor humiditySensor = new HumiditySensor( catalogueDouble);

        // Act
        Value value = humiditySensor.getValue();
        double humidityValue = Double.parseDouble(value.toString());

        // Assert
        assertTrue(humidityValue >= 0 && humidityValue <= 100);
    }
}