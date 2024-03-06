package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlindRollerActuatorTest {

    /**
     * Test to see if the constructor works.
     */
    @Test
    void testForConstructor() throws InstantiationException {
        // Arrange
        String description = "BlindRollerActuator";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // Act
        new BlindRollerActuator(catalogueDouble);
    }


    /**
     * Test to see if the constructor throws an exception when the actuator type is invalid.
     */
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

    /**
     * Test to see if the getActuatorType method works.
     *
     * @throws InstantiationException
     */
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

    /**
     * Test to see if the setValue method works.
     *
     * @throws InstantiationException
     */
    @Test
    void testForSetValue() throws InstantiationException {
        // Arrange
        String description = "BlindRollerActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        BlindRollerValue blindRollerValueDouble = mock(BlindRollerValue.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        when(blindRollerValueDouble.clone()).thenReturn(blindRollerValueDouble);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(catalogueDouble);

        // Act
        Value result = blindRollerActuator.setValue(blindRollerValueDouble);

        // Assert
        assertEquals(blindRollerValueDouble, result);
    }

    /**
     * Test to see if the setValue method returns null when the value is not of type BlindRollerValue.
     *
     * @throws InstantiationException
     */
    @Test
    void testForSetValueWithInvalidValue() throws InstantiationException {
        // Arrange
        String description = "BlindRollerActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(catalogueDouble);

        // Act
        Value result = blindRollerActuator.setValue(null);

        // Assert
        assertNull(result);
    }

}
