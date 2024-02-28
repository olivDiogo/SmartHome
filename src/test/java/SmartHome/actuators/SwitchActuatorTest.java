package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import org.junit.jupiter.api.Test;
import SmartHome.domain.CatalogueActuator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwitchActuatorTest {

    /**
     * Should create instance of SwitchActuator class.
     *
     * @throws InstantiationException
     */
    @Test
    void shouldCreateInstanceOfSwitchActuator() throws InstantiationException
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
    void whenNonExistentActuactorTypeForSwitchActuator_thenThrowsException() {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(null);

        // act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(InstantiationException.class, () -> new SwitchActuator(catalogueDouble));

        // assert
        org.junit.jupiter.api.Assertions.assertEquals("ActuatorType with description 'SwitchActuator' does not exist.", exception.getMessage());
    }


    /**
     * Should get an actuator type.
     *
     * @throws InstantiationException if the actuator type cannot be created
     */

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


    /**
     * Should get the wrong actuator type
     *
     * @throws InstantiationException
     */

    @Test
    void getActuatorTypeReturnsWrongActuatorType() throws InstantiationException {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        ActuatorType actuatorTypeDouble2 = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // act
        ActuatorType actuatorType = switchActuator.getActuatorType();


        // assert
        assertNotEquals(actuatorType, actuatorTypeDouble2);
    }

}
