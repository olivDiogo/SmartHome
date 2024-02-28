package SmartHome.actuators;

import SmartHome.domain.ActuatorType;
import SmartHome.domain.CatalogueActuator;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SetDecimalActuatorTest {

    @Test
    void shouldCreateInstanceOfSetDecimalActuator() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0.0;
        double upperLimit = 100.0;

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        // Act
        new SetDecimalActuator(catalogueDouble, lowerLimit, upperLimit);
    }

    @Test
    void createSetDecimalActuatorWithInvalidActuatorType_thenThrowException() {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0.0;
        double upperLimit = 100.0;
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(null);

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(InstantiationException.class, () -> new SetDecimalActuator(catalogueDouble, lowerLimit, upperLimit));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals("ActuatorType with description 'SetDecimal' does not exist.", exception.getMessage());
    }

    @Test
    void getActuatorTypeReturnsCorrectActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0.0;
        double upperLimit = 100.0;

        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble, lowerLimit, upperLimit);

        // Act
        ActuatorType actuatorType = setDecimalActuator.getActuatorType();

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(actuatorTypeDouble, actuatorType);
    }

    @Test
    void getActuatorTypeReturnsWrongActuatorType() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0.0;
        double upperLimit = 100.0;
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
        ActuatorType actuatorTypeDouble2 = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble, lowerLimit, upperLimit);

        // Act
        ActuatorType actuatorType = setDecimalActuator.getActuatorType();

        // Assert
        org.junit.jupiter.api.Assertions.assertNotEquals(actuatorTypeDouble2, actuatorType);
    }

    @Test
    void setValueRangeReturnsCorrectValue() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0.0;
        double upperLimit = 100.0;
        double value = 50.0;
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble, lowerLimit, upperLimit);

        // Act
        double result = setDecimalActuator.setValueRange(value);

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(value, result);
    }

    @Test
    void setValueBelowLowerLimit_thenThrowException() throws InstantiationException {
        // Arrange
        String description = "SetDecimal";
        double lowerLimit = 0.0;
        double upperLimit = 100.0;
        double value = -1.0;
        CatalogueActuator catalogueDouble = mock(CatalogueActuator.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(catalogueDouble.getActuatorType(description)).thenReturn(actuatorTypeDouble);

        SetDecimalActuator setDecimalActuator = new SetDecimalActuator(catalogueDouble, lowerLimit, upperLimit);

        String expected = "The value is out of the limits.";

        // Act
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> setDecimalActuator.setValueRange(value));

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(expected, exception.getMessage());
    }

}