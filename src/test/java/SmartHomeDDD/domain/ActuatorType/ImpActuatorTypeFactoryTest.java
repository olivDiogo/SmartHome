package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.TypeDescription;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

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

        try(MockedConstruction<ActuatorType> actuatorTypeDouble = mockConstruction(ActuatorType.class,(mock, context) -> {
            TypeDescription actualTypeDescription = (TypeDescription) context.arguments().get(0);
            when(mock.getActuatorTypeName()).thenReturn(actualTypeDescription);

        })) {
            ImpActuatorTypeFactory impActuatorTypeFactory = new ImpActuatorTypeFactory();

            // Act
            ActuatorType actuatorType = impActuatorTypeFactory.createActuatorType(typeDescriptionDouble);

            // Assert
            List<ActuatorType> actuatorTypeList = actuatorTypeDouble.constructed();
            assertEquals(1, actuatorTypeList.size());
            assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
        }
    }

    /**
     * Test to ensure that an ActuatorType cannot be created when the typeDescription is null
     */
    @Test
    void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
        // Arrange
        TypeDescription typeDescriptionDouble = null;

        String expectedMessage = "Actuator type name must not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ActuatorType(typeDescriptionDouble);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}