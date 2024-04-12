package smartHome.domain.actuatorType;

import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ImpActuatorTypeFactoryTest {

    /**
     * Test to ensure that an ActuatorType can be created successfully
     */
    @Test
    void shouldCreateActuatorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        ActuatorTypeFactoryImpl factory = new ActuatorTypeFactoryImpl();
        UnitID unitID = mock(UnitID.class);

        // Act
        ActuatorType actuatorType = factory.createActuatorType(typeDescriptionDouble, unitID);

        // Assert
        assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
    }
}