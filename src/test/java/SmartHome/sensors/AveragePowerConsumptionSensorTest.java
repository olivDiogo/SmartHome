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
    void shouldReturnSensorType() throws InstantiationException {
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
//    @Test
//    void shouldReturnNewPowerConsumptionSensorWithValidValue() throws InstantiationException {
//        // Arrange
//        String description = "Power Consumption";
//        Double value = 1500.0;
//
//        int expectedSize = 1;
//
//        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
//        SensorType sensorTypeDouble = mock(SensorType.class);
//        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
//
//        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
//        {
//            when(mock.getValue()).thenReturn(value);
//        })) {
//            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);
//            averagePowerConsumptionSensor.getValue();
//
//            // Assert
//            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
//            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
//            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue());
//
//        }
//    }

    @Test
    void shouldReturnNewPowerConsumptionSensorWithValidValueWithAGivenPeriod() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        Double value = 1500.0;

        int expectedSize = 1;

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);
            LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
            LocalDateTime finalTime = LocalDateTime.now();

            averagePowerConsumptionSensor.addReading(initialTime, 1000);
            averagePowerConsumptionSensor.addReading(finalTime, 2000);

            averagePowerConsumptionSensor.getValue(initialTime, finalTime);

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue());

        }
    }



    /**
     * tests if Exception is thrown for non-existing Sensor type.
     */
    @Test
    void shouldThrowExceptionForNonExistentSensorTypeOfPowerConsumptionSensor() {
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
    void shouldThrowExceptionForNullSensorTypeOfPowerConsumptionSensor() {
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
    void shouldReturnAveragePowerConsumptionForAGivenPeriod() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        int expectedSize = 1;
        double value = 1250.0;

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

            LocalDateTime initialTime = LocalDateTime.of(2024, 2, 29, 10, 10, 5);
            LocalDateTime finalTime = LocalDateTime.of(2024, 2, 29, 10, 10, 10);

            averagePowerConsumptionSensor.addReading(initialTime, 1000);
            averagePowerConsumptionSensor.addReading(finalTime, 1500);


            // Act
            averagePowerConsumptionSensor.getValue(initialTime, finalTime);

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue(), 0.01);

        }
    }

    /**
     * See if the getAverageValue method works with another time.
     */

    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodOnADiferenteFormat() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        int expectedSize = 1;
        double value = 1500.0;

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

            LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
            LocalDateTime finalTime = LocalDateTime.now();

            averagePowerConsumptionSensor.addReading(initialTime, 1000);
            averagePowerConsumptionSensor.addReading(finalTime, 2000);

            // Act
            averagePowerConsumptionSensor.getValue(initialTime, finalTime);

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue(), 0.01);
        }
    }


    /**
     * See if the getAverageValue method works with more values than the initial and final time.
     */
    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodWithThreeReadings() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        int expectedSize = 1;
        double value = 1500.0;

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
        })) {

            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

            LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
            LocalDateTime secondTime = LocalDateTime.now().minusHours(1);
            LocalDateTime finalTime = LocalDateTime.now();

            averagePowerConsumptionSensor.addReading(initialTime, 1000);
            averagePowerConsumptionSensor.addReading(secondTime, 1500);
            averagePowerConsumptionSensor.addReading(finalTime, 2000);


            // Act
            averagePowerConsumptionSensor.getValue(initialTime, finalTime);

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue(), 0.01);
        }
    }

    /**
     * See if the getAverageValue method works with readings out of range of the initial and final times.
     */

    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodWithExtraPeriodReadings() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        int expectedSize = 1;
        double value = 1400.0;

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
        })) {

            LocalDateTime OtherTime1 = LocalDateTime.now().minusHours(3);
            LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
            LocalDateTime OtherTime2 = LocalDateTime.now().minusHours(1);
            LocalDateTime finalTime = LocalDateTime.now();
            LocalDateTime OtherTime3 = LocalDateTime.now().plusHours(1);

            averagePowerConsumptionSensor.addReading(OtherTime1, 500);
            averagePowerConsumptionSensor.addReading(initialTime, 1000);
            averagePowerConsumptionSensor.addReading(OtherTime2, 1200);
            averagePowerConsumptionSensor.addReading(finalTime, 2000);
            averagePowerConsumptionSensor.addReading(OtherTime3, 400);

            // Act
            averagePowerConsumptionSensor.getValue(initialTime, finalTime);

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue(), 0.01);
        }
    }

    /**
     * See if the getAverageValue method works with non-sequential readings.
     */
    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodWithNonSequentialReadings() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        int expectedSize = 1;
        double value = 1133.33;

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);
        try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
        {
            when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
        })) {

            LocalDateTime initialTime = LocalDateTime.now();
            LocalDateTime finalTime = LocalDateTime.now().plusHours(2);
            LocalDateTime otherTime1 = LocalDateTime.now().minusHours(1);
            LocalDateTime otherTime2 = LocalDateTime.now().plusHours(3);
            LocalDateTime otherTime3 = LocalDateTime.now().plusHours(1);

            averagePowerConsumptionSensor.addReading(otherTime2, 500);
            averagePowerConsumptionSensor.addReading(initialTime, 1000);
            averagePowerConsumptionSensor.addReading(otherTime1, 1200);
            averagePowerConsumptionSensor.addReading(finalTime, 2000);
            averagePowerConsumptionSensor.addReading(otherTime3, 400);


            // Act
            averagePowerConsumptionSensor.getValue(initialTime, finalTime);

            // Assert
            List<AveragePowerConsumptionSensorValue> averagePowerConsumptionSensorValues = powerConsumptionSensorValueDouble.constructed();
            Assertions.assertEquals(expectedSize, averagePowerConsumptionSensorValues.size());
            Assertions.assertEquals(value, averagePowerConsumptionSensorValues.get(0).getValue(), 0.01);
        }
    }

    /**
     * Tests if Exception is thrown for initial time after final time.
     */
    @Test
    void shouldThrowExceptionWhenInitialTimeAfterFinalTime() throws InstantiationException {
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
    void shouldReturnAverageValueWhenInitialEqualsToFinalTime() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        String expectedMessage = "There is already a reading for this time";

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);

        LocalDateTime initialTime = LocalDateTime.now();

        averagePowerConsumptionSensor.addReading(initialTime, 1000);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> averagePowerConsumptionSensor.addReading(initialTime, 1000));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the getValue method works but value is not a dummy, for this instance.
     */

    @Test
    void shouldReturnAverageValueForThisInstant() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);
        double expectedAverage = 0;

        // Act
        Value averageValue = averagePowerConsumptionSensor.getValue();
        //Assert
        Assertions.assertEquals(expectedAverage, Double.parseDouble(averageValue.toString()), 0.01);
    }

    /**
     * See if the addReading method works.
     */

    @Test
    void shouldReturnReading() throws InstantiationException {
        // Arrange
        String description = "Power Consumption";
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(catalogueDouble);
        LocalDateTime initialTime = LocalDateTime.now();
        //Act
        double reading = averagePowerConsumptionSensor.addReading(initialTime, 500);
        //Assert
        Assertions.assertEquals(500, reading, 0.01);
    }
}







