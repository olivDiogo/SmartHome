package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SwitchSensorTest {
    @Test
    void newValidSwitchSensor() throws InstantiationException {
        // Arrange

        String description = "Switch";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new SwitchSensor( catalogueDouble);
    }

    @Test
    void newInvalidSwitchSensor() {
        // Arrange
        String description = "Switch";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SwitchSensor( catalogueDouble));

        // Assert
        assertEquals( "SensorType with description 'Switch' does not exist.", exception.getMessage() );
    }

    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Switch";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble);

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertEquals( sensorTypeDouble, result );
    }

    @Test
    void getValueReturnsOff() throws InstantiationException {
        // Arrange
        String description = "Switch";
        String state = "Off";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<SwitchSensorValue> switchSensorValueDouble = mockConstruction(SwitchSensorValue.class, (mock, context) -> {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(state);
        })) {
            SwitchSensor switchSensor = new SwitchSensor(catalogueDouble);

            // Act
            Value result = switchSensor.getValue();

            // Assert
            assertEquals("Off", result.toString());
        }
    }
}