package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class PowerConsumptionSensorTest {

    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiatePowerConsumptionSensor() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        new PowerConsumptionSensor(catalogueDouble);

    }

    /**
     * See if the getSensorType method works.
     */
    @Test
    void getSensorType() throws InstantiationException {
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

    /**
     * See if the getValue method works.
     */
    @Test
    void newPowerConsumptionSensorWithValidValue() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        String value = "1500.0";

        int expectedSize = 1;

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        PowerConsumptionSensorValue powerConsumptionSensorValue = mock(PowerConsumptionSensorValue.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);


        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        try (MockedConstruction<PowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(PowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(powerConsumptionSensorValue.clone()).thenReturn(powerConsumptionSensorValue);
            when(powerConsumptionSensorValue.toString()).thenReturn(value);
            when(mock.toString()).thenReturn(value);
        })) {

            LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
            LocalDateTime finalTime = LocalDateTime.now();

            powerConsumptionSensor.setValue(initialTime, 1000);
            powerConsumptionSensor.setValue(finalTime, 2000);

            // Act
            powerConsumptionSensor.getAverageValue(initialTime, finalTime);
            Value result = powerConsumptionSensor.getValue();
            System.out.println(result);


            // Assert
            List<PowerConsumptionSensorValue> powerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, powerConsumptionSensorValues.size());
            Assertions.assertEquals(value, powerConsumptionSensorValues.get(0).toString());
        }
    }

    /**
     * tests if Exception is thrown for non-existing Sensor type.
     */
    @Test
    void newNonExistentSensorTypeForPowerConsumptionSensor() {
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

    /**
     * Tests if Exception is thrown for null Sensor type.
     */
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

    /**
     * See if the getAverageValue method works.
     */

    @Test
    void getAverageValueTest() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.of(2024, 2, 29, 10, 10, 5);
        LocalDateTime finalTime = LocalDateTime.of(2024, 2, 29, 10, 10, 10);

        powerConsumptionSensor.setValue(initialTime, 1000);
        powerConsumptionSensor.setValue(finalTime, 1500);

        double expectedAverage = 1250;

        // Act
        double averageValue = powerConsumptionSensor.getAverageValue(initialTime, finalTime);

        // Assert
        Assertions.assertEquals(expectedAverage, averageValue, 0.01);

    }

    /**
     * See if the getAverageValue method works with another time.
     */

    @Test
    void getAverageValueUsingAnotherTime() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
        LocalDateTime finalTime = LocalDateTime.now();

        powerConsumptionSensor.setValue(initialTime, 1000);
        powerConsumptionSensor.setValue(finalTime, 2000);

        double expectedAverage = 1500;

        // Act
        double averageValue = powerConsumptionSensor.getAverageValue(initialTime, finalTime);

        // Assert
        Assertions.assertEquals(expectedAverage, averageValue, 0.01);
    }


    /**
     * See if the getAverageValue method works with more values than the initial and final time.
     */
    @Test
    void getAverageValueOfMoreValuesThanTheInitialAndFinalTime() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
        LocalDateTime secondTime = LocalDateTime.now().minusHours(1);
        LocalDateTime finalTime = LocalDateTime.now();

        powerConsumptionSensor.setValue(initialTime, 1000);
        powerConsumptionSensor.setValue(secondTime, 1500);
        powerConsumptionSensor.setValue(finalTime, 2000);

        double expectedAverage = 1500;

        // Act
        double averageValue = powerConsumptionSensor.getAverageValue(initialTime, finalTime);

        // Assert
        Assertions.assertEquals(expectedAverage, averageValue, 0.01);
    }

    /**
     * See if the getAverageValue method works with readings out of range of the initial and final times.
     */

    @Test
    void getAverageValueWithReadingsOutOfRange() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime OtherTime1 = LocalDateTime.now().minusHours(3);
        LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
        LocalDateTime OtherTime2 = LocalDateTime.now().minusHours(1);
        LocalDateTime finalTime = LocalDateTime.now();
        LocalDateTime OtherTime3 = LocalDateTime.now().plusHours(1);

        powerConsumptionSensor.setValue(OtherTime1, 500);
        powerConsumptionSensor.setValue(initialTime, 1000);
        powerConsumptionSensor.setValue(OtherTime2, 1200);
        powerConsumptionSensor.setValue(finalTime, 2000);
        powerConsumptionSensor.setValue(OtherTime3, 400);

        double expectedAverage = 1400;

        // Act
        double averageValue = powerConsumptionSensor.getAverageValue(initialTime, finalTime);

        // Assert
        Assertions.assertEquals(expectedAverage, averageValue, 0.01);
    }

    /**
     * See if the getAverageValue method works with non-sequential readings.
     */
    @Test
    void getAverageValueWithNonSequencialReadings() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now();
        LocalDateTime finalTime = LocalDateTime.now().plusHours(2);
        LocalDateTime otherTime1 = LocalDateTime.now().minusHours(1);
        LocalDateTime otherTime2 = LocalDateTime.now().plusHours(3);
        LocalDateTime otherTime3 = LocalDateTime.now().plusHours(1);

        powerConsumptionSensor.setValue(otherTime2, 500);
        powerConsumptionSensor.setValue(initialTime, 1000);
        powerConsumptionSensor.setValue(otherTime1, 1200);
        powerConsumptionSensor.setValue(finalTime, 2000);
        powerConsumptionSensor.setValue(otherTime3, 400);

        double expectedAverage = 1133.33;

        // Act
        double averageValue = powerConsumptionSensor.getAverageValue(initialTime, finalTime);

        // Assert
        Assertions.assertEquals(expectedAverage, averageValue, 0.01);
    }

    /**
     * Tests if Exception is thrown for average with only one reading.
     */
    @Test
    void getAverageValueWithOnlyOneReading() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        String expectedMessage = "Initial time cannot be equal to final time";

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime BeforeInitialTime = LocalDateTime.now().minusHours(3);

        powerConsumptionSensor.setValue(BeforeInitialTime, 500);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> powerConsumptionSensor.getAverageValue(BeforeInitialTime, BeforeInitialTime));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        ;
    }

    /**
     * Tests if Exception is thrown for initial time after final time.
     */
    @Test
    void getAverageValueWithInitialTimeAfterFinalTime() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        String expectedMessage = "Initial time must be before final time";

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now().plusHours(3);
        LocalDateTime finalTime = LocalDateTime.now();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> powerConsumptionSensor.getAverageValue(initialTime, finalTime));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        ;
    }

    /**
     * Tests if Exception is thrown for initial time equals to final time.
     */
    @Test
    void getAverageValueWithInitialEqualsToFinalTime() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        String expectedMessage = "There is already a reading for this time";

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        PowerConsumptionSensor powerConsumptionSensor = new PowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now();

        powerConsumptionSensor.setValue(initialTime, 1000);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> powerConsumptionSensor.setValue(initialTime, 1000));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }


}







