package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AveragePowerConsumptionSensorTest {

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
        new AveragePowerConsumptionSensor(catalogueDouble);

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
        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        // Act
        SensorType result = averagePowerConsumptionSensor.getSensorType();

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
        Double value = 1500.0;

        int expectedSize = 1;

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.clone()).thenReturn(mock);
            when(mock.getValue()).thenReturn(value);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);
            averagePowerConsumptionSensor.getValue();

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue());

        }
    }

    @Test
    void newPowerConsumptionSensorWithValidValue2() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        String value = "1500.0";

        int expectedSize = 1;

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.clone()).thenReturn(mock);
            when(mock.toString()).thenReturn(value);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

            averagePowerConsumptionSensor.getValue();
            Assertions.assertEquals(expectedSize, powerConsumptionSensorValueDouble.constructed().size());
            Assertions.assertEquals(value, powerConsumptionSensorValueDouble.constructed().get(0).toString());
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
                () -> new AveragePowerConsumptionSensor(catalogueDouble));

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
        Exception exception = Assertions.assertThrows(InstantiationException.class, () -> new AveragePowerConsumptionSensor(catalogueDouble));

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.of(2024, 2, 29, 10, 10, 5);
        LocalDateTime finalTime = LocalDateTime.of(2024, 2, 29, 10, 10, 10);

        averagePowerConsumptionSensor.setValue(initialTime, 1000);
        averagePowerConsumptionSensor.setValue(finalTime, 1500);

        double expectedAverage = 1250;

        // Act
        double averageValue = averagePowerConsumptionSensor.getAverageValue(initialTime, finalTime);

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
        LocalDateTime finalTime = LocalDateTime.now();

        averagePowerConsumptionSensor.setValue(initialTime, 1000);
        averagePowerConsumptionSensor.setValue(finalTime, 2000);

        double expectedAverage = 1500;

        // Act
        double averageValue = averagePowerConsumptionSensor.getAverageValue(initialTime, finalTime);

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
        LocalDateTime secondTime = LocalDateTime.now().minusHours(1);
        LocalDateTime finalTime = LocalDateTime.now();

        averagePowerConsumptionSensor.setValue(initialTime, 1000);
        averagePowerConsumptionSensor.setValue(secondTime, 1500);
        averagePowerConsumptionSensor.setValue(finalTime, 2000);

        double expectedAverage = 1500;

        // Act
        double averageValue = averagePowerConsumptionSensor.getAverageValue(initialTime, finalTime);

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime OtherTime1 = LocalDateTime.now().minusHours(3);
        LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
        LocalDateTime OtherTime2 = LocalDateTime.now().minusHours(1);
        LocalDateTime finalTime = LocalDateTime.now();
        LocalDateTime OtherTime3 = LocalDateTime.now().plusHours(1);

        averagePowerConsumptionSensor.setValue(OtherTime1, 500);
        averagePowerConsumptionSensor.setValue(initialTime, 1000);
        averagePowerConsumptionSensor.setValue(OtherTime2, 1200);
        averagePowerConsumptionSensor.setValue(finalTime, 2000);
        averagePowerConsumptionSensor.setValue(OtherTime3, 400);

        double expectedAverage = 1400;

        // Act
        double averageValue = averagePowerConsumptionSensor.getAverageValue(initialTime, finalTime);

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now();
        LocalDateTime finalTime = LocalDateTime.now().plusHours(2);
        LocalDateTime otherTime1 = LocalDateTime.now().minusHours(1);
        LocalDateTime otherTime2 = LocalDateTime.now().plusHours(3);
        LocalDateTime otherTime3 = LocalDateTime.now().plusHours(1);

        averagePowerConsumptionSensor.setValue(otherTime2, 500);
        averagePowerConsumptionSensor.setValue(initialTime, 1000);
        averagePowerConsumptionSensor.setValue(otherTime1, 1200);
        averagePowerConsumptionSensor.setValue(finalTime, 2000);
        averagePowerConsumptionSensor.setValue(otherTime3, 400);

        double expectedAverage = 1133.33;

        // Act
        double averageValue = averagePowerConsumptionSensor.getAverageValue(initialTime, finalTime);

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime BeforeInitialTime = LocalDateTime.now().minusHours(3);

        averagePowerConsumptionSensor.setValue(BeforeInitialTime, 500);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> averagePowerConsumptionSensor.getAverageValue(BeforeInitialTime, BeforeInitialTime));

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now().plusHours(3);
        LocalDateTime finalTime = LocalDateTime.now();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> averagePowerConsumptionSensor.getAverageValue(initialTime, finalTime));

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

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now();

        averagePowerConsumptionSensor.setValue(initialTime, 1000);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> averagePowerConsumptionSensor.setValue(initialTime, 1000));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }


}







