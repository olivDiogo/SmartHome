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


}
