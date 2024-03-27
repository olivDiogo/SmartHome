package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImpActuatorTypeFactoryTest {

    /**
     * Test to ensure that an ActuatorType can be created successfully
     */
    @Test
    void shouldCreateActuatorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        ImpActuatorTypeFactory factory = new ImpActuatorTypeFactory();

        // Act
        ActuatorType actuatorType = factory.createActuatorType(typeDescriptionDouble);

        // Assert
        assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
    }
}