package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class AveragePowerConsumptionSensorTest {

    /**
     * See if the constructor works.
     */
    @Test
    void shouldInstantiateAveragePowerConsumptionSensor() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            // Act
            new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
        }
    }

    /**
     * tests if Exception is thrown for null Sensor type.
     */

    @Test
    void shouldThrowExceptionForNonExistentSensorTypeOfPowerConsumptionSensor() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = null;

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            // Act + Assert
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble));
        }
    }

    /**
     * tests if Exception is thrown for null DeviceID.
     */

    @Test
    void shouldThrowExceptionForNullDeviceIDOfPowerConsumptionSensor() {
        // Arrange
        DeviceID deviceIDDouble = null;
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            // Act + Assert
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble));
        }
    }

    /**
     * tests if Exception is thrown for null ModelPath.
     */
    @Test
    void shouldThrowExceptionForNullModelPathOfPowerConsumptionSensor() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = null;
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            // Act + Assert
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble));
        }
    }

    /**
     * tests if Exception is thrown for null SensorName.
     */
    @Test
    void shouldThrowExceptionForNullSensorNameOfPowerConsumptionSensor() {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = null;
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            // Act + Assert
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble));
        }
    }

    /**
     * See if the getAverageValue method works.
     */

    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriod() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        int expectedSize = 1;
        double value = 1250.0;

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
            {
                when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
            })) {
                AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

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
    }


    /**
     * See if the getAverageValue method works with another time.
     */

    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodOnADiferenteFormat() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        int expectedSize = 1;
        double value = 1500.0;

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
            {
                when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
            })) {
                AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

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
    }


    /**
     * See if the getAverageValue method works with more values than the initial and final time.
     */
    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodWithThreeReadings() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        int expectedSize = 1;
        double value = 1500.0;

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
            {
                when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
            })) {

                AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

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
    }

    /**
     * See if the getAverageValue method works with readings out of range of the initial and final times.
     */

    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodWithExtraPeriodReadings() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        int expectedSize = 1;
        double value = 1400.0;


        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {
            try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
            {
                when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
            })) {
                AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

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
    }

    /**
     * See if the getAverageValue method works with non-sequential readings.
     */
    @Test
    void shouldReturnAveragePowerConsumptionForAGivenPeriodWithNonSequentialReadings() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        int expectedSize = 1;
        double value = 1133.33;

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {

            try (MockedConstruction<AveragePowerConsumptionSensorValue> powerConsumptionSensorValueDouble = mockConstruction(AveragePowerConsumptionSensorValue.class, (mock, context) ->
            {
                when(mock.getValue()).thenReturn(Double.valueOf(context.arguments().get(0).toString()));
            })) {
                AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

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
    }

    /**
     * Tests if Exception is thrown for initial time after final time.
     */
    @Test
    void shouldThrowExceptionWhenInitialTimeAfterFinalTime() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        String expectedMessage = "Initial time must be before final time";

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {

            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

            LocalDateTime initialTime = LocalDateTime.now().plusHours(3);
            LocalDateTime finalTime = LocalDateTime.now();

            Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> averagePowerConsumptionSensor.getValue(initialTime, finalTime));

            // Assert
            String actualMessage = exception.getMessage();
            Assertions.assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * Tests if Exception is thrown for initial time equals to final time.
     */
    @Test
    void shouldReturnAverageValueWhenInitialEqualsToFinalTime() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        String expectedMessage = "There is already a reading for this time";

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {

            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);

            LocalDateTime initialTime = LocalDateTime.now();

            averagePowerConsumptionSensor.addReading(initialTime, 1000);

            Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> averagePowerConsumptionSensor.addReading(initialTime, 1000));

            // Assert
            String actualMessage = exception.getMessage();
            Assertions.assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    /**
     * See if the getValue method works but value is not a dummy, for this instance.
     */

    @Test
    void shouldReturnAverageValueForThisInstant() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {

            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            double expectedAverage = 0;

            // Act
            ValueObject averageValue = averagePowerConsumptionSensor.getValue();
            //Assert
            Assertions.assertEquals(expectedAverage, Double.parseDouble(averageValue.toString()), 0.01);
        }
    }

    /**
     * See if the addReading method works.
     */

    @Test
    void shouldReturnReading() throws InstantiationException {
        // Arrange
        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> sensorID = mockConstruction(SensorID.class)) {

            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            LocalDateTime initialTime = LocalDateTime.now();
            //Act
            double reading = averagePowerConsumptionSensor.addReading(initialTime, 500);
            //Assert
            Assertions.assertEquals(500, reading, 0.01);
        }
    }

    /**
     * Test to verify that the {@link AveragePowerConsumptionSensor} generates an ID when the sensor is instantiated.
     */
    @Test
    void shouldGenerateSensorID_WhenAveragePowerConsumptionSensorIsInstantiated() throws InstantiationException {
        // Arrange
        String sensorID = "sensorID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorID);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            SensorID result = averagePowerConsumptionSensor.getID();

            Assertions.assertEquals(sensorID, result.toString());
        }
    }

    /**
     * Test to verify that the {@link AveragePowerConsumptionSensor} returns the sensor name.
     */
    @Test
    void shouldGetSensorName_WhenAveragePowerConsumptionSensorIsInstantiated() throws InstantiationException {
        // Arrange
        String sensorName = "sensorName";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorNameDouble.toString()).thenReturn(sensorName);

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorName);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            SensorName result = averagePowerConsumptionSensor.getName();

            Assertions.assertEquals(sensorName, result.toString());
        }
    }

    /**
     * Test to verify that the {@link AveragePowerConsumptionSensor} returns the model path.
     */

    @Test
    void shouldGetModelPath_WhenAveragePowerConsumptionSensorIsInstantiated() throws InstantiationException {
        // Arrange
        String modelPath = "SmartHome.sensors.DewPointSensor";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(modelPathDouble.toString()).thenReturn(modelPath);

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(modelPath);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            ModelPath result = averagePowerConsumptionSensor.getModelPath();

            Assertions.assertEquals(modelPath, result.toString());
        }
    }

    /**
     * Test to verify that the {@link AveragePowerConsumptionSensor} returns the device ID.
     */

    @Test
    void shouldGetDeviceID_WhenAveragePowerConsumptionSensorIsInstantiated() throws InstantiationException {
        // Arrange
        String deviceID = "deviceID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(deviceIDDouble.toString()).thenReturn(deviceID);

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(deviceID);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            DeviceID result = averagePowerConsumptionSensor.getDeviceID();

            Assertions.assertEquals(deviceID, result.toString());
        }
    }

    /**
     * Test to verify that the {@link AveragePowerConsumptionSensor} returns the sensor type ID.
     */

    @Test
    void shouldGetSensorTypeID_WhenAveragePowerConsumptionSensorIsInstantiated() throws InstantiationException {
        // Arrange
        String sensorTypeID = "sensorTypeID";

        DeviceID deviceIDDouble = mock(DeviceID.class);
        ModelPath modelPathDouble = mock(ModelPath.class);
        SensorName sensorNameDouble = mock(SensorName.class);
        SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

        when(sensorTypeIDDouble.toString()).thenReturn(sensorTypeID);

        try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(deviceIDDouble, modelPathDouble, sensorTypeIDDouble, sensorNameDouble);
            SensorTypeID result = averagePowerConsumptionSensor.getSensorTypeID();

            Assertions.assertEquals(sensorTypeID, result.toString());
        }
    }

}