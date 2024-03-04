package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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
     * @throws InstantiationException if the actuator type cannot be created
     */
    @Test
    void getActuatorTypeReturnsWrongActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetInteger";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        ActuatorType actuatorTypeDouble2 = mock(ActuatorType.class);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        ActuatorType actuatorType = setIntegerActuator.getActuatorType();

        // Assert
        assertNotEquals(actuatorTypeDouble2, actuatorType);
    }

    /**
     * Set value in range.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void setValueInRange() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        int lowerLimit = 0;
        int upperLimit = 100;

        String value = "50";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        Value valueDouble = mock(Value.class);
        when(valueDouble.toString()).thenReturn(value);


        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        Value result = setIntegerActuator.setValueInRange(lowerLimit, upperLimit, valueDouble);

        // Assert
        assertEquals(value, result.toString());
    }

    /**
     * Set value below lower limit.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void setValueBelowLowerLimit_thenThrowException() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        int lowerLimit = 0;
        int upperLimit = 100;

        String value = "-1";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        Value valueDouble = mock(Value.class);
        when(valueDouble.toString()).thenReturn(value);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        String expected = "Value cannot be less than the lower limit.";

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> setIntegerActuator.setValueInRange(lowerLimit, upperLimit, valueDouble));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(expected, exception.getMessage());
    }

    /**
     * Set value above upper limit.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void setValueAboveUpperLimit_thenThrowException() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        int lowerLimit = 0;
        int upperLimit = 100;

        String value = "101";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        Value valueDouble = mock(Value.class);
        when(valueDouble.toString()).thenReturn(value);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        String expected = "Value cannot be greater than the upper limit.";

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> setIntegerActuator.setValueInRange(lowerLimit, upperLimit, valueDouble));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(expected, exception.getMessage());
    }
}
