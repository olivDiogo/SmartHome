package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import org.junit.jupiter.api.Test;
import SmartHome.domain.CatalogueActuator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwitchActuatorTest {

    @Test
    void shouldCreateInstanceOfBinarySwitchActuator() throws InstantiationException
    {
        // arrange
        String description = "SwitchActuator";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // act
        new SwitchActuator(catalogueDouble);
    }

    /**
     * Should throw exception when creating instance of BinarySwitchActuator class with invalid actuator type.
     */
    @Test
    void createBinarySwitchActuatorWithInvalidActuatorType_thenThrowException() {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(null);

        // act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(InstantiationException.class, () -> new SwitchActuator(catalogueDouble));

        // assert
        org.junit.jupiter.api.Assertions.assertEquals("ActuatorType with description 'BinarySwitch' does not exist.", exception.getMessage());
    }

    @Test
    void getActuatorTypeReturnsCorrectActuatorType() throws InstantiationException {
        // arrange
        String description = "SwitchActuator";
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // act
        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // assert
        assertEquals(switchActuator.getActuatorType(), actuatorTypeDouble);
    }



}
