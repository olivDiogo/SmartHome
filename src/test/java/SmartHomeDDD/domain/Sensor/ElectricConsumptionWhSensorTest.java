package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ElectricConsumptionWhSensorTest {
    /**
     * Should throw IllegalArgumentException when modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenModelPathIsNull() {
        //Arrange
        ModelPath modelPath = null;
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        String expectedMessage = "ModelPath is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Should throw IllegalArgumentException when sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorNameIsNull() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = null;
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        String expectedMessage = "SensorName is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Should throw IllegalArgumentException when sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNull() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = null;
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        String expectedMessage = "SensorTypeID is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Should throw IllegalArgumentException when sensorTypeID is not ElectricConsumptionWh.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNotElectricConsumptionWh() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("NotElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        String expectedMessage = "SensorTypeID must be of type 'ElectricConsumptionWh'";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Should throw IllegalArgumentException when deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenDeviceIDIsNull() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = null;
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        String expectedMessage = "DeviceID is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Should throw IllegalArgumentException when datePeriod is null.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenDatePeriodIsNull() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        DatePeriod datePeriod = null;
        String expectedMessage = "DatePeriod is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorID() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

        //Act
       SensorID sensorID = electricConsumptionWhSensor.getID();
        //Assert
        assertTrue(electricConsumptionWhSensor.toString().contains(sensorID.toString()));
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorModelPath() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        //Act
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        //Assert
        assertEquals(modelPath, electricConsumptionWhSensor.getModelPath());
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorSensorTypeID() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        //Act
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        //Assert
        assertEquals(sensorTypeID, electricConsumptionWhSensor.getSensorTypeID());
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorSensorName() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        //Act
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        //Assert
        assertEquals(sensorName, electricConsumptionWhSensor.getName());
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnDeviceIDWhenGetDeviceID() {
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        //Act
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        //Assert
        assertEquals(deviceID, electricConsumptionWhSensor.getDeviceID());
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnElectricConsumptionWhForGivenTimePeriodWhenGetValue(){
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        int consumptionInWh = datePeriod.getDurationInMinutes() * 5;
        ElectricConsumptionWhValue expectedValue = new ElectricConsumptionWhValue(consumptionInWh);
        //Act
        ElectricConsumptionWhValue value = (ElectricConsumptionWhValue)electricConsumptionWhSensor.getValue();
        //Assert
        assertEquals(expectedValue, value);
    }
    /**
     * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
     */
    @Test
    void shouldReturnZeroWhenDatePeriodIsZero(){
        //Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        ElectricConsumptionWhValue expectedValue = new ElectricConsumptionWhValue(0);
        String expectedReading = "ElectricConsumptionWh{0}";
        //Act
        ElectricConsumptionWhValue value = (ElectricConsumptionWhValue)electricConsumptionWhSensor.getValue();
        //Assert
        assertEquals(expectedValue, value);
        assertEquals(expectedReading, value.toString());
    }

}