package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.Value;
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
    void shouldCreateInstanceOfSwitchActuator() throws InstantiationException {
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

        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // act
        ActuatorType actuatorType = switchActuator.getActuatorType();

        // assert
        assertEquals(actuatorType, actuatorTypeDouble);
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

    /**
     * Should set the value of the actuator to on.
     *
     * @throws InstantiationException
     */

    @Test
    void setValueOn() throws InstantiationException {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        when(switchActuatorValueDouble.clone()).thenReturn(switchActuatorValueDouble);

        boolean actuatorValue = true;

        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // act
        Value value = switchActuator.setValue(actuatorValue);

        // assert
        assertEquals("On", value.toString());
    }

    /**
     * Set Invalid Value when actuator value is false
     *
     * @throws InstantiationException
     */
    @Test
    void setInavlidValue_On() throws InstantiationException {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        when(switchActuatorValueDouble.clone()).thenReturn(switchActuatorValueDouble);

        boolean actuatorValue = false;

        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // act
        Value value = switchActuator.setValue(actuatorValue);

        // assert
        assertNotEquals("On", value.toString());
    }

    /**
     * Should set the value of the actuator to off.
     *
     * @throws InstantiationException
     */

    @Test
    void setValueOff() throws InstantiationException {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        when(switchActuatorValueDouble.clone()).thenReturn(switchActuatorValueDouble);

        boolean actuatorValue = false;

        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // act
        Value value = switchActuator.setValue(actuatorValue);

        // assert
        assertEquals("Off", value.toString());
    }

    /**
     * Set Invalid Value when actuator value is true
     *
     * @throws InstantiationException
     */

    @Test
    void setInvalidValue_Off() throws InstantiationException {
        // arrange
        String description = "SwitchActuator";
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        SwitchActuatorValue switchActuatorValueDouble = mock(SwitchActuatorValue.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);
        when(switchActuatorValueDouble.clone()).thenReturn(switchActuatorValueDouble);

        boolean actuatorValue = true;

        SwitchActuator switchActuator = new SwitchActuator(catalogueDouble);

        // act
        Value value = switchActuator.setValue(actuatorValue);

        // assert
        assertNotEquals("Off", value.toString());
    }
}
