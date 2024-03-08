package SmartHome.domain;


import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SensorTypeFactoryTest {
    @Test
    void shouldReturnMockedSensorType_TestWithoutWhen() {
        // Arrange
        String description = "Temperature";
        Unit unit = Unit.TEMPERATURE; // Assuming an example unit

        try (MockedConstruction<SensorType> mocked = Mockito.mockConstruction(SensorType.class)) {
            // Act
            SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
            SensorType sensorType = sensorTypeFactory.createSensorType(description, unit);
            // Assert
            assertTrue(mocked.constructed().contains(sensorType));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldReturnMockedSensorType_TestWithWhen() throws InstantiationException {
        // Arrange
        String description = "Temperature";
        Unit unit = Unit.TEMPERATURE; // Assuming an example unit

        try (MockedConstruction<SensorType> mocked = Mockito.mockConstruction(SensorType.class, (mock, context) -> {
            when(mock.getDescription()).thenReturn(description);
            when(mock.getUnit()).thenReturn(unit);
        })) {
            // Act
            SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
            SensorType sensorType = sensorTypeFactory.createSensorType(description, unit);
            // Assert
            assertEquals(description, sensorType.getDescription());
            assertEquals(unit, sensorType.getUnit());
        }
    }
}
