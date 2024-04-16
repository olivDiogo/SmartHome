package smartHome.domain.sensorType;

import org.junit.jupiter.api.Test;
import smartHome.valueObject.SensorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

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

    /**
     * Test to ensure that a SensorType can be created successfully with a SensorTypeID
     */
    @Test
    void shouldCreateSensorType_whenAttributesAreValidWithSensorTypeID() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        SensorTypeFactoryImpl factory = new SensorTypeFactoryImpl();

        // Act
        SensorType createdSensorType = factory.createSensorType(sensorTypeIDDouble, typeDescriptionDouble, unitDouble);

        // Assert
        assertNotNull(createdSensorType);
    }
}
