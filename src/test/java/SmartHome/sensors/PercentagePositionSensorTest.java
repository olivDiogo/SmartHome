package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PercentagePositionSensorTest {

    @Test
    void newValidPercentedPosicionSensor() throws InstantiationException {
        // Arrange

        String description = "Percented";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor( catalogueDouble );

        // Assert
        assertNotNull(percentagePositionSensor);
    }

    @Test
    void newInvalidPercentedPosicionSensor() throws InstantiationException {
        // Arrange
        String description = "Percented";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

            when(catalogueDouble.getSensorType(description)).thenReturn(null);

            // Act
            Exception exception = assertThrows(InstantiationException.class, () -> new PercentagePositionSensor( catalogueDouble ));

            // Assert
            assertEquals( "SensorType with description 'Percented' does not exist.", exception.getMessage() );
    }

    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Percented";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor( catalogueDouble );

        // Act
        SensorType result = percentagePositionSensor.getSensorType();

        // Assert
        assertEquals( sensorTypeDouble, result );
    }

    @Test
    void getValueReturnsValidValue() throws InstantiationException {
        // Arrange
        String description = "Percented";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor( catalogueDouble );

        // Act
        Object result = percentagePositionSensor.getValue();

        // Assert
        assertNotNull( result );
    }

}