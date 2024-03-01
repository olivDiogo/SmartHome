package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlindRollerActuatorTest {

    @Test
    void testForConstructor() throws InstantiationException{
        // Arrange
        String description = "BlindRollerActuator";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // Act
        new BlindRollerActuator(catalogueDouble);

        // Assert
        assertNotNull(BlindRollerActuator.class);
    }

    @Test
    void testForInvalidActuatorType() {
        // Arrange
        String description = "BlindRollerActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(null);

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new BlindRollerActuator(catalogueDouble));

        // Assert
        assertEquals("ActuatorType with description 'BlindRollerActuator' does not exist.", exception.getMessage());
    }

    @Test
    void testForGetActuatorType() throws InstantiationException {
        // Arrange
        String description = "BlindRollerActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(catalogueDouble);

        // Act
        ActuatorType actuatorType = blindRollerActuator.getActuatorType();

        // Assert
        assertEquals(actuatorTypeDouble, actuatorType);
    }

    @Test
    void testForSetValue() throws InstantiationException {
        // Arrange
        String description = "BlindRollerActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(catalogueDouble);
        int value = 50;

        // Act
        blindRollerActuator.setValue(new BlindRollerValue(value));

        // Assert
        assertNotNull(blindRollerActuator);
    }

    @Test
    void testForInvalidSetValue() throws InstantiationException {
        // Arrange
        String description = "BlindRollerActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(catalogueDouble);
        int value = 150;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> blindRollerActuator.setValue(new BlindRollerValue(value)));

        // Assert
        assertEquals("The value must be between 0 and 100", exception.getMessage());
    }

}
