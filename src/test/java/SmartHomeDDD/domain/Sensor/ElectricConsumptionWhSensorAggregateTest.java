package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.domain.Sensor.ElectricConsumptionWhSensor.ElectricConsumptionWhSensor;
import SmartHomeDDD.domain.Sensor.ElectricConsumptionWhSensor.ElectricConsumptionWhValue;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ElectricConsumptionWhSensorAggregateTest {

    /**
     * Test getID method
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorID_WhenGetIDisCalled() {
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
     * Test getName method
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorModelPath_whenGetModelPathIsCalled() {
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
     * Test getSensorTypeID method
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorSensorTypeID_WhenGetSensorTypeIDisCalled() {
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
     * Test getName method
     */
    @Test
    void shouldReturnElectricConsumptionWhSensorSensorName_WhenGetNameIsCalled() {
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
     * Test getDeviceID method
     */
    @Test
    void shouldReturnDeviceID_WhenGetDeviceID() {
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
     * Test getValue method
     */
    @Test
    void shouldReturnElectricConsumptionWhForGivenTimePeriod_WhenGetValue(){
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
        ElectricConsumptionWhValue value = electricConsumptionWhSensor.getValue();
        //Assert
        assertEquals(expectedValue, value);
    }
    /**
     * Should return value when date period is zero.
     */
    @Test
    void shouldReturnZero_WhenDatePeriodIsZero(){
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
        //Act
        ElectricConsumptionWhValue value = electricConsumptionWhSensor.getValue();
        //Assert
        assertEquals(expectedValue, value);
    }

    /**
     * Test method equals of class ElectricConsumptionWhSensor when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_whenInstanceIsComparedToItself() {
        // Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

        // Act
        boolean result = electricConsumptionWhSensor.equals(electricConsumptionWhSensor);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of method equals when the instances are not equal.
     */
    @Test
    void shouldReturnFalse_whenInstancesAreNotEqual() {
        // Arrange
        ModelPath modelPath1 = new ModelPath("modelPath1");
        ModelPath modelPath2 = new ModelPath("modelPath2");
        DeviceID deviceID1 = new DeviceID("deviceID1");
        DeviceID deviceID2 = new DeviceID("deviceID2");
        SensorTypeID sensorTypeID1 = new SensorTypeID("ElectricConsumptionWh");
        SensorTypeID sensorTypeID2 = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName1 = new SensorName("sensorName1");
        SensorName sensorName2 = new SensorName("sensorName2");
        LocalDateTime startDate1 = LocalDateTime.now().minusDays(1);
        LocalDateTime startDate2 = LocalDateTime.now().minusDays(2);
        LocalDateTime endDate1 = LocalDateTime.now();
        LocalDateTime endDate2 = LocalDateTime.now().minusDays(1);
        DatePeriod datePeriod1 = new DatePeriod(startDate1, endDate1);
        DatePeriod datePeriod2 = new DatePeriod(startDate2, endDate2);

        ElectricConsumptionWhSensor electricConsumptionWhSensor1 = new ElectricConsumptionWhSensor(deviceID1, modelPath1, sensorTypeID1, sensorName1, datePeriod1);
        ElectricConsumptionWhSensor electricConsumptionWhSensor2 = new ElectricConsumptionWhSensor(deviceID2, modelPath2, sensorTypeID2, sensorName2, datePeriod2);

        // Act
        boolean result = electricConsumptionWhSensor1.equals(electricConsumptionWhSensor2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test of method equals when the instance is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_whenComparedWithDifferentClass() {
        // Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);

        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

        // Act
        boolean result = electricConsumptionWhSensor.equals(sensorName);
        // Assert
        assertFalse(result);
    }

    /**
     * Test of method toString.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled() {
        // Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

        SensorID sensorID = electricConsumptionWhSensor.getID();
        ElectricConsumptionWhValue electricConsumptionWhValue = electricConsumptionWhSensor.getValue();
        String expected = deviceID + " " + modelPath + " " + sensorTypeID + " " + electricConsumptionWhValue + " " + sensorName + " " + sensorID + " " + datePeriod;
        // Act

        String result = electricConsumptionWhSensor.toString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of method hashcode.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        // Arrange
        ModelPath modelPath = new ModelPath("modelPath");
        DeviceID deviceID = new DeviceID("deviceID");
        SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
        SensorName sensorName = new SensorName("sensorName");
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
        SensorID sensorID = electricConsumptionWhSensor.getID();
        int expected = sensorID.getId().hashCode();
        // Act
        int result = electricConsumptionWhSensor.hashCode();
        // Assert
        assertEquals(expected, result);
    }

}