package SmartHomeDDD.domain.SensorType;

import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

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
