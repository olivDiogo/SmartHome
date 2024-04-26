package smarthome.domain.sensor_type;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

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
