package smartHome.domain.sensorType;

import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ImpSensorTypeFactoryTest {

    /**
     * Test to ensure that a SensorType can be created successfully
     */
    @Test
    void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);

        SensorTypeFactoryImpl factory = new SensorTypeFactoryImpl();

        // Act
        SensorType createdSensorType = factory.createSensorType(typeDescriptionDouble, unitDouble);

        // Assert
        assertNotNull(createdSensorType);
    }
}
