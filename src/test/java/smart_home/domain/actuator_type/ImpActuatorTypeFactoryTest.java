package smart_home.domain.actuator_type;

import org.junit.jupiter.api.Test;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ImpActuatorTypeFactoryTest {

    /**
     * Test to ensure that an ActuatorType can be created successfully in the first method.
     */
    @Test
    void shouldCreateActuatorTypeInTheFirstMethod_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        ActuatorTypeFactoryImpl factory = new ActuatorTypeFactoryImpl();
        UnitID unitID = mock(UnitID.class);

        // Act
        ActuatorType actuatorType = factory.createActuatorType(typeDescriptionDouble, unitID);

        // Assert
        assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
    }

    /**
     * Test to ensure that an ActuatorType can be created successfully in the second method.
     */
    @Test
    void shouldCreateActuatorTypeInTheSecondMethod_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        ActuatorTypeFactoryImpl factory = new ActuatorTypeFactoryImpl();
        UnitID unitID = mock(UnitID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

        // Act
        ActuatorType actuatorType = factory.createActuatorType(typeDescriptionDouble, unitID, actuatorTypeID);

        // Assert
        assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
    }
}