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
    void shouldThrowException_whenCreatingSetIntegerActuatorWithInvalidActuatorType() {
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
    void shouldGetCorrectActuatorType() throws InstantiationException {
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
     * Set value in range at the lower limit boundary.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void shouldGetValueEqualToZero_whenLowerLimitIsZero() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        int lowerLimit = 0;
        int upperLimit = 100;

        String value = "0";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetIntegerValue valueDouble = mock(SetIntegerValue.class);
        when(valueDouble.toString()).thenReturn(value);
        when(valueDouble.clone()).thenReturn(valueDouble);


        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        Value result = setIntegerActuator.setValueInRange(lowerLimit, upperLimit, valueDouble);

        // Assert
        assertEquals(value, result.toString());
    }

    /**
     * Set value in range at the upper limit boundary.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void shouldGetValueEqualTo100_whenUpperLimitIs100() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        int lowerLimit = 0;
        int upperLimit = 100;

        String value = "100";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetIntegerValue valueDouble = mock(SetIntegerValue.class);
        when(valueDouble.toString()).thenReturn(value);
        when(valueDouble.clone()).thenReturn(valueDouble);


        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        Value result = setIntegerActuator.setValueInRange(lowerLimit, upperLimit, valueDouble);

        // Assert
        assertEquals(value, result.toString());
    }

    /**
     * Set wrong value type in range, returns null.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void shouldReturnNull_whenTheWrongTypeOfValueIsPassedAsArgument() throws InstantiationException {
        // Arrange
        String description = "SetInteger";
        int lowerLimit = 0;
        int upperLimit = 100;

        String value = "50";

        Value expected = null;

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        Value valueDouble = mock(Value.class);
        when(valueDouble.toString()).thenReturn(value);

        SetIntegerActuator setIntegerActuator = new SetIntegerActuator(catalogueDouble);

        // Act
        Value result = setIntegerActuator.setValueInRange(lowerLimit, upperLimit, valueDouble); //valueDouble is not an instance of SetIntegerValue, hence returning null

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Set value below lower limit.
     * @throws InstantiationException if the value cannot be set
     */
    @Test
    void shouldThrowException_whenSettingValueBelowLowerLimit() throws InstantiationException {
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
    void shouldThrowException_whenSettingValueAboveUpperLimit() throws InstantiationException {
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
