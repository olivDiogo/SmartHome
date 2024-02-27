package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SwitchSensorTest {
    @Test
    void newValidSwitchSensor() throws InstantiationException {
        // Arrange

        String description = "Switch";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble );

        // Assert
        assertNotNull( switchSensor );
    }

    @Test
    void newInvalidSwitchSensor() throws InstantiationException {
        // Arrange
        String description = "Switch";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

            when(catalogueDouble.getSensorType(description)).thenReturn(null);

            // Act
            Exception exception = assertThrows(InstantiationException.class, () -> new SwitchSensor( catalogueDouble ));

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

        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble );

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertEquals( sensorTypeDouble, result );
    }

    @Test
    void getSensorTypeReturnsWrongSensorType() throws InstantiationException {
        // Arrange
        String description = "Switch";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorType wrongSensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble );

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertNotEquals( wrongSensorTypeDouble, result );
    }

    @Test
    void getValueReturnsOff() throws InstantiationException {
        // Arrange
        String description = "Switch";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble );

        // Act
        Value result = switchSensor.getValue();

        // Assert
        assertEquals( "Off", result.toString() );
    }
}