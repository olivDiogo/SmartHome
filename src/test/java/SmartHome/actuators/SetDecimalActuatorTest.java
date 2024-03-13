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
    void shouldCreateInstanceOfSetDecimalActuatorWhenValidActuatorTypeIsProvided() throws InstantiationException {
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
    void shouldThrowExceptionWhenInvalidActuatorTypeIsProvided() {
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
    void shouldReturnCorrectActuatorTypeWhenGetActuatorTypeIsCalled() throws InstantiationException {
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
    void shouldNotReturnIncorrectActuatorTypeWhenGetActuatorTypeIsCalled() throws InstantiationException {
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

    /**
     * Test case to verify that the method returns a value equal to zero when the lower limit is set to zero.
     *
     * @throws InstantiationException if an error occurs during instantiation of objects.
     */
    @Test
    void shouldGetValueEqualToZero_whenLowerLimitIsSetToZero() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0;
        double upperLimit = 100;

        String value = "0";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalValue valueDouble = mock(SetDecimalValue.class);
        when(valueDouble.toString()).thenReturn(value);
        when(valueDouble.clone()).thenReturn(valueDouble);


        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        Value result = setDecimalActuator.setValueInRange(valueDouble, lowerLimit, upperLimit);

        // Assert
        assertEquals(value, result.toString());
    }

    /**
     * Test case to verify that the method returns a value equal to the upper limit when the upper limit is set to 100.
     *
     * @throws InstantiationException if an error occurs during instantiation of objects.
     */
    @Test
    void shouldGetValueEqualToUpperLimit_whenUpperLimitIsSetTo100() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0;
        double upperLimit = 100;

        String value = "100";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalValue valueDouble = mock(SetDecimalValue.class);
        when(valueDouble.toString()).thenReturn(value);
        when(valueDouble.clone()).thenReturn(valueDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        Value result = setDecimalActuator.setValueInRange(valueDouble, lowerLimit, upperLimit);

        // Assert
        assertEquals(value, result.toString());
    }

    /**
     * Test case to verify that an IllegalArgumentException is thrown when the value is less than the lower limit.
     *
     * @throws InstantiationException if an error occurs during instantiation of objects.
     */
    @Test
    void shouldThrowExceptionWhenValueIsLessThanLowerLimit() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0;
        double upperLimit = 100;

        String value = "-1";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalValue valueDouble = mock(SetDecimalValue.class);
        when(valueDouble.toString()).thenReturn(value);
        when(valueDouble.clone()).thenReturn(valueDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> setDecimalActuator.setValueInRange(valueDouble, lowerLimit, upperLimit));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("Value cannot be less than the lower limit.", exception.getMessage());
    }

    /**
     * Test case to verify that an IllegalArgumentException is thrown when the value is greater than the upper limit.
     *
     * @throws InstantiationException if an error occurs during instantiation of objects.
     */
    @Test
    void shouldThrowExceptionWhenValueIsGreaterThanUpperLimit() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0;
        double upperLimit = 100;

        String value = "101";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalValue valueDouble = mock(SetDecimalValue.class);
        when(valueDouble.toString()).thenReturn(value);
        when(valueDouble.clone()).thenReturn(valueDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> setDecimalActuator.setValueInRange(valueDouble, lowerLimit, upperLimit));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("Value cannot be greater than the upper limit.", exception.getMessage());
    }

    /**
     * Test case to verify that null is returned when the value is not an instance of SetDecimalValue.
     *
     * @throws InstantiationException if an error occurs during instantiation of objects.
     */
    @Test
    void shouldReturnNullWhenValueIsNotInstanceOfSetDecimalValue() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0;
        double upperLimit = 100;

        String value = "50";

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        Value valueDouble = mock(Value.class);
        when(valueDouble.toString()).thenReturn(value);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble);

        // Act
        Value result = setDecimalActuator.setValueInRange(valueDouble, lowerLimit, upperLimit);

        // Assert
        org.junit.jupiter.api.Assertions.assertNull(result);
    }



}
