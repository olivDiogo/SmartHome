package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.apache.commons.beanutils.BasicDynaBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PowerConsumptionSensorTest {

    @Test
    void newPowerConsumptionSensor() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        // Act
        SensorType result = powerConsumptionSensor.getSensorType();

        // Assert
        Assertions.assertEquals(sensorTypeDouble, result);
    }

    @Test
    void newPowerConsumptionSensorWithValidValue() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        // Act
        double value = Double.parseDouble(powerConsumptionSensor.getValue().toString());

        // Assert
        Assertions.assertTrue(value >= 0 && value <= 2000);
    }

    @Test
    void newNonExistentSensorTypeForPowerConsumptionSensor() throws InstantiationException {
        // Arrange
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        String expectedMessage = "SensorType with description 'Power Consumption' does not exist.";

        // Act + Assert
        Exception exception = Assertions.assertThrows(InstantiationException.class,
                () -> new PowerConsumptionSensor(catalogueDouble));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void newNullSensorTypeForPowerConsumptionSensor() {
        //Arrange
        String description = "Power Consumption";
        String expectedMessage = "SensorType with description 'Power Consumption' does not exist.";

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(null);
        //Act + Assert
        Exception exception = Assertions.assertThrows(InstantiationException.class, () -> new PowerConsumptionSensor(catalogueDouble));

        //Assert
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

}
