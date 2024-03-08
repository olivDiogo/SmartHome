package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains test cases for the SetDecimalActuator class.
 */
class SetDecimalActuatorTest {

    /**
     * Tests the creation of an instance of SetDecimalActuator.
     *
     * @throws InstantiationException if the actuator type does not exist in the catalogue.
     */
    @Test
    void shouldCreateInstanceOfSetDecimalActuator() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // Act
        new SetDecimalActuator(catalogueDouble);
    }

    /**
     * Tests creating a SetDecimalActuator with an invalid actuator type.
     */
    @Test
    void createSetDecimalActuatorWithInvalidActuatorType_thenThrowException() {
        // Arrange
        String description = "SetDecimal";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(null);

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(InstantiationException.class, () -> new SetDecimalActuator(catalogueDouble));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("ActuatorType with description 'SetDecimal' does not exist.", exception.getMessage());
    }

    /**
     * Tests the getActuatorType method of SetDecimalActuator.
     *
     * @throws InstantiationException if the actuator type does not exist in the catalogue.
     */
    @Test
    void getActuatorTypeReturnsCorrectActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        ActuatorType result = setDecimalActuator.getActuatorType();

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(actuatorTypeDouble, result);
    }

    /**
     * Tests the getActuatorType method of SetDecimalActuator when the returned actuator type is incorrect.
     *
     * @throws InstantiationException if the actuator type does not exist in the catalogue.
     */
    @Test
    void getActuatorTypeReturnsWrongActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        ActuatorType actuatorTypeDouble2 = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        ActuatorType result = setDecimalActuator.getActuatorType();

        // Assert
        org.junit.jupiter.api.Assertions.assertNotEquals(actuatorTypeDouble2, result);
    }

}
