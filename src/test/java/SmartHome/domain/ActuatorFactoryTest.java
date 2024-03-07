package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorFactoryTest {

    /**
     * Test for constructor of ActuatorFactory.
     *
     * @throws InstantiationException
     */
    @Test
    void createActuator() throws InstantiationException {
        // arrange
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        String strModel = "SmartHome.actuators.BlindRollerActuator";
        String strDescription = "BlindRollerActuator";

        when(catalogueDouble.getActuatorType(strDescription)).thenReturn(actuatorTypeDouble);

        // act
        Actuator actuator = actuatorFactory.createActuator(strModel, catalogueDouble);

        // assert
        assertNotNull(actuator);
    }

    @Test
    void createInvalidActuator() throws InstantiationException {
        // arrange
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);

        String description = "InvalidActuator";
        String strModel = "SmartHome.actuators.InvalidActuator";

        when(catalogueDouble.getActuatorType(description)).thenThrow(IllegalArgumentException.class);

        // act
        Actuator actuator = actuatorFactory.createActuator(strModel, catalogueDouble);

        // assert
        assertNull(actuator);
    }

}
