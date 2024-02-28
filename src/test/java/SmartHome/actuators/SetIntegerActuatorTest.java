package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SetIntegerActuatorTest {

    /**
     * Should create instance of SetIntegerActuator class.
     */
    @Test
    void shouldCreateInstanceOfSetIntegerActuator() throws InstantiationException {
        // Arrange
        String description = "SetInteger";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // Act
        new SetIntegerActuator(catalogueDouble);
    }

    /**
     * Should throw exception when creating instance of SetIntegerActuator class with invalid actuator type.
     */
    @Test
    void createSetIntegerActuatorWithInvalidActuatorType_thenThrowException() {
        // Arrange
        String description = "SetInteger";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(null);

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(InstantiationException.class, () -> new SetIntegerActuator(catalogueDouble));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("ActuatorType with description 'SetInteger' does not exist.", exception.getMessage());
    }

    /**
     * Should get actuator type.
     * @throws InstantiationException if the actuator type cannot be created
     */
    @Test
    void getActuatorTypeReturnsCorrectActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        ActuatorType actuatorType = setIntegerActuator.getActuatorType();

        // Assert
        assertEquals(actuatorTypeDouble, actuatorType);
    }

    /**
     * Should get a different actuator type than expected
     * @throws InstantiationException
     */
    @Test
    void getActuatorTypeReturnsWrongActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        ActuatorType actuatorTypeDouble2 = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        ActuatorType actuatorType = setIntegerActuator.getActuatorType();

        // Assert
        assertNotEquals(actuatorTypeDouble2, actuatorType);
    }
}
