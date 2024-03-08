package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for {@link SwitchSensor}.
 * This class performs unit tests on the SwitchSensor class to ensure its proper functionality.
 */
class SwitchSensorTest {
    /**
     * Tests that a SwitchSensor can be successfully instantiated with a valid sensor type.
     *
     * @throws InstantiationException if the sensor type "Switch" does not exist in the catalogue.
     */
    @Test
    void shouldReturnNewValidSwitchSensor_WhenArgumentsAreValid() throws InstantiationException {
        // Arrange
        String description = "Switch";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new SwitchSensor(catalogueDouble);
    }

    /**
     * Tests that an InstantiationException is thrown when trying to instantiate a SwitchSensor
     * with an invalid sensor type.
     */
    @Test
    void shouldThrowException_WhenDescriptionIsInvalid() {
        // Arrange
        String description = "Switch";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new SwitchSensor(catalogueDouble));

        // Assert
        assertEquals("SensorType with description 'Switch' does not exist.", exception.getMessage());
    }

    /**
     * Tests that the getSensorType method returns the correct SensorType for a SwitchSensor.
     *
     * @throws InstantiationException if the sensor type "Switch" does not exist in the catalogue.
     */
    @Test
    void shouldThrowInstantiationException_WhenSensorTypeIsInvalid() throws InstantiationException {
        // Arrange
        String description = "Switch";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SwitchSensor switchSensor = new SwitchSensor(catalogueDouble);

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertEquals(sensorTypeDouble, result);
    }

    /**
     * Tests that the getValue method returns a SwitchSensorValue indicating the sensor is "Off".
     * This test uses a mocked construction of SwitchSensorValue to simulate the sensor's state.
     *
     * @throws InstantiationException if the sensor type "Switch" does not exist in the catalogue.
     */
    @Test
    void shouldReturnOffSwitchSensorValue_WhenGetValueIsCalled() throws InstantiationException {
        // Arrange
        String description = "Switch";
        String state = "Off";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<SwitchSensorValue> switchSensorValueDouble = mockConstruction(SwitchSensorValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn((boolean) context.arguments().get(0)? "On" : "Off");
        })) {
            SwitchSensor switchSensor = new SwitchSensor(catalogueDouble);

            // Act
            Value result = switchSensor.getValue();

            // Assert
            List<SwitchSensorValue> constructed = switchSensorValueDouble.constructed();
            assertEquals(2, constructed.size());
            assertEquals(result.toString(), constructed.get(1).toString());
            assertEquals(state, result.toString());
        }
    }
}
